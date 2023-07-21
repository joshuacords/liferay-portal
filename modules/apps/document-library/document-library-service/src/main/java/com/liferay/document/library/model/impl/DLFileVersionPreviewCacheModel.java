/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.model.impl;

import com.liferay.document.library.model.DLFileVersionPreview;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DLFileVersionPreview in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileVersionPreviewCacheModel
	implements CacheModel<DLFileVersionPreview>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DLFileVersionPreviewCacheModel)) {
			return false;
		}

		DLFileVersionPreviewCacheModel dlFileVersionPreviewCacheModel =
			(DLFileVersionPreviewCacheModel)object;

		if (dlFileVersionPreviewId ==
				dlFileVersionPreviewCacheModel.dlFileVersionPreviewId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dlFileVersionPreviewId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{dlFileVersionPreviewId=");
		sb.append(dlFileVersionPreviewId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", fileVersionId=");
		sb.append(fileVersionId);
		sb.append(", previewStatus=");
		sb.append(previewStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DLFileVersionPreview toEntityModel() {
		DLFileVersionPreviewImpl dlFileVersionPreviewImpl =
			new DLFileVersionPreviewImpl();

		dlFileVersionPreviewImpl.setDlFileVersionPreviewId(
			dlFileVersionPreviewId);
		dlFileVersionPreviewImpl.setGroupId(groupId);
		dlFileVersionPreviewImpl.setFileEntryId(fileEntryId);
		dlFileVersionPreviewImpl.setFileVersionId(fileVersionId);
		dlFileVersionPreviewImpl.setPreviewStatus(previewStatus);

		dlFileVersionPreviewImpl.resetOriginalValues();

		return dlFileVersionPreviewImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		dlFileVersionPreviewId = objectInput.readLong();

		groupId = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		fileVersionId = objectInput.readLong();

		previewStatus = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(dlFileVersionPreviewId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeLong(fileVersionId);

		objectOutput.writeInt(previewStatus);
	}

	public long dlFileVersionPreviewId;
	public long groupId;
	public long fileEntryId;
	public long fileVersionId;
	public int previewStatus;

}