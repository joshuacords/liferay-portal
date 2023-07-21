/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;

import java.util.List;

/**
 * @author Chema Balsas
 */
public class SelectTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClaySelect");
		setModuleBaseName("select");

		return super.doStartTag();
	}

	public void setDisabled(Boolean disabled) {
		putValue("disabled", disabled);
	}

	public void setLabel(String label) {
		putValue("label", label);
	}

	public void setMultiple(Boolean multiple) {
		putValue("multiple", multiple);
	}

	public void setName(String name) {
		putValue("name", name);
	}

	public void setOptions(List<SelectOption> selectOptions) {
		putValue("options", selectOptions);
	}

	public void setWrapperType(String wrapperType) {
		putValue("wrapperType", wrapperType);
	}

}