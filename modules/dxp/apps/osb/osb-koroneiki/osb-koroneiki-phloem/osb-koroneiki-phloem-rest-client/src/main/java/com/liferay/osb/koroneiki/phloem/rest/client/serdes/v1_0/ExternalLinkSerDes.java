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

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
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
public class ExternalLinkSerDes {

	public static ExternalLink toDTO(String json) {
		ExternalLinkJSONParser externalLinkJSONParser =
			new ExternalLinkJSONParser();

		return externalLinkJSONParser.parseToDTO(json);
	}

	public static ExternalLink[] toDTOs(String json) {
		ExternalLinkJSONParser externalLinkJSONParser =
			new ExternalLinkJSONParser();

		return externalLinkJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ExternalLink externalLink) {
		if (externalLink == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (externalLink.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(externalLink.getDateCreated()));

			sb.append("\"");
		}

		if (externalLink.getDomain() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"domain\": ");

			sb.append("\"");

			sb.append(_escape(externalLink.getDomain()));

			sb.append("\"");
		}

		if (externalLink.getEntityId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entityId\": ");

			sb.append("\"");

			sb.append(_escape(externalLink.getEntityId()));

			sb.append("\"");
		}

		if (externalLink.getEntityName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"entityName\": ");

			sb.append("\"");

			sb.append(_escape(externalLink.getEntityName()));

			sb.append("\"");
		}

		if (externalLink.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(externalLink.getKey()));

			sb.append("\"");
		}

		if (externalLink.getUrl() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"url\": ");

			sb.append("\"");

			sb.append(_escape(externalLink.getUrl()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ExternalLinkJSONParser externalLinkJSONParser =
			new ExternalLinkJSONParser();

		return externalLinkJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ExternalLink externalLink) {
		if (externalLink == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (externalLink.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(externalLink.getDateCreated()));
		}

		if (externalLink.getDomain() == null) {
			map.put("domain", null);
		}
		else {
			map.put("domain", String.valueOf(externalLink.getDomain()));
		}

		if (externalLink.getEntityId() == null) {
			map.put("entityId", null);
		}
		else {
			map.put("entityId", String.valueOf(externalLink.getEntityId()));
		}

		if (externalLink.getEntityName() == null) {
			map.put("entityName", null);
		}
		else {
			map.put("entityName", String.valueOf(externalLink.getEntityName()));
		}

		if (externalLink.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(externalLink.getKey()));
		}

		if (externalLink.getUrl() == null) {
			map.put("url", null);
		}
		else {
			map.put("url", String.valueOf(externalLink.getUrl()));
		}

		return map;
	}

	public static class ExternalLinkJSONParser
		extends BaseJSONParser<ExternalLink> {

		@Override
		protected ExternalLink createDTO() {
			return new ExternalLink();
		}

		@Override
		protected ExternalLink[] createDTOArray(int size) {
			return new ExternalLink[size];
		}

		@Override
		protected void setField(
			ExternalLink externalLink, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					externalLink.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "domain")) {
				if (jsonParserFieldValue != null) {
					externalLink.setDomain((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "entityId")) {
				if (jsonParserFieldValue != null) {
					externalLink.setEntityId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "entityName")) {
				if (jsonParserFieldValue != null) {
					externalLink.setEntityName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					externalLink.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "url")) {
				if (jsonParserFieldValue != null) {
					externalLink.setUrl((String)jsonParserFieldValue);
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