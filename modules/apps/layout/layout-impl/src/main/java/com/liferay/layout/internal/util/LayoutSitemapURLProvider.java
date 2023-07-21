/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.util;

import com.liferay.layout.admin.kernel.model.LayoutTypePortletConstants;
import com.liferay.layout.admin.kernel.util.Sitemap;
import com.liferay.layout.admin.kernel.util.SitemapURLProvider;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.util.LayoutTypeControllerTracker;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(service = SitemapURLProvider.class)
public class LayoutSitemapURLProvider implements SitemapURLProvider {

	@Override
	public String getClassName() {
		return Layout.class.getName();
	}

	@Override
	public void visitLayout(
			Element element, String layoutUuid, LayoutSet layoutSet,
			ThemeDisplay themeDisplay)
		throws PortalException {

		Layout layout = _layoutLocalService.getLayoutByUuidAndGroupId(
			layoutUuid, layoutSet.getGroupId(), layoutSet.isPrivateLayout());

		visitLayout(element, layout, themeDisplay);
	}

	@Override
	public void visitLayoutSet(
			Element element, LayoutSet layoutSet, ThemeDisplay themeDisplay)
		throws PortalException {

		if (layoutSet.isPrivateLayout()) {
			return;
		}

		Map<String, LayoutTypeController> layoutTypeControllers =
			LayoutTypeControllerTracker.getLayoutTypeControllers();

		for (Map.Entry<String, LayoutTypeController> entry :
				layoutTypeControllers.entrySet()) {

			LayoutTypeController layoutTypeController = entry.getValue();

			if (!layoutTypeController.isSitemapable()) {
				continue;
			}

			int start = QueryUtil.ALL_POS;
			int end = QueryUtil.ALL_POS;

			int count = _layoutService.getLayoutsCount(
				layoutSet.getGroupId(), layoutSet.isPrivateLayout(),
				entry.getKey());

			if (count > Sitemap.MAXIMUM_ENTRIES) {
				start = count - Sitemap.MAXIMUM_ENTRIES;
				end = count;
			}

			List<Layout> layouts = _layoutService.getLayouts(
				layoutSet.getGroupId(), layoutSet.isPrivateLayout(),
				entry.getKey(), start, end);

			for (Layout layout : layouts) {
				visitLayout(element, layout, themeDisplay);
			}
		}
	}

	protected void visitLayout(
			Element element, Layout layout, ThemeDisplay themeDisplay)
		throws PortalException {

		if (layout.isSystem()) {
			return;
		}

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		if (!GetterUtil.getBoolean(
				typeSettingsProperties.getProperty(
					LayoutTypePortletConstants.SITEMAP_INCLUDE),
				true)) {

			return;
		}

		String layoutFullURL = _portal.getLayoutFullURL(layout, themeDisplay);

		layoutFullURL = _portal.getCanonicalURL(
			layoutFullURL, themeDisplay, layout);

		Map<Locale, String> alternateURLs = _sitemap.getAlternateURLs(
			layoutFullURL, themeDisplay, layout);

		for (String alternateURL : alternateURLs.values()) {
			_sitemap.addURLElement(
				element, alternateURL, typeSettingsProperties,
				layout.getModifiedDate(), layoutFullURL, alternateURLs);
		}
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutService _layoutService;

	@Reference
	private Portal _portal;

	@Reference
	private Sitemap _sitemap;

}