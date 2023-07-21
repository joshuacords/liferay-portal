/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.url.provider;

import com.liferay.asset.info.display.url.provider.util.AssetInfoEditURLProviderUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.display.url.provider.InfoEditURLProvider;
import com.liferay.petra.string.StringPool;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Jürgen Kappler
 * @deprecated As of Mueller (7.2.x), in favour of {@link InfoEditURLProvider}
 */
@Deprecated
public class BaseAssetInfoEditURLProvider
	implements InfoEditURLProvider<AssetEntry> {

	@Override
	public String getURL(
			AssetEntry assetEntry, HttpServletRequest httpServletRequest)
		throws Exception {

		if (assetEntry == null) {
			return StringPool.BLANK;
		}

		return AssetInfoEditURLProviderUtil.getURL(
			assetEntry.getClassName(), assetEntry.getClassPK(),
			httpServletRequest);
	}

}