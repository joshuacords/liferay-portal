/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;

/**
 * @author Chema Balsas
 */
public class ButtonTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayButton");
		setModuleBaseName("button");

		return super.doStartTag();
	}

	public void setAriaLabel(String ariaLabel) {
		putValue("ariaLabel", ariaLabel);
	}

	public void setBlock(Boolean block) {
		putValue("block", block);
	}

	public void setDisabled(Boolean disabled) {
		putValue("disabled", disabled);
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

	public void setMonospaced(Boolean monospaced) {
		putValue("monospaced", monospaced);
	}

	public void setName(String name) {
		putValue("name", name);
	}

	public void setSize(String size) {
		putValue("size", size);
	}

	public void setStyle(Boolean style) {
		putValue("style", style);
	}

	public void setStyle(String style) {
		putValue("style", style);
	}

	public void setTitle(String title) {
		putValue("title", title);
	}

	public void setType(String type) {
		putValue("type", type);
	}

	public void setValue(String value) {
		putValue("value", value);
	}

}