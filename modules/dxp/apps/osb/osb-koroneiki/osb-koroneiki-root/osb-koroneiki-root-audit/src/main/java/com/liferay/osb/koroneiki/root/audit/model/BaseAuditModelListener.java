/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.service.AuditEntryLocalService;
import com.liferay.osb.koroneiki.root.util.AuditEntryThreadLocal;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class BaseAuditModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	@Override
	public void onAfterCreate(T model) throws ModelListenerException {
		if (!isEnabled()) {
			return;
		}

		try {
			ServiceContext serviceContext = getServiceContext(model);

			Map<String, Object> attributes = model.getModelAttributes();

			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				String field = entry.getKey();

				if (isIgnoredField(field)) {
					continue;
				}

				auditEntryLocalService.addAuditEntry(
					getUserId(), getClassNameId(model), getClassPK(model),
					getFieldClassNameId(model), getFieldClassPK(model),
					AuditEntry.Action.ADD.toString(), field, StringPool.BLANK,
					StringPool.BLANK, StringPool.BLANK,
					String.valueOf(entry.getValue()), getDescription(model),
					serviceContext);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(T model) throws ModelListenerException {
		if (!isEnabled()) {
			return;
		}

		try {
			ServiceContext serviceContext = getServiceContext(model);

			Map<String, Object> attributes = model.getModelAttributes();

			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				String field = entry.getKey();

				if (isIgnoredField(field)) {
					continue;
				}

				auditEntryLocalService.addAuditEntry(
					getUserId(), getClassNameId(model), getClassPK(model),
					getFieldClassNameId(model), getFieldClassPK(model),
					AuditEntry.Action.DELETE.toString(), field,
					StringPool.BLANK, String.valueOf(entry.getValue()),
					StringPool.BLANK, StringPool.BLANK, getDescription(model),
					serviceContext);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeUpdate(T model) throws ModelListenerException {
		if (!isEnabled()) {
			return;
		}

		try {
			ServiceContext serviceContext = getServiceContext(model);

			long classPK = (Long)model.getPrimaryKeyObj();

			T oldModel = getModel(classPK);

			Map<String, Object> oldAttributes = oldModel.getModelAttributes();

			Map<String, Object> attributes = model.getModelAttributes();

			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				String field = entry.getKey();

				if (isIgnoredField(field)) {
					continue;
				}

				Object oldValue = oldAttributes.get(field);

				Object value = entry.getValue();

				if (isSkipFieldUpdate(field, oldValue, value)) {
					continue;
				}

				auditEntryLocalService.addAuditEntry(
					getUserId(), getClassNameId(model), getClassPK(model),
					getFieldClassNameId(model), getFieldClassPK(model),
					AuditEntry.Action.UPDATE.toString(), field,
					StringPool.BLANK, String.valueOf(oldValue),
					StringPool.BLANK, String.valueOf(value),
					getDescription(model), serviceContext);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	protected long getClassNameId(T model) {
		return classNameLocalService.getClassNameId(model.getModelClass());
	}

	protected long getClassPK(T model) throws PortalException {
		return (Long)model.getPrimaryKeyObj();
	}

	protected String getDescription(T model) throws PortalException {
		return StringPool.BLANK;
	}

	protected long getFieldClassNameId(T model) {
		return classNameLocalService.getClassNameId(model.getModelClass());
	}

	protected long getFieldClassPK(T model) {
		return (Long)model.getPrimaryKeyObj();
	}

	protected <T> T getModel(long classPK) throws PortalException {
		return null;
	}

	protected ServiceContext getServiceContext(long classNameId, long classPK) {
		String auditSetKey = classNameId + StringPool.POUND + classPK;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		long auditSetId = GetterUtil.getLong(
			serviceContext.getAttribute("auditSetId"));
		String curAuditSetKey = GetterUtil.getString(
			serviceContext.getAttribute("auditSetKey"));

		if ((auditSetId <= 0) || !auditSetKey.equals(curAuditSetKey)) {
			serviceContext.setAttribute(
				"auditSetId", _getAuditSetId(auditSetKey));
			serviceContext.setAttribute("auditSetKey", auditSetKey);
		}

		return serviceContext;
	}

	protected ServiceContext getServiceContext(T model) {
		long classPK = 0;

		Serializable primaryKeyObj = model.getPrimaryKeyObj();

		if (primaryKeyObj instanceof Long) {
			classPK = (long)primaryKeyObj;
		}
		else {
			classPK = primaryKeyObj.hashCode();
		}

		return getServiceContext(
			classNameLocalService.getClassNameId(model.getModelClass()),
			classPK);
	}

	protected long getUserId() throws PortalException {
		long userId = PrincipalThreadLocal.getUserId();

		if (userId <= 0) {
			long companyId = portalInstancesLocalService.getDefaultCompanyId();

			userId = userLocalService.getDefaultUserId(companyId);
		}

		return userId;
	}

	protected boolean isEnabled() {
		if (AuditEntryThreadLocal.isEnabled()) {
			return true;
		}

		return false;
	}

	protected boolean isIgnoredField(String field) {
		if (field.equals("entityCacheEnabled") ||
			field.equals("finderCacheEnabled") || field.equals("mvccVersion")) {

			return true;
		}

		return false;
	}

	protected boolean isSkipFieldUpdate(
		String field, Object oldValue, Object newValue) {

		if (Objects.equals(oldValue, newValue)) {
			return true;
		}

		return false;
	}

	@Reference
	protected AuditEntryLocalService auditEntryLocalService;

	@Reference
	protected ClassNameLocalService classNameLocalService;

	@Reference
	protected CounterLocalService counterLocalService;

	@Reference
	protected PortalInstancesLocalService portalInstancesLocalService;

	@Reference
	protected UserLocalService userLocalService;

	private long _getAuditSetId(String auditSetKey) {
		Map<String, Long> auditSetIdMap = _auditSetIdMap.get();

		Long auditSetId = auditSetIdMap.get(auditSetKey);

		if ((auditSetId == null) || (auditSetId <= 0)) {
			auditSetId = counterLocalService.increment(
				com.liferay.osb.koroneiki.root.model.AuditEntry.class.
					getName());

			auditSetIdMap.put(auditSetKey, auditSetId);
		}

		return auditSetId;
	}

	private static final ThreadLocal<Map<String, Long>> _auditSetIdMap =
		new CentralizedThreadLocal<>("._auditSetIdMap", HashMap::new);

}