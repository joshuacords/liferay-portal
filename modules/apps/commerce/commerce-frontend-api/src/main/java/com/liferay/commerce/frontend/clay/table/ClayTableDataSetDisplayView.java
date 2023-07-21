/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.table;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetConstants;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Marco Leo
 */
public abstract class ClayTableDataSetDisplayView
	implements ClayDataSetDisplayView {

	public abstract ClayTableSchema getClayTableSchema();

	public String getContentRenderer() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_TABLE;
	}

	public String getLabel() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_TABLE;
	}

	public ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	public String getThumbnail() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_TABLE;
	}

}