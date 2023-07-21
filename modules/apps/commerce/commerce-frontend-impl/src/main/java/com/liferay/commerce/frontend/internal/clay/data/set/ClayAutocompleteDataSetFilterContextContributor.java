/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayAutocompleteDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributor;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	property = "commerce.data.set.filter.type=autocomplete",
	service = ClayDataSetFilterContextContributor.class
)
public class ClayAutocompleteDataSetFilterContextContributor
	implements ClayDataSetFilterContextContributor {

	public Map<String, Object> getClayDataSetFilterContext(
		ClayDataSetFilter clayDataSetFilter, Locale locale) {

		if (clayDataSetFilter instanceof ClayAutocompleteDataSetFilter) {
			return _serialize(
				(ClayAutocompleteDataSetFilter)clayDataSetFilter, locale);
		}

		return Collections.emptyMap();
	}

	private Map<String, Object> _serialize(
		ClayAutocompleteDataSetFilter clayAutocompleteDataSetFilter,
		Locale locale) {

		ResourceBundle resourceBundle =
			clayAutocompleteDataSetFilter.getResourceBundle(locale);

		Map<String, Object> context = new HashMap<>();

		context.put("apiUrl", clayAutocompleteDataSetFilter.getApiURL());
		context.put(
			"inputPlaceholder",
			ResourceBundleUtil.getString(
				resourceBundle,
				clayAutocompleteDataSetFilter.getPlaceholder()));
		context.put("itemKey", clayAutocompleteDataSetFilter.getItemKey());
		context.put("itemLabel", clayAutocompleteDataSetFilter.getItemLabel());

		String selectionType = "single";

		if (clayAutocompleteDataSetFilter.isMultipleSelection()) {
			selectionType = "multiple";
		}

		context.put("selectionType", selectionType);

		return context;
	}

}