/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.util;

import com.liferay.petra.string.StringPool;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Julio Camarero
 * @deprecated As of Judson (7.1.x)
 */
@Deprecated
public abstract class BaseAssetEntryQueryProcessor
	implements AssetEntryQueryProcessor {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public String getKey() {
		Class<?> clazz = getClass();

		return clazz.getSimpleName();
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public String getTitle(Locale locale) {
		return StringPool.BLANK;
	}

	/**
	 * @throws     IOException
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {
	}

}