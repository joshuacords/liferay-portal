/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceShipmentItem in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceShipmentItemCacheModel
	implements CacheModel<CommerceShipmentItem>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceShipmentItemCacheModel)) {
			return false;
		}

		CommerceShipmentItemCacheModel commerceShipmentItemCacheModel =
			(CommerceShipmentItemCacheModel)object;

		if (commerceShipmentItemId ==
				commerceShipmentItemCacheModel.commerceShipmentItemId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceShipmentItemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{commerceShipmentItemId=");
		sb.append(commerceShipmentItemId);
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
		sb.append(", commerceShipmentId=");
		sb.append(commerceShipmentId);
		sb.append(", commerceOrderItemId=");
		sb.append(commerceOrderItemId);
		sb.append(", commerceInventoryWarehouseId=");
		sb.append(commerceInventoryWarehouseId);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceShipmentItem toEntityModel() {
		CommerceShipmentItemImpl commerceShipmentItemImpl =
			new CommerceShipmentItemImpl();

		commerceShipmentItemImpl.setCommerceShipmentItemId(
			commerceShipmentItemId);
		commerceShipmentItemImpl.setGroupId(groupId);
		commerceShipmentItemImpl.setCompanyId(companyId);
		commerceShipmentItemImpl.setUserId(userId);

		if (userName == null) {
			commerceShipmentItemImpl.setUserName("");
		}
		else {
			commerceShipmentItemImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceShipmentItemImpl.setCreateDate(null);
		}
		else {
			commerceShipmentItemImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceShipmentItemImpl.setModifiedDate(null);
		}
		else {
			commerceShipmentItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		commerceShipmentItemImpl.setCommerceShipmentId(commerceShipmentId);
		commerceShipmentItemImpl.setCommerceOrderItemId(commerceOrderItemId);
		commerceShipmentItemImpl.setCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
		commerceShipmentItemImpl.setQuantity(quantity);

		commerceShipmentItemImpl.resetOriginalValues();

		return commerceShipmentItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceShipmentItemId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceShipmentId = objectInput.readLong();

		commerceOrderItemId = objectInput.readLong();

		commerceInventoryWarehouseId = objectInput.readLong();

		quantity = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceShipmentItemId);

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

		objectOutput.writeLong(commerceShipmentId);

		objectOutput.writeLong(commerceOrderItemId);

		objectOutput.writeLong(commerceInventoryWarehouseId);

		objectOutput.writeInt(quantity);
	}

	public long commerceShipmentItemId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceShipmentId;
	public long commerceOrderItemId;
	public long commerceInventoryWarehouseId;
	public int quantity;

}