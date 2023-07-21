/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.table.selectable;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetConstants;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.petra.string.StringPool;

import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 */
public abstract class ClaySelectableTableDataSetDisplayView
	implements ClayDataSetDisplayView {

	public String getContentRenderer() {
		return ClayDataSetConstants.
			CLAY_DATA_SET_CONTENT_RENDERER_SELECTABLE_TABLE;
	}

	public abstract String getFirstColumnLabel(Locale locale);

	public abstract String getFirstColumnName();

	public String getLabel() {
		return ClayDataSetConstants.
			CLAY_DATA_SET_CONTENT_RENDERER_SELECTABLE_TABLE;
	}

	public String getThumbnail() {
		return StringPool.BLANK;
	}

}