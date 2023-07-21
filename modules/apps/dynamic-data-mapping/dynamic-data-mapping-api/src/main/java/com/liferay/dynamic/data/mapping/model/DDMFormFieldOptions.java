/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pablo Carvalho
 */
public class DDMFormFieldOptions implements Serializable {

	public DDMFormFieldOptions() {
		_defaultLocale = LocaleUtil.getDefault();
	}

	public DDMFormFieldOptions(DDMFormFieldOptions ddmFormFieldOptions) {
		_defaultLocale = ddmFormFieldOptions._defaultLocale;

		Map<String, LocalizedValue> options = ddmFormFieldOptions._options;

		for (Map.Entry<String, LocalizedValue> entry : options.entrySet()) {
			LocalizedValue localizedValue = entry.getValue();

			for (Locale locale : localizedValue.getAvailableLocales()) {
				addOptionLabel(
					entry.getKey(), locale, localizedValue.getString(locale));
			}
		}
	}

	public void addOption(String value) {
		_options.put(value, new LocalizedValue(_defaultLocale));
	}

	public void addOptionLabel(
		String optionValue, Locale locale, String label) {

		LocalizedValue labels = _options.get(optionValue);

		if (labels == null) {
			labels = new LocalizedValue(_defaultLocale);

			_options.put(optionValue, labels);
		}

		labels.addString(locale, label);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormFieldOptions)) {
			return false;
		}

		DDMFormFieldOptions ddmFormFieldOptions = (DDMFormFieldOptions)obj;

		if (Objects.equals(
				_defaultLocale, ddmFormFieldOptions._defaultLocale) &&
			Objects.equals(_options, ddmFormFieldOptions._options)) {

			return true;
		}

		return false;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	public LocalizedValue getOptionLabels(String optionValue) {
		return _options.get(optionValue);
	}

	public Map<String, LocalizedValue> getOptions() {
		return _options;
	}

	public Set<String> getOptionsValues() {
		return _options.keySet();
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _defaultLocale);

		return HashUtil.hash(hash, _options);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;

		for (LocalizedValue localizedValue : _options.values()) {
			localizedValue.setDefaultLocale(defaultLocale);
		}
	}

	private Locale _defaultLocale;
	private final Map<String, LocalizedValue> _options = new LinkedHashMap<>();

}