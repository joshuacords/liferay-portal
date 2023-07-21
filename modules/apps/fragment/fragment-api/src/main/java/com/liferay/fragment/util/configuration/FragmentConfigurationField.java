/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.util.configuration;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

/**
 * @author Víctor Galán
 */
public class FragmentConfigurationField {

	public FragmentConfigurationField(JSONObject fieldJSONObject) {
		_name = fieldJSONObject.getString("name");
		_dataType = fieldJSONObject.getString("dataType");
		_defaultValue = fieldJSONObject.getString("defaultValue");
		_type = fieldJSONObject.getString("type");
	}

	public FragmentConfigurationField(
		String name, String dataType, String defaultValue, String type) {

		_name = name;
		_dataType = dataType;
		_defaultValue = defaultValue;
		_type = type;
	}

	public String getDataType() {
		return _dataType;
	}

	public String getDefaultValue() {
		if (Validator.isNotNull(_defaultValue)) {
			return _defaultValue;
		}

		if (Objects.equals(_type, "colorPalette")) {
			return _getColorPaletteDefaultValue();
		}

		return StringPool.BLANK;
	}

	public String getName() {
		return _name;
	}

	public String getType() {
		return _type;
	}

	private String _getColorPaletteDefaultValue() {
		JSONObject defaultValueJSONObject = JSONUtil.put(
			"cssClass", StringPool.BLANK
		).put(
			"rgbValue", StringPool.BLANK
		);

		return defaultValueJSONObject.toString();
	}

	private final String _dataType;
	private final String _defaultValue;
	private final String _name;
	private final String _type;

}