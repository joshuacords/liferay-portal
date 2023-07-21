/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import java.io.Serializable;

import java.util.Locale;

/**
 * @author Lino Alves
 */
public class AssetCategoriesSearchFacetTermDisplayContext
	implements Serializable {

	public long getAssetCategoryId() {
		return _assetCategoryId;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public int getFrequency() {
		return _frequency;
	}

	public Locale getLocale() {
		return _locale;
	}

	public int getPopularity() {
		return _popularity;
	}

	public boolean isFrequencyVisible() {
		return _frequencyVisible;
	}

	public boolean isSelected() {
		return _selected;
	}

	public void setAssetCategoryId(long assetCategoryId) {
		_assetCategoryId = assetCategoryId;
	}

	public void setDisplayName(String title) {
		_displayName = title;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public void setFrequencyVisible(boolean frequencyVisible) {
		_frequencyVisible = frequencyVisible;
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setPopularity(int popularity) {
		_popularity = popularity;
	}

	public void setSelected(boolean selected) {
		_selected = selected;
	}

	private long _assetCategoryId;
	private String _displayName;
	private int _frequency;
	private boolean _frequencyVisible;
	private Locale _locale;
	private int _popularity;
	private boolean _selected;

}