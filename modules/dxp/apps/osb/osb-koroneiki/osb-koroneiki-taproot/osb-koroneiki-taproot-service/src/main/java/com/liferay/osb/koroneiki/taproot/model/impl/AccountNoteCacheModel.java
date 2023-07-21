/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
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
 * The cache model class for representing AccountNote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountNoteCacheModel
	implements CacheModel<AccountNote>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountNoteCacheModel)) {
			return false;
		}

		AccountNoteCacheModel accountNoteCacheModel =
			(AccountNoteCacheModel)object;

		if ((accountNoteId == accountNoteCacheModel.accountNoteId) &&
			(mvccVersion == accountNoteCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, accountNoteId);

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
		StringBundler sb = new StringBundler(37);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", accountNoteId=");
		sb.append(accountNoteId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", creatorUID=");
		sb.append(creatorUID);
		sb.append(", creatorName=");
		sb.append(creatorName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", modifierUID=");
		sb.append(modifierUID);
		sb.append(", modifierName=");
		sb.append(modifierName);
		sb.append(", accountNoteKey=");
		sb.append(accountNoteKey);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", content=");
		sb.append(content);
		sb.append(", format=");
		sb.append(format);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountNote toEntityModel() {
		AccountNoteImpl accountNoteImpl = new AccountNoteImpl();

		accountNoteImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			accountNoteImpl.setUuid("");
		}
		else {
			accountNoteImpl.setUuid(uuid);
		}

		accountNoteImpl.setAccountNoteId(accountNoteId);
		accountNoteImpl.setCompanyId(companyId);
		accountNoteImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			accountNoteImpl.setCreateDate(null);
		}
		else {
			accountNoteImpl.setCreateDate(new Date(createDate));
		}

		if (creatorUID == null) {
			accountNoteImpl.setCreatorUID("");
		}
		else {
			accountNoteImpl.setCreatorUID(creatorUID);
		}

		if (creatorName == null) {
			accountNoteImpl.setCreatorName("");
		}
		else {
			accountNoteImpl.setCreatorName(creatorName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountNoteImpl.setModifiedDate(null);
		}
		else {
			accountNoteImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (modifierUID == null) {
			accountNoteImpl.setModifierUID("");
		}
		else {
			accountNoteImpl.setModifierUID(modifierUID);
		}

		if (modifierName == null) {
			accountNoteImpl.setModifierName("");
		}
		else {
			accountNoteImpl.setModifierName(modifierName);
		}

		if (accountNoteKey == null) {
			accountNoteImpl.setAccountNoteKey("");
		}
		else {
			accountNoteImpl.setAccountNoteKey(accountNoteKey);
		}

		accountNoteImpl.setAccountId(accountId);

		if (type == null) {
			accountNoteImpl.setType("");
		}
		else {
			accountNoteImpl.setType(type);
		}

		accountNoteImpl.setPriority(priority);

		if (content == null) {
			accountNoteImpl.setContent("");
		}
		else {
			accountNoteImpl.setContent(content);
		}

		if (format == null) {
			accountNoteImpl.setFormat("");
		}
		else {
			accountNoteImpl.setFormat(format);
		}

		if (status == null) {
			accountNoteImpl.setStatus("");
		}
		else {
			accountNoteImpl.setStatus(status);
		}

		accountNoteImpl.resetOriginalValues();

		return accountNoteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		accountNoteId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		creatorUID = objectInput.readUTF();
		creatorName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		modifierUID = objectInput.readUTF();
		modifierName = objectInput.readUTF();
		accountNoteKey = objectInput.readUTF();

		accountId = objectInput.readLong();
		type = objectInput.readUTF();

		priority = objectInput.readInt();
		content = objectInput.readUTF();
		format = objectInput.readUTF();
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

		objectOutput.writeLong(accountNoteId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (creatorUID == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(creatorUID);
		}

		if (creatorName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(creatorName);
		}

		objectOutput.writeLong(modifiedDate);

		if (modifierUID == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifierUID);
		}

		if (modifierName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifierName);
		}

		if (accountNoteKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountNoteKey);
		}

		objectOutput.writeLong(accountId);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(priority);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		if (format == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(format);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long accountNoteId;
	public long companyId;
	public long userId;
	public long createDate;
	public String creatorUID;
	public String creatorName;
	public long modifiedDate;
	public String modifierUID;
	public String modifierName;
	public String accountNoteKey;
	public long accountId;
	public String type;
	public int priority;
	public String content;
	public String format;
	public String status;

}