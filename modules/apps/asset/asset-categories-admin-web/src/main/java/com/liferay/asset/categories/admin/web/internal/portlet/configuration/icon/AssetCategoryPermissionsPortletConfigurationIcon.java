/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.admin.web.internal.portlet.configuration.icon;

import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesAdminPortletKeys;
import com.liferay.asset.categories.admin.web.internal.display.context.AssetCategoriesDisplayContext;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.security.PermissionsURLTag;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN,
		"path=/view_categories.jsp"
	},
	service = PortletConfigurationIcon.class
)
public class AssetCategoryPermissionsPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), "permissions");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		long categoryId = ParamUtil.getLong(portletRequest, "categoryId");

		if (categoryId <= 0) {
			return StringPool.BLANK;
		}

		String url = StringPool.BLANK;

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetCategory assetCategory = _assetCategoryLocalService.fetchCategory(
			categoryId);

		try {
			url = PermissionsURLTag.doTag(
				StringPool.BLANK, AssetCategory.class.getName(),
				assetCategory.getTitle(themeDisplay.getLocale()), null,
				String.valueOf(assetCategory.getCategoryId()),
				LiferayWindowState.POP_UP.toString(), null,
				themeDisplay.getRequest());
		}
		catch (Exception exception) {
		}

		return url;
	}

	@Override
	public double getWeight() {
		return 180;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		AssetCategoriesDisplayContext assetCategoriesDisplayContext =
			new AssetCategoriesDisplayContext(
				null, null, _portal.getHttpServletRequest(portletRequest));

		AssetCategory category = assetCategoriesDisplayContext.getCategory();

		if (category == null) {
			return false;
		}

		try {
			return assetCategoriesDisplayContext.hasPermission(
				category, ActionKeys.PERMISSIONS);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return false;
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

	@Override
	public boolean isUseDialog() {
		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetCategoryPermissionsPortletConfigurationIcon.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private Portal _portal;

}