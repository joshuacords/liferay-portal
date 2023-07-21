/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.file.rank.model.impl;

import com.liferay.document.library.file.rank.model.DLFileRank;
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
 * The cache model class for representing DLFileRank in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileRankCacheModel
	implements CacheModel<DLFileRank>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DLFileRankCacheModel)) {
			return false;
		}

		DLFileRankCacheModel dlFileRankCacheModel =
			(DLFileRankCacheModel)object;

		if ((fileRankId == dlFileRankCacheModel.fileRankId) &&
			(mvccVersion == dlFileRankCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fileRankId);

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
		StringBundler sb = new StringBundler(17);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", fileRankId=");
		sb.append(fileRankId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DLFileRank toEntityModel() {
		DLFileRankImpl dlFileRankImpl = new DLFileRankImpl();

		dlFileRankImpl.setMvccVersion(mvccVersion);
		dlFileRankImpl.setFileRankId(fileRankId);
		dlFileRankImpl.setGroupId(groupId);
		dlFileRankImpl.setCompanyId(companyId);
		dlFileRankImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			dlFileRankImpl.setCreateDate(null);
		}
		else {
			dlFileRankImpl.setCreateDate(new Date(createDate));
		}

		dlFileRankImpl.setFileEntryId(fileEntryId);
		dlFileRankImpl.setActive(active);

		dlFileRankImpl.resetOriginalValues();

		return dlFileRankImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		fileRankId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(fileRankId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeBoolean(active);
	}

	public long mvccVersion;
	public long fileRankId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long fileEntryId;
	public boolean active;

}