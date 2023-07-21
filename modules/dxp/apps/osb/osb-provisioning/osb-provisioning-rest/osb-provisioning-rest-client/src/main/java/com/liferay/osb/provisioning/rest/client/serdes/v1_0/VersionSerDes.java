/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.serdes.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.Type;
import com.liferay.osb.provisioning.rest.client.dto.v1_0.Version;
import com.liferay.osb.provisioning.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class VersionSerDes {

	public static Version toDTO(String json) {
		VersionJSONParser versionJSONParser = new VersionJSONParser();

		return versionJSONParser.parseToDTO(json);
	}

	public static Version[] toDTOs(String json) {
		VersionJSONParser versionJSONParser = new VersionJSONParser();

		return versionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Version version) {
		if (version == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (version.getLabel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"label\": ");

			sb.append("\"");

			sb.append(_escape(version.getLabel()));

			sb.append("\"");
		}

		if (version.getTypes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"types\": ");

			sb.append("[");

			for (int i = 0; i < version.getTypes().length; i++) {
				sb.append(String.valueOf(version.getTypes()[i]));

				if ((i + 1) < version.getTypes().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		VersionJSONParser versionJSONParser = new VersionJSONParser();

		return versionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Version version) {
		if (version == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (version.getLabel() == null) {
			map.put("label", null);
		}
		else {
			map.put("label", String.valueOf(version.getLabel()));
		}

		if (version.getTypes() == null) {
			map.put("types", null);
		}
		else {
			map.put("types", String.valueOf(version.getTypes()));
		}

		return map;
	}

	public static class VersionJSONParser extends BaseJSONParser<Version> {

		@Override
		protected Version createDTO() {
			return new Version();
		}

		@Override
		protected Version[] createDTOArray(int size) {
			return new Version[size];
		}

		@Override
		protected void setField(
			Version version, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "label")) {
				if (jsonParserFieldValue != null) {
					version.setLabel((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "types")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Type[] typesArray = new Type[jsonParserFieldValues.length];

					for (int i = 0; i < typesArray.length; i++) {
						typesArray[i] = TypeSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					version.setTypes(typesArray);
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