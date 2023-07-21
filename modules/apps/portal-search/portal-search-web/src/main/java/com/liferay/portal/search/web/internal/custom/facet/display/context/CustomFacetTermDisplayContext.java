/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.custom.facet.display.context;

/**
 * @author Wade Cao
 */
public class CustomFacetTermDisplayContext {

	public String getFieldName() {
		return _fieldName;
	}

	public int getFrequency() {
		return _frequency;
	}

	public boolean isFrequencyVisible() {
		return _frequencyVisible;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public void setFrequencyVisible(boolean showFrequency) {
		_frequencyVisible = showFrequency;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	private String _fieldName;
	private int _frequency;
	private boolean _frequencyVisible;
	private boolean _selected;

}