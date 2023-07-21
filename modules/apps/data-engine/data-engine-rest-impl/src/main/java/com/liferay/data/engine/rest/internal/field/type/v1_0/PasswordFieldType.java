/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.field.type.v1_0;

import com.liferay.data.engine.field.type.BaseFieldType;
import com.liferay.data.engine.field.type.FieldType;
import com.liferay.data.engine.field.type.FieldTypeTracker;
import com.liferay.data.engine.field.type.util.LocalizedValueUtil;
import com.liferay.data.engine.rest.internal.field.type.v1_0.util.CustomPropertiesUtil;
import com.liferay.data.engine.spi.dto.SPIDataDefinitionField;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jeyvison Nascimento
 */
@Component(
	immediate = true,
	property = {
		"data.engine.field.type.display.order:Integer=9",
		"data.engine.field.type.icon=password-policies",
		"data.engine.field.type.js.module=dynamic-data-mapping-form-field-type/Password/Password.es",
		"data.engine.field.type.system=true"
	},
	service = FieldType.class
)
public class PasswordFieldType extends BaseFieldType {

	@Override
	public SPIDataDefinitionField deserialize(
			FieldTypeTracker fieldTypeTracker, JSONObject jsonObject)
		throws Exception {

		SPIDataDefinitionField spiDataDefinitionField = super.deserialize(
			fieldTypeTracker, jsonObject);

		Map<String, Object> customProperties =
			spiDataDefinitionField.getCustomProperties();

		customProperties.put(
			"placeholder",
			LocalizedValueUtil.toLocalizedValues(
				jsonObject.getJSONObject("placeholder")));
		customProperties.put(
			"tooltip",
			LocalizedValueUtil.toLocalizedValues(
				jsonObject.getJSONObject("tooltip")));

		return spiDataDefinitionField;
	}

	@Override
	public String getName() {
		return "password";
	}

	@Override
	public JSONObject toJSONObject(
			FieldTypeTracker fieldTypeTracker,
			SPIDataDefinitionField spiDataDefinitionField)
		throws Exception {

		JSONObject jsonObject = super.toJSONObject(
			fieldTypeTracker, spiDataDefinitionField);

		return jsonObject.put(
			"placeholder",
			LocalizedValueUtil.toJSONObject(
				CustomPropertiesUtil.getMap(
					spiDataDefinitionField.getCustomProperties(),
					"placeholder"))
		).put(
			"tooltip",
			LocalizedValueUtil.toJSONObject(
				CustomPropertiesUtil.getMap(
					spiDataDefinitionField.getCustomProperties(), "tooltip"))
		);
	}

	@Override
	protected void includeContext(
		Map<String, Object> context, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		SPIDataDefinitionField spiDataDefinitionField) {

		context.put(
			"placeholder",
			MapUtil.getString(
				CustomPropertiesUtil.getMap(
					spiDataDefinitionField.getCustomProperties(),
					"placeholder"),
				LanguageUtil.getLanguageId(httpServletRequest)));
		context.put(
			"tooltip",
			MapUtil.getString(
				CustomPropertiesUtil.getMap(
					spiDataDefinitionField.getCustomProperties(), "tooltip"),
				LanguageUtil.getLanguageId(httpServletRequest)));
	}

}