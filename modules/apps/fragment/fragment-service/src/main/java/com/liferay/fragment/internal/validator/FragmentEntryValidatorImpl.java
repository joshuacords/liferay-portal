/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.validator;

import com.liferay.fragment.exception.FragmentEntryConfigurationException;
import com.liferay.fragment.validator.FragmentEntryValidator;
import com.liferay.petra.json.validator.JSONValidator;
import com.liferay.petra.json.validator.JSONValidatorException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = FragmentEntryValidator.class)
public class FragmentEntryValidatorImpl implements FragmentEntryValidator {

	@Override
	public void validateConfiguration(String configuration)
		throws FragmentEntryConfigurationException {

		if (Validator.isNull(configuration)) {
			return;
		}

		InputStream configurationJSONSchemaInputStream =
			FragmentEntryValidatorImpl.class.getResourceAsStream(
				"dependencies/configuration-json-schema.json");

		try {
			JSONValidator.validate(
				configuration, configurationJSONSchemaInputStream);

			JSONObject configurationJSONObject =
				JSONFactoryUtil.createJSONObject(configuration);

			JSONArray fieldSetsJSONArray = configurationJSONObject.getJSONArray(
				"fieldSets");

			Set<String> fieldNames = new HashSet<>();

			for (int fieldSetIndex = 0;
				 fieldSetIndex < fieldSetsJSONArray.length(); fieldSetIndex++) {

				JSONObject fieldSetJSONObject =
					fieldSetsJSONArray.getJSONObject(fieldSetIndex);

				JSONArray fieldsJSONArray = fieldSetJSONObject.getJSONArray(
					"fields");

				for (int fieldIndex = 0; fieldIndex < fieldsJSONArray.length();
					 fieldIndex++) {

					JSONObject fieldJSONObject = fieldsJSONArray.getJSONObject(
						fieldIndex);

					if (fieldNames.contains(
							fieldJSONObject.getString("name"))) {

						throw new FragmentEntryConfigurationException(
							"Field names must be unique");
					}

					fieldNames.add(fieldJSONObject.getString("name"));
				}
			}
		}
		catch (JSONException jsonException) {
			throw new FragmentEntryConfigurationException(
				jsonException.getMessage(), jsonException);
		}
		catch (JSONValidatorException jsonValidatorException) {
			throw new FragmentEntryConfigurationException(
				jsonValidatorException.getMessage(), jsonValidatorException);
		}
	}

}