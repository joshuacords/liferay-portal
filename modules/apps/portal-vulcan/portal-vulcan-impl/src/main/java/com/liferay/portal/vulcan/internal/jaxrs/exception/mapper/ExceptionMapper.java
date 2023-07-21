/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.exception.mapper;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Providers;

/**
 * @author Javier Gamarra
 */
public class ExceptionMapper extends BaseExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		Throwable throwable = exception.getCause();

		if (throwable == null) {
			return super.toResponse(exception);
		}

		javax.ws.rs.ext.ExceptionMapper<Throwable> exceptionMapper =
			_providers.getExceptionMapper(
				(Class<Throwable>)throwable.getClass());

		if (exceptionMapper != null) {
			return exceptionMapper.toResponse(throwable);
		}

		return super.toResponse(exception);
	}

	@Override
	protected Problem getProblem(Exception exception) {
		_log.error(exception, exception);

		return new Problem(
			Response.Status.INTERNAL_SERVER_ERROR, exception.getMessage());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExceptionMapper.class);

	@Context
	private Providers _providers;

}