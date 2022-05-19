/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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