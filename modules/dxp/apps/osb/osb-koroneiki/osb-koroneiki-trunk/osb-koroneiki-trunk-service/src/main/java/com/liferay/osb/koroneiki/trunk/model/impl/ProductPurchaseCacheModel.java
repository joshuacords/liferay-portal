/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
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
 * The cache model class for representing ProductPurchase in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductPurchaseCacheModel
	implements CacheModel<ProductPurchase>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductPurchaseCacheModel)) {
			return false;
		}

		ProductPurchaseCacheModel productPurchaseCacheModel =
			(ProductPurchaseCacheModel)object;

		if ((productPurchaseId ==
				productPurchaseCacheModel.productPurchaseId) &&
			(mvccVersion == productPurchaseCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productPurchaseId);

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
		StringBundler sb = new StringBundler(31);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", productPurchaseId=");
		sb.append(productPurchaseId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", productPurchaseKey=");
		sb.append(productPurchaseKey);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", originalEndDate=");
		sb.append(originalEndDate);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductPurchase toEntityModel() {
		ProductPurchaseImpl productPurchaseImpl = new ProductPurchaseImpl();

		productPurchaseImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			productPurchaseImpl.setUuid("");
		}
		else {
			productPurchaseImpl.setUuid(uuid);
		}

		productPurchaseImpl.setProductPurchaseId(productPurchaseId);
		productPurchaseImpl.setCompanyId(companyId);
		productPurchaseImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			productPurchaseImpl.setCreateDate(null);
		}
		else {
			productPurchaseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			productPurchaseImpl.setModifiedDate(null);
		}
		else {
			productPurchaseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (productPurchaseKey == null) {
			productPurchaseImpl.setProductPurchaseKey("");
		}
		else {
			productPurchaseImpl.setProductPurchaseKey(productPurchaseKey);
		}

		productPurchaseImpl.setAccountId(accountId);
		productPurchaseImpl.setProductEntryId(productEntryId);

		if (startDate == Long.MIN_VALUE) {
			productPurchaseImpl.setStartDate(null);
		}
		else {
			productPurchaseImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			productPurchaseImpl.setEndDate(null);
		}
		else {
			productPurchaseImpl.setEndDate(new Date(endDate));
		}

		if (originalEndDate == Long.MIN_VALUE) {
			productPurchaseImpl.setOriginalEndDate(null);
		}
		else {
			productPurchaseImpl.setOriginalEndDate(new Date(originalEndDate));
		}

		productPurchaseImpl.setQuantity(quantity);
		productPurchaseImpl.setStatus(status);

		productPurchaseImpl.resetOriginalValues();

		return productPurchaseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		productPurchaseId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		productPurchaseKey = objectInput.readUTF();

		accountId = objectInput.readLong();

		productEntryId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		originalEndDate = objectInput.readLong();

		quantity = objectInput.readInt();

		status = objectInput.readInt();
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

		objectOutput.writeLong(productPurchaseId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (productPurchaseKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productPurchaseKey);
		}

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(productEntryId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeLong(originalEndDate);

		objectOutput.writeInt(quantity);

		objectOutput.writeInt(status);
	}

	public long mvccVersion;
	public String uuid;
	public long productPurchaseId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String productPurchaseKey;
	public long accountId;
	public long productEntryId;
	public long startDate;
	public long endDate;
	public long originalEndDate;
	public int quantity;
	public int status;

}