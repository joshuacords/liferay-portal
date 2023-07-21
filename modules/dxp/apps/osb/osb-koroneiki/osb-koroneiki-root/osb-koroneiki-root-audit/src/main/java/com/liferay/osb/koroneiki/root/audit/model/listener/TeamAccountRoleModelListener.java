/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class TeamAccountRoleModelListener
	extends BaseAuditModelListener<TeamAccountRole> {

	@Override
	public void onAfterCreate(TeamAccountRole teamAccountRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Account account = teamAccountRole.getAccount();

			Team team = teamAccountRole.getTeam();
			TeamRole teamRole = teamAccountRole.getTeamRole();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				teamAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				AuditEntry.Action.ASSIGN.toString(), "Team Role",
				StringPool.BLANK, StringPool.BLANK, teamRole.getName(),
				String.valueOf(teamAccountRole.getTeamRoleId()), team.getName(),
				getServiceContext(teamAccountRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				classNameLocalService.getClassNameId(TeamRole.class),
				teamAccountRole.getTeamRoleId(),
				AuditEntry.Action.ASSIGN.toString(), "Account",
				StringPool.BLANK, StringPool.BLANK, account.getName(),
				String.valueOf(account.getAccountId()), teamRole.getName(),
				getServiceContext(teamAccountRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(TeamAccountRole teamAccountRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Account account = teamAccountRole.getAccount();

			Team team = teamAccountRole.getTeam();
			TeamRole teamRole = teamAccountRole.getTeamRole();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				teamAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				AuditEntry.Action.UNASSIGN.toString(), "Team Role",
				teamRole.getName(),
				String.valueOf(teamAccountRole.getTeamRoleId()),
				StringPool.BLANK, StringPool.BLANK, team.getName(),
				getServiceContext(teamAccountRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				teamAccountRole.getTeamId(),
				classNameLocalService.getClassNameId(TeamRole.class),
				teamAccountRole.getTeamRoleId(),
				AuditEntry.Action.UNASSIGN.toString(), "Account",
				account.getName(), String.valueOf(account.getAccountId()),
				StringPool.BLANK, StringPool.BLANK, teamRole.getName(),
				getServiceContext(teamAccountRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

}