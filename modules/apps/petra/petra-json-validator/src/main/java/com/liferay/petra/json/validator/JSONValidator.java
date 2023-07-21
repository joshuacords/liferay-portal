/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.json.validator;

import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author Rubén Pulido
 */
public class JSONValidator {

	public static void validate(String json, InputStream inputStream)
		throws JSONValidatorException {

		if (Validator.isNull(json)) {
			return;
		}

		try {
			JSONObject jsonSchemaJSONObject = new JSONObject(
				new JSONTokener(inputStream));

			Schema schema = SchemaLoader.load(jsonSchemaJSONObject);

			JSONObject jsonObject = new JSONObject(json);

			schema.validate(jsonObject);
		}
		catch (Exception exception) {
			if (exception instanceof JSONException) {
				JSONException jsonException = (JSONException)exception;

				throw new JSONValidatorException(
					jsonException.getMessage(), jsonException);
			}
			else if (exception instanceof ValidationException) {
				ValidationException validationException =
					(ValidationException)exception;

				String errorMessage = validationException.getErrorMessage();

				List<String> messages = validationException.getAllMessages();

				if (!messages.isEmpty()) {
					List<String> formattedMessages = new ArrayList<>();

					messages.forEach(
						message -> {
							if (message.startsWith("#: ")) {
								message = message.substring(3);
							}
							else if (message.startsWith("#")) {
								message = message.substring(1);
							}

							formattedMessages.add(message);
						});

					errorMessage = StringUtil.merge(
						formattedMessages, StringPool.NEW_LINE);
				}

				throw new JSONValidatorException(errorMessage, exception);
			}

			throw new JSONValidatorException(exception);
		}
	}

}