/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.type;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pavel Savinov
 */
public interface SiteNavigationMenuItemType {

	public default boolean exportData(
			PortletDataContext portletDataContext,
			Element siteNavigationMenuItemElement,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws PortalException {

		return true;
	}

	public default PortletURL getAddURL(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return null;
	}

	public default String getIcon() {
		return "magic";
	}

	public String getLabel(Locale locale);

	public default Layout getLayout(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		return null;
	}

	public default String getName(String typeSettings) {
		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.fastLoad(typeSettings);

		return typeSettingsProperties.get("name");
	}

	public default String getRegularURL(
			HttpServletRequest httpServletRequest,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		return StringPool.BLANK;
	}

	public default String getResetLayoutURL(
			HttpServletRequest httpServletRequest,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		return StringPool.BLANK;
	}

	public default String getResetMaxStateURL(
			HttpServletRequest httpServletRequest,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		return StringPool.BLANK;
	}

	public default String getSubtitle(
		SiteNavigationMenuItem siteNavigationMenuItem, Locale locale) {

		return getLabel(locale);
	}

	public default String getTarget(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		return StringPool.BLANK;
	}

	public default String getTitle(
		SiteNavigationMenuItem siteNavigationMenuItem, Locale locale) {

		return siteNavigationMenuItem.getName();
	}

	public default String getType() {
		return StringPool.BLANK;
	}

	public default String getTypeSettingsFromLayout(Layout layout) {
		return layout.getTypeSettings();
	}

	public default String getUnescapedName(
		SiteNavigationMenuItem siteNavigationMenuItem, String languageId) {

		return getTitle(
			siteNavigationMenuItem, LocaleUtil.fromLanguageId(languageId));
	}

	public default boolean hasPermission(
			PermissionChecker permissionChecker,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws PortalException {

		return true;
	}

	public default String iconURL(
		SiteNavigationMenuItem siteNavigationMenuItem, String pathImage) {

		return StringPool.BLANK;
	}

	public default boolean importData(
			PortletDataContext portletDataContext,
			SiteNavigationMenuItem siteNavigationMenuItem,
			SiteNavigationMenuItem importedSiteNavigationMenuItem)
		throws PortalException {

		return true;
	}

	public default boolean isBrowsable(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		return false;
	}

	public default boolean isChildSelected(
			boolean selectable, SiteNavigationMenuItem siteNavigationMenuItem,
			Layout curLayout)
		throws PortalException {

		return false;
	}

	public default boolean isSelected(
			boolean selectable, SiteNavigationMenuItem siteNavigationMenuItem,
			Layout curLayout)
		throws Exception {

		return false;
	}

	public default void renderAddPage(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {
	}

	public default void renderEditPage(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws IOException {
	}

}