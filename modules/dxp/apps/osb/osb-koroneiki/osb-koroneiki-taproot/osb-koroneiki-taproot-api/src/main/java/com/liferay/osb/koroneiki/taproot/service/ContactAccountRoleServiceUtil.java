/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for ContactAccountRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactAccountRoleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRoleService
 * @generated
 */
public class ContactAccountRoleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactAccountRoleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ContactAccountRole addContactAccountRole(
			long contactId, long accountId, long contactRoleId)
		throws PortalException {

		return getService().addContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	public static ContactAccountRole deleteContactAccountRole(
			long contactId, long accountId, long contactRoleId)
		throws PortalException {

		return getService().deleteContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	public static void deleteContactAccountRoles(
			long contactId, long accountId, String contactRoleType)
		throws PortalException {

		getService().deleteContactAccountRoles(
			contactId, accountId, contactRoleType);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ContactAccountRoleService getService() {
		return _service;
	}

	public static void setService(ContactAccountRoleService service) {
		_service = service;
	}

	private static volatile ContactAccountRoleService _service;

}