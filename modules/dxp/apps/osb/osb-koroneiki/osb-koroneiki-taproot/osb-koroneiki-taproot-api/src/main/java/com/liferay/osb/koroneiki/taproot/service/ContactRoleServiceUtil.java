/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for ContactRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactRoleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleService
 * @generated
 */
public class ContactRoleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactRoleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ContactRole addContactRole(
			String name, String description, String type)
		throws PortalException {

		return getService().addContactRole(name, description, type);
	}

	public static ContactRole deleteContactRole(long contactRoleId)
		throws PortalException {

		return getService().deleteContactRole(contactRoleId);
	}

	public static ContactRole deleteContactRole(String contactRoleKey)
		throws PortalException {

		return getService().deleteContactRole(contactRoleKey);
	}

	public static List<ContactRole> getContactAccountContactRoles(
			long accountId, long contactId, String[] types, int start, int end)
		throws PortalException {

		return getService().getContactAccountContactRoles(
			accountId, contactId, types, start, end);
	}

	public static int getContactAccountContactRolesCount(
			long accountId, long contactId, String[] types)
		throws PortalException {

		return getService().getContactAccountContactRolesCount(
			accountId, contactId, types);
	}

	public static List<ContactRole> getContactContactRoles(
			long contactId, int start, int end)
		throws PortalException {

		return getService().getContactContactRoles(contactId, start, end);
	}

	public static ContactRole getContactRole(long contactRoleId)
		throws PortalException {

		return getService().getContactRole(contactRoleId);
	}

	public static ContactRole getContactRole(String contactRoleKey)
		throws PortalException {

		return getService().getContactRole(contactRoleKey);
	}

	public static ContactRole getContactRole(String name, String type)
		throws PortalException {

		return getService().getContactRole(name, type);
	}

	public static List<ContactRole> getContactTeamContactRoles(
			long teamId, long contactId, String[] types, int start, int end)
		throws PortalException {

		return getService().getContactTeamContactRoles(
			teamId, contactId, types, start, end);
	}

	public static int getContactTeamContactRolesCount(
			long teamId, long contactId, String[] types)
		throws PortalException {

		return getService().getContactTeamContactRolesCount(
			teamId, contactId, types);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ContactRole updateContactRole(
			long contactRoleId, String name, String description)
		throws PortalException {

		return getService().updateContactRole(contactRoleId, name, description);
	}

	public static ContactRoleService getService() {
		return _service;
	}

	public static void setService(ContactRoleService service) {
		_service = service;
	}

	private static volatile ContactRoleService _service;

}