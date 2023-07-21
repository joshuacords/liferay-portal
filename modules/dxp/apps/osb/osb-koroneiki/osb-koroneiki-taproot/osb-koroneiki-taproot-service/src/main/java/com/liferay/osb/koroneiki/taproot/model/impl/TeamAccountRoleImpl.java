/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class TeamAccountRoleImpl extends TeamAccountRoleBaseImpl {

	public TeamAccountRoleImpl() {
	}

	public Account getAccount() throws PortalException {
		return AccountLocalServiceUtil.getAccount(getAccountId());
	}

	public Team getTeam() throws PortalException {
		return TeamLocalServiceUtil.getTeam(getTeamId());
	}

	public TeamRole getTeamRole() throws PortalException {
		return TeamRoleLocalServiceUtil.getTeamRole(getTeamRoleId());
	}

}