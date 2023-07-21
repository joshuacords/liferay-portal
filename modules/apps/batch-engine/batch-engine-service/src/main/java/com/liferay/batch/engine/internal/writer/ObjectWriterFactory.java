/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.writer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class ObjectWriterFactory {

	public static ObjectWriter getObjectWriter(
		Set<String> allFieldNames, List<String> includeFieldNames) {

		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();

		if (includeFieldNames.isEmpty()) {
			simpleFilterProvider.setFailOnUnknownId(false);
		}
		else {
			Set<String> excludeFieldNames = new HashSet<>(allFieldNames);

			excludeFieldNames.removeAll(includeFieldNames);

			SimpleBeanPropertyFilter simpleBeanPropertyFilter =
				SimpleBeanPropertyFilter.serializeAllExcept(excludeFieldNames);

			simpleFilterProvider.addFilter(
				"Liferay.Vulcan", simpleBeanPropertyFilter);
		}

		return _objectMapper.writer(simpleFilterProvider);
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
			enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};

}