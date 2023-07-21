/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
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
public class NoteSerDes {

	public static Note toDTO(String json) {
		NoteJSONParser noteJSONParser = new NoteJSONParser();

		return noteJSONParser.parseToDTO(json);
	}

	public static Note[] toDTOs(String json) {
		NoteJSONParser noteJSONParser = new NoteJSONParser();

		return noteJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Note note) {
		if (note == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (note.getContent() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"content\": ");

			sb.append("\"");

			sb.append(_escape(note.getContent()));

			sb.append("\"");
		}

		if (note.getCreatorName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creatorName\": ");

			sb.append("\"");

			sb.append(_escape(note.getCreatorName()));

			sb.append("\"");
		}

		if (note.getCreatorUID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creatorUID\": ");

			sb.append("\"");

			sb.append(_escape(note.getCreatorUID()));

			sb.append("\"");
		}

		if (note.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(note.getDateCreated()));

			sb.append("\"");
		}

		if (note.getDateModified() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateModified\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(note.getDateModified()));

			sb.append("\"");
		}

		if (note.getFormat() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"format\": ");

			sb.append("\"");

			sb.append(note.getFormat());

			sb.append("\"");
		}

		if (note.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(note.getKey()));

			sb.append("\"");
		}

		if (note.getModifierName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifierName\": ");

			sb.append("\"");

			sb.append(_escape(note.getModifierName()));

			sb.append("\"");
		}

		if (note.getModifierUID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifierUID\": ");

			sb.append("\"");

			sb.append(_escape(note.getModifierUID()));

			sb.append("\"");
		}

		if (note.getPriority() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"priority\": ");

			sb.append(note.getPriority());
		}

		if (note.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(note.getStatus());

			sb.append("\"");
		}

		if (note.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(note.getType());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		NoteJSONParser noteJSONParser = new NoteJSONParser();

		return noteJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Note note) {
		if (note == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (note.getContent() == null) {
			map.put("content", null);
		}
		else {
			map.put("content", String.valueOf(note.getContent()));
		}

		if (note.getCreatorName() == null) {
			map.put("creatorName", null);
		}
		else {
			map.put("creatorName", String.valueOf(note.getCreatorName()));
		}

		if (note.getCreatorUID() == null) {
			map.put("creatorUID", null);
		}
		else {
			map.put("creatorUID", String.valueOf(note.getCreatorUID()));
		}

		if (note.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(note.getDateCreated()));
		}

		if (note.getDateModified() == null) {
			map.put("dateModified", null);
		}
		else {
			map.put(
				"dateModified",
				liferayToJSONDateFormat.format(note.getDateModified()));
		}

		if (note.getFormat() == null) {
			map.put("format", null);
		}
		else {
			map.put("format", String.valueOf(note.getFormat()));
		}

		if (note.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(note.getKey()));
		}

		if (note.getModifierName() == null) {
			map.put("modifierName", null);
		}
		else {
			map.put("modifierName", String.valueOf(note.getModifierName()));
		}

		if (note.getModifierUID() == null) {
			map.put("modifierUID", null);
		}
		else {
			map.put("modifierUID", String.valueOf(note.getModifierUID()));
		}

		if (note.getPriority() == null) {
			map.put("priority", null);
		}
		else {
			map.put("priority", String.valueOf(note.getPriority()));
		}

		if (note.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put("status", String.valueOf(note.getStatus()));
		}

		if (note.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(note.getType()));
		}

		return map;
	}

	public static class NoteJSONParser extends BaseJSONParser<Note> {

		@Override
		protected Note createDTO() {
			return new Note();
		}

		@Override
		protected Note[] createDTOArray(int size) {
			return new Note[size];
		}

		@Override
		protected void setField(
			Note note, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "content")) {
				if (jsonParserFieldValue != null) {
					note.setContent((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creatorName")) {
				if (jsonParserFieldValue != null) {
					note.setCreatorName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creatorUID")) {
				if (jsonParserFieldValue != null) {
					note.setCreatorUID((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					note.setDateCreated(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					note.setDateModified(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "format")) {
				if (jsonParserFieldValue != null) {
					note.setFormat(
						Note.Format.create((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					note.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifierName")) {
				if (jsonParserFieldValue != null) {
					note.setModifierName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifierUID")) {
				if (jsonParserFieldValue != null) {
					note.setModifierUID((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "priority")) {
				if (jsonParserFieldValue != null) {
					note.setPriority(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					note.setStatus(
						Note.Status.create((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					note.setType(
						Note.Type.create((String)jsonParserFieldValue));
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