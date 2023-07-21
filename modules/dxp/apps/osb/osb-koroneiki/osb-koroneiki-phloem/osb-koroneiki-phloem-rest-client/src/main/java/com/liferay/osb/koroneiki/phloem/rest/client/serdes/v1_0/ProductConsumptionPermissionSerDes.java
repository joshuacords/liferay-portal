/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumptionPermission;
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
public class ProductConsumptionPermissionSerDes {

	public static ProductConsumptionPermission toDTO(String json) {
		ProductConsumptionPermissionJSONParser
			productConsumptionPermissionJSONParser =
				new ProductConsumptionPermissionJSONParser();

		return productConsumptionPermissionJSONParser.parseToDTO(json);
	}

	public static ProductConsumptionPermission[] toDTOs(String json) {
		ProductConsumptionPermissionJSONParser
			productConsumptionPermissionJSONParser =
				new ProductConsumptionPermissionJSONParser();

		return productConsumptionPermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		ProductConsumptionPermission productConsumptionPermission) {

		if (productConsumptionPermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productConsumptionPermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(productConsumptionPermission.getDelete());
		}

		if (productConsumptionPermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(productConsumptionPermission.getPermissions());
		}

		if (productConsumptionPermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0;
				 i < productConsumptionPermission.getRoleNames().length; i++) {

				sb.append("\"");

				sb.append(
					_escape(productConsumptionPermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) <
						productConsumptionPermission.getRoleNames().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productConsumptionPermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(productConsumptionPermission.getUpdate());
		}

		if (productConsumptionPermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(productConsumptionPermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductConsumptionPermissionJSONParser
			productConsumptionPermissionJSONParser =
				new ProductConsumptionPermissionJSONParser();

		return productConsumptionPermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductConsumptionPermission productConsumptionPermission) {

		if (productConsumptionPermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productConsumptionPermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put(
				"delete",
				String.valueOf(productConsumptionPermission.getDelete()));
		}

		if (productConsumptionPermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions",
				String.valueOf(productConsumptionPermission.getPermissions()));
		}

		if (productConsumptionPermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put(
				"roleNames",
				String.valueOf(productConsumptionPermission.getRoleNames()));
		}

		if (productConsumptionPermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put(
				"update",
				String.valueOf(productConsumptionPermission.getUpdate()));
		}

		if (productConsumptionPermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put(
				"view", String.valueOf(productConsumptionPermission.getView()));
		}

		return map;
	}

	public static class ProductConsumptionPermissionJSONParser
		extends BaseJSONParser<ProductConsumptionPermission> {

		@Override
		protected ProductConsumptionPermission createDTO() {
			return new ProductConsumptionPermission();
		}

		@Override
		protected ProductConsumptionPermission[] createDTOArray(int size) {
			return new ProductConsumptionPermission[size];
		}

		@Override
		protected void setField(
			ProductConsumptionPermission productConsumptionPermission,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					productConsumptionPermission.setDelete(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					productConsumptionPermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					productConsumptionPermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					productConsumptionPermission.setUpdate(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					productConsumptionPermission.setView(
						(Boolean)jsonParserFieldValue);
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