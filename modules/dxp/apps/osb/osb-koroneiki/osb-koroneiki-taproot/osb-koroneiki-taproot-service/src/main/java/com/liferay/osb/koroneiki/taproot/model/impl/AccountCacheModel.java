/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.Account;
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
 * The cache model class for representing Account in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountCacheModel
	implements CacheModel<Account>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountCacheModel)) {
			return false;
		}

		AccountCacheModel accountCacheModel = (AccountCacheModel)object;

		if ((accountId == accountCacheModel.accountId) &&
			(mvccVersion == accountCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, accountId);

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
		StringBundler sb = new StringBundler(49);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountKey=");
		sb.append(accountKey);
		sb.append(", parentAccountId=");
		sb.append(parentAccountId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", code=");
		sb.append(code);
		sb.append(", description=");
		sb.append(description);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", contactEmailAddress=");
		sb.append(contactEmailAddress);
		sb.append(", profileEmailAddress=");
		sb.append(profileEmailAddress);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", faxNumber=");
		sb.append(faxNumber);
		sb.append(", website=");
		sb.append(website);
		sb.append(", tier=");
		sb.append(tier);
		sb.append(", region=");
		sb.append(region);
		sb.append(", dataRegion=");
		sb.append(dataRegion);
		sb.append(", language=");
		sb.append(language);
		sb.append(", internal=");
		sb.append(internal);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Account toEntityModel() {
		AccountImpl accountImpl = new AccountImpl();

		accountImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			accountImpl.setUuid("");
		}
		else {
			accountImpl.setUuid(uuid);
		}

		accountImpl.setAccountId(accountId);
		accountImpl.setCompanyId(companyId);
		accountImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			accountImpl.setCreateDate(null);
		}
		else {
			accountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountImpl.setModifiedDate(null);
		}
		else {
			accountImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (accountKey == null) {
			accountImpl.setAccountKey("");
		}
		else {
			accountImpl.setAccountKey(accountKey);
		}

		accountImpl.setParentAccountId(parentAccountId);

		if (name == null) {
			accountImpl.setName("");
		}
		else {
			accountImpl.setName(name);
		}

		if (code == null) {
			accountImpl.setCode("");
		}
		else {
			accountImpl.setCode(code);
		}

		if (description == null) {
			accountImpl.setDescription("");
		}
		else {
			accountImpl.setDescription(description);
		}

		accountImpl.setLogoId(logoId);

		if (contactEmailAddress == null) {
			accountImpl.setContactEmailAddress("");
		}
		else {
			accountImpl.setContactEmailAddress(contactEmailAddress);
		}

		if (profileEmailAddress == null) {
			accountImpl.setProfileEmailAddress("");
		}
		else {
			accountImpl.setProfileEmailAddress(profileEmailAddress);
		}

		if (phoneNumber == null) {
			accountImpl.setPhoneNumber("");
		}
		else {
			accountImpl.setPhoneNumber(phoneNumber);
		}

		if (faxNumber == null) {
			accountImpl.setFaxNumber("");
		}
		else {
			accountImpl.setFaxNumber(faxNumber);
		}

		if (website == null) {
			accountImpl.setWebsite("");
		}
		else {
			accountImpl.setWebsite(website);
		}

		if (tier == null) {
			accountImpl.setTier("");
		}
		else {
			accountImpl.setTier(tier);
		}

		if (region == null) {
			accountImpl.setRegion("");
		}
		else {
			accountImpl.setRegion(region);
		}

		if (dataRegion == null) {
			accountImpl.setDataRegion("");
		}
		else {
			accountImpl.setDataRegion(dataRegion);
		}

		if (language == null) {
			accountImpl.setLanguage("");
		}
		else {
			accountImpl.setLanguage(language);
		}

		accountImpl.setInternal(internal);

		if (status == null) {
			accountImpl.setStatus("");
		}
		else {
			accountImpl.setStatus(status);
		}

		accountImpl.resetOriginalValues();

		return accountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		accountId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		accountKey = objectInput.readUTF();

		parentAccountId = objectInput.readLong();
		name = objectInput.readUTF();
		code = objectInput.readUTF();
		description = objectInput.readUTF();

		logoId = objectInput.readLong();
		contactEmailAddress = objectInput.readUTF();
		profileEmailAddress = objectInput.readUTF();
		phoneNumber = objectInput.readUTF();
		faxNumber = objectInput.readUTF();
		website = objectInput.readUTF();
		tier = objectInput.readUTF();
		region = objectInput.readUTF();
		dataRegion = objectInput.readUTF();
		language = objectInput.readUTF();

		internal = objectInput.readBoolean();
		status = objectInput.readUTF();
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

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (accountKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountKey);
		}

		objectOutput.writeLong(parentAccountId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (code == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(code);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(logoId);

		if (contactEmailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactEmailAddress);
		}

		if (profileEmailAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(profileEmailAddress);
		}

		if (phoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		if (faxNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(faxNumber);
		}

		if (website == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(website);
		}

		if (tier == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tier);
		}

		if (region == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(region);
		}

		if (dataRegion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dataRegion);
		}

		if (language == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(language);
		}

		objectOutput.writeBoolean(internal);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long accountId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String accountKey;
	public long parentAccountId;
	public String name;
	public String code;
	public String description;
	public long logoId;
	public String contactEmailAddress;
	public String profileEmailAddress;
	public String phoneNumber;
	public String faxNumber;
	public String website;
	public String tier;
	public String region;
	public String dataRegion;
	public String language;
	public boolean internal;
	public String status;

}