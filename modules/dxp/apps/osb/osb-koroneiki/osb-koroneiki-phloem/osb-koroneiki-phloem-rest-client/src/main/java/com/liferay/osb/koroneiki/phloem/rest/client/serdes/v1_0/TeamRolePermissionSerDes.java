/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRolePermission;
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
public class TeamRolePermissionSerDes {

	public static TeamRolePermission toDTO(String json) {
		TeamRolePermissionJSONParser teamRolePermissionJSONParser =
			new TeamRolePermissionJSONParser();

		return teamRolePermissionJSONParser.parseToDTO(json);
	}

	public static TeamRolePermission[] toDTOs(String json) {
		TeamRolePermissionJSONParser teamRolePermissionJSONParser =
			new TeamRolePermissionJSONParser();

		return teamRolePermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(TeamRolePermission teamRolePermission) {
		if (teamRolePermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (teamRolePermission.getAssignTeam() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignTeam\": ");

			sb.append(teamRolePermission.getAssignTeam());
		}

		if (teamRolePermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(teamRolePermission.getDelete());
		}

		if (teamRolePermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(teamRolePermission.getPermissions());
		}

		if (teamRolePermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0; i < teamRolePermission.getRoleNames().length; i++) {
				sb.append("\"");

				sb.append(_escape(teamRolePermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) < teamRolePermission.getRoleNames().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (teamRolePermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(teamRolePermission.getUpdate());
		}

		if (teamRolePermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(teamRolePermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TeamRolePermissionJSONParser teamRolePermissionJSONParser =
			new TeamRolePermissionJSONParser();

		return teamRolePermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		TeamRolePermission teamRolePermission) {

		if (teamRolePermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (teamRolePermission.getAssignTeam() == null) {
			map.put("assignTeam", null);
		}
		else {
			map.put(
				"assignTeam",
				String.valueOf(teamRolePermission.getAssignTeam()));
		}

		if (teamRolePermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put("delete", String.valueOf(teamRolePermission.getDelete()));
		}

		if (teamRolePermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions",
				String.valueOf(teamRolePermission.getPermissions()));
		}

		if (teamRolePermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put(
				"roleNames", String.valueOf(teamRolePermission.getRoleNames()));
		}

		if (teamRolePermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put("update", String.valueOf(teamRolePermission.getUpdate()));
		}

		if (teamRolePermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put("view", String.valueOf(teamRolePermission.getView()));
		}

		return map;
	}

	public static class TeamRolePermissionJSONParser
		extends BaseJSONParser<TeamRolePermission> {

		@Override
		protected TeamRolePermission createDTO() {
			return new TeamRolePermission();
		}

		@Override
		protected TeamRolePermission[] createDTOArray(int size) {
			return new TeamRolePermission[size];
		}

		@Override
		protected void setField(
			TeamRolePermission teamRolePermission, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "assignTeam")) {
				if (jsonParserFieldValue != null) {
					teamRolePermission.setAssignTeam(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					teamRolePermission.setDelete((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					teamRolePermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					teamRolePermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					teamRolePermission.setUpdate((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					teamRolePermission.setView((Boolean)jsonParserFieldValue);
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