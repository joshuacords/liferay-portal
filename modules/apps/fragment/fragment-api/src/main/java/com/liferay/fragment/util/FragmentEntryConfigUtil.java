/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.fragment.util.configuration.FragmentConfigurationField;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Rubén Pulido
 */
public class FragmentEntryConfigUtil {

	public static JSONObject getConfigurationDefaultValuesJSONObject(
		String configuration) {

		List<FragmentConfigurationField> fragmentConfigurationFields =
			getFragmentConfigurationFields(configuration);

		JSONObject defaultValuesJSONObject = JSONFactoryUtil.createJSONObject();

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			defaultValuesJSONObject.put(
				fragmentConfigurationField.getName(),
				getFieldValue(fragmentConfigurationField, null));
		}

		return defaultValuesJSONObject;
	}

	public static JSONObject getConfigurationJSONObject(
			String configuration, String editableValues,
			long[] segmentsExperienceIds)
		throws JSONException {

		JSONObject configurationDefaultValuesJSONObject =
			getConfigurationDefaultValuesJSONObject(configuration);

		if (configurationDefaultValuesJSONObject == null) {
			return JSONFactoryUtil.createJSONObject();
		}

		JSONObject editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
			editableValues);

		JSONObject configurationValuesJSONObject =
			editableValuesJSONObject.getJSONObject(
				_KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

		if (configurationValuesJSONObject == null) {
			return configurationDefaultValuesJSONObject;
		}

		JSONObject configurationJSONObject = configurationValuesJSONObject;

		if (isPersonalizationSupported(configurationValuesJSONObject)) {
			configurationJSONObject = getSegmentedConfigurationValues(
				segmentsExperienceIds, configurationValuesJSONObject);
		}

		List<FragmentConfigurationField> configurationFields =
			getFragmentConfigurationFields(configuration);

		for (FragmentConfigurationField configurationField :
				configurationFields) {

			String name = configurationField.getName();

			Object object = configurationJSONObject.get(name);

			if (Validator.isNull(object)) {
				continue;
			}

			configurationDefaultValuesJSONObject.put(
				name,
				getFieldValue(
					configurationField,
					configurationJSONObject.getString(name)));
		}

		return configurationDefaultValuesJSONObject;
	}

	public static Map<String, Object> getContextObjects(
		JSONObject configurationValuesJSONObject, String configuration) {

		HashMap<String, Object> contextObjects = new HashMap<>();

		List<FragmentConfigurationField> fragmentConfigurationFields =
			getFragmentConfigurationFields(configuration);

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			String name = fragmentConfigurationField.getName();

			Object contextObject = _getContextObject(
				fragmentConfigurationField.getType(),
				configurationValuesJSONObject.getString(name));

			if (contextObject != null) {
				contextObjects.put(
					name + _CONTEXT_OBJECT_SUFFIX, contextObject);
			}
		}

		return contextObjects;
	}

	public static Object getFieldValue(
		FragmentConfigurationField fragmentConfigurationField, String value) {

		value = GetterUtil.getString(
			value, fragmentConfigurationField.getDefaultValue());

		if (StringUtil.equalsIgnoreCase(
				fragmentConfigurationField.getType(), "checkbox")) {

			return _getFieldValue("bool", value);
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "colorPalette")) {

			return _getFieldValue("object", value);
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "itemSelector")) {

			return _getAssetEntryJSONObject(value);
		}
		else if (StringUtil.equalsIgnoreCase(
					fragmentConfigurationField.getType(), "select") ||
				 StringUtil.equalsIgnoreCase(
					 fragmentConfigurationField.getType(), "text")) {

			String dataType = fragmentConfigurationField.getDataType();

			if (Validator.isNull(dataType)) {
				dataType = "string";
			}

			return _getFieldValue(dataType, value);
		}

		return _getFieldValue("string", value);
	}

	public static Object getFieldValue(
		String configuration, String editableValues,
		long[] segmentsExperienceIds, String name) {

		JSONObject editableValuesJSONObject = null;

		try {
			editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
				editableValues);
		}
		catch (Exception exception) {
			return null;
		}

		JSONObject configurationValuesJSONObject =
			editableValuesJSONObject.getJSONObject(
				_KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR);

		if (configurationValuesJSONObject == null) {
			return null;
		}

		List<FragmentConfigurationField> fragmentConfigurationFields =
			getFragmentConfigurationFields(configuration);

		for (FragmentConfigurationField fragmentConfigurationField :
				fragmentConfigurationFields) {

			if (!Objects.equals(fragmentConfigurationField.getName(), name)) {
				continue;
			}

			if (isPersonalizationSupported(configurationValuesJSONObject)) {
				JSONObject configurationJSONObject =
					getSegmentedConfigurationValues(
						segmentsExperienceIds, configurationValuesJSONObject);

				return configurationJSONObject.get(name);
			}

			return configurationValuesJSONObject.get(name);
		}

		return null;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #getFieldValue(FragmentConfigurationField, String)}
	 */
	@Deprecated
	public static Object getFieldValue(
		String configuration, String fieldName, String value) {

		throw new UnsupportedOperationException();
	}

	public static List<FragmentConfigurationField>
		getFragmentConfigurationFields(String configuration) {

		JSONArray fieldSetsJSONArray = _getFieldSetsJSONArray(configuration);

		if (fieldSetsJSONArray == null) {
			return Collections.emptyList();
		}

		List<FragmentConfigurationField> fragmentConfigurationFields =
			new ArrayList<>();

		Iterator<JSONObject> iteratorFieldSet = fieldSetsJSONArray.iterator();

		iteratorFieldSet.forEachRemaining(
			fieldSetJSONObject -> {
				JSONArray fieldSetFieldsJSONArray =
					fieldSetJSONObject.getJSONArray("fields");

				Iterator<JSONObject> iteratorFieldSetFields =
					fieldSetFieldsJSONArray.iterator();

				iteratorFieldSetFields.forEachRemaining(
					fieldSetFieldsJSONObject -> fragmentConfigurationFields.add(
						new FragmentConfigurationField(
							fieldSetFieldsJSONObject)));
			});

		return fragmentConfigurationFields;
	}

	public static JSONObject getSegmentedConfigurationValues(
		long[] segmentsExperienceIds,
		JSONObject configurationValuesJSONObject) {

		long segmentsExperienceId = SegmentsExperienceConstants.ID_DEFAULT;

		if (segmentsExperienceIds.length > 0) {
			segmentsExperienceId = segmentsExperienceIds[0];
		}

		JSONObject configurationJSONObject =
			configurationValuesJSONObject.getJSONObject(
				SegmentsExperienceConstants.ID_PREFIX + segmentsExperienceId);

		if (configurationJSONObject == null) {
			configurationJSONObject = JSONFactoryUtil.createJSONObject();
		}

		return configurationJSONObject;
	}

	public static boolean isPersonalizationSupported(JSONObject jsonObject) {
		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (key.startsWith(SegmentsExperienceConstants.ID_PREFIX)) {
				return true;
			}
		}

		return false;
	}

	public static String translateConfiguration(
		JSONObject jsonObject, ResourceBundle resourceBundle) {

		JSONArray fieldSetsJSONArray = jsonObject.getJSONArray("fieldSets");

		if (fieldSetsJSONArray == null) {
			return StringPool.BLANK;
		}

		Iterator<JSONObject> iterator = fieldSetsJSONArray.iterator();

		iterator.forEachRemaining(
			fieldSetJSONObject -> {
				String fieldSetLabel = fieldSetJSONObject.getString("label");

				fieldSetJSONObject.put(
					"label",
					LanguageUtil.get(
						resourceBundle, fieldSetLabel, fieldSetLabel));

				JSONArray fieldsJSONArray = fieldSetJSONObject.getJSONArray(
					"fields");

				Iterator<JSONObject> fieldsIterator =
					fieldsJSONArray.iterator();

				fieldsIterator.forEachRemaining(
					fieldJSONObject -> _translateConfigurationField(
						fieldJSONObject, resourceBundle));
			});

		return jsonObject.toString();
	}

	private static Object _getAssetEntry(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(value);

			String className = GetterUtil.getString(
				jsonObject.getString("className"));
			long classPK = GetterUtil.getLong(jsonObject.getString("classPK"));

			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
				className, classPK);

			if (assetEntry != null) {
				AssetRenderer<?> assetRenderer = assetEntry.getAssetRenderer();

				return assetRenderer.getAssetObject();
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get asset entry: " + value, exception);
			}
		}

		return null;
	}

	private static JSONObject _getAssetEntryJSONObject(String value) {
		if (Validator.isNull(value)) {
			return JSONFactoryUtil.createJSONObject();
		}

		try {
			JSONObject configurationValueJSONObject =
				JSONFactoryUtil.createJSONObject(value);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(_getAssetEntry(value)));

			jsonObject.put(
				"className",
				GetterUtil.getString(
					configurationValueJSONObject.getString("className"))
			).put(
				"classNameId",
				GetterUtil.getString(
					configurationValueJSONObject.getString("classNameId"))
			).put(
				"classPK",
				GetterUtil.getLong(
					configurationValueJSONObject.getString("classPK"))
			).put(
				"template",
				GetterUtil.getLong(
					configurationValueJSONObject.getString("template"))
			);

			return jsonObject;
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to serialize asset entry to JSON: " + value,
					jsonException);
			}
		}

		return null;
	}

	private static Object _getContextObject(String type, String value) {
		if (StringUtil.equalsIgnoreCase(type, "itemSelector")) {
			return _getAssetEntry(value);
		}

		return null;
	}

	private static JSONArray _getFieldSetsJSONArray(String configuration) {
		try {
			JSONObject configurationJSONObject =
				JSONFactoryUtil.createJSONObject(configuration);

			return configurationJSONObject.getJSONArray("fieldSets");
		}
		catch (JSONException jsonException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse configuration JSON: " + configuration,
					jsonException);
			}
		}

		return null;
	}

	private static Object _getFieldValue(String dataType, String value) {
		if (StringUtil.equalsIgnoreCase(dataType, "bool")) {
			return GetterUtil.getBoolean(value);
		}
		else if (StringUtil.equalsIgnoreCase(dataType, "double")) {
			return GetterUtil.getDouble(value);
		}
		else if (StringUtil.equalsIgnoreCase(dataType, "int")) {
			return GetterUtil.getInteger(value);
		}
		else if (StringUtil.equalsIgnoreCase(dataType, "object")) {
			try {
				return JSONFactoryUtil.createJSONObject(value);
			}
			catch (JSONException jsonException) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Unable to parse configuration JSON: " + value,
						jsonException);
				}
			}
		}
		else if (StringUtil.equalsIgnoreCase(dataType, "string")) {
			return value;
		}

		return null;
	}

	private static void _translateConfigurationField(
		JSONObject fieldJSONObject, ResourceBundle resourceBundle) {

		String fieldDescription = fieldJSONObject.getString("description");

		fieldJSONObject.put(
			"description",
			LanguageUtil.get(
				resourceBundle, fieldDescription, fieldDescription));

		String fieldLabel = fieldJSONObject.getString("label");

		fieldJSONObject.put(
			"label", LanguageUtil.get(resourceBundle, fieldLabel, fieldLabel));

		String type = fieldJSONObject.getString("type");

		if (!Objects.equals(type, "select")) {
			return;
		}

		JSONObject typeOptionsJSONObject = fieldJSONObject.getJSONObject(
			"typeOptions");

		JSONArray validValuesJSONArray = typeOptionsJSONObject.getJSONArray(
			"validValues");

		Iterator<JSONObject> validValuesIterator =
			validValuesJSONArray.iterator();

		validValuesIterator.forEachRemaining(
			validValueJSONObject -> {
				String value = validValueJSONObject.getString("value");

				String label = validValueJSONObject.getString("label", value);

				validValueJSONObject.put(
					"label", LanguageUtil.get(resourceBundle, label, label));
			});
	}

	private static final String _CONTEXT_OBJECT_SUFFIX = "Object";

	private static final String _KEY_FREEMARKER_FRAGMENT_ENTRY_PROCESSOR =
		"com.liferay.fragment.entry.processor.freemarker." +
			"FreeMarkerFragmentEntryProcessor";

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryConfigUtil.class);

}