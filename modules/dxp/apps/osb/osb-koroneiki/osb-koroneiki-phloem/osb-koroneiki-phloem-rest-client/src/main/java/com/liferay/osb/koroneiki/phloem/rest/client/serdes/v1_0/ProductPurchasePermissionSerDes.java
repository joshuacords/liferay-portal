/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchasePermission;
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
public class ProductPurchasePermissionSerDes {

	public static ProductPurchasePermission toDTO(String json) {
		ProductPurchasePermissionJSONParser
			productPurchasePermissionJSONParser =
				new ProductPurchasePermissionJSONParser();

		return productPurchasePermissionJSONParser.parseToDTO(json);
	}

	public static ProductPurchasePermission[] toDTOs(String json) {
		ProductPurchasePermissionJSONParser
			productPurchasePermissionJSONParser =
				new ProductPurchasePermissionJSONParser();

		return productPurchasePermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(
		ProductPurchasePermission productPurchasePermission) {

		if (productPurchasePermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productPurchasePermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(productPurchasePermission.getDelete());
		}

		if (productPurchasePermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(productPurchasePermission.getPermissions());
		}

		if (productPurchasePermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0; i < productPurchasePermission.getRoleNames().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(productPurchasePermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) < productPurchasePermission.getRoleNames().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productPurchasePermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(productPurchasePermission.getUpdate());
		}

		if (productPurchasePermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(productPurchasePermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductPurchasePermissionJSONParser
			productPurchasePermissionJSONParser =
				new ProductPurchasePermissionJSONParser();

		return productPurchasePermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductPurchasePermission productPurchasePermission) {

		if (productPurchasePermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productPurchasePermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put(
				"delete",
				String.valueOf(productPurchasePermission.getDelete()));
		}

		if (productPurchasePermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions",
				String.valueOf(productPurchasePermission.getPermissions()));
		}

		if (productPurchasePermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put(
				"roleNames",
				String.valueOf(productPurchasePermission.getRoleNames()));
		}

		if (productPurchasePermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put(
				"update",
				String.valueOf(productPurchasePermission.getUpdate()));
		}

		if (productPurchasePermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put(
				"view", String.valueOf(productPurchasePermission.getView()));
		}

		return map;
	}

	public static class ProductPurchasePermissionJSONParser
		extends BaseJSONParser<ProductPurchasePermission> {

		@Override
		protected ProductPurchasePermission createDTO() {
			return new ProductPurchasePermission();
		}

		@Override
		protected ProductPurchasePermission[] createDTOArray(int size) {
			return new ProductPurchasePermission[size];
		}

		@Override
		protected void setField(
			ProductPurchasePermission productPurchasePermission,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					productPurchasePermission.setDelete(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					productPurchasePermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					productPurchasePermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					productPurchasePermission.setUpdate(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					productPurchasePermission.setView(
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