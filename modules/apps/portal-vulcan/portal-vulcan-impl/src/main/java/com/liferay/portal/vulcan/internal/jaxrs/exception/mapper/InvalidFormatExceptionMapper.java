/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.exception.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

/**
 * Converts any {@code InvalidFormatException} to a {@code 400} error.
 *
 * @author Alejandro Hernández
 * @review
 */
public class InvalidFormatExceptionMapper
	extends BaseExceptionMapper<InvalidFormatException> {

	@Override
	protected Problem getProblem(
		InvalidFormatException invalidFormatException) {

		if (_log.isDebugEnabled()) {
			_log.debug(invalidFormatException, invalidFormatException);
		}

		List<JsonMappingException.Reference> references =
			invalidFormatException.getPath();

		Stream<JsonMappingException.Reference> stream = references.stream();

		String path = stream.map(
			JsonMappingException.Reference::getFieldName
		).collect(
			Collectors.joining(".")
		);

		Class<?> clazz = invalidFormatException.getTargetType();

		String message = StringBundler.concat(
			"Unable to map JSON path \"", path, "\" with value \"",
			invalidFormatException.getValue(), "\" to class \"",
			clazz.getSimpleName(), "\"");

		return new Problem(
			invalidFormatException.getLocalizedMessage(),
			Response.Status.BAD_REQUEST, message, "InvalidFormatException");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InvalidFormatExceptionMapper.class);

}