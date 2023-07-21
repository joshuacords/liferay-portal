/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.provisioning.koroneiki.exception.ValidationException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Converts any {@code ValidationException} to a {@code 422} error.
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Provisioning.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Provisioning.REST.ValidationExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ValidationExceptionMapper
	implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException validationException) {
		return Response.status(
			422
		).entity(
			_getEntity(validationException)
		).type(
			MediaType.APPLICATION_JSON
		).build();
	}

	private String _getEntity(ValidationException validationException) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put(
			"status", "UNPROCESSABLE_ENTITY"
		).put(
			"title", validationException.getMessage()
		);

		return jsonObject.toString();
	}

	@Reference
	private JSONFactory _jsonFactory;

}