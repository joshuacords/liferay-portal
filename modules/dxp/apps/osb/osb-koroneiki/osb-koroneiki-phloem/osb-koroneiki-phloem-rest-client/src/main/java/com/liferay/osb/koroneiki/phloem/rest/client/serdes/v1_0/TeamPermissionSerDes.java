/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamPermission;
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
public class TeamPermissionSerDes {

	public static TeamPermission toDTO(String json) {
		TeamPermissionJSONParser teamPermissionJSONParser =
			new TeamPermissionJSONParser();

		return teamPermissionJSONParser.parseToDTO(json);
	}

	public static TeamPermission[] toDTOs(String json) {
		TeamPermissionJSONParser teamPermissionJSONParser =
			new TeamPermissionJSONParser();

		return teamPermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(TeamPermission teamPermission) {
		if (teamPermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (teamPermission.getAssignContact() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignContact\": ");

			sb.append(teamPermission.getAssignContact());
		}

		if (teamPermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(teamPermission.getDelete());
		}

		if (teamPermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(teamPermission.getPermissions());
		}

		if (teamPermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0; i < teamPermission.getRoleNames().length; i++) {
				sb.append("\"");

				sb.append(_escape(teamPermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) < teamPermission.getRoleNames().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (teamPermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(teamPermission.getUpdate());
		}

		if (teamPermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(teamPermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TeamPermissionJSONParser teamPermissionJSONParser =
			new TeamPermissionJSONParser();

		return teamPermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(TeamPermission teamPermission) {
		if (teamPermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (teamPermission.getAssignContact() == null) {
			map.put("assignContact", null);
		}
		else {
			map.put(
				"assignContact",
				String.valueOf(teamPermission.getAssignContact()));
		}

		if (teamPermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put("delete", String.valueOf(teamPermission.getDelete()));
		}

		if (teamPermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions", String.valueOf(teamPermission.getPermissions()));
		}

		if (teamPermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put("roleNames", String.valueOf(teamPermission.getRoleNames()));
		}

		if (teamPermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put("update", String.valueOf(teamPermission.getUpdate()));
		}

		if (teamPermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put("view", String.valueOf(teamPermission.getView()));
		}

		return map;
	}

	public static class TeamPermissionJSONParser
		extends BaseJSONParser<TeamPermission> {

		@Override
		protected TeamPermission createDTO() {
			return new TeamPermission();
		}

		@Override
		protected TeamPermission[] createDTOArray(int size) {
			return new TeamPermission[size];
		}

		@Override
		protected void setField(
			TeamPermission teamPermission, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "assignContact")) {
				if (jsonParserFieldValue != null) {
					teamPermission.setAssignContact(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					teamPermission.setDelete((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					teamPermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					teamPermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					teamPermission.setUpdate((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					teamPermission.setView((Boolean)jsonParserFieldValue);
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