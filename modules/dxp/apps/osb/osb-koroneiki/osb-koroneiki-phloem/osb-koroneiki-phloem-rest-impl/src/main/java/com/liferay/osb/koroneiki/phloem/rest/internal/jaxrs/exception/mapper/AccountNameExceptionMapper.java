/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.koroneiki.taproot.exception.AccountNameException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code AccountNameException} to a {@code 400} error.
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Koroneiki.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Koroneiki.REST.AccountNameExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class AccountNameExceptionMapper
	implements ExceptionMapper<AccountNameException> {

	@Override
	public Response toResponse(AccountNameException accountNameException) {
		return Response.status(
			400
		).entity(
			"Please enter a valid name"
		).type(
			MediaType.TEXT_PLAIN
		).build();
	}

}