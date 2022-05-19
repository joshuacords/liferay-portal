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