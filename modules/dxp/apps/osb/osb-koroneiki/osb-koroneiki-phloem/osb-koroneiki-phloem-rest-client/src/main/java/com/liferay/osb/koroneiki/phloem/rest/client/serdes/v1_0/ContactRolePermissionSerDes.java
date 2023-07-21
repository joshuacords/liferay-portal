/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRolePermission;
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
public class ContactRolePermissionSerDes {

	public static ContactRolePermission toDTO(String json) {
		ContactRolePermissionJSONParser contactRolePermissionJSONParser =
			new ContactRolePermissionJSONParser();

		return contactRolePermissionJSONParser.parseToDTO(json);
	}

	public static ContactRolePermission[] toDTOs(String json) {
		ContactRolePermissionJSONParser contactRolePermissionJSONParser =
			new ContactRolePermissionJSONParser();

		return contactRolePermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ContactRolePermission contactRolePermission) {
		if (contactRolePermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (contactRolePermission.getAssignContact() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignContact\": ");

			sb.append(contactRolePermission.getAssignContact());
		}

		if (contactRolePermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(contactRolePermission.getDelete());
		}

		if (contactRolePermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(contactRolePermission.getPermissions());
		}

		if (contactRolePermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0; i < contactRolePermission.getRoleNames().length;
				 i++) {

				sb.append("\"");

				sb.append(_escape(contactRolePermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) < contactRolePermission.getRoleNames().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (contactRolePermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(contactRolePermission.getUpdate());
		}

		if (contactRolePermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(contactRolePermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ContactRolePermissionJSONParser contactRolePermissionJSONParser =
			new ContactRolePermissionJSONParser();

		return contactRolePermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ContactRolePermission contactRolePermission) {

		if (contactRolePermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (contactRolePermission.getAssignContact() == null) {
			map.put("assignContact", null);
		}
		else {
			map.put(
				"assignContact",
				String.valueOf(contactRolePermission.getAssignContact()));
		}

		if (contactRolePermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put(
				"delete", String.valueOf(contactRolePermission.getDelete()));
		}

		if (contactRolePermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions",
				String.valueOf(contactRolePermission.getPermissions()));
		}

		if (contactRolePermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put(
				"roleNames",
				String.valueOf(contactRolePermission.getRoleNames()));
		}

		if (contactRolePermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put(
				"update", String.valueOf(contactRolePermission.getUpdate()));
		}

		if (contactRolePermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put("view", String.valueOf(contactRolePermission.getView()));
		}

		return map;
	}

	public static class ContactRolePermissionJSONParser
		extends BaseJSONParser<ContactRolePermission> {

		@Override
		protected ContactRolePermission createDTO() {
			return new ContactRolePermission();
		}

		@Override
		protected ContactRolePermission[] createDTOArray(int size) {
			return new ContactRolePermission[size];
		}

		@Override
		protected void setField(
			ContactRolePermission contactRolePermission,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "assignContact")) {
				if (jsonParserFieldValue != null) {
					contactRolePermission.setAssignContact(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					contactRolePermission.setDelete(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					contactRolePermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					contactRolePermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					contactRolePermission.setUpdate(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					contactRolePermission.setView(
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