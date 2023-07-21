/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterContextContributorRegistry;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterRegistry;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilterSerializer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = ClayDataSetFilterSerializer.class)
public class ClayDataSetFilterSerializerImpl
	implements ClayDataSetFilterSerializer {

	@Override
	public JSONArray serialize(String dataSetFilterKey, Locale locale) {
		JSONArray jsonArray = _jsonFactory.createJSONArray();

		List<ClayDataSetFilter> clayDataSetFilters =
			_clayDataSetFilterRegistry.getClayDataSetFilters(dataSetFilterKey);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		for (ClayDataSetFilter clayDataSetFilter : clayDataSetFilters) {
			JSONObject jsonObject = _jsonFactory.createJSONObject();

			String label = LanguageUtil.get(
				resourceBundle, clayDataSetFilter.getLabel());

			jsonObject.put("id", clayDataSetFilter.getId());
			jsonObject.put("label", label);
			jsonObject.put("type", clayDataSetFilter.getType());

			List<ClayDataSetFilterContextContributor>
				clayDataSetFilterContextContributors =
					_clayDataSetFilterContextContributorRegistry.
						getClayDataSetFilterContextContributors(
							clayDataSetFilter.getType());

			for (ClayDataSetFilterContextContributor
					clayDataSetFilterContextContributor :
						clayDataSetFilterContextContributors) {

				Map<String, Object> filterContext =
					clayDataSetFilterContextContributor.
						getClayDataSetFilterContext(clayDataSetFilter, locale);

				if (filterContext == null) {
					continue;
				}

				for (Map.Entry<String, Object> filterContextEntry :
						filterContext.entrySet()) {

					jsonObject.put(
						filterContextEntry.getKey(),
						filterContextEntry.getValue());
				}
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Reference
	private ClayDataSetFilterContextContributorRegistry
		_clayDataSetFilterContextContributorRegistry;

	@Reference
	private ClayDataSetFilterRegistry _clayDataSetFilterRegistry;

	@Reference
	private JSONFactory _jsonFactory;

}