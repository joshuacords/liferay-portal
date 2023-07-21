/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.model.impl;

import com.liferay.message.boards.model.MBBan;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MBBan in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MBBanCacheModel implements CacheModel<MBBan>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MBBanCacheModel)) {
			return false;
		}

		MBBanCacheModel mbBanCacheModel = (MBBanCacheModel)object;

		if (banId == mbBanCacheModel.banId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, banId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", banId=");
		sb.append(banId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", banUserId=");
		sb.append(banUserId);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MBBan toEntityModel() {
		MBBanImpl mbBanImpl = new MBBanImpl();

		if (uuid == null) {
			mbBanImpl.setUuid("");
		}
		else {
			mbBanImpl.setUuid(uuid);
		}

		mbBanImpl.setBanId(banId);
		mbBanImpl.setGroupId(groupId);
		mbBanImpl.setCompanyId(companyId);
		mbBanImpl.setUserId(userId);

		if (userName == null) {
			mbBanImpl.setUserName("");
		}
		else {
			mbBanImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mbBanImpl.setCreateDate(null);
		}
		else {
			mbBanImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mbBanImpl.setModifiedDate(null);
		}
		else {
			mbBanImpl.setModifiedDate(new Date(modifiedDate));
		}

		mbBanImpl.setBanUserId(banUserId);

		if (lastPublishDate == Long.MIN_VALUE) {
			mbBanImpl.setLastPublishDate(null);
		}
		else {
			mbBanImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		mbBanImpl.resetOriginalValues();

		return mbBanImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		banId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		banUserId = objectInput.readLong();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(banId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(banUserId);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long banId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long banUserId;
	public long lastPublishDate;

}