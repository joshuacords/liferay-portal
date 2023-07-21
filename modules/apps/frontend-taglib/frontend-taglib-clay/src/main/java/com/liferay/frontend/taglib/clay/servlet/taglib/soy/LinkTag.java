/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;

/**
 * @author Carlos Lancha
 */
public class LinkTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayLink");
		setHydrate(true);
		setModuleBaseName("link");

		return super.doStartTag();
	}

	public void setAriaLabel(String ariaLabel) {
		putValue("ariaLabel", ariaLabel);
	}

	public void setButtonStyle(String buttonStyle) {
		putValue("buttonStyle", buttonStyle);
	}

	public void setDownload(String download) {
		putValue("download", download);
	}

	public void setHref(String href) {
		putValue("href", href);
	}

	public void setIcon(String icon) {
		putValue("icon", icon);
	}

	public void setIconAlignment(String iconAlignment) {
		putValue("iconAlignment", iconAlignment);
	}

	public void setLabel(String label) {
		putValue("label", label);
	}

	public void setStyle(String style) {
		putValue("style", style);
	}

	public void setTarget(String target) {
		putValue("target", target);
	}

	public void setTitle(String title) {
		putValue("title", title);
	}

}