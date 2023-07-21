/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactAccountRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRoleService
 * @generated
 */
public class ContactAccountRoleServiceWrapper
	implements ContactAccountRoleService,
			   ServiceWrapper<ContactAccountRoleService> {

	public ContactAccountRoleServiceWrapper(
		ContactAccountRoleService contactAccountRoleService) {

		_contactAccountRoleService = contactAccountRoleService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			addContactAccountRole(
				long contactId, long accountId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleService.addContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			deleteContactAccountRole(
				long contactId, long accountId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleService.deleteContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	@Override
	public void deleteContactAccountRoles(
			long contactId, long accountId, String contactRoleType)
		throws com.liferay.portal.kernel.exception.PortalException {

		_contactAccountRoleService.deleteContactAccountRoles(
			contactId, accountId, contactRoleType);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactAccountRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public ContactAccountRoleService getWrappedService() {
		return _contactAccountRoleService;
	}

	@Override
	public void setWrappedService(
		ContactAccountRoleService contactAccountRoleService) {

		_contactAccountRoleService = contactAccountRoleService;
	}

	private ContactAccountRoleService _contactAccountRoleService;

}