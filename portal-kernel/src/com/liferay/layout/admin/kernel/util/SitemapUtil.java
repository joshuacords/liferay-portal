/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.xml.Element;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class SitemapUtil {

	public static void addURLElement(
		Element element, String url, UnicodeProperties typeSettingsProperties,
		Date modifiedDate, String canonicalURL,
		Map<Locale, String> alternateURLs) {

		getSitemap().addURLElement(
			element, url, typeSettingsProperties, modifiedDate, canonicalURL,
			alternateURLs);
	}

	public static String encodeXML(String input) {
		return getSitemap().encodeXML(input);
	}

	public static Map<Locale, String> getAlternateURLs(
			String canonicalURL, ThemeDisplay themeDisplay, Layout layout)
		throws PortalException {

		return getSitemap().getAlternateURLs(
			canonicalURL, themeDisplay, layout);
	}

	public static Sitemap getSitemap() {
		return _sitemap;
	}

	public static String getSitemap(
			long groupId, boolean privateLayout, ThemeDisplay themeDisplay)
		throws PortalException {

		return getSitemap().getSitemap(groupId, privateLayout, themeDisplay);
	}

	public static String getSitemap(
			String layoutUuid, long groupId, boolean privateLayout,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return getSitemap().getSitemap(
			layoutUuid, groupId, privateLayout, themeDisplay);
	}

	private static volatile Sitemap _sitemap =
		ServiceProxyFactory.newServiceTrackedInstance(
			Sitemap.class, SitemapUtil.class, "_sitemap", false);

}