/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.CountryRegion;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class CountryRegionSerDes {

	public static CountryRegion toDTO(String json) {
		CountryRegionJSONParser countryRegionJSONParser =
			new CountryRegionJSONParser();

		return countryRegionJSONParser.parseToDTO(json);
	}

	public static CountryRegion[] toDTOs(String json) {
		CountryRegionJSONParser countryRegionJSONParser =
			new CountryRegionJSONParser();

		return countryRegionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CountryRegion countryRegion) {
		if (countryRegion == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (countryRegion.getActive() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(countryRegion.getActive());
		}

		if (countryRegion.getCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"code\": ");

			sb.append("\"");

			sb.append(_escape(countryRegion.getCode()));

			sb.append("\"");
		}

		if (countryRegion.getCountryName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryName\": ");

			sb.append("\"");

			sb.append(_escape(countryRegion.getCountryName()));

			sb.append("\"");
		}

		if (countryRegion.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(countryRegion.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CountryRegionJSONParser countryRegionJSONParser =
			new CountryRegionJSONParser();

		return countryRegionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(CountryRegion countryRegion) {
		if (countryRegion == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (countryRegion.getActive() == null) {
			map.put("active", null);
		}
		else {
			map.put("active", String.valueOf(countryRegion.getActive()));
		}

		if (countryRegion.getCode() == null) {
			map.put("code", null);
		}
		else {
			map.put("code", String.valueOf(countryRegion.getCode()));
		}

		if (countryRegion.getCountryName() == null) {
			map.put("countryName", null);
		}
		else {
			map.put(
				"countryName", String.valueOf(countryRegion.getCountryName()));
		}

		if (countryRegion.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(countryRegion.getName()));
		}

		return map;
	}

	public static class CountryRegionJSONParser
		extends BaseJSONParser<CountryRegion> {

		@Override
		protected CountryRegion createDTO() {
			return new CountryRegion();
		}

		@Override
		protected CountryRegion[] createDTOArray(int size) {
			return new CountryRegion[size];
		}

		@Override
		protected void setField(
			CountryRegion countryRegion, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "active")) {
				if (jsonParserFieldValue != null) {
					countryRegion.setActive((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "code")) {
				if (jsonParserFieldValue != null) {
					countryRegion.setCode((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "countryName")) {
				if (jsonParserFieldValue != null) {
					countryRegion.setCountryName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					countryRegion.setName((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}