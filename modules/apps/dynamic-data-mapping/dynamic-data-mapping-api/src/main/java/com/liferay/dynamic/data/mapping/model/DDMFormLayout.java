/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class DDMFormLayout implements Serializable {

	public static final String SETTINGS_MODE = "settings";

	public static final String SINGLE_PAGE_MODE = "single-page";

	public static final String TABBED_MODE = "tabbed";

	public static final String WIZARD_MODE = "wizard";

	public DDMFormLayout() {
	}

	public DDMFormLayout(DDMFormLayout ddmFormLayout) {
		_defaultLocale = ddmFormLayout._defaultLocale;
		_paginationMode = ddmFormLayout._paginationMode;

		for (DDMFormLayoutPage ddmFormLayoutPage :
				ddmFormLayout._ddmFormLayoutPages) {

			addDDMFormLayoutPage(new DDMFormLayoutPage(ddmFormLayoutPage));
		}

		for (DDMFormRule ddmFormRule : ddmFormLayout._ddmFormRules) {
			addDDMFormRule(new DDMFormRule(ddmFormRule));
		}
	}

	public void addDDMFormLayoutPage(DDMFormLayoutPage ddmFormLayoutPage) {
		_ddmFormLayoutPages.add(ddmFormLayoutPage);
	}

	public void addDDMFormRule(DDMFormRule ddmFormRule) {
		_ddmFormRules.add(ddmFormRule);
	}

	public Set<Locale> getAvailableLocales() {
		return _availableLocales;
	}

	public DDMFormLayoutPage getDDMFormLayoutPage(int index) {
		return _ddmFormLayoutPages.get(index);
	}

	public List<DDMFormLayoutPage> getDDMFormLayoutPages() {
		return _ddmFormLayoutPages;
	}

	public List<DDMFormRule> getDDMFormRules() {
		return _ddmFormRules;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	public String getPaginationMode() {
		return _paginationMode;
	}

	public void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	public void setDDMFormLayoutPages(
		List<DDMFormLayoutPage> ddmFormLayoutPages) {

		_ddmFormLayoutPages = ddmFormLayoutPages;
	}

	public void setDDMFormRules(List<DDMFormRule> ddmFormRules) {
		_ddmFormRules = ddmFormRules;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;
	}

	public void setPaginationMode(String paginationMode) {
		_paginationMode = paginationMode;
	}

	private Set<Locale> _availableLocales = new LinkedHashSet<>();
	private List<DDMFormLayoutPage> _ddmFormLayoutPages = new ArrayList<>();
	private List<DDMFormRule> _ddmFormRules = new ArrayList<>();
	private Locale _defaultLocale;
	private String _paginationMode;

}