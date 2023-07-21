/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;

/**
 * @author Chema Balsas
 */
public class CheckboxTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayCheckbox");
		setModuleBaseName("checkbox");

		return super.doStartTag();
	}

	public void setChecked(Boolean checked) {
		putValue("checked", checked);
	}

	public void setDisabled(Boolean disabled) {
		putValue("disabled", disabled);
	}

	public void setIndeterminate(Boolean indeterminate) {
		putValue("indeterminate", indeterminate);
	}

	public void setInline(Boolean inline) {
		putValue("inline", inline);
	}

	public void setLabel(String label) {
		putValue("label", label);
	}

	public void setName(String name) {
		putValue("name", name);
	}

	public void setShowLabel(Boolean showLabel) {
		putValue("showLabel", showLabel);
	}

	public void setValue(String value) {
		putValue("value", value);
	}

}