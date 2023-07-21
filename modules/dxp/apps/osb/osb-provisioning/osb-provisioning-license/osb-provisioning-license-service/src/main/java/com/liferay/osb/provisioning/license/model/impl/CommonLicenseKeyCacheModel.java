/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
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
 * The cache model class for representing CommonLicenseKey in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommonLicenseKeyCacheModel
	implements CacheModel<CommonLicenseKey>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommonLicenseKeyCacheModel)) {
			return false;
		}

		CommonLicenseKeyCacheModel commonLicenseKeyCacheModel =
			(CommonLicenseKeyCacheModel)object;

		if ((commonLicenseKeyId ==
				commonLicenseKeyCacheModel.commonLicenseKeyId) &&
			(mvccVersion == commonLicenseKeyCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, commonLicenseKeyId);

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
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", commonLicenseKeyId=");
		sb.append(commonLicenseKeyId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", productGroup=");
		sb.append(productGroup);
		sb.append(", productEnvironment=");
		sb.append(productEnvironment);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", fileSize=");
		sb.append(fileSize);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommonLicenseKey toEntityModel() {
		CommonLicenseKeyImpl commonLicenseKeyImpl = new CommonLicenseKeyImpl();

		commonLicenseKeyImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			commonLicenseKeyImpl.setUuid("");
		}
		else {
			commonLicenseKeyImpl.setUuid(uuid);
		}

		commonLicenseKeyImpl.setCommonLicenseKeyId(commonLicenseKeyId);
		commonLicenseKeyImpl.setCompanyId(companyId);
		commonLicenseKeyImpl.setUserId(userId);

		if (userName == null) {
			commonLicenseKeyImpl.setUserName("");
		}
		else {
			commonLicenseKeyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commonLicenseKeyImpl.setCreateDate(null);
		}
		else {
			commonLicenseKeyImpl.setCreateDate(new Date(createDate));
		}

		if (productGroup == null) {
			commonLicenseKeyImpl.setProductGroup("");
		}
		else {
			commonLicenseKeyImpl.setProductGroup(productGroup);
		}

		if (productEnvironment == null) {
			commonLicenseKeyImpl.setProductEnvironment("");
		}
		else {
			commonLicenseKeyImpl.setProductEnvironment(productEnvironment);
		}

		if (productVersion == null) {
			commonLicenseKeyImpl.setProductVersion("");
		}
		else {
			commonLicenseKeyImpl.setProductVersion(productVersion);
		}

		if (startDate == Long.MIN_VALUE) {
			commonLicenseKeyImpl.setStartDate(null);
		}
		else {
			commonLicenseKeyImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			commonLicenseKeyImpl.setEndDate(null);
		}
		else {
			commonLicenseKeyImpl.setEndDate(new Date(endDate));
		}

		if (fileName == null) {
			commonLicenseKeyImpl.setFileName("");
		}
		else {
			commonLicenseKeyImpl.setFileName(fileName);
		}

		commonLicenseKeyImpl.setFileSize(fileSize);

		commonLicenseKeyImpl.resetOriginalValues();

		return commonLicenseKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		commonLicenseKeyId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		productGroup = objectInput.readUTF();
		productEnvironment = objectInput.readUTF();
		productVersion = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		fileName = objectInput.readUTF();

		fileSize = objectInput.readLong();
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

		objectOutput.writeLong(commonLicenseKeyId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		if (productGroup == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productGroup);
		}

		if (productEnvironment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productEnvironment);
		}

		if (productVersion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productVersion);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(fileSize);
	}

	public long mvccVersion;
	public String uuid;
	public long commonLicenseKeyId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public String productGroup;
	public String productEnvironment;
	public String productVersion;
	public long startDate;
	public long endDate;
	public String fileName;
	public long fileSize;

}