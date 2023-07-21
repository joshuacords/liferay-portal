/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.browser.web.internal.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class AssetBrowserSearchTerms extends AssetBrowserDisplayTerms {

	public AssetBrowserSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		description = DAOParamUtil.getString(portletRequest, DESCRIPTION);
		groupId = ParamUtil.getLong(portletRequest, GROUP_ID);
		title = DAOParamUtil.getString(portletRequest, TITLE);
		userName = DAOParamUtil.getString(portletRequest, USER_NAME);
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

}