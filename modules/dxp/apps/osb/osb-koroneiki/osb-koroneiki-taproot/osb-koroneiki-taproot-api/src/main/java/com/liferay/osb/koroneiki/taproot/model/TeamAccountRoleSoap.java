/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.taproot.service.http.TeamAccountRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeamAccountRoleSoap implements Serializable {

	public static TeamAccountRoleSoap toSoapModel(TeamAccountRole model) {
		TeamAccountRoleSoap soapModel = new TeamAccountRoleSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setTeamId(model.getTeamId());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setTeamRoleId(model.getTeamRoleId());

		return soapModel;
	}

	public static TeamAccountRoleSoap[] toSoapModels(TeamAccountRole[] models) {
		TeamAccountRoleSoap[] soapModels =
			new TeamAccountRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TeamAccountRoleSoap[][] toSoapModels(
		TeamAccountRole[][] models) {

		TeamAccountRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TeamAccountRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TeamAccountRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TeamAccountRoleSoap[] toSoapModels(
		List<TeamAccountRole> models) {

		List<TeamAccountRoleSoap> soapModels =
			new ArrayList<TeamAccountRoleSoap>(models.size());

		for (TeamAccountRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TeamAccountRoleSoap[soapModels.size()]);
	}

	public TeamAccountRoleSoap() {
	}

	public TeamAccountRolePK getPrimaryKey() {
		return new TeamAccountRolePK(_teamId, _accountId, _teamRoleId);
	}

	public void setPrimaryKey(TeamAccountRolePK pk) {
		setTeamId(pk.teamId);
		setAccountId(pk.accountId);
		setTeamRoleId(pk.teamRoleId);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getTeamId() {
		return _teamId;
	}

	public void setTeamId(long teamId) {
		_teamId = teamId;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getTeamRoleId() {
		return _teamRoleId;
	}

	public void setTeamRoleId(long teamRoleId) {
		_teamRoleId = teamRoleId;
	}

	private long _mvccVersion;
	private long _teamId;
	private long _accountId;
	private long _teamRoleId;

}