/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.model.impl;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
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
 * The cache model class for representing Entitlement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntitlementCacheModel
	implements CacheModel<Entitlement>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EntitlementCacheModel)) {
			return false;
		}

		EntitlementCacheModel entitlementCacheModel =
			(EntitlementCacheModel)object;

		if ((entitlementId == entitlementCacheModel.entitlementId) &&
			(mvccVersion == entitlementCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, entitlementId);

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
		StringBundler sb = new StringBundler(21);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", entitlementId=");
		sb.append(entitlementId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", entitlementDefinitionId=");
		sb.append(entitlementDefinitionId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Entitlement toEntityModel() {
		EntitlementImpl entitlementImpl = new EntitlementImpl();

		entitlementImpl.setMvccVersion(mvccVersion);
		entitlementImpl.setEntitlementId(entitlementId);
		entitlementImpl.setCompanyId(companyId);
		entitlementImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			entitlementImpl.setCreateDate(null);
		}
		else {
			entitlementImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			entitlementImpl.setModifiedDate(null);
		}
		else {
			entitlementImpl.setModifiedDate(new Date(modifiedDate));
		}

		entitlementImpl.setEntitlementDefinitionId(entitlementDefinitionId);
		entitlementImpl.setClassNameId(classNameId);
		entitlementImpl.setClassPK(classPK);

		if (name == null) {
			entitlementImpl.setName("");
		}
		else {
			entitlementImpl.setName(name);
		}

		entitlementImpl.resetOriginalValues();

		return entitlementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		entitlementId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		entitlementDefinitionId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(entitlementId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(entitlementDefinitionId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long mvccVersion;
	public long entitlementId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long entitlementDefinitionId;
	public long classNameId;
	public long classPK;
	public String name;

}