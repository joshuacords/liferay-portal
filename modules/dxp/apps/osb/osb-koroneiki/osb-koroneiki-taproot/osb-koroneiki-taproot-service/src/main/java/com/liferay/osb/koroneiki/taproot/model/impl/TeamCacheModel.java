/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.Team;
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
 * The cache model class for representing Team in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeamCacheModel
	implements CacheModel<Team>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeamCacheModel)) {
			return false;
		}

		TeamCacheModel teamCacheModel = (TeamCacheModel)object;

		if ((teamId == teamCacheModel.teamId) &&
			(mvccVersion == teamCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, teamId);

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
		sb.append(", teamId=");
		sb.append(teamId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", teamKey=");
		sb.append(teamKey);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", system=");
		sb.append(system);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Team toEntityModel() {
		TeamImpl teamImpl = new TeamImpl();

		teamImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			teamImpl.setUuid("");
		}
		else {
			teamImpl.setUuid(uuid);
		}

		teamImpl.setTeamId(teamId);
		teamImpl.setCompanyId(companyId);
		teamImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			teamImpl.setCreateDate(null);
		}
		else {
			teamImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			teamImpl.setModifiedDate(null);
		}
		else {
			teamImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (teamKey == null) {
			teamImpl.setTeamKey("");
		}
		else {
			teamImpl.setTeamKey(teamKey);
		}

		teamImpl.setAccountId(accountId);

		if (name == null) {
			teamImpl.setName("");
		}
		else {
			teamImpl.setName(name);
		}

		teamImpl.setSystem(system);

		teamImpl.resetOriginalValues();

		return teamImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		teamId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		teamKey = objectInput.readUTF();

		accountId = objectInput.readLong();
		name = objectInput.readUTF();

		system = objectInput.readBoolean();
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

		objectOutput.writeLong(teamId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (teamKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(teamKey);
		}

		objectOutput.writeLong(accountId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(system);
	}

	public long mvccVersion;
	public String uuid;
	public long teamId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String teamKey;
	public long accountId;
	public String name;
	public boolean system;

}