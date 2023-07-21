/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.timeline;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetConstants;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;

/**
 * @author Marco Leo
 */
public abstract class ClayTimelineDataSetDisplayView
	implements ClayDataSetDisplayView {

	public String getContentRenderer() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_TIMELINE;
	}

	public abstract String getDate();

	public abstract String getDescription();

	public String getLabel() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_TIMELINE;
	}

	public String getThumbnail() {
		return ClayDataSetConstants.CLAY_DATA_SET_CONTENT_RENDERER_TIMELINE;
	}

	public abstract String getTitle();

}