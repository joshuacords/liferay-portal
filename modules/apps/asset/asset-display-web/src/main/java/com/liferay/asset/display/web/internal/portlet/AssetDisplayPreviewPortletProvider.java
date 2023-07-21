/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.web.internal.portlet;

import com.liferay.asset.display.web.internal.constants.AssetDisplayPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.PreviewPortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.asset.kernel.model.AssetEntry",
	service = PreviewPortletProvider.class
)
public class AssetDisplayPreviewPortletProvider
	extends BasePortletProvider implements PreviewPortletProvider {

	@Override
	public String getPortletName() {
		return AssetDisplayPortletKeys.ASSET_DISPLAY;
	}

}