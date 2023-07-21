/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.util.AddMenuKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ambrín Chaudhary
 */
public class AddMenuItemTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public Map<String, Object> getAnchorData() {
		return _anchorData;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public String getId() {
		return _id;
	}

	public String getTitle() {
		return _title;
	}

	public AddMenuKeys.AddMenuType getType() {
		return _addMenuType;
	}

	public String getUrl() {
		return _url;
	}

	public void setAnchorData(Map<String, Object> anchorData) {
		_anchorData = anchorData;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setType(AddMenuKeys.AddMenuType addMenuType) {
		_addMenuType = addMenuType;
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_addMenuType = AddMenuKeys.AddMenuType.DEFAULT;
		_anchorData = null;
		_cssClass = StringPool.BLANK;
		_id = null;
		_title = null;
		_url = null;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		List<AddMenuItem> addMenuItems =
			(List<AddMenuItem>)httpServletRequest.getAttribute(
				"liferay-frontend:add-menu:addMenuItems");

		if (addMenuItems != null) {
			AddMenuItem addMenuItem = new AddMenuItem(
				_anchorData, _cssClass, _id, _title, _addMenuType, _url);

			addMenuItems.add(addMenuItem);
		}
	}

	private AddMenuKeys.AddMenuType _addMenuType =
		AddMenuKeys.AddMenuType.DEFAULT;
	private Map<String, Object> _anchorData;
	private String _cssClass = StringPool.BLANK;
	private String _id;
	private String _title;
	private String _url;

}