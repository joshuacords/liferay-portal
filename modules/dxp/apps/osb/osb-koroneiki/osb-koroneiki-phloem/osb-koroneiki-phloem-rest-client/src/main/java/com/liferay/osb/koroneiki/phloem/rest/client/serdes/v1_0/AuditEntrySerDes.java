/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
public class AuditEntrySerDes {

	public static AuditEntry toDTO(String json) {
		AuditEntryJSONParser auditEntryJSONParser = new AuditEntryJSONParser();

		return auditEntryJSONParser.parseToDTO(json);
	}

	public static AuditEntry[] toDTOs(String json) {
		AuditEntryJSONParser auditEntryJSONParser = new AuditEntryJSONParser();

		return auditEntryJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AuditEntry auditEntry) {
		if (auditEntry == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (auditEntry.getAction() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"action\": ");

			sb.append("\"");

			sb.append(auditEntry.getAction());

			sb.append("\"");
		}

		if (auditEntry.getAgentName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"agentName\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getAgentName()));

			sb.append("\"");
		}

		if (auditEntry.getAgentUID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"agentUID\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getAgentUID()));

			sb.append("\"");
		}

		if (auditEntry.getAuditSetId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"auditSetId\": ");

			sb.append(auditEntry.getAuditSetId());
		}

		if (auditEntry.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(auditEntry.getDateCreated()));

			sb.append("\"");
		}

		if (auditEntry.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getDescription()));

			sb.append("\"");
		}

		if (auditEntry.getField() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"field\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getField()));

			sb.append("\"");
		}

		if (auditEntry.getFieldClassLabel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldClassLabel\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getFieldClassLabel()));

			sb.append("\"");
		}

		if (auditEntry.getFieldClassPK() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fieldClassPK\": ");

			sb.append(auditEntry.getFieldClassPK());
		}

		if (auditEntry.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getKey()));

			sb.append("\"");
		}

		if (auditEntry.getNewValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"newValue\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getNewValue()));

			sb.append("\"");
		}

		if (auditEntry.getOldValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"oldValue\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getOldValue()));

			sb.append("\"");
		}

		if (auditEntry.getSummary() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"summary\": ");

			sb.append("\"");

			sb.append(_escape(auditEntry.getSummary()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AuditEntryJSONParser auditEntryJSONParser = new AuditEntryJSONParser();

		return auditEntryJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AuditEntry auditEntry) {
		if (auditEntry == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (auditEntry.getAction() == null) {
			map.put("action", null);
		}
		else {
			map.put("action", String.valueOf(auditEntry.getAction()));
		}

		if (auditEntry.getAgentName() == null) {
			map.put("agentName", null);
		}
		else {
			map.put("agentName", String.valueOf(auditEntry.getAgentName()));
		}

		if (auditEntry.getAgentUID() == null) {
			map.put("agentUID", null);
		}
		else {
			map.put("agentUID", String.valueOf(auditEntry.getAgentUID()));
		}

		if (auditEntry.getAuditSetId() == null) {
			map.put("auditSetId", null);
		}
		else {
			map.put("auditSetId", String.valueOf(auditEntry.getAuditSetId()));
		}

		if (auditEntry.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(auditEntry.getDateCreated()));
		}

		if (auditEntry.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(auditEntry.getDescription()));
		}

		if (auditEntry.getField() == null) {
			map.put("field", null);
		}
		else {
			map.put("field", String.valueOf(auditEntry.getField()));
		}

		if (auditEntry.getFieldClassLabel() == null) {
			map.put("fieldClassLabel", null);
		}
		else {
			map.put(
				"fieldClassLabel",
				String.valueOf(auditEntry.getFieldClassLabel()));
		}

		if (auditEntry.getFieldClassPK() == null) {
			map.put("fieldClassPK", null);
		}
		else {
			map.put(
				"fieldClassPK", String.valueOf(auditEntry.getFieldClassPK()));
		}

		if (auditEntry.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(auditEntry.getKey()));
		}

		if (auditEntry.getNewValue() == null) {
			map.put("newValue", null);
		}
		else {
			map.put("newValue", String.valueOf(auditEntry.getNewValue()));
		}

		if (auditEntry.getOldValue() == null) {
			map.put("oldValue", null);
		}
		else {
			map.put("oldValue", String.valueOf(auditEntry.getOldValue()));
		}

		if (auditEntry.getSummary() == null) {
			map.put("summary", null);
		}
		else {
			map.put("summary", String.valueOf(auditEntry.getSummary()));
		}

		return map;
	}

	public static class AuditEntryJSONParser
		extends BaseJSONParser<AuditEntry> {

		@Override
		protected AuditEntry createDTO() {
			return new AuditEntry();
		}

		@Override
		protected AuditEntry[] createDTOArray(int size) {
			return new AuditEntry[size];
		}

		@Override
		protected void setField(
			AuditEntry auditEntry, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "action")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setAction(
						AuditEntry.Action.create((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "agentName")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setAgentName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "agentUID")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setAgentUID((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "auditSetId")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setAuditSetId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "field")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setField((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fieldClassLabel")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setFieldClassLabel((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fieldClassPK")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setFieldClassPK(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "newValue")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setNewValue((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "oldValue")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setOldValue((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "summary")) {
				if (jsonParserFieldValue != null) {
					auditEntry.setSummary((String)jsonParserFieldValue);
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