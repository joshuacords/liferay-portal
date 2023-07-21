/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code NoSuchContactException} to a {@code 400}
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Koroneiki.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Koroneiki.REST.NoSuchContactExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class NoSuchContactExceptionMapper
	implements ExceptionMapper<NoSuchContactException> {

	@Override
	public Response toResponse(NoSuchContactException noSuchContactException) {
		return Response.status(
			400
		).entity(
			"The contact could not be found."
		).type(
			MediaType.TEXT_PLAIN
		).build();
	}

}