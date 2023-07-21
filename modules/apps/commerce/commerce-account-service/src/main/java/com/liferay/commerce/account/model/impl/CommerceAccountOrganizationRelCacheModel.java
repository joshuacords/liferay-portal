/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceAccountOrganizationRel in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceAccountOrganizationRelCacheModel
	implements CacheModel<CommerceAccountOrganizationRel>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceAccountOrganizationRelCacheModel)) {
			return false;
		}

		CommerceAccountOrganizationRelCacheModel
			commerceAccountOrganizationRelCacheModel =
				(CommerceAccountOrganizationRelCacheModel)object;

		if (commerceAccountOrganizationRelPK.equals(
				commerceAccountOrganizationRelCacheModel.
					commerceAccountOrganizationRelPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceAccountOrganizationRelPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{commerceAccountId=");
		sb.append(commerceAccountId);
		sb.append(", organizationId=");
		sb.append(organizationId);
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
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceAccountOrganizationRel toEntityModel() {
		CommerceAccountOrganizationRelImpl commerceAccountOrganizationRelImpl =
			new CommerceAccountOrganizationRelImpl();

		commerceAccountOrganizationRelImpl.setCommerceAccountId(
			commerceAccountId);
		commerceAccountOrganizationRelImpl.setOrganizationId(organizationId);
		commerceAccountOrganizationRelImpl.setCompanyId(companyId);
		commerceAccountOrganizationRelImpl.setUserId(userId);

		if (userName == null) {
			commerceAccountOrganizationRelImpl.setUserName("");
		}
		else {
			commerceAccountOrganizationRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceAccountOrganizationRelImpl.setCreateDate(null);
		}
		else {
			commerceAccountOrganizationRelImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceAccountOrganizationRelImpl.setModifiedDate(null);
		}
		else {
			commerceAccountOrganizationRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceAccountOrganizationRelImpl.resetOriginalValues();

		return commerceAccountOrganizationRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commerceAccountId = objectInput.readLong();

		organizationId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceAccountOrganizationRelPK = new CommerceAccountOrganizationRelPK(
			commerceAccountId, organizationId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(commerceAccountId);

		objectOutput.writeLong(organizationId);

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
	}

	public long commerceAccountId;
	public long organizationId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public transient CommerceAccountOrganizationRelPK
		commerceAccountOrganizationRelPK;

}