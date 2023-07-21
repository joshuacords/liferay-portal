/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.table;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetContentRendererContextContributor;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableDataSetDisplayView;
import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	property = "commerce.data.set.content.renderer.name=table",
	service = ClayDataSetContentRendererContextContributor.class
)
public class ClayTableContextContributor
	implements ClayDataSetContentRendererContextContributor {

	public Map<String, Object> getContentRendererContext(
		ClayDataSetDisplayView clayDataSetDisplayView, Locale locale) {

		if (clayDataSetDisplayView instanceof ClayTableDataSetDisplayView) {
			return _serialize(
				(ClayTableDataSetDisplayView)clayDataSetDisplayView, locale);
		}

		return Collections.emptyMap();
	}

	private Map<String, Object> _serialize(
		ClayTableDataSetDisplayView clayTableDataSetDisplayView,
		Locale locale) {

		Map<String, Object> context = new HashMap<>();

		JSONObject schemaJSONObject = _jsonFactory.createJSONObject();

		JSONArray fieldsJSONArray = _jsonFactory.createJSONArray();

		ClayTableSchema clayTableSchema =
			clayTableDataSetDisplayView.getClayTableSchema();

		Map<String, ClayTableSchemaField> fieldsMap =
			clayTableSchema.getFields();

		ResourceBundle resourceBundle =
			clayTableDataSetDisplayView.getResourceBundle(locale);

		for (ClayTableSchemaField clayTableSchemaField : fieldsMap.values()) {
			JSONObject jsonObject = _jsonFactory.createJSONObject();

			String label = LanguageUtil.get(
				resourceBundle, clayTableSchemaField.getLabel());

			if (Validator.isNull(label)) {
				label = StringPool.BLANK;
			}

			String name = clayTableSchemaField.getFieldName();

			jsonObject.put("actionId", clayTableSchemaField.getActionId());
			jsonObject.put(
				"contentRenderer", clayTableSchemaField.getContentRenderer());
			jsonObject.put(
				"contentRendererModuleUrl",
				clayTableSchemaField.getContentRendererModuleUrl());
			jsonObject.put("expand", clayTableSchemaField.isExpand());

			if (name.contains(StringPool.PERIOD)) {
				jsonObject.put(
					"fieldName", StringUtil.split(name, StringPool.PERIOD));
			}
			else {
				jsonObject.put("fieldName", name);
			}

			jsonObject.put("label", label);
			jsonObject.put("sortable", clayTableSchemaField.isSortable());

			ClayTableSchemaField.SortingOrder sortingOrder =
				clayTableSchemaField.getSortingOrder();

			if (sortingOrder != null) {
				jsonObject.put(
					"sortingOrder",
					StringUtil.toLowerCase(sortingOrder.toString()));
			}

			fieldsJSONArray.put(jsonObject);
		}

		schemaJSONObject.put("fields", fieldsJSONArray);

		context.put("schema", schemaJSONObject);

		return context;
	}

	@Reference
	private JSONFactory _jsonFactory;

}