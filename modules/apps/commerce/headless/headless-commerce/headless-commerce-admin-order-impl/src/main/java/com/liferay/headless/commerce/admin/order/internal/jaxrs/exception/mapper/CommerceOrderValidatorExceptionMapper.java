/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.order.internal.jaxrs.exception.mapper;

import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.headless.commerce.core.exception.mapper.BaseExceptionMapper;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Order)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.Order.CommerceOrderValidatorExceptionMapper"
	},
	service = ExceptionMapper.class
)
@Provider
public class CommerceOrderValidatorExceptionMapper
	extends BaseExceptionMapper<CommerceOrderValidatorException> {

	@Override
	public String getErrorDescription() {
		return "Invalid order";
	}

	@Override
	public Response.Status getStatus() {
		return Response.Status.BAD_REQUEST;
	}

	@Override
	public Response toResponse(
		CommerceOrderValidatorException commerceOrderValidatorException) {

		_log.error("General REST exception", commerceOrderValidatorException);

		Response.ResponseBuilder responseBuilder = Response.status(getStatus());

		Response.Status status = getStatus();

		responseBuilder.entity(
			toJSON(
				_getErrorMessage(commerceOrderValidatorException),
				status.getStatusCode()));

		responseBuilder.type(MediaType.APPLICATION_JSON_TYPE);

		return responseBuilder.build();
	}

	private String _getErrorMessage(
		CommerceOrderValidatorException commerceOrderValidatorException) {

		List<CommerceOrderValidatorResult> commerceOrderValidatorResults =
			commerceOrderValidatorException.getCommerceOrderValidatorResults();

		if (commerceOrderValidatorResults.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_CURLY_BRACE);

		for (CommerceOrderValidatorResult commerceOrderValidatorResult :
				commerceOrderValidatorResults) {

			if (commerceOrderValidatorResults.indexOf(
					commerceOrderValidatorResult) > 0) {

				sb.append(StringPool.COMMA);
			}

			sb.append(StringPool.QUOTE);
			sb.append(commerceOrderValidatorResult.getLocalizedMessage());
			sb.append(StringPool.QUOTE);
		}

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderValidatorExceptionMapper.class);

}