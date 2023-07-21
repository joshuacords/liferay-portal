/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.kernel;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Cristina González
 */
public class LayoutSEOLinkManagerUtil {

	public static List<LayoutSEOLink> getLocalizedLayoutSEOLinks(
			Layout layout, Locale locale, String canonicalURL,
			Map<Locale, String> alternateURLs)
		throws PortalException {

		return _layoutSEOLinkManager.getLocalizedLayoutSEOLinks(
			layout, locale, canonicalURL, alternateURLs);
	}

	private static volatile LayoutSEOLinkManager _layoutSEOLinkManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			LayoutSEOLinkManager.class, LayoutSEOLinkManagerUtil.class,
			"_layoutSEOLinkManager", false);

}