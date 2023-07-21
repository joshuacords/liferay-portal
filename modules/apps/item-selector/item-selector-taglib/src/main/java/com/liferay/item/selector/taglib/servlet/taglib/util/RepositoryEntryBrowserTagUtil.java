/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.taglib.servlet.taglib.util;

import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class RepositoryEntryBrowserTagUtil {

	public static String getOrderByCol(
		HttpServletRequest httpServletRequest,
		PortalPreferences portalPreferences) {

		String orderByCol = ParamUtil.getString(
			httpServletRequest, "orderByCol");

		if (Validator.isNotNull(orderByCol)) {
			portalPreferences.setValue(
				_TAGLIB_UI_REPOSITORY_ENTRY_BROWSER_PAGE_NAMESPACE,
				"order-by-col", orderByCol);
		}
		else {
			orderByCol = portalPreferences.getValue(
				_TAGLIB_UI_REPOSITORY_ENTRY_BROWSER_PAGE_NAMESPACE,
				"order-by-col", "title");
		}

		return orderByCol;
	}

	public static String getOrderByType(
		HttpServletRequest httpServletRequest,
		PortalPreferences portalPreferences) {

		String orderByType = ParamUtil.getString(
			httpServletRequest, "orderByType");

		if (Validator.isNotNull(orderByType)) {
			portalPreferences.setValue(
				_TAGLIB_UI_REPOSITORY_ENTRY_BROWSER_PAGE_NAMESPACE,
				"order-by-type", orderByType);
		}
		else {
			orderByType = portalPreferences.getValue(
				_TAGLIB_UI_REPOSITORY_ENTRY_BROWSER_PAGE_NAMESPACE,
				"order-by-type", "asc");
		}

		return orderByType;
	}

	private static final String
		_TAGLIB_UI_REPOSITORY_ENTRY_BROWSER_PAGE_NAMESPACE =
			"taglib_ui_repository_entry_browse_page";

}