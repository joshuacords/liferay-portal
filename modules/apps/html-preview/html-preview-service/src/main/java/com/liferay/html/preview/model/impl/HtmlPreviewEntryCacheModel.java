/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.html.preview.model.impl;

import com.liferay.html.preview.model.HtmlPreviewEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HtmlPreviewEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HtmlPreviewEntryCacheModel
	implements CacheModel<HtmlPreviewEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HtmlPreviewEntryCacheModel)) {
			return false;
		}

		HtmlPreviewEntryCacheModel htmlPreviewEntryCacheModel =
			(HtmlPreviewEntryCacheModel)object;

		if (htmlPreviewEntryId ==
				htmlPreviewEntryCacheModel.htmlPreviewEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, htmlPreviewEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{htmlPreviewEntryId=");
		sb.append(htmlPreviewEntryId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HtmlPreviewEntry toEntityModel() {
		HtmlPreviewEntryImpl htmlPreviewEntryImpl = new HtmlPreviewEntryImpl();

		htmlPreviewEntryImpl.setHtmlPreviewEntryId(htmlPreviewEntryId);
		htmlPreviewEntryImpl.setGroupId(groupId);
		htmlPreviewEntryImpl.setCompanyId(companyId);
		htmlPreviewEntryImpl.setUserId(userId);

		if (userName == null) {
			htmlPreviewEntryImpl.setUserName("");
		}
		else {
			htmlPreviewEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			htmlPreviewEntryImpl.setCreateDate(null);
		}
		else {
			htmlPreviewEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			htmlPreviewEntryImpl.setModifiedDate(null);
		}
		else {
			htmlPreviewEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		htmlPreviewEntryImpl.setClassNameId(classNameId);
		htmlPreviewEntryImpl.setClassPK(classPK);
		htmlPreviewEntryImpl.setFileEntryId(fileEntryId);

		htmlPreviewEntryImpl.resetOriginalValues();

		return htmlPreviewEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		htmlPreviewEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		fileEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(htmlPreviewEntryId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(fileEntryId);
	}

	public long htmlPreviewEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long fileEntryId;

}