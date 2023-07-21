/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactTeamRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRoleService
 * @generated
 */
public class ContactTeamRoleServiceWrapper
	implements ContactTeamRoleService, ServiceWrapper<ContactTeamRoleService> {

	public ContactTeamRoleServiceWrapper(
		ContactTeamRoleService contactTeamRoleService) {

		_contactTeamRoleService = contactTeamRoleService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
			addContactTeamRole(long contactId, long teamId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleService.addContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactTeamRole
			deleteContactTeamRole(
				long contactId, long teamId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactTeamRoleService.deleteContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	@Override
	public void deleteContactTeamRoles(long contactId, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_contactTeamRoleService.deleteContactTeamRoles(contactId, teamId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactTeamRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public ContactTeamRoleService getWrappedService() {
		return _contactTeamRoleService;
	}

	@Override
	public void setWrappedService(
		ContactTeamRoleService contactTeamRoleService) {

		_contactTeamRoleService = contactTeamRoleService;
	}

	private ContactTeamRoleService _contactTeamRoleService;

}