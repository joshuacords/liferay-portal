/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ResourceBlock;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ResourceBlock in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceBlockCacheModel
	implements CacheModel<ResourceBlock>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ResourceBlockCacheModel)) {
			return false;
		}

		ResourceBlockCacheModel resourceBlockCacheModel =
			(ResourceBlockCacheModel)object;

		if ((resourceBlockId == resourceBlockCacheModel.resourceBlockId) &&
			(mvccVersion == resourceBlockCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, resourceBlockId);

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
		StringBundler sb = new StringBundler(15);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", resourceBlockId=");
		sb.append(resourceBlockId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", permissionsHash=");
		sb.append(permissionsHash);
		sb.append(", referenceCount=");
		sb.append(referenceCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ResourceBlock toEntityModel() {
		ResourceBlockImpl resourceBlockImpl = new ResourceBlockImpl();

		resourceBlockImpl.setMvccVersion(mvccVersion);
		resourceBlockImpl.setResourceBlockId(resourceBlockId);
		resourceBlockImpl.setCompanyId(companyId);
		resourceBlockImpl.setGroupId(groupId);

		if (name == null) {
			resourceBlockImpl.setName("");
		}
		else {
			resourceBlockImpl.setName(name);
		}

		if (permissionsHash == null) {
			resourceBlockImpl.setPermissionsHash("");
		}
		else {
			resourceBlockImpl.setPermissionsHash(permissionsHash);
		}

		resourceBlockImpl.setReferenceCount(referenceCount);

		resourceBlockImpl.resetOriginalValues();

		return resourceBlockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		resourceBlockId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();
		name = objectInput.readUTF();
		permissionsHash = objectInput.readUTF();

		referenceCount = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(resourceBlockId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (permissionsHash == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(permissionsHash);
		}

		objectOutput.writeLong(referenceCount);
	}

	public long mvccVersion;
	public long resourceBlockId;
	public long companyId;
	public long groupId;
	public String name;
	public String permissionsHash;
	public long referenceCount;

}