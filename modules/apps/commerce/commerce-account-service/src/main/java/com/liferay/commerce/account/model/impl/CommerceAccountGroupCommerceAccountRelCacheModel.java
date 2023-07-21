/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceAccountGroupCommerceAccountRel in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceAccountGroupCommerceAccountRelCacheModel
	implements CacheModel<CommerceAccountGroupCommerceAccountRel>,
			   Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof
				CommerceAccountGroupCommerceAccountRelCacheModel)) {

			return false;
		}

		CommerceAccountGroupCommerceAccountRelCacheModel
			commerceAccountGroupCommerceAccountRelCacheModel =
				(CommerceAccountGroupCommerceAccountRelCacheModel)object;

		if (commerceAccountGroupCommerceAccountRelId ==
				commerceAccountGroupCommerceAccountRelCacheModel.
					commerceAccountGroupCommerceAccountRelId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceAccountGroupCommerceAccountRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", commerceAccountGroupCommerceAccountRelId=");
		sb.append(commerceAccountGroupCommerceAccountRelId);
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
		sb.append(", commerceAccountGroupId=");
		sb.append(commerceAccountGroupId);
		sb.append(", commerceAccountId=");
		sb.append(commerceAccountId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceAccountGroupCommerceAccountRel toEntityModel() {
		CommerceAccountGroupCommerceAccountRelImpl
			commerceAccountGroupCommerceAccountRelImpl =
				new CommerceAccountGroupCommerceAccountRelImpl();

		if (externalReferenceCode == null) {
			commerceAccountGroupCommerceAccountRelImpl.setExternalReferenceCode(
				"");
		}
		else {
			commerceAccountGroupCommerceAccountRelImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		commerceAccountGroupCommerceAccountRelImpl.
			setCommerceAccountGroupCommerceAccountRelId(
				commerceAccountGroupCommerceAccountRelId);
		commerceAccountGroupCommerceAccountRelImpl.setCompanyId(companyId);
		commerceAccountGroupCommerceAccountRelImpl.setUserId(userId);

		if (userName == null) {
			commerceAccountGroupCommerceAccountRelImpl.setUserName("");
		}
		else {
			commerceAccountGroupCommerceAccountRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceAccountGroupCommerceAccountRelImpl.setCreateDate(null);
		}
		else {
			commerceAccountGroupCommerceAccountRelImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceAccountGroupCommerceAccountRelImpl.setModifiedDate(null);
		}
		else {
			commerceAccountGroupCommerceAccountRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceAccountGroupCommerceAccountRelImpl.setCommerceAccountGroupId(
			commerceAccountGroupId);
		commerceAccountGroupCommerceAccountRelImpl.setCommerceAccountId(
			commerceAccountId);

		commerceAccountGroupCommerceAccountRelImpl.resetOriginalValues();

		return commerceAccountGroupCommerceAccountRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		externalReferenceCode = objectInput.readUTF();

		commerceAccountGroupCommerceAccountRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceAccountGroupId = objectInput.readLong();

		commerceAccountId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(commerceAccountGroupCommerceAccountRelId);

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

		objectOutput.writeLong(commerceAccountGroupId);

		objectOutput.writeLong(commerceAccountId);
	}

	public String externalReferenceCode;
	public long commerceAccountGroupCommerceAccountRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceAccountGroupId;
	public long commerceAccountId;

}