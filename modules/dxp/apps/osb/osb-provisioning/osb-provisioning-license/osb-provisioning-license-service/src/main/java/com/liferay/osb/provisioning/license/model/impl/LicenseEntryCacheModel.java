/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.model.LicenseEntry;
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
 * The cache model class for representing LicenseEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseEntryCacheModel
	implements CacheModel<LicenseEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseEntryCacheModel)) {
			return false;
		}

		LicenseEntryCacheModel licenseEntryCacheModel =
			(LicenseEntryCacheModel)object;

		if ((licenseEntryId == licenseEntryCacheModel.licenseEntryId) &&
			(mvccVersion == licenseEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, licenseEntryId);

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
		sb.append(", licenseEntryId=");
		sb.append(licenseEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", productKey=");
		sb.append(productKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", versionMin=");
		sb.append(versionMin);
		sb.append(", versionMax=");
		sb.append(versionMax);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LicenseEntry toEntityModel() {
		LicenseEntryImpl licenseEntryImpl = new LicenseEntryImpl();

		licenseEntryImpl.setMvccVersion(mvccVersion);
		licenseEntryImpl.setLicenseEntryId(licenseEntryId);
		licenseEntryImpl.setUserId(userId);

		if (userName == null) {
			licenseEntryImpl.setUserName("");
		}
		else {
			licenseEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseEntryImpl.setCreateDate(null);
		}
		else {
			licenseEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseEntryImpl.setModifiedDate(null);
		}
		else {
			licenseEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (productKey == null) {
			licenseEntryImpl.setProductKey("");
		}
		else {
			licenseEntryImpl.setProductKey(productKey);
		}

		if (name == null) {
			licenseEntryImpl.setName("");
		}
		else {
			licenseEntryImpl.setName(name);
		}

		if (type == null) {
			licenseEntryImpl.setType("");
		}
		else {
			licenseEntryImpl.setType(type);
		}

		if (versionMin == null) {
			licenseEntryImpl.setVersionMin("");
		}
		else {
			licenseEntryImpl.setVersionMin(versionMin);
		}

		if (versionMax == null) {
			licenseEntryImpl.setVersionMax("");
		}
		else {
			licenseEntryImpl.setVersionMax(versionMax);
		}

		licenseEntryImpl.resetOriginalValues();

		return licenseEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		licenseEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		productKey = objectInput.readUTF();
		name = objectInput.readUTF();
		type = objectInput.readUTF();
		versionMin = objectInput.readUTF();
		versionMax = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(licenseEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (productKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (versionMin == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(versionMin);
		}

		if (versionMax == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(versionMax);
		}
	}

	public long mvccVersion;
	public long licenseEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String productKey;
	public String name;
	public String type;
	public String versionMin;
	public String versionMax;

}