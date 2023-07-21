/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPermission;
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
public class ProductPermissionSerDes {

	public static ProductPermission toDTO(String json) {
		ProductPermissionJSONParser productPermissionJSONParser =
			new ProductPermissionJSONParser();

		return productPermissionJSONParser.parseToDTO(json);
	}

	public static ProductPermission[] toDTOs(String json) {
		ProductPermissionJSONParser productPermissionJSONParser =
			new ProductPermissionJSONParser();

		return productPermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductPermission productPermission) {
		if (productPermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productPermission.getConsume() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"consume\": ");

			sb.append(productPermission.getConsume());
		}

		if (productPermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(productPermission.getDelete());
		}

		if (productPermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(productPermission.getPermissions());
		}

		if (productPermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0; i < productPermission.getRoleNames().length; i++) {
				sb.append("\"");

				sb.append(_escape(productPermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) < productPermission.getRoleNames().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productPermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(productPermission.getUpdate());
		}

		if (productPermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(productPermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductPermissionJSONParser productPermissionJSONParser =
			new ProductPermissionJSONParser();

		return productPermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductPermission productPermission) {

		if (productPermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productPermission.getConsume() == null) {
			map.put("consume", null);
		}
		else {
			map.put("consume", String.valueOf(productPermission.getConsume()));
		}

		if (productPermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put("delete", String.valueOf(productPermission.getDelete()));
		}

		if (productPermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions",
				String.valueOf(productPermission.getPermissions()));
		}

		if (productPermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put(
				"roleNames", String.valueOf(productPermission.getRoleNames()));
		}

		if (productPermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put("update", String.valueOf(productPermission.getUpdate()));
		}

		if (productPermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put("view", String.valueOf(productPermission.getView()));
		}

		return map;
	}

	public static class ProductPermissionJSONParser
		extends BaseJSONParser<ProductPermission> {

		@Override
		protected ProductPermission createDTO() {
			return new ProductPermission();
		}

		@Override
		protected ProductPermission[] createDTOArray(int size) {
			return new ProductPermission[size];
		}

		@Override
		protected void setField(
			ProductPermission productPermission, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "consume")) {
				if (jsonParserFieldValue != null) {
					productPermission.setConsume((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					productPermission.setDelete((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					productPermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					productPermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					productPermission.setUpdate((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					productPermission.setView((Boolean)jsonParserFieldValue);
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