/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.jaxrs.exception.mapper;

import com.liferay.osb.provisioning.koroneiki.exception.UnexpectedErrorException;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * Converts any {@code UnexpectedErrorException} to a {@code 500} error.
 *
 * @author Amos Fong
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Provisioning.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Provisioning.REST.UnexpectedErrorExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class UnexpectedErrorExceptionMapper
	extends BaseExceptionMapper<UnexpectedErrorException> {

	@Override
	protected Problem getProblem(
		UnexpectedErrorException unexpectedErrorException) {

		return new Problem(
			Response.Status.INTERNAL_SERVER_ERROR,
			"An unexpected error occurred");
	}

}