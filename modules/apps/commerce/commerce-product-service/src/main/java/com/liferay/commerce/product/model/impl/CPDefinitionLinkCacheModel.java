/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPDefinitionLink in entity cache.
 *
 * @author Marco Leo
 * @generated
 */
public class CPDefinitionLinkCacheModel
	implements CacheModel<CPDefinitionLink>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CPDefinitionLinkCacheModel)) {
			return false;
		}

		CPDefinitionLinkCacheModel cpDefinitionLinkCacheModel =
			(CPDefinitionLinkCacheModel)object;

		if (CPDefinitionLinkId ==
				cpDefinitionLinkCacheModel.CPDefinitionLinkId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPDefinitionLinkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPDefinitionLinkId=");
		sb.append(CPDefinitionLinkId);
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
		sb.append(", CPDefinitionId=");
		sb.append(CPDefinitionId);
		sb.append(", CProductId=");
		sb.append(CProductId);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDefinitionLink toEntityModel() {
		CPDefinitionLinkImpl cpDefinitionLinkImpl = new CPDefinitionLinkImpl();

		if (uuid == null) {
			cpDefinitionLinkImpl.setUuid("");
		}
		else {
			cpDefinitionLinkImpl.setUuid(uuid);
		}

		cpDefinitionLinkImpl.setCPDefinitionLinkId(CPDefinitionLinkId);
		cpDefinitionLinkImpl.setGroupId(groupId);
		cpDefinitionLinkImpl.setCompanyId(companyId);
		cpDefinitionLinkImpl.setUserId(userId);

		if (userName == null) {
			cpDefinitionLinkImpl.setUserName("");
		}
		else {
			cpDefinitionLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpDefinitionLinkImpl.setCreateDate(null);
		}
		else {
			cpDefinitionLinkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpDefinitionLinkImpl.setModifiedDate(null);
		}
		else {
			cpDefinitionLinkImpl.setModifiedDate(new Date(modifiedDate));
		}

		cpDefinitionLinkImpl.setCPDefinitionId(CPDefinitionId);
		cpDefinitionLinkImpl.setCProductId(CProductId);
		cpDefinitionLinkImpl.setPriority(priority);

		if (type == null) {
			cpDefinitionLinkImpl.setType("");
		}
		else {
			cpDefinitionLinkImpl.setType(type);
		}

		cpDefinitionLinkImpl.resetOriginalValues();

		return cpDefinitionLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPDefinitionLinkId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		CPDefinitionId = objectInput.readLong();

		CProductId = objectInput.readLong();

		priority = objectInput.readDouble();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(CPDefinitionLinkId);

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

		objectOutput.writeLong(CPDefinitionId);

		objectOutput.writeLong(CProductId);

		objectOutput.writeDouble(priority);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public String uuid;
	public long CPDefinitionLinkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long CPDefinitionId;
	public long CProductId;
	public double priority;
	public String type;

}