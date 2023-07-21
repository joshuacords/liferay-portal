/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.amazon.rankings.web.internal.util;

import com.liferay.amazon.rankings.web.internal.configuration.AmazonRankingsConfiguration;
import com.liferay.amazon.rankings.web.internal.model.AmazonRankings;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.text.DateFormat;

import java.util.Calendar;

/**
 * @author Brian Wing Shun Chan
 */
public class AmazonRankingsUtil {

	public static AmazonRankings getAmazonRankings(
		AmazonRankingsConfiguration amazonRankingsConfiguration, String isbn) {

		isbn = isbn.replaceAll(StringPool.DASH, StringPool.BLANK);

		WebCacheItem wci = new AmazonRankingsWebCacheItem(
			amazonRankingsConfiguration, isbn);

		return (AmazonRankings)WebCachePoolUtil.get(
			AmazonRankingsUtil.class.getName() + StringPool.PERIOD + isbn, wci);
	}

	public static String getTimestamp() {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			_TIMESTAMP);

		dateFormat.setTimeZone(TimeZoneUtil.getDefault());

		Calendar calendar = Calendar.getInstance();

		return dateFormat.format(calendar.getTime());
	}

	private static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

}