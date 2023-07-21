/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.list;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.list.ClayListDataSetDisplayView;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	property = "commerce.data.set.content.renderer.name=list",
	service = ClayDataSetContentRendererContextContributor.class
)
public class ClayListContextContributor
	implements ClayDataSetContentRendererContextContributor {

	public Map<String, Object> getContentRendererContext(
		ClayDataSetDisplayView clayDataSetDisplayView, Locale locale) {

		if (clayDataSetDisplayView instanceof ClayListDataSetDisplayView) {
			return _serialize(
				(ClayListDataSetDisplayView)clayDataSetDisplayView);
		}

		return Collections.emptyMap();
	}

	private Map<String, Object> _serialize(
		ClayListDataSetDisplayView clayListDataSetDisplayView) {

		Map<String, Object> context = new HashMap<>();

		JSONObject schemaJSONObject = _jsonFactory.createJSONObject();

		schemaJSONObject.put(
			"description", clayListDataSetDisplayView.getDescription());
		schemaJSONObject.put(
			"thumbnail", clayListDataSetDisplayView.getThumbnail());

		String title = clayListDataSetDisplayView.getTitle();

		if (title.contains(StringPool.PERIOD)) {
			schemaJSONObject.put(
				"title", StringUtil.split(title, StringPool.PERIOD));
		}
		else {
			schemaJSONObject.put("title", title);
		}

		schemaJSONObject.put("title", title);

		context.put("schema", schemaJSONObject);

		return context;
	}

	@Reference
	private JSONFactory _jsonFactory;

}