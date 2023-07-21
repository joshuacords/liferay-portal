/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for ContactTeamRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactTeamRoleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleService
 * @generated
 */
public class ContactTeamRoleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactTeamRoleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ContactTeamRole addContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		return getService().addContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	public static ContactTeamRole deleteContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		return getService().deleteContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	public static void deleteContactTeamRoles(long contactId, long teamId)
		throws PortalException {

		getService().deleteContactTeamRoles(contactId, teamId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ContactTeamRoleService getService() {
		return _service;
	}

	public static void setService(ContactTeamRoleService service) {
		_service = service;
	}

	private static volatile ContactTeamRoleService _service;

}