/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.layoutconfiguration.util.xml;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class RenderURLLogic extends ActionURLLogic {

	public static final String CLOSE_1_TAG = "</runtime-render-url>";

	public static final String CLOSE_2_TAG = "/>";

	public static final String OPEN_TAG = "<runtime-render-url";

	public RenderURLLogic(RenderResponse renderResponse) {
		super(renderResponse);
	}

	@Override
	public String getClose1Tag() {
		return CLOSE_1_TAG;
	}

	@Override
	public String getLifecycle() {
		return _LIFECYCLE;
	}

	@Override
	public String getOpenTag() {
		return OPEN_TAG;
	}

	private static final String _LIFECYCLE = PortletRequest.RENDER_PHASE;

}