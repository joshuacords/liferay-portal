/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.asah.connector.internal.client.data.binding;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.liferay.segments.asah.connector.internal.client.model.PageMetadata;
import com.liferay.segments.asah.connector.internal.client.model.Results;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author David Arques
 */
public class AsahFaroBackendJSONObjectMapper {

	public static <T> T map(String json, Class<T> clazz) throws IOException {
		return _objectMapper.readValue(json, clazz);
	}

	public static <T> Results<T> mapToResults(
			String json, String embeddedRelName, Class<T> clazz)
		throws IOException {

		TypeFactory typeFactory = TypeFactory.defaultInstance();

		ObjectReader objectReader = _objectMapper.readerFor(
			typeFactory.constructCollectionType(ArrayList.class, clazz));

		JsonNode responseJsonNode = _objectMapper.readTree(json);

		JsonNode embeddedJsonNode = responseJsonNode.get("_embedded");

		List<T> items = Collections.emptyList();

		if (embeddedJsonNode != null) {
			JsonNode embeddedRelJsonNode = embeddedJsonNode.get(
				embeddedRelName);

			items = objectReader.readValue(embeddedRelJsonNode);
		}

		JsonNode pageJsonNode = responseJsonNode.get("page");

		if (pageJsonNode != null) {
			PageMetadata pageMetadata = _objectMapper.treeToValue(
				pageJsonNode, PageMetadata.class);

			return new Results<>(items, (int)pageMetadata.getTotalElements());
		}

		return new Results<>(items, items.size());
	}

	private AsahFaroBackendJSONObjectMapper() {
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
	};

}