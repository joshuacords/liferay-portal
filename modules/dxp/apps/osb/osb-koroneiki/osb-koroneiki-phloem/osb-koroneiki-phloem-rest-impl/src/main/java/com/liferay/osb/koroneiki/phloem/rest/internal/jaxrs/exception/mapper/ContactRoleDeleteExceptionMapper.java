/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.koroneiki.taproot.exception.RequiredContactRoleException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code RequiredContactRoleException} to a {@code 409} error.
 *
 * @author Rebecca Dai
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Koroneiki.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Koroneiki.REST.ContactRoleDeleteExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ContactRoleDeleteExceptionMapper
	implements ExceptionMapper<RequiredContactRoleException> {

	@Override
	public Response toResponse(
		RequiredContactRoleException requiredContactRoleException) {

		return Response.status(
			409
		).entity(
			"Please remove any contacts assigned to the contact role before " +
				"attempting to delete"
		).type(
			MediaType.TEXT_PLAIN
		).build();
	}

}