/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
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
 * The cache model class for representing ProductConsumption in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductConsumptionCacheModel
	implements CacheModel<ProductConsumption>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductConsumptionCacheModel)) {
			return false;
		}

		ProductConsumptionCacheModel productConsumptionCacheModel =
			(ProductConsumptionCacheModel)object;

		if ((productConsumptionId ==
				productConsumptionCacheModel.productConsumptionId) &&
			(mvccVersion == productConsumptionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productConsumptionId);

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
		StringBundler sb = new StringBundler(27);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", productConsumptionId=");
		sb.append(productConsumptionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", productConsumptionKey=");
		sb.append(productConsumptionKey);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", productPurchaseId=");
		sb.append(productPurchaseId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductConsumption toEntityModel() {
		ProductConsumptionImpl productConsumptionImpl =
			new ProductConsumptionImpl();

		productConsumptionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			productConsumptionImpl.setUuid("");
		}
		else {
			productConsumptionImpl.setUuid(uuid);
		}

		productConsumptionImpl.setProductConsumptionId(productConsumptionId);
		productConsumptionImpl.setCompanyId(companyId);
		productConsumptionImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			productConsumptionImpl.setCreateDate(null);
		}
		else {
			productConsumptionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			productConsumptionImpl.setModifiedDate(null);
		}
		else {
			productConsumptionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (productConsumptionKey == null) {
			productConsumptionImpl.setProductConsumptionKey("");
		}
		else {
			productConsumptionImpl.setProductConsumptionKey(
				productConsumptionKey);
		}

		productConsumptionImpl.setAccountId(accountId);
		productConsumptionImpl.setProductEntryId(productEntryId);
		productConsumptionImpl.setProductPurchaseId(productPurchaseId);

		if (startDate == Long.MIN_VALUE) {
			productConsumptionImpl.setStartDate(null);
		}
		else {
			productConsumptionImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			productConsumptionImpl.setEndDate(null);
		}
		else {
			productConsumptionImpl.setEndDate(new Date(endDate));
		}

		productConsumptionImpl.resetOriginalValues();

		return productConsumptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		productConsumptionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		productConsumptionKey = objectInput.readUTF();

		accountId = objectInput.readLong();

		productEntryId = objectInput.readLong();

		productPurchaseId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
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

		objectOutput.writeLong(productConsumptionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (productConsumptionKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productConsumptionKey);
		}

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(productPurchaseId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
	}

	public long mvccVersion;
	public String uuid;
	public long productConsumptionId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String productConsumptionKey;
	public long accountId;
	public long productEntryId;
	public long productPurchaseId;
	public long startDate;
	public long endDate;

}