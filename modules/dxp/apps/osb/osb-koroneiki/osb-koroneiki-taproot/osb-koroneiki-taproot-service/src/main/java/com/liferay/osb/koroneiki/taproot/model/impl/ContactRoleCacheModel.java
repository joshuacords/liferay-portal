/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
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
 * The cache model class for representing ContactRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactRoleCacheModel
	implements CacheModel<ContactRole>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactRoleCacheModel)) {
			return false;
		}

		ContactRoleCacheModel contactRoleCacheModel =
			(ContactRoleCacheModel)object;

		if ((contactRoleId == contactRoleCacheModel.contactRoleId) &&
			(mvccVersion == contactRoleCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contactRoleId);

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
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", contactRoleId=");
		sb.append(contactRoleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", contactRoleKey=");
		sb.append(contactRoleKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", system=");
		sb.append(system);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContactRole toEntityModel() {
		ContactRoleImpl contactRoleImpl = new ContactRoleImpl();

		contactRoleImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			contactRoleImpl.setUuid("");
		}
		else {
			contactRoleImpl.setUuid(uuid);
		}

		contactRoleImpl.setContactRoleId(contactRoleId);
		contactRoleImpl.setCompanyId(companyId);
		contactRoleImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			contactRoleImpl.setCreateDate(null);
		}
		else {
			contactRoleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contactRoleImpl.setModifiedDate(null);
		}
		else {
			contactRoleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (contactRoleKey == null) {
			contactRoleImpl.setContactRoleKey("");
		}
		else {
			contactRoleImpl.setContactRoleKey(contactRoleKey);
		}

		if (name == null) {
			contactRoleImpl.setName("");
		}
		else {
			contactRoleImpl.setName(name);
		}

		if (description == null) {
			contactRoleImpl.setDescription("");
		}
		else {
			contactRoleImpl.setDescription(description);
		}

		if (type == null) {
			contactRoleImpl.setType("");
		}
		else {
			contactRoleImpl.setType(type);
		}

		contactRoleImpl.setSystem(system);

		contactRoleImpl.resetOriginalValues();

		return contactRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		contactRoleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		contactRoleKey = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		type = objectInput.readUTF();

		system = objectInput.readBoolean();
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

		objectOutput.writeLong(contactRoleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (contactRoleKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contactRoleKey);
		}

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

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeBoolean(system);
	}

	public long mvccVersion;
	public String uuid;
	public long contactRoleId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String contactRoleKey;
	public String name;
	public String description;
	public String type;
	public boolean system;

}