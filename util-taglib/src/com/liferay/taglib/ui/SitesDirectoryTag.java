/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class SitesDirectoryTag extends IncludeTag {

	public static final String SITES_CHILDREN = "children";

	public static final String SITES_PARENT_LEVEL = "parent-level";

	public static final String SITES_SIBLINGS = "siblings";

	public static final String SITES_TOP_LEVEL = "top-level";

	public String getDisplayStyle() {
		return _displayStyle;
	}

	public String getSites() {
		return _sites;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setSites(String sites) {
		_sites = sites;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_displayStyle = "descriptive";
		_sites = SITES_TOP_LEVEL;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-ui:sites-directory:displayStyle", _displayStyle);
		httpServletRequest.setAttribute(
			"liferay-ui:sites-directory:sites", String.valueOf(_sites));
	}

	private static final String _PAGE =
		"/html/taglib/ui/sites_directory/page.jsp";

	private String _displayStyle = "descriptive";
	private String _sites = SITES_TOP_LEVEL;

}