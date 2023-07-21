/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.browser.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class AssetBrowserDisplayTerms extends DisplayTerms {

	public static final String DESCRIPTION = "description";

	public static final String GROUP_ID = "groupId";

	public static final String TITLE = "title";

	public static final String USER_NAME = "user-name";

	public AssetBrowserDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		groupId = ParamUtil.getLong(portletRequest, GROUP_ID);
		title = ParamUtil.getString(portletRequest, TITLE);
		userName = ParamUtil.getString(portletRequest, USER_NAME);
	}

	public String getDescription() {
		return description;
	}

	public long getGroupId() {
		return groupId;
	}

	public String getTitle() {
		return title;
	}

	public String getUserName() {
		return userName;
	}

	protected String description;
	protected long groupId;
	protected String title;
	protected String userName;

}