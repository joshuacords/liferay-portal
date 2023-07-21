/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.koroneiki.taproot.exception.ContactEmailAddressException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code ContactEmailAddressException} to a {@code 400} or
 * {@code 409} error.
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Koroneiki.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Koroneiki.REST.ContactEmailAddressExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class ContactEmailAddressExceptionMapper
	implements ExceptionMapper<ContactEmailAddressException> {

	@Override
	public Response toResponse(
		ContactEmailAddressException contactEmailAddressException) {

		if (contactEmailAddressException instanceof
				ContactEmailAddressException.MustNotBeDuplicate) {

			return Response.status(
				409
			).entity(
				"The email address you requested is already taken"
			).type(
				MediaType.TEXT_PLAIN
			).build();
		}

		return Response.status(
			400
		).entity(
			"Please enter a valid email address"
		).type(
			MediaType.TEXT_PLAIN
		).build();
	}

}