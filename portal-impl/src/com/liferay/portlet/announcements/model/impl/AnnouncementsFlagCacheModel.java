/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.announcements.model.impl;

import com.liferay.announcements.kernel.model.AnnouncementsFlag;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AnnouncementsFlag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnnouncementsFlagCacheModel
	implements CacheModel<AnnouncementsFlag>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AnnouncementsFlagCacheModel)) {
			return false;
		}

		AnnouncementsFlagCacheModel announcementsFlagCacheModel =
			(AnnouncementsFlagCacheModel)object;

		if (flagId == announcementsFlagCacheModel.flagId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, flagId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{flagId=");
		sb.append(flagId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AnnouncementsFlag toEntityModel() {
		AnnouncementsFlagImpl announcementsFlagImpl =
			new AnnouncementsFlagImpl();

		announcementsFlagImpl.setFlagId(flagId);
		announcementsFlagImpl.setCompanyId(companyId);
		announcementsFlagImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			announcementsFlagImpl.setCreateDate(null);
		}
		else {
			announcementsFlagImpl.setCreateDate(new Date(createDate));
		}

		announcementsFlagImpl.setEntryId(entryId);
		announcementsFlagImpl.setValue(value);

		announcementsFlagImpl.resetOriginalValues();

		return announcementsFlagImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		flagId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();

		entryId = objectInput.readLong();

		value = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(flagId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(entryId);

		objectOutput.writeInt(value);
	}

	public long flagId;
	public long companyId;
	public long userId;
	public long createDate;
	public long entryId;
	public int value;

}