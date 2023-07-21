/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ClusterGroup;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ClusterGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @deprecated
 * @generated
 */
@Deprecated
public class ClusterGroupCacheModel
	implements CacheModel<ClusterGroup>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ClusterGroupCacheModel)) {
			return false;
		}

		ClusterGroupCacheModel clusterGroupCacheModel =
			(ClusterGroupCacheModel)object;

		if ((clusterGroupId == clusterGroupCacheModel.clusterGroupId) &&
			(mvccVersion == clusterGroupCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, clusterGroupId);

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
		StringBundler sb = new StringBundler(11);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", clusterGroupId=");
		sb.append(clusterGroupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", clusterNodeIds=");
		sb.append(clusterNodeIds);
		sb.append(", wholeCluster=");
		sb.append(wholeCluster);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ClusterGroup toEntityModel() {
		ClusterGroupImpl clusterGroupImpl = new ClusterGroupImpl();

		clusterGroupImpl.setMvccVersion(mvccVersion);
		clusterGroupImpl.setClusterGroupId(clusterGroupId);

		if (name == null) {
			clusterGroupImpl.setName("");
		}
		else {
			clusterGroupImpl.setName(name);
		}

		if (clusterNodeIds == null) {
			clusterGroupImpl.setClusterNodeIds("");
		}
		else {
			clusterGroupImpl.setClusterNodeIds(clusterNodeIds);
		}

		clusterGroupImpl.setWholeCluster(wholeCluster);

		clusterGroupImpl.resetOriginalValues();

		return clusterGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		clusterGroupId = objectInput.readLong();
		name = objectInput.readUTF();
		clusterNodeIds = objectInput.readUTF();

		wholeCluster = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(clusterGroupId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (clusterNodeIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(clusterNodeIds);
		}

		objectOutput.writeBoolean(wholeCluster);
	}

	public long mvccVersion;
	public long clusterGroupId;
	public String name;
	public String clusterNodeIds;
	public boolean wholeCluster;

}