/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.util.AddMenuKeys;

import java.util.Map;

/**
 * @author Ambrín Chaudhary
 */
public class AddMenuItem extends MenuItem {

	public AddMenuItem(
		Map<String, Object> anchorData, String id, String label,
		AddMenuKeys.AddMenuType addMenuType, String url) {

		super(anchorData, id, label, url);

		_addMenuType = addMenuType;
	}

	public AddMenuItem(
		Map<String, Object> anchorData, String id, String label, String url) {

		super(anchorData, id, label, url);

		_addMenuType = AddMenuKeys.AddMenuType.DEFAULT;
	}

	public AddMenuItem(
		Map<String, Object> anchorData, String cssClass, String id,
		String label, AddMenuKeys.AddMenuType addMenuType, String url) {

		super(anchorData, cssClass, id, label, url);

		_addMenuType = addMenuType;
	}

	public AddMenuItem(
		Map<String, Object> anchorData, String cssClass, String id,
		String label, String url) {

		super(anchorData, cssClass, id, label, url);

		_addMenuType = AddMenuKeys.AddMenuType.DEFAULT;
	}

	public AddMenuItem(String label, String url) {
		super(label, url);

		_addMenuType = AddMenuKeys.AddMenuType.DEFAULT;
	}

	public AddMenuItem(String id, String label, String url) {
		super(id, label, url);

		_addMenuType = AddMenuKeys.AddMenuType.DEFAULT;
	}

	public AddMenuItem(String cssClass, String id, String label, String url) {
		super(cssClass, id, label, url);

		_addMenuType = AddMenuKeys.AddMenuType.DEFAULT;
	}

	public AddMenuKeys.AddMenuType getType() {
		return _addMenuType;
	}

	public void setType(AddMenuKeys.AddMenuType addMenuType) {
		_addMenuType = addMenuType;
	}

	private AddMenuKeys.AddMenuType _addMenuType;

}