/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.jaxrs.context.provider;

import com.liferay.portal.vulcan.fields.FieldsQueryParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

/**
 * @author Alejandro Hernández
 */
@Provider
public class FieldsQueryParamContextProvider
	implements ContextProvider<FieldsQueryParam> {

	@Override
	public FieldsQueryParam createContext(Message message) {
		HttpServletRequest httpServletRequest =
			ContextProviderUtil.getHttpServletRequest(message);

		String fieldNamesString = httpServletRequest.getParameter("fields");

		if (fieldNamesString == null) {
			return () -> null;
		}

		if (fieldNamesString.isEmpty()) {
			return Collections::emptySet;
		}

		Stream<String> stream = Arrays.stream(fieldNamesString.split(","));

		Set<String> fieldNames = stream.map(
			this::_toPaths
		).flatMap(
			List::stream
		).collect(
			Collectors.toSet()
		);

		return () -> fieldNames;
	}

	private List<String> _toPaths(String string) {
		if (!string.contains(".")) {
			return Collections.singletonList(string);
		}

		List<String> list = new ArrayList<>();

		String pending = string;

		while (!pending.equals("")) {
			list.add(pending);

			if (pending.contains(".")) {
				pending = pending.substring(0, pending.lastIndexOf("."));
			}
			else {
				pending = "";
			}
		}

		return list;
	}

}