/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeamAccountRolePK
	implements Comparable<TeamAccountRolePK>, Serializable {

	public long teamId;
	public long accountId;
	public long teamRoleId;

	public TeamAccountRolePK() {
	}

	public TeamAccountRolePK(long teamId, long accountId, long teamRoleId) {
		this.teamId = teamId;
		this.accountId = accountId;
		this.teamRoleId = teamRoleId;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getTeamRoleId() {
		return teamRoleId;
	}

	public void setTeamRoleId(long teamRoleId) {
		this.teamRoleId = teamRoleId;
	}

	@Override
	public int compareTo(TeamAccountRolePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (teamId < pk.teamId) {
			value = -1;
		}
		else if (teamId > pk.teamId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (accountId < pk.accountId) {
			value = -1;
		}
		else if (accountId > pk.accountId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (teamRoleId < pk.teamRoleId) {
			value = -1;
		}
		else if (teamRoleId > pk.teamRoleId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeamAccountRolePK)) {
			return false;
		}

		TeamAccountRolePK pk = (TeamAccountRolePK)object;

		if ((teamId == pk.teamId) && (accountId == pk.accountId) &&
			(teamRoleId == pk.teamRoleId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, teamId);
		hashCode = HashUtil.hash(hashCode, accountId);
		hashCode = HashUtil.hash(hashCode, teamRoleId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("teamId=");

		sb.append(teamId);
		sb.append(", accountId=");

		sb.append(accountId);
		sb.append(", teamRoleId=");

		sb.append(teamRoleId);

		sb.append("}");

		return sb.toString();
	}

}