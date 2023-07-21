/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ResourceBlockPermission;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ResourceBlockPermission in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ResourceBlockPermissionCacheModel
	implements CacheModel<ResourceBlockPermission>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ResourceBlockPermissionCacheModel)) {
			return false;
		}

		ResourceBlockPermissionCacheModel resourceBlockPermissionCacheModel =
			(ResourceBlockPermissionCacheModel)object;

		if ((resourceBlockPermissionId ==
				resourceBlockPermissionCacheModel.resourceBlockPermissionId) &&
			(mvccVersion == resourceBlockPermissionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, resourceBlockPermissionId);

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
		StringBundler sb = new StringBundler(13);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", resourceBlockPermissionId=");
		sb.append(resourceBlockPermissionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", resourceBlockId=");
		sb.append(resourceBlockId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", actionIds=");
		sb.append(actionIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ResourceBlockPermission toEntityModel() {
		ResourceBlockPermissionImpl resourceBlockPermissionImpl =
			new ResourceBlockPermissionImpl();

		resourceBlockPermissionImpl.setMvccVersion(mvccVersion);
		resourceBlockPermissionImpl.setResourceBlockPermissionId(
			resourceBlockPermissionId);
		resourceBlockPermissionImpl.setCompanyId(companyId);
		resourceBlockPermissionImpl.setResourceBlockId(resourceBlockId);
		resourceBlockPermissionImpl.setRoleId(roleId);
		resourceBlockPermissionImpl.setActionIds(actionIds);

		resourceBlockPermissionImpl.resetOriginalValues();

		return resourceBlockPermissionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		resourceBlockPermissionId = objectInput.readLong();

		companyId = objectInput.readLong();

		resourceBlockId = objectInput.readLong();

		roleId = objectInput.readLong();

		actionIds = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(resourceBlockPermissionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(resourceBlockId);

		objectOutput.writeLong(roleId);

		objectOutput.writeLong(actionIds);
	}

	public long mvccVersion;
	public long resourceBlockPermissionId;
	public long companyId;
	public long resourceBlockId;
	public long roleId;
	public long actionIds;

}