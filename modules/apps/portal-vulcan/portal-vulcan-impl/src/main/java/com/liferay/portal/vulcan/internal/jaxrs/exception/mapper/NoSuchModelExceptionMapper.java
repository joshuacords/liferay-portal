/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Converts any {@code NoSuchModelException} to a {@code 404} error.
 *
 * <p>In the case of a DELETE request a {@code 204} (NO-CONTENT) response is
 * returned so the request is idempotent.
 *
 * @author Alejandro Hernández
 * @review
 */
public class NoSuchModelExceptionMapper
	extends BaseExceptionMapper<NoSuchModelException> {

	@Override
	public Response toResponse(NoSuchModelException noSuchModelException) {
		String method = _httpServletRequest.getMethod();

		if (method.equals(HttpMethods.DELETE)) {
			return Response.status(
				Response.Status.NO_CONTENT
			).build();
		}

		Problem problem = getProblem(noSuchModelException);

		return Response.status(
			problem.getStatus()
		).entity(
			problem
		).type(
			getMediaType()
		).build();
	}

	@Override
	protected Problem getProblem(NoSuchModelException noSuchModelException) {
		return new Problem(
			Response.Status.NOT_FOUND, noSuchModelException.getMessage());
	}

	@Context
	private HttpServletRequest _httpServletRequest;

}