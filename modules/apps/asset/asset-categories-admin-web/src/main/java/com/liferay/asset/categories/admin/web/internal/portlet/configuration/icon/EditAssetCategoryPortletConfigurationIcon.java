/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.admin.web.internal.portlet.configuration.icon;

import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesAdminPortletKeys;
import com.liferay.asset.categories.admin.web.internal.display.context.AssetCategoriesDisplayContext;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

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
public class EditAssetCategoryPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), "edit");
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		long vocabularyId = ParamUtil.getLong(portletRequest, "vocabularyId");

		long categoryId = ParamUtil.getLong(portletRequest, "categoryId");

		try {
			PortletURL editCategoryURL = PortletURLFactoryUtil.create(
				portletRequest,
				AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN,
				PortletRequest.RENDER_PHASE);

			editCategoryURL.setParameter("mvcPath", "/edit_category.jsp");
			editCategoryURL.setParameter(
				"redirect", _portal.getCurrentURL(portletRequest));
			editCategoryURL.setParameter(
				"categoryId", String.valueOf(categoryId));
			editCategoryURL.setParameter(
				"vocabularyId", String.valueOf(vocabularyId));
			editCategoryURL.setWindowState(WindowState.MAXIMIZED);

			return editCategoryURL.toString();
		}
		catch (WindowStateException windowStateException) {
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 200;
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
				category, ActionKeys.UPDATE);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAssetCategoryPortletConfigurationIcon.class);

	@Reference
	private Portal _portal;

}