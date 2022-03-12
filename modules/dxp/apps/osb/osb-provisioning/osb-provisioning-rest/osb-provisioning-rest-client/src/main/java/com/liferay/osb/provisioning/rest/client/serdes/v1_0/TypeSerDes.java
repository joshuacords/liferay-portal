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

package com.liferay.osb.provisioning.rest.client.serdes.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.Type;
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
public class TypeSerDes {

	public static Type toDTO(String json) {
		TypeJSONParser typeJSONParser = new TypeJSONParser();

		return typeJSONParser.parseToDTO(json);
	}

	public static Type[] toDTOs(String json) {
		TypeJSONParser typeJSONParser = new TypeJSONParser();

		return typeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Type type) {
		if (type == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (type.getLicenseEntryDisplayName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryDisplayName\": ");

			sb.append("\"");

			sb.append(_escape(type.getLicenseEntryDisplayName()));

			sb.append("\"");
		}

		if (type.getLicenseEntryName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryName\": ");

			sb.append("\"");

			sb.append(_escape(type.getLicenseEntryName()));

			sb.append("\"");
		}

		if (type.getLicenseEntryType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryType\": ");

			sb.append("\"");

			sb.append(_escape(type.getLicenseEntryType()));

			sb.append("\"");
		}

		if (type.getProductKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(type.getProductKey()));

			sb.append("\"");
		}

		if (type.getRequiredDetails() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requiredDetails\": ");

			sb.append("\"");

			sb.append(_escape(type.getRequiredDetails()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TypeJSONParser typeJSONParser = new TypeJSONParser();

		return typeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Type type) {
		if (type == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (type.getLicenseEntryDisplayName() == null) {
			map.put("licenseEntryDisplayName", null);
		}
		else {
			map.put(
				"licenseEntryDisplayName",
				String.valueOf(type.getLicenseEntryDisplayName()));
		}

		if (type.getLicenseEntryName() == null) {
			map.put("licenseEntryName", null);
		}
		else {
			map.put(
				"licenseEntryName", String.valueOf(type.getLicenseEntryName()));
		}

		if (type.getLicenseEntryType() == null) {
			map.put("licenseEntryType", null);
		}
		else {
			map.put(
				"licenseEntryType", String.valueOf(type.getLicenseEntryType()));
		}

		if (type.getProductKey() == null) {
			map.put("productKey", null);
		}
		else {
			map.put("productKey", String.valueOf(type.getProductKey()));
		}

		if (type.getRequiredDetails() == null) {
			map.put("requiredDetails", null);
		}
		else {
			map.put(
				"requiredDetails", String.valueOf(type.getRequiredDetails()));
		}

		return map;
	}

	public static class TypeJSONParser extends BaseJSONParser<Type> {

		@Override
		protected Type createDTO() {
			return new Type();
		}

		@Override
		protected Type[] createDTOArray(int size) {
			return new Type[size];
		}

		@Override
		protected void setField(
			Type type, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName, "licenseEntryDisplayName")) {

				if (jsonParserFieldValue != null) {
					type.setLicenseEntryDisplayName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseEntryName")) {
				if (jsonParserFieldValue != null) {
					type.setLicenseEntryName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseEntryType")) {
				if (jsonParserFieldValue != null) {
					type.setLicenseEntryType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productKey")) {
				if (jsonParserFieldValue != null) {
					type.setProductKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "requiredDetails")) {
				if (jsonParserFieldValue != null) {
					type.setRequiredDetails((String)jsonParserFieldValue);
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