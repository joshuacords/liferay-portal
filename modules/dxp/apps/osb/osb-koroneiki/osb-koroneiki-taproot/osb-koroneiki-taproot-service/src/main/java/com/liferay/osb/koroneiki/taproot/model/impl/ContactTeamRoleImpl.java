/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
public class ContactTeamRoleImpl extends ContactTeamRoleBaseImpl {

	public ContactTeamRoleImpl() {
	}

	public Contact getContact() throws PortalException {
		return ContactLocalServiceUtil.getContact(getContactId());
	}

	public ContactRole getContactRole() throws PortalException {
		return ContactRoleLocalServiceUtil.getContactRole(getContactRoleId());
	}

	public Team getTeam() throws PortalException {
		return TeamLocalServiceUtil.getTeam(getTeamId());
	}

}