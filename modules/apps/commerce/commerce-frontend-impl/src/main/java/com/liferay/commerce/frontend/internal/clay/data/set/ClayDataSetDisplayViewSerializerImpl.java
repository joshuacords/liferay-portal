/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.data.set;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributorRegistry;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayViewRegistry;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayViewSerializer;
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
@Component(service = ClayDataSetDisplayViewSerializer.class)
public class ClayDataSetDisplayViewSerializerImpl
	implements ClayDataSetDisplayViewSerializer {

	@Override
	public JSONArray serialize(String dataSetDisplayViewKey, Locale locale) {
		JSONArray jsonArray = _jsonFactory.createJSONArray();

		List<ClayDataSetDisplayView> clayDataSetDisplayViews =
			_clayDataSetDisplayViewRegistry.getClayDataSetDisplayViews(
				dataSetDisplayViewKey);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		for (ClayDataSetDisplayView clayDataSetDisplayView :
				clayDataSetDisplayViews) {

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			String label = LanguageUtil.get(
				resourceBundle, clayDataSetDisplayView.getLabel());

			jsonObject.put(
				"contentRenderer", clayDataSetDisplayView.getContentRenderer());
			jsonObject.put(
				"contentRendererModuleUrl",
				clayDataSetDisplayView.getContentRendererModuleUrl());
			jsonObject.put("label", label);

			List<ClayDataSetContentRendererContextContributor>
				clayDataSetContentRendererContextContributors =
					_clayDataSetContentRendererContextContributorRegistry.
						getClayDataSetContentRendererContextContributors(
							clayDataSetDisplayView.getContentRenderer());

			for (ClayDataSetContentRendererContextContributor
					clayDataSetContentRendererContextContributor :
						clayDataSetContentRendererContextContributors) {

				Map<String, Object> contentRendererContext =
					clayDataSetContentRendererContextContributor.
						getContentRendererContext(
							clayDataSetDisplayView, locale);

				if (contentRendererContext == null) {
					continue;
				}

				for (Map.Entry<String, Object> contentRendererContextEntry :
						contentRendererContext.entrySet()) {

					jsonObject.put(
						contentRendererContextEntry.getKey(),
						contentRendererContextEntry.getValue());
				}
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Reference
	private ClayDataSetContentRendererContextContributorRegistry
		_clayDataSetContentRendererContextContributorRegistry;

	@Reference
	private ClayDataSetDisplayViewRegistry _clayDataSetDisplayViewRegistry;

	@Reference
	private JSONFactory _jsonFactory;

}