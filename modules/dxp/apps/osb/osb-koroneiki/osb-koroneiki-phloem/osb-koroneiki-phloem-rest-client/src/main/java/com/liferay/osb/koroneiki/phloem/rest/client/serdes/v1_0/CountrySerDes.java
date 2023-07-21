/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.CountryRegion;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class CountrySerDes {

	public static Country toDTO(String json) {
		CountryJSONParser countryJSONParser = new CountryJSONParser();

		return countryJSONParser.parseToDTO(json);
	}

	public static Country[] toDTOs(String json) {
		CountryJSONParser countryJSONParser = new CountryJSONParser();

		return countryJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Country country) {
		if (country == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (country.getA2() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"a2\": ");

			sb.append("\"");

			sb.append(_escape(country.getA2()));

			sb.append("\"");
		}

		if (country.getA3() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"a3\": ");

			sb.append("\"");

			sb.append(_escape(country.getA3()));

			sb.append("\"");
		}

		if (country.getActive() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(country.getActive());
		}

		if (country.getCountryRegions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryRegions\": ");

			sb.append("[");

			for (int i = 0; i < country.getCountryRegions().length; i++) {
				sb.append(String.valueOf(country.getCountryRegions()[i]));

				if ((i + 1) < country.getCountryRegions().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (country.getIdd() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"idd\": ");

			sb.append("\"");

			sb.append(_escape(country.getIdd()));

			sb.append("\"");
		}

		if (country.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(country.getName()));

			sb.append("\"");
		}

		if (country.getZipRequired() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"zipRequired\": ");

			sb.append(country.getZipRequired());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CountryJSONParser countryJSONParser = new CountryJSONParser();

		return countryJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Country country) {
		if (country == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (country.getA2() == null) {
			map.put("a2", null);
		}
		else {
			map.put("a2", String.valueOf(country.getA2()));
		}

		if (country.getA3() == null) {
			map.put("a3", null);
		}
		else {
			map.put("a3", String.valueOf(country.getA3()));
		}

		if (country.getActive() == null) {
			map.put("active", null);
		}
		else {
			map.put("active", String.valueOf(country.getActive()));
		}

		if (country.getCountryRegions() == null) {
			map.put("countryRegions", null);
		}
		else {
			map.put(
				"countryRegions", String.valueOf(country.getCountryRegions()));
		}

		if (country.getIdd() == null) {
			map.put("idd", null);
		}
		else {
			map.put("idd", String.valueOf(country.getIdd()));
		}

		if (country.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(country.getName()));
		}

		if (country.getZipRequired() == null) {
			map.put("zipRequired", null);
		}
		else {
			map.put("zipRequired", String.valueOf(country.getZipRequired()));
		}

		return map;
	}

	public static class CountryJSONParser extends BaseJSONParser<Country> {

		@Override
		protected Country createDTO() {
			return new Country();
		}

		@Override
		protected Country[] createDTOArray(int size) {
			return new Country[size];
		}

		@Override
		protected void setField(
			Country country, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "a2")) {
				if (jsonParserFieldValue != null) {
					country.setA2((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "a3")) {
				if (jsonParserFieldValue != null) {
					country.setA3((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "active")) {
				if (jsonParserFieldValue != null) {
					country.setActive((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "countryRegions")) {
				if (jsonParserFieldValue != null) {
					country.setCountryRegions(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> CountryRegionSerDes.toDTO((String)object)
						).toArray(
							size -> new CountryRegion[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "idd")) {
				if (jsonParserFieldValue != null) {
					country.setIdd((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					country.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "zipRequired")) {
				if (jsonParserFieldValue != null) {
					country.setZipRequired((Boolean)jsonParserFieldValue);
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