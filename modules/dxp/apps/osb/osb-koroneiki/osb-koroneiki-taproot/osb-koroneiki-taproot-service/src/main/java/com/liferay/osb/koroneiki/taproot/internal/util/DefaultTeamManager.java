/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.util;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = DefaultTeamManager.class)
public class DefaultTeamManager {

	public void sync(Account account) throws PortalException {
		Team team = _teamLocalService.fetchTeam(account.getAccountId(), true);

		if (team == null) {
			team = _teamLocalService.addTeam(
				account.getUserId(), account.getAccountId(), account.getName(),
				true);
		}
		else {
			team = _teamLocalService.updateTeam(
				team.getTeamId(), account.getName());
		}

		List<Contact> accountContacts = _contactLocalService.getAccountContacts(
			account.getAccountId(),
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				ACCOUNT_CUSTOMER.toString(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<Contact> teamContacts = _contactLocalService.getTeamContacts(
			team.getTeamId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		teamContacts = ListUtil.copy(teamContacts);

		ContactRole contactRole = _contactRoleLocalService.getMemberContactRole(
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				TEAM.toString());

		for (Contact contact : accountContacts) {
			if (teamContacts.remove(contact)) {
				continue;
			}

			_contactTeamRoleLocalService.addContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}

		for (Contact contact : teamContacts) {
			_contactTeamRoleLocalService.deleteContactTeamRoles(
				contact.getContactId(), team.getTeamId());
		}

		_teamLocalService.reindex(team.getTeamId());
	}

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}