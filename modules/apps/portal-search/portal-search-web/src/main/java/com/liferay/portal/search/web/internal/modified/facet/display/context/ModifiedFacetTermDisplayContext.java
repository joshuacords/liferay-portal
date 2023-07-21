/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.modified.facet.display.context;

import java.io.Serializable;

/**
 * @author Lino Alves
 */
public class ModifiedFacetTermDisplayContext implements Serializable {

	public int getFrequency() {
		return _frequency;
	}

	public String getLabel() {
		return _label;
	}

	public String getRange() {
		return _range;
	}

	public String getRangeURL() {
		return _rangeURL;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setRange(String range) {
		_range = range;
	}

	public void setRangeURL(String rangeURL) {
		_rangeURL = rangeURL;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	private int _frequency;
	private String _label;
	private String _range;
	private String _rangeURL;
	private boolean _selected;

}