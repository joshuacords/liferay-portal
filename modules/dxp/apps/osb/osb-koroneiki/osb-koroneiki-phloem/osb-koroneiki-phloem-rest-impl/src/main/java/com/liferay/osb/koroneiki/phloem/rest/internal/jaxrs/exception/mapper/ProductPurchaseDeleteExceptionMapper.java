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

package com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.koroneiki.trunk.exception.RequiredProductPurchaseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code RequiredProductPurchaseException} to a {@code 409} error.
 *
 * @author Rebecca Dai
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Koroneiki.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Koroneiki.REST.ProductPurchaseDeleteExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ProductPurchaseDeleteExceptionMapper
	implements ExceptionMapper<RequiredProductPurchaseException> {

	@Override
	public Response toResponse(
		RequiredProductPurchaseException requiredProductPurchaseException) {

		return Response.status(
			409
		).entity(
			"Please remove any product consumptions before attempting to delete"
		).type(
			MediaType.TEXT_PLAIN
		).build();
	}

}