/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.field.type.v1_0;

import com.liferay.data.engine.field.type.BaseFieldType;
import com.liferay.data.engine.field.type.FieldType;
import com.liferay.data.engine.rest.internal.field.type.v1_0.util.CustomPropertiesUtil;
import com.liferay.data.engine.spi.dto.SPIDataDefinitionField;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gabriel Albuquerque
 */
@Component(
	immediate = true,
	property = {
		"data.engine.field.type.icon=icon-font",
		"data.engine.field.type.js.module=dynamic-data-mapping-form-field-type/Validation/Validation.es",
		"data.engine.field.type.system=true"
	},
	service = FieldType.class
)
public class ValidationFieldType extends BaseFieldType {

	@Override
	public String getName() {
		return "validation";
	}

	@Override
	protected void includeContext(
		Map<String, Object> context, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		SPIDataDefinitionField spiDataDefinitionField) {

		context.put("value", _getValue(spiDataDefinitionField));
	}

	private Map<String, String> _getValue(
		SPIDataDefinitionField spiDataDefinitionField) {

		Map<String, String> value = new HashMap<>();

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				CustomPropertiesUtil.getString(
					spiDataDefinitionField.getCustomProperties(), "value"));

			value.put("errorMessage", jsonObject.getString("errorMessage"));
			value.put("expression", jsonObject.getString("expression"));
		}
		catch (JSONException jsonException) {
			if (_log.isWarnEnabled()) {
				_log.warn(jsonException, jsonException);
			}

			value.put("errorMessage", StringPool.BLANK);
			value.put("expression", StringPool.BLANK);
		}

		return value;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ValidationFieldType.class);

}