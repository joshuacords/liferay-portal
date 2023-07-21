/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccountField in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountFieldCacheModel
	implements CacheModel<AccountField>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountFieldCacheModel)) {
			return false;
		}

		AccountFieldCacheModel accountFieldCacheModel =
			(AccountFieldCacheModel)object;

		if ((accountFieldId == accountFieldCacheModel.accountFieldId) &&
			(mvccVersion == accountFieldCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, accountFieldId);

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
		StringBundler sb = new StringBundler(15);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", accountFieldId=");
		sb.append(accountFieldId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountField toEntityModel() {
		AccountFieldImpl accountFieldImpl = new AccountFieldImpl();

		accountFieldImpl.setMvccVersion(mvccVersion);
		accountFieldImpl.setAccountFieldId(accountFieldId);
		accountFieldImpl.setCompanyId(companyId);
		accountFieldImpl.setUserId(userId);
		accountFieldImpl.setAccountId(accountId);

		if (name == null) {
			accountFieldImpl.setName("");
		}
		else {
			accountFieldImpl.setName(name);
		}

		if (value == null) {
			accountFieldImpl.setValue("");
		}
		else {
			accountFieldImpl.setValue(value);
		}

		accountFieldImpl.resetOriginalValues();

		return accountFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		accountFieldId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		accountId = objectInput.readLong();
		name = objectInput.readUTF();
		value = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(accountFieldId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(accountId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}
	}

	public long mvccVersion;
	public long accountFieldId;
	public long companyId;
	public long userId;
	public long accountId;
	public String name;
	public String value;

}