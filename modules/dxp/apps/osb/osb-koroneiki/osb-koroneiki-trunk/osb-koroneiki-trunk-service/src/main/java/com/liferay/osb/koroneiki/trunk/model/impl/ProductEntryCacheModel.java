/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
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
 * The cache model class for representing ProductEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductEntryCacheModel
	implements CacheModel<ProductEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductEntryCacheModel)) {
			return false;
		}

		ProductEntryCacheModel productEntryCacheModel =
			(ProductEntryCacheModel)object;

		if ((productEntryId == productEntryCacheModel.productEntryId) &&
			(mvccVersion == productEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productEntryId);

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
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", productEntryKey=");
		sb.append(productEntryKey);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductEntry toEntityModel() {
		ProductEntryImpl productEntryImpl = new ProductEntryImpl();

		productEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			productEntryImpl.setUuid("");
		}
		else {
			productEntryImpl.setUuid(uuid);
		}

		productEntryImpl.setProductEntryId(productEntryId);
		productEntryImpl.setCompanyId(companyId);
		productEntryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			productEntryImpl.setCreateDate(null);
		}
		else {
			productEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			productEntryImpl.setModifiedDate(null);
		}
		else {
			productEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (productEntryKey == null) {
			productEntryImpl.setProductEntryKey("");
		}
		else {
			productEntryImpl.setProductEntryKey(productEntryKey);
		}

		if (name == null) {
			productEntryImpl.setName("");
		}
		else {
			productEntryImpl.setName(name);
		}

		productEntryImpl.resetOriginalValues();

		return productEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		productEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		productEntryKey = objectInput.readUTF();
		name = objectInput.readUTF();
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

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (productEntryKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productEntryKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long productEntryId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String productEntryKey;
	public String name;

}