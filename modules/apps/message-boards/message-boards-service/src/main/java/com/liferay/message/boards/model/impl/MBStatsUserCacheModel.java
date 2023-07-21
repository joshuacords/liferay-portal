/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.model.impl;

import com.liferay.message.boards.model.MBStatsUser;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MBStatsUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MBStatsUserCacheModel
	implements CacheModel<MBStatsUser>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MBStatsUserCacheModel)) {
			return false;
		}

		MBStatsUserCacheModel mbStatsUserCacheModel =
			(MBStatsUserCacheModel)object;

		if (statsUserId == mbStatsUserCacheModel.statsUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, statsUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{statsUserId=");
		sb.append(statsUserId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", messageCount=");
		sb.append(messageCount);
		sb.append(", lastPostDate=");
		sb.append(lastPostDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MBStatsUser toEntityModel() {
		MBStatsUserImpl mbStatsUserImpl = new MBStatsUserImpl();

		mbStatsUserImpl.setStatsUserId(statsUserId);
		mbStatsUserImpl.setGroupId(groupId);
		mbStatsUserImpl.setCompanyId(companyId);
		mbStatsUserImpl.setUserId(userId);
		mbStatsUserImpl.setMessageCount(messageCount);

		if (lastPostDate == Long.MIN_VALUE) {
			mbStatsUserImpl.setLastPostDate(null);
		}
		else {
			mbStatsUserImpl.setLastPostDate(new Date(lastPostDate));
		}

		mbStatsUserImpl.resetOriginalValues();

		return mbStatsUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		statsUserId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		messageCount = objectInput.readInt();
		lastPostDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(statsUserId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeInt(messageCount);
		objectOutput.writeLong(lastPostDate);
	}

	public long statsUserId;
	public long groupId;
	public long companyId;
	public long userId;
	public int messageCount;
	public long lastPostDate;

}