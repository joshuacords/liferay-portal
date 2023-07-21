/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.timeline;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.timeline.ClayTimelineDataSetDisplayView;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

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
	property = "commerce.data.set.content.renderer.name=timeline",
	service = ClayDataSetContentRendererContextContributor.class
)
public class ClayTimelineContextContributor
	implements ClayDataSetContentRendererContextContributor {

	public Map<String, Object> getContentRendererContext(
		ClayDataSetDisplayView clayDataSetDisplayView, Locale locale) {

		if (clayDataSetDisplayView instanceof ClayTimelineDataSetDisplayView) {
			return _serialize(
				(ClayTimelineDataSetDisplayView)clayDataSetDisplayView);
		}

		return Collections.emptyMap();
	}

	private Map<String, Object> _serialize(
		ClayTimelineDataSetDisplayView clayTimelineDataSetDisplayView) {

		Map<String, Object> context = new HashMap<>();

		JSONObject schemaJSONObject = _jsonFactory.createJSONObject();

		schemaJSONObject.put("date", clayTimelineDataSetDisplayView.getDate());
		schemaJSONObject.put(
			"description", clayTimelineDataSetDisplayView.getDescription());
		schemaJSONObject.put(
			"title", clayTimelineDataSetDisplayView.getTitle());

		context.put("schema", schemaJSONObject);

		return context;
	}

	@Reference
	private JSONFactory _jsonFactory;

}