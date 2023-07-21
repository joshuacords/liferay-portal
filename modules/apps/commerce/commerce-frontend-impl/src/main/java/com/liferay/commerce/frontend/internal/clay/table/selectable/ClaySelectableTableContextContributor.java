/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.table.selectable;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.selectable.ClaySelectableTableDataSetDisplayView;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "commerce.data.set.content.renderer.name=selectableTable",
	service = ClayDataSetContentRendererContextContributor.class
)
public class ClaySelectableTableContextContributor
	implements ClayDataSetContentRendererContextContributor {

	public Map<String, Object> getContentRendererContext(
		ClayDataSetDisplayView clayDataSetDisplayView, Locale locale) {

		if (clayDataSetDisplayView instanceof
				ClaySelectableTableDataSetDisplayView) {

			return _serialize(
				(ClaySelectableTableDataSetDisplayView)clayDataSetDisplayView,
				locale);
		}

		return Collections.emptyMap();
	}

	private Map<String, Object> _serialize(
		ClaySelectableTableDataSetDisplayView
			claySelectableTableDataSetDisplayView,
		Locale locale) {

		Map<String, Object> context = new HashMap<>();

		JSONObject schemaJSONObject = _jsonFactory.createJSONObject();

		schemaJSONObject.put(
			"firstColumnLabel",
			claySelectableTableDataSetDisplayView.getFirstColumnLabel(locale));
		schemaJSONObject.put(
			"firstColumnName",
			claySelectableTableDataSetDisplayView.getFirstColumnName());

		context.put("schema", schemaJSONObject);

		return context;
	}

	@Reference
	private JSONFactory _jsonFactory;

}