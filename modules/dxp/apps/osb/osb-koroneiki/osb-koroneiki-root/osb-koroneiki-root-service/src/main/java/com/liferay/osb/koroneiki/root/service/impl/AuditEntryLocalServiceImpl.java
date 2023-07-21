/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.impl;

import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.osb.koroneiki.root.service.base.AuditEntryLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.root.model.AuditEntry",
	service = AopService.class
)
public class AuditEntryLocalServiceImpl extends AuditEntryLocalServiceBaseImpl {

	public AuditEntry addAuditEntry(
			long userId, long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, String action, String field, String oldLabel,
			String oldValue, String newLabel, String newValue,
			String description, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		String agentName = user.getFullName();

		if (serviceContext != null) {
			agentName = GetterUtil.getString(
				serviceContext.getAttribute("agentName"), agentName);
		}

		String agentUID = StringPool.BLANK;

		if (serviceContext != null) {
			agentUID = GetterUtil.getString(
				serviceContext.getAttribute("agentUID"));
		}

		long auditSetId = 0;

		if (serviceContext != null) {
			auditSetId = GetterUtil.getLong(
				serviceContext.getAttribute("auditSetId"));
		}

		if (auditSetId <= 0) {
			auditSetId = counterLocalService.increment(
				AuditEntry.class.getName());
		}

		long auditEntryId = counterLocalService.increment();

		AuditEntry auditEntry = auditEntryPersistence.create(auditEntryId);

		auditEntry.setCompanyId(user.getCompanyId());
		auditEntry.setUserId(userId);
		auditEntry.setAuditEntryKey(ModelKeyGenerator.generate(auditEntryId));
		auditEntry.setAgentName(agentName);
		auditEntry.setAgentUID(agentUID);
		auditEntry.setClassNameId(classNameId);
		auditEntry.setClassPK(classPK);
		auditEntry.setAuditSetId(auditSetId);
		auditEntry.setFieldClassNameId(fieldClassNameId);
		auditEntry.setFieldClassPK(fieldClassPK);
		auditEntry.setAction(action);
		auditEntry.setField(field);
		auditEntry.setOldLabel(oldLabel);
		auditEntry.setOldValue(oldValue);
		auditEntry.setNewLabel(newLabel);
		auditEntry.setNewValue(newValue);
		auditEntry.setDescription(description);

		return auditEntryPersistence.update(auditEntry, serviceContext);
	}

	public List<AuditEntry> getAuditEntries(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AuditEntry> obc) {

		return auditEntryPersistence.findByC_C(
			classNameId, classPK, start, end, obc);
	}

	public List<AuditEntry> getAuditEntries(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end) {

		return auditEntryPersistence.findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end);
	}

	public int getAuditEntriesCount(long classNameId, long classPK) {
		return auditEntryPersistence.countByC_C(classNameId, classPK);
	}

	public int getAuditEntriesCount(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		return auditEntryPersistence.countByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	public AuditEntry getAuditEntry(String auditEntryKey)
		throws PortalException {

		return auditEntryPersistence.findByAuditEntryKey(auditEntryKey);
	}

}