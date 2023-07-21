/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.taglib.ui;

/**
 * @author Iván Zaera
 */
public class URLToolbarItem extends ToolbarItem implements URLUIItem {

	@Override
	public String getTarget() {
		return _target;
	}

	@Override
	public String getURL() {
		return _url;
	}

	@Override
	public void setTarget(String target) {
		_target = target;
	}

	@Override
	public void setURL(String url) {
		_url = url;
	}

	private static final String _TARGET_DEFAULT = "_self";

	private String _target = _TARGET_DEFAULT;
	private String _url;

}