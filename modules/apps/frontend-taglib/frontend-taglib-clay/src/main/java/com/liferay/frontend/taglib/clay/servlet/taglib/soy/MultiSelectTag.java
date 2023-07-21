/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;

import java.util.HashMap;
import java.util.List;

/**
 * @author Luismi Barcos
 */
public class MultiSelectTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayMultiSelect");
		setHydrate(true);
		setModuleBaseName("multi-select");

		return super.doStartTag();
	}

	public void setDataSource(Object dataSource) {
		putValue("dataSource", dataSource);
	}

	public void setExtractData(Object extractData) {
		putValue("extractData", extractData);
	}

	public void setHelpText(String helpText) {
		putValue("helpText", helpText);
	}

	public void setInputName(String inputName) {
		putValue("inputName", inputName);
	}

	public void setLabel(String label) {
		putValue("label", label);
	}

	public void setLabelLocator(String labelLocator) {
		putValue("labelLocator", labelLocator);
	}

	public void setRequestOptions(HashMap<String, Object> requestOptions) {
		putValue("requestOptions", requestOptions);
	}

	public void setSelectedItems(List<Object> selectedItems) {
		putValue("selectedItems", selectedItems);
	}

	public void setValueLocator(String valueLocator) {
		putValue("valueLocator", valueLocator);
	}

}