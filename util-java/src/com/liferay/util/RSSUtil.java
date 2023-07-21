/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import java.util.List;

import org.jdom.IllegalDataException;

/**
 * @author     Brian Wing Shun Chan
 * @author     Eduardo García
 * @see        com.liferay.rss.util.RSSUtil
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class RSSUtil extends com.liferay.portal.kernel.util.RSSUtil {

	public static String export(SyndFeed feed) throws FeedException {
		feed.setEncoding(StringPool.UTF8);

		SyndFeedOutput output = new SyndFeedOutput();

		try {
			return output.outputString(feed);
		}
		catch (IllegalDataException illegalDataException) {

			// LEP-4450

			_regexpStrip(feed);

			return output.outputString(feed);
		}
	}

	private static String _regexpStrip(String text) {
		text = Normalizer.normalizeToAscii(text);

		char[] array = text.toCharArray();

		for (int i = 0; i < array.length; i++) {
			String s = String.valueOf(array[i]);

			if (!s.matches(_REGEXP_STRIP)) {
				array[i] = CharPool.SPACE;
			}
		}

		return new String(array);
	}

	private static void _regexpStrip(SyndFeed syndFeed) {
		syndFeed.setTitle(_regexpStrip(syndFeed.getTitle()));
		syndFeed.setDescription(_regexpStrip(syndFeed.getDescription()));

		List<SyndEntry> syndEntries = syndFeed.getEntries();

		for (SyndEntry syndEntry : syndEntries) {
			syndEntry.setTitle(_regexpStrip(syndEntry.getTitle()));

			SyndContent syndContent = syndEntry.getDescription();

			syndContent.setValue(_regexpStrip(syndContent.getValue()));
		}
	}

	private static final String _REGEXP_STRIP = "[\\d\\w]";

}