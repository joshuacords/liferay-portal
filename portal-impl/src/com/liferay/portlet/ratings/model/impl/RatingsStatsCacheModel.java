/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.ratings.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.ratings.kernel.model.RatingsStats;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing RatingsStats in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RatingsStatsCacheModel
	implements CacheModel<RatingsStats>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RatingsStatsCacheModel)) {
			return false;
		}

		RatingsStatsCacheModel ratingsStatsCacheModel =
			(RatingsStatsCacheModel)object;

		if (statsId == ratingsStatsCacheModel.statsId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, statsId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{statsId=");
		sb.append(statsId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", totalEntries=");
		sb.append(totalEntries);
		sb.append(", totalScore=");
		sb.append(totalScore);
		sb.append(", averageScore=");
		sb.append(averageScore);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RatingsStats toEntityModel() {
		RatingsStatsImpl ratingsStatsImpl = new RatingsStatsImpl();

		ratingsStatsImpl.setStatsId(statsId);
		ratingsStatsImpl.setCompanyId(companyId);
		ratingsStatsImpl.setClassNameId(classNameId);
		ratingsStatsImpl.setClassPK(classPK);
		ratingsStatsImpl.setTotalEntries(totalEntries);
		ratingsStatsImpl.setTotalScore(totalScore);
		ratingsStatsImpl.setAverageScore(averageScore);

		ratingsStatsImpl.resetOriginalValues();

		return ratingsStatsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		statsId = objectInput.readLong();

		companyId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		totalEntries = objectInput.readInt();

		totalScore = objectInput.readDouble();

		averageScore = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(statsId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeInt(totalEntries);

		objectOutput.writeDouble(totalScore);

		objectOutput.writeDouble(averageScore);
	}

	public long statsId;
	public long companyId;
	public long classNameId;
	public long classPK;
	public int totalEntries;
	public double totalScore;
	public double averageScore;

}