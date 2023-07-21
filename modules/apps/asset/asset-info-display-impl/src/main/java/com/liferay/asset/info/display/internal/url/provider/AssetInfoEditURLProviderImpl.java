/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.internal.url.provider;

import com.liferay.asset.info.display.url.provider.AssetInfoEditURLProvider;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = AssetInfoEditURLProvider.class)
public class AssetInfoEditURLProviderImpl implements AssetInfoEditURLProvider {

	public String getURL(
		String className, long classPK, HttpServletRequest httpServletRequest) {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory == null) {
			return StringPool.BLANK;
		}

		try {
			AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
				classPK);

			if (assetRenderer == null) {
				return StringPool.BLANK;
			}

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (!assetRenderer.hasEditPermission(
					themeDisplay.getPermissionChecker())) {

				return StringPool.BLANK;
			}

			PortletURL editAssetEntryURL = assetRenderer.getURLEdit(
				httpServletRequest, LiferayWindowState.NORMAL,
				themeDisplay.getURLCurrent());

			if (editAssetEntryURL == null) {
				return StringPool.BLANK;
			}

			editAssetEntryURL.setParameter(
				"portletResource", assetRendererFactory.getPortletId());

			return editAssetEntryURL.toString();
		}
		catch (Exception exception) {
		}

		return StringPool.BLANK;
	}

}