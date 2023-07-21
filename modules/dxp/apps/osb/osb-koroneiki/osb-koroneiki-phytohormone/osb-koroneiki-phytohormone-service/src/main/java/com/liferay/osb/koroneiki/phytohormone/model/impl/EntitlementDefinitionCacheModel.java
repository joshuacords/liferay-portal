/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model.impl;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
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
 * The cache model class for representing EntitlementDefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntitlementDefinitionCacheModel
	implements CacheModel<EntitlementDefinition>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EntitlementDefinitionCacheModel)) {
			return false;
		}

		EntitlementDefinitionCacheModel entitlementDefinitionCacheModel =
			(EntitlementDefinitionCacheModel)object;

		if ((entitlementDefinitionId ==
				entitlementDefinitionCacheModel.entitlementDefinitionId) &&
			(mvccVersion == entitlementDefinitionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, entitlementDefinitionId);

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
		sb.append(", entitlementDefinitionId=");
		sb.append(entitlementDefinitionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", entitlementDefinitionKey=");
		sb.append(entitlementDefinitionKey);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", definition=");
		sb.append(definition);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntitlementDefinition toEntityModel() {
		EntitlementDefinitionImpl entitlementDefinitionImpl =
			new EntitlementDefinitionImpl();

		entitlementDefinitionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			entitlementDefinitionImpl.setUuid("");
		}
		else {
			entitlementDefinitionImpl.setUuid(uuid);
		}

		entitlementDefinitionImpl.setEntitlementDefinitionId(
			entitlementDefinitionId);
		entitlementDefinitionImpl.setCompanyId(companyId);
		entitlementDefinitionImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			entitlementDefinitionImpl.setCreateDate(null);
		}
		else {
			entitlementDefinitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			entitlementDefinitionImpl.setModifiedDate(null);
		}
		else {
			entitlementDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (entitlementDefinitionKey == null) {
			entitlementDefinitionImpl.setEntitlementDefinitionKey("");
		}
		else {
			entitlementDefinitionImpl.setEntitlementDefinitionKey(
				entitlementDefinitionKey);
		}

		entitlementDefinitionImpl.setClassNameId(classNameId);

		if (name == null) {
			entitlementDefinitionImpl.setName("");
		}
		else {
			entitlementDefinitionImpl.setName(name);
		}

		if (description == null) {
			entitlementDefinitionImpl.setDescription("");
		}
		else {
			entitlementDefinitionImpl.setDescription(description);
		}

		if (definition == null) {
			entitlementDefinitionImpl.setDefinition("");
		}
		else {
			entitlementDefinitionImpl.setDefinition(definition);
		}

		entitlementDefinitionImpl.setStatus(status);

		entitlementDefinitionImpl.resetOriginalValues();

		return entitlementDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		entitlementDefinitionId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		entitlementDefinitionKey = objectInput.readUTF();

		classNameId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		definition = objectInput.readUTF();

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

		objectOutput.writeLong(entitlementDefinitionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (entitlementDefinitionKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(entitlementDefinitionKey);
		}

		objectOutput.writeLong(classNameId);

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

		if (definition == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(definition);
		}

		objectOutput.writeInt(status);
	}

	public long mvccVersion;
	public String uuid;
	public long entitlementDefinitionId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String entitlementDefinitionKey;
	public long classNameId;
	public String name;
	public String description;
	public String definition;
	public int status;

}