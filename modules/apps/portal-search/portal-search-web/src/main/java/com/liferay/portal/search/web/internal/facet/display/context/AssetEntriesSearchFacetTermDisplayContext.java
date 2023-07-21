/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import java.io.Serializable;

/**
 * @author Lino Alves
 */
public class AssetEntriesSearchFacetTermDisplayContext implements Serializable {

	public String getAssetType() {
		return _assetType;
	}

	public int getFrequency() {
		return _frequency;
	}

	public String getTypeName() {
		return _typeName;
	}

	public boolean isFrequencyVisible() {
		return _frequencyVisible;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setAssetType(String assetType) {
		_assetType = assetType;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public void setFrequencyVisible(boolean frequencyVisible) {
		_frequencyVisible = frequencyVisible;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	public void setTypeName(String typeName) {
		_typeName = typeName;
	}

	private String _assetType;
	private int _frequency;
	private boolean _frequencyVisible;
	private boolean _selected;
	private String _typeName;

}