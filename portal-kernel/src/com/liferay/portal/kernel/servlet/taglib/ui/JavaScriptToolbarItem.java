/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.taglib.ui;

/**
 * @author Iván Zaera
 */
public class JavaScriptToolbarItem
	extends ToolbarItem implements JavaScriptUIItem {

	public String getJavaScript() {
		return _javaScript;
	}

	@Override
	public String getOnClick() {
		return _onClick;
	}

	public void setJavaScript(String javaScript) {
		_javaScript = javaScript;
	}

	@Override
	public void setOnClick(String onClick) {
		_onClick = onClick;
	}

	private String _javaScript;
	private String _onClick;

}