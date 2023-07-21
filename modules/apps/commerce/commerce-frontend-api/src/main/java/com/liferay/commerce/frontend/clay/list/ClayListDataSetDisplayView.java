/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.list;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetConstants;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.petra.string.StringPool;

/**
 * @author Alessio Antonio Rendina
 */
public abstract class ClayListDataSetDisplayView
	implements ClayDataSetDisplayView {

	public String getContentRenderer() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_LIST;
	}

	public abstract String getDescription();

	public String getLabel() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_LIST;
	}

	public String getThumbnail() {
		return StringPool.BLANK;
	}

	public abstract String getTitle();

}