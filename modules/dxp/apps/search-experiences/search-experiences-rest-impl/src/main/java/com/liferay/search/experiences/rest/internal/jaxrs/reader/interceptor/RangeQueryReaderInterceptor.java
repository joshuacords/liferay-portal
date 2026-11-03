/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.rest.internal.jaxrs.reader.interceptor;

import com.liferay.petra.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.search.experiences.rest.dto.v1_0.SXPBlueprint;
import com.liferay.search.experiences.rest.dto.v1_0.SXPElement;
import com.liferay.search.experiences.rest.dto.v1_0.util.RangeQueryUtil;

import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import org.osgi.service.component.annotations.Component;

/**
 * @author Joshua Cords
 */
@Component(
	enabled = false,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Search.Experiences.REST)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Liferay.Search.Experiences.REST.RangeQueryReaderInterceptor"
	},
	service = ReaderInterceptor.class
)
public class RangeQueryReaderInterceptor implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(
			ReaderInterceptorContext readerInterceptorContext)
		throws IOException {

		Class<?> type = readerInterceptorContext.getType();

		if ((type != SXPBlueprint.class) && (type != SXPElement.class)) {
			return readerInterceptorContext.proceed();
		}

		String json = RangeQueryUtil.upgradeRangeQueries(
			StringUtil.read(readerInterceptorContext.getInputStream()));

		readerInterceptorContext.setInputStream(
			new UnsyncByteArrayInputStream(
				json.getBytes(StandardCharsets.UTF_8)));

		return readerInterceptorContext.proceed();
	}

}