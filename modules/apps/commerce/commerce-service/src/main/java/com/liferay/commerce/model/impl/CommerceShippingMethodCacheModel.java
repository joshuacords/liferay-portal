/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceShippingMethod in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceShippingMethodCacheModel
	implements CacheModel<CommerceShippingMethod>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceShippingMethodCacheModel)) {
			return false;
		}

		CommerceShippingMethodCacheModel commerceShippingMethodCacheModel =
			(CommerceShippingMethodCacheModel)object;

		if (commerceShippingMethodId ==
				commerceShippingMethodCacheModel.commerceShippingMethodId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceShippingMethodId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{commerceShippingMethodId=");
		sb.append(commerceShippingMethodId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", engineKey=");
		sb.append(engineKey);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceShippingMethod toEntityModel() {
		CommerceShippingMethodImpl commerceShippingMethodImpl =
			new CommerceShippingMethodImpl();

		commerceShippingMethodImpl.setCommerceShippingMethodId(
			commerceShippingMethodId);
		commerceShippingMethodImpl.setGroupId(groupId);
		commerceShippingMethodImpl.setCompanyId(companyId);
		commerceShippingMethodImpl.setUserId(userId);

		if (userName == null) {
			commerceShippingMethodImpl.setUserName("");
		}
		else {
			commerceShippingMethodImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceShippingMethodImpl.setCreateDate(null);
		}
		else {
			commerceShippingMethodImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceShippingMethodImpl.setModifiedDate(null);
		}
		else {
			commerceShippingMethodImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			commerceShippingMethodImpl.setName("");
		}
		else {
			commerceShippingMethodImpl.setName(name);
		}

		if (description == null) {
			commerceShippingMethodImpl.setDescription("");
		}
		else {
			commerceShippingMethodImpl.setDescription(description);
		}

		commerceShippingMethodImpl.setImageId(imageId);

		if (engineKey == null) {
			commerceShippingMethodImpl.setEngineKey("");
		}
		else {
			commerceShippingMethodImpl.setEngineKey(engineKey);
		}

		commerceShippingMethodImpl.setPriority(priority);
		commerceShippingMethodImpl.setActive(active);

		commerceShippingMethodImpl.resetOriginalValues();

		return commerceShippingMethodImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceShippingMethodId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		imageId = objectInput.readLong();
		engineKey = objectInput.readUTF();

		priority = objectInput.readDouble();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceShippingMethodId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(imageId);

		if (engineKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(engineKey);
		}

		objectOutput.writeDouble(priority);

		objectOutput.writeBoolean(active);
	}

	public long commerceShippingMethodId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public long imageId;
	public String engineKey;
	public double priority;
	public boolean active;

}