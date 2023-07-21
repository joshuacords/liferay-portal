/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service;

import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for AuditEntry. This utility wraps
 * <code>com.liferay.osb.koroneiki.root.service.impl.AuditEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryService
 * @generated
 */
public class AuditEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.root.service.impl.AuditEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AuditEntry addAuditEntry(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, String action, String field, String oldLabel,
			String oldValue, String newLabel, String newValue,
			String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addAuditEntry(
			classNameId, classPK, fieldClassNameId, fieldClassPK, action, field,
			oldLabel, oldValue, newLabel, newValue, description,
			serviceContext);
	}

	public static List<AuditEntry> getAuditEntries(
			long classNameId, long classPK, int start, int end,
			OrderByComparator<AuditEntry> obc)
		throws PortalException {

		return getService().getAuditEntries(
			classNameId, classPK, start, end, obc);
	}

	public static List<AuditEntry> getAuditEntries(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, int start, int end)
		throws PortalException {

		return getService().getAuditEntries(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end);
	}

	public static int getAuditEntriesCount(long classNameId, long classPK)
		throws PortalException {

		return getService().getAuditEntriesCount(classNameId, classPK);
	}

	public static int getAuditEntriesCount(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK)
		throws PortalException {

		return getService().getAuditEntriesCount(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	public static AuditEntry getAuditEntry(long auditEntryId)
		throws PortalException {

		return getService().getAuditEntry(auditEntryId);
	}

	public static AuditEntry getAuditEntry(String auditEntryKey)
		throws PortalException {

		return getService().getAuditEntry(auditEntryKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AuditEntryService getService() {
		return _service;
	}

	public static void setService(AuditEntryService service) {
		_service = service;
	}

	private static volatile AuditEntryService _service;

}