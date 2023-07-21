/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class ManagementBarFilterItemTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public String getId() {
		return _id;
	}

	public String getLabel() {
		return _label;
	}

	public String getUrl() {
		return _url;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setLabel(String label) {
		_label = label;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_active = false;
		_id = null;
		_label = null;
		_url = null;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return super.isCleanUpSetAttributes();
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		ManagementBarNavigationTag managementBarNavigationTag =
			(ManagementBarNavigationTag)findAncestorWithClass(
				this, ManagementBarNavigationTag.class);

		List<ManagementBarFilterItem> managementBarFilterItems =
			managementBarNavigationTag.getManagementBarFilterItems();

		if (managementBarFilterItems != null) {
			ManagementBarFilterItem managementBarFilterItem =
				new ManagementBarFilterItem(_active, _id, _label, _url);

			managementBarFilterItems.add(managementBarFilterItem);
		}
	}

	private boolean _active;
	private String _id;
	private String _label;
	private String _url;

}