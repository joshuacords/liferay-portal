/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.sync.model.SyncDevice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SyncDevice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SyncDeviceCacheModel
	implements CacheModel<SyncDevice>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SyncDeviceCacheModel)) {
			return false;
		}

		SyncDeviceCacheModel syncDeviceCacheModel =
			(SyncDeviceCacheModel)object;

		if (syncDeviceId == syncDeviceCacheModel.syncDeviceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, syncDeviceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", syncDeviceId=");
		sb.append(syncDeviceId);
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
		sb.append(", type=");
		sb.append(type);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", featureSet=");
		sb.append(featureSet);
		sb.append(", hostname=");
		sb.append(hostname);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SyncDevice toEntityModel() {
		SyncDeviceImpl syncDeviceImpl = new SyncDeviceImpl();

		if (uuid == null) {
			syncDeviceImpl.setUuid("");
		}
		else {
			syncDeviceImpl.setUuid(uuid);
		}

		syncDeviceImpl.setSyncDeviceId(syncDeviceId);
		syncDeviceImpl.setCompanyId(companyId);
		syncDeviceImpl.setUserId(userId);

		if (userName == null) {
			syncDeviceImpl.setUserName("");
		}
		else {
			syncDeviceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			syncDeviceImpl.setCreateDate(null);
		}
		else {
			syncDeviceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			syncDeviceImpl.setModifiedDate(null);
		}
		else {
			syncDeviceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (type == null) {
			syncDeviceImpl.setType("");
		}
		else {
			syncDeviceImpl.setType(type);
		}

		syncDeviceImpl.setBuildNumber(buildNumber);
		syncDeviceImpl.setFeatureSet(featureSet);

		if (hostname == null) {
			syncDeviceImpl.setHostname("");
		}
		else {
			syncDeviceImpl.setHostname(hostname);
		}

		syncDeviceImpl.setStatus(status);

		syncDeviceImpl.resetOriginalValues();

		return syncDeviceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		syncDeviceId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		type = objectInput.readUTF();

		buildNumber = objectInput.readLong();

		featureSet = objectInput.readInt();
		hostname = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(syncDeviceId);

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

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(buildNumber);

		objectOutput.writeInt(featureSet);

		if (hostname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hostname);
		}

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long syncDeviceId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String type;
	public long buildNumber;
	public int featureSet;
	public String hostname;
	public int status;

}