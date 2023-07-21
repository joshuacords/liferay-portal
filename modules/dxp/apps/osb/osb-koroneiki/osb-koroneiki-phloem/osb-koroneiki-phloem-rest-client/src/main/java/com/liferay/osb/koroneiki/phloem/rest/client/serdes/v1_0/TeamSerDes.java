/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
public class TeamSerDes {

	public static Team toDTO(String json) {
		TeamJSONParser teamJSONParser = new TeamJSONParser();

		return teamJSONParser.parseToDTO(json);
	}

	public static Team[] toDTOs(String json) {
		TeamJSONParser teamJSONParser = new TeamJSONParser();

		return teamJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Team team) {
		if (team == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (team.getAccount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"account\": ");

			sb.append(String.valueOf(team.getAccount()));
		}

		if (team.getAccountKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountKey\": ");

			sb.append("\"");

			sb.append(_escape(team.getAccountKey()));

			sb.append("\"");
		}

		if (team.getContacts() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contacts\": ");

			sb.append("[");

			for (int i = 0; i < team.getContacts().length; i++) {
				sb.append(String.valueOf(team.getContacts()[i]));

				if ((i + 1) < team.getContacts().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (team.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(team.getDateCreated()));

			sb.append("\"");
		}

		if (team.getDateModified() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(team.getDateModified()));

			sb.append("\"");
		}

		if (team.getExternalLinks() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalLinks\": ");

			sb.append("[");

			for (int i = 0; i < team.getExternalLinks().length; i++) {
				sb.append(String.valueOf(team.getExternalLinks()[i]));

				if ((i + 1) < team.getExternalLinks().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (team.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(team.getKey()));

			sb.append("\"");
		}

		if (team.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(team.getName()));

			sb.append("\"");
		}

		if (team.getSystem() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"system\": ");

			sb.append(team.getSystem());
		}

		if (team.getTeamRoles() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"teamRoles\": ");

			sb.append("[");

			for (int i = 0; i < team.getTeamRoles().length; i++) {
				sb.append(String.valueOf(team.getTeamRoles()[i]));

				if ((i + 1) < team.getTeamRoles().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TeamJSONParser teamJSONParser = new TeamJSONParser();

		return teamJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Team team) {
		if (team == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (team.getAccount() == null) {
			map.put("account", null);
		}
		else {
			map.put("account", String.valueOf(team.getAccount()));
		}

		if (team.getAccountKey() == null) {
			map.put("accountKey", null);
		}
		else {
			map.put("accountKey", String.valueOf(team.getAccountKey()));
		}

		if (team.getContacts() == null) {
			map.put("contacts", null);
		}
		else {
			map.put("contacts", String.valueOf(team.getContacts()));
		}

		if (team.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(team.getDateCreated()));
		}

		if (team.getDateModified() == null) {
			map.put("dateModified", null);
		}
		else {
			map.put(
				"dateModified",
				liferayToJSONDateFormat.format(team.getDateModified()));
		}

		if (team.getExternalLinks() == null) {
			map.put("externalLinks", null);
		}
		else {
			map.put("externalLinks", String.valueOf(team.getExternalLinks()));
		}

		if (team.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(team.getKey()));
		}

		if (team.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(team.getName()));
		}

		if (team.getSystem() == null) {
			map.put("system", null);
		}
		else {
			map.put("system", String.valueOf(team.getSystem()));
		}

		if (team.getTeamRoles() == null) {
			map.put("teamRoles", null);
		}
		else {
			map.put("teamRoles", String.valueOf(team.getTeamRoles()));
		}

		return map;
	}

	public static class TeamJSONParser extends BaseJSONParser<Team> {

		@Override
		protected Team createDTO() {
			return new Team();
		}

		@Override
		protected Team[] createDTOArray(int size) {
			return new Team[size];
		}

		@Override
		protected void setField(
			Team team, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "account")) {
				if (jsonParserFieldValue != null) {
					team.setAccount(
						AccountSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "accountKey")) {
				if (jsonParserFieldValue != null) {
					team.setAccountKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "contacts")) {
				if (jsonParserFieldValue != null) {
					team.setContacts(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ContactSerDes.toDTO((String)object)
						).toArray(
							size -> new Contact[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					team.setDateCreated(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					team.setDateModified(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "externalLinks")) {
				if (jsonParserFieldValue != null) {
					team.setExternalLinks(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ExternalLinkSerDes.toDTO((String)object)
						).toArray(
							size -> new ExternalLink[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					team.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					team.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "system")) {
				if (jsonParserFieldValue != null) {
					team.setSystem((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "teamRoles")) {
				if (jsonParserFieldValue != null) {
					team.setTeamRoles(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> TeamRoleSerDes.toDTO((String)object)
						).toArray(
							size -> new TeamRole[size]
						));
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