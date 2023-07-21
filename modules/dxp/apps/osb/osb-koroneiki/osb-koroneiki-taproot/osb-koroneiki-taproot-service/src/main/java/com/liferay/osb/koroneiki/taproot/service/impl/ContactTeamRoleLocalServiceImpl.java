/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.exception.ContactRoleTypeException;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.base.ContactTeamRoleLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.ContactTeamRole",
	service = AopService.class
)
public class ContactTeamRoleLocalServiceImpl
	extends ContactTeamRoleLocalServiceBaseImpl {

	public ContactTeamRole addContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		validate(contactId, teamId, contactRoleId);

		ContactTeamRolePK contactTeamRolePK = new ContactTeamRolePK(
			contactId, teamId, contactRoleId);

		ContactTeamRole contactTeamRole =
			contactTeamRolePersistence.fetchByPrimaryKey(contactTeamRolePK);

		if (contactTeamRole == null) {
			contactTeamRole = contactTeamRolePersistence.create(
				contactTeamRolePK);

			contactTeamRole = contactTeamRolePersistence.update(
				contactTeamRole);
		}

		return contactTeamRole;
	}

	public void deleteAccountTeamContact(long accountId, long contactId)
		throws PortalException {

		List<Team> teams = teamPersistence.findByAccountId(accountId);

		for (Team team : teams) {
			deleteContactTeamRoles(contactId, team.getTeamId());
		}
	}

	public ContactTeamRole deleteContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		ContactTeamRolePK contactTeamRolePK = new ContactTeamRolePK(
			contactId, teamId, contactRoleId);

		ContactTeamRole contactTeamRole =
			contactTeamRolePersistence.fetchByPrimaryKey(contactTeamRolePK);

		if (contactTeamRole != null) {
			deleteContactTeamRole(contactTeamRole);
		}

		return contactTeamRole;
	}

	public void deleteContactTeamRoles(long contactId, long teamId)
		throws PortalException {

		contactTeamRolePersistence.removeByCI_TI(contactId, teamId);
	}

	public List<ContactTeamRole> getContactTeamRoles(long contactId) {
		return contactTeamRolePersistence.findByContactId(contactId);
	}

	public List<ContactTeamRole> getContactTeamRolesByTeamId(long teamId) {
		return contactTeamRolePersistence.findByTeamId(teamId);
	}

	protected void validate(long contactId, long teamId, long contactRoleId)
		throws PortalException {

		contactPersistence.findByPrimaryKey(contactId);

		teamPersistence.findByPrimaryKey(teamId);

		ContactRole contactRole = contactRolePersistence.findByPrimaryKey(
			contactRoleId);

		String type = contactRole.getType();

		if (!type.equals(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
					TEAM.toString())) {

			throw new ContactRoleTypeException();
		}
	}

}