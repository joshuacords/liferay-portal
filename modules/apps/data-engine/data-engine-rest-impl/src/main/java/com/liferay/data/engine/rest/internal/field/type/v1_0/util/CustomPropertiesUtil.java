/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.field.type.v1_0.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Marcela Cunha
 */
public class CustomPropertiesUtil {

	public static <K, V> Map<K, V> getMap(
		Map<String, Object> customProperties, String key) {

		if (MapUtil.isEmpty(customProperties)) {
			return Collections.emptyMap();
		}

		return (Map<K, V>)GetterUtil.getObject(
			customProperties.get(key), Collections.emptyMap());
	}

	public static String getString(
		Map<String, Object> customProperties, String key) {

		return getString(customProperties, key, StringPool.BLANK);
	}

	public static String getString(
		Map<String, Object> customProperties, String key, String defaultValue) {

		if (MapUtil.isEmpty(customProperties)) {
			return defaultValue;
		}

		return GetterUtil.getString(customProperties.get(key), defaultValue);
	}

	public static <T> T getValue(
		Map<String, Object> customProperties, String key) {

		return (T)GetterUtil.getObject(
			customProperties.get(key), Collections.emptyMap());
	}

	public static List<String> getValues(
		Map<String, Object> customProperties, String key) {

		String json = getString(customProperties, key, "[]");

		JSONArray jsonArray = null;

		try {
			jsonArray = JSONFactoryUtil.createJSONArray(json);
		}
		catch (JSONException jsonException) {
			jsonArray = JSONFactoryUtil.createJSONArray();
		}

		return JSONUtil.toStringList(jsonArray);
	}

	public static JSONObject toJSONObject(Map<String, String> values) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (values.isEmpty()) {
			return jsonObject;
		}

		for (Map.Entry<String, String> entry : values.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}

		return jsonObject;
	}

	public static Map<String, String> toMap(JSONObject jsonObject) {
		Map<String, String> values = new HashMap<>();

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			values.put(key, jsonObject.getString(key));
		}

		return values;
	}

}