/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.taglib.ui;

import com.liferay.petra.lang.HashUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Julio Camarero
 */
public abstract class BaseAssetAddonEntry implements AssetAddonEntry {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetAddonEntry)) {
			return false;
		}

		AssetAddonEntry assetAddonEntry = (AssetAddonEntry)obj;

		String key = assetAddonEntry.getKey();

		if (key.equals(getKey())) {
			return true;
		}

		return false;
	}

	@Override
	public String getIcon() {
		return _DEFAUTL_ICON;
	}

	@Override
	public String getKey() {
		Class<?> clazz = getClass();

		return clazz.getSimpleName();
	}

	@Override
	public Double getWeight() {
		return 10.0;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, getKey());
	}

	/**
	 * @throws IOException
	 */
	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private static final String _DEFAUTL_ICON = "circle-blank";

}