/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.service.persistence.UserGroupGroupRolePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.UserGroupGroupRoleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserGroupGroupRoleSoap implements Serializable {

	public static UserGroupGroupRoleSoap toSoapModel(UserGroupGroupRole model) {
		UserGroupGroupRoleSoap soapModel = new UserGroupGroupRoleSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUserGroupId(model.getUserGroupId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setCompanyId(model.getCompanyId());

		return soapModel;
	}

	public static UserGroupGroupRoleSoap[] toSoapModels(
		UserGroupGroupRole[] models) {

		UserGroupGroupRoleSoap[] soapModels =
			new UserGroupGroupRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserGroupGroupRoleSoap[][] toSoapModels(
		UserGroupGroupRole[][] models) {

		UserGroupGroupRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserGroupGroupRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserGroupGroupRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserGroupGroupRoleSoap[] toSoapModels(
		List<UserGroupGroupRole> models) {

		List<UserGroupGroupRoleSoap> soapModels =
			new ArrayList<UserGroupGroupRoleSoap>(models.size());

		for (UserGroupGroupRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new UserGroupGroupRoleSoap[soapModels.size()]);
	}

	public UserGroupGroupRoleSoap() {
	}

	public UserGroupGroupRolePK getPrimaryKey() {
		return new UserGroupGroupRolePK(_userGroupId, _groupId, _roleId);
	}

	public void setPrimaryKey(UserGroupGroupRolePK pk) {
		setUserGroupId(pk.userGroupId);
		setGroupId(pk.groupId);
		setRoleId(pk.roleId);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getUserGroupId() {
		return _userGroupId;
	}

	public void setUserGroupId(long userGroupId) {
		_userGroupId = userGroupId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	private long _mvccVersion;
	private long _userGroupId;
	private long _groupId;
	private long _roleId;
	private long _companyId;

}