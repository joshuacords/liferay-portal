/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TeamRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeamRoleCacheModel
	implements CacheModel<TeamRole>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeamRoleCacheModel)) {
			return false;
		}

		TeamRoleCacheModel teamRoleCacheModel = (TeamRoleCacheModel)object;

		if ((teamRoleId == teamRoleCacheModel.teamRoleId) &&
			(mvccVersion == teamRoleCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, teamRoleId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", teamRoleId=");
		sb.append(teamRoleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", teamRoleKey=");
		sb.append(teamRoleKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TeamRole toEntityModel() {
		TeamRoleImpl teamRoleImpl = new TeamRoleImpl();

		teamRoleImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			teamRoleImpl.setUuid("");
		}
		else {
			teamRoleImpl.setUuid(uuid);
		}

		teamRoleImpl.setTeamRoleId(teamRoleId);
		teamRoleImpl.setCompanyId(companyId);
		teamRoleImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			teamRoleImpl.setCreateDate(null);
		}
		else {
			teamRoleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			teamRoleImpl.setModifiedDate(null);
		}
		else {
			teamRoleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (teamRoleKey == null) {
			teamRoleImpl.setTeamRoleKey("");
		}
		else {
			teamRoleImpl.setTeamRoleKey(teamRoleKey);
		}

		if (name == null) {
			teamRoleImpl.setName("");
		}
		else {
			teamRoleImpl.setName(name);
		}

		if (description == null) {
			teamRoleImpl.setDescription("");
		}
		else {
			teamRoleImpl.setDescription(description);
		}

		if (type == null) {
			teamRoleImpl.setType("");
		}
		else {
			teamRoleImpl.setType(type);
		}

		teamRoleImpl.resetOriginalValues();

		return teamRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		teamRoleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		teamRoleKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(teamRoleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (teamRoleKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(teamRoleKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long teamRoleId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String teamRoleKey;
	public String name;
	public String description;
	public String type;

}