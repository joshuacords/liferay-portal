/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.impl;

import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.osb.koroneiki.root.permission.ModelPermissionRegistry;
import com.liferay.osb.koroneiki.root.service.base.AuditEntryServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=AuditEntry"
	},
	service = AopService.class
)
public class AuditEntryServiceImpl extends AuditEntryServiceBaseImpl {

	public AuditEntry addAuditEntry(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, String action, String field, String oldLabel,
			String oldValue, String newLabel, String newValue,
			String description, ServiceContext serviceContext)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.UPDATE);

		return auditEntryLocalService.addAuditEntry(
			getUserId(), classNameId, classPK, fieldClassNameId, fieldClassPK,
			action, field, oldLabel, oldValue, newLabel, newValue, description,
			serviceContext);
	}

	public List<AuditEntry> getAuditEntries(
			long classNameId, long classPK, int start, int end,
			OrderByComparator<AuditEntry> obc)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.VIEW);

		return auditEntryLocalService.getAuditEntries(
			classNameId, classPK, start, end, obc);
	}

	public List<AuditEntry> getAuditEntries(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, int start, int end)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), fieldClassNameId, fieldClassPK,
			ActionKeys.VIEW);

		return auditEntryLocalService.getAuditEntries(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end);
	}

	public int getAuditEntriesCount(long classNameId, long classPK)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.VIEW);

		return auditEntryLocalService.getAuditEntriesCount(
			classNameId, classPK);
	}

	public int getAuditEntriesCount(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK)
		throws PortalException {

		_modelPermissionRegistry.check(
			getPermissionChecker(), fieldClassNameId, fieldClassPK,
			ActionKeys.VIEW);

		return auditEntryLocalService.getAuditEntriesCount(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	public AuditEntry getAuditEntry(long auditEntryId) throws PortalException {
		AuditEntry auditEntry = auditEntryLocalService.getAuditEntry(
			auditEntryId);

		_modelPermissionRegistry.check(
			getPermissionChecker(), auditEntry.getClassNameId(),
			auditEntry.getClassPK(), ActionKeys.VIEW);

		return auditEntry;
	}

	public AuditEntry getAuditEntry(String auditEntryKey)
		throws PortalException {

		AuditEntry auditEntry = auditEntryLocalService.getAuditEntry(
			auditEntryKey);

		_modelPermissionRegistry.check(
			getPermissionChecker(), auditEntry.getClassNameId(),
			auditEntry.getClassPK(), ActionKeys.VIEW);

		return auditEntry;
	}

	@Reference
	private ModelPermissionRegistry _modelPermissionRegistry;

}