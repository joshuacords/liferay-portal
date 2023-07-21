/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

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
public class ContactAccountViewSerDes {

	public static ContactAccountView toDTO(String json) {
		ContactAccountViewJSONParser contactAccountViewJSONParser =
			new ContactAccountViewJSONParser();

		return contactAccountViewJSONParser.parseToDTO(json);
	}

	public static ContactAccountView[] toDTOs(String json) {
		ContactAccountViewJSONParser contactAccountViewJSONParser =
			new ContactAccountViewJSONParser();

		return contactAccountViewJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ContactAccountView contactAccountView) {
		if (contactAccountView == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (contactAccountView.getAccount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"account\": ");

			sb.append(String.valueOf(contactAccountView.getAccount()));
		}

		if (contactAccountView.getCustomerContactRoles() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"customerContactRoles\": ");

			sb.append("[");

			for (int i = 0;
				 i < contactAccountView.getCustomerContactRoles().length; i++) {

				sb.append(
					String.valueOf(
						contactAccountView.getCustomerContactRoles()[i]));

				if ((i + 1) <
						contactAccountView.getCustomerContactRoles().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (contactAccountView.getWorkerContactRoles() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workerContactRoles\": ");

			sb.append("[");

			for (int i = 0;
				 i < contactAccountView.getWorkerContactRoles().length; i++) {

				sb.append(
					String.valueOf(
						contactAccountView.getWorkerContactRoles()[i]));

				if ((i + 1) <
						contactAccountView.getWorkerContactRoles().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ContactAccountViewJSONParser contactAccountViewJSONParser =
			new ContactAccountViewJSONParser();

		return contactAccountViewJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ContactAccountView contactAccountView) {

		if (contactAccountView == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (contactAccountView.getAccount() == null) {
			map.put("account", null);
		}
		else {
			map.put("account", String.valueOf(contactAccountView.getAccount()));
		}

		if (contactAccountView.getCustomerContactRoles() == null) {
			map.put("customerContactRoles", null);
		}
		else {
			map.put(
				"customerContactRoles",
				String.valueOf(contactAccountView.getCustomerContactRoles()));
		}

		if (contactAccountView.getWorkerContactRoles() == null) {
			map.put("workerContactRoles", null);
		}
		else {
			map.put(
				"workerContactRoles",
				String.valueOf(contactAccountView.getWorkerContactRoles()));
		}

		return map;
	}

	public static class ContactAccountViewJSONParser
		extends BaseJSONParser<ContactAccountView> {

		@Override
		protected ContactAccountView createDTO() {
			return new ContactAccountView();
		}

		@Override
		protected ContactAccountView[] createDTOArray(int size) {
			return new ContactAccountView[size];
		}

		@Override
		protected void setField(
			ContactAccountView contactAccountView, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "account")) {
				if (jsonParserFieldValue != null) {
					contactAccountView.setAccount(
						AccountSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "customerContactRoles")) {

				if (jsonParserFieldValue != null) {
					contactAccountView.setCustomerContactRoles(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ContactRoleSerDes.toDTO((String)object)
						).toArray(
							size -> new ContactRole[size]
						));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "workerContactRoles")) {

				if (jsonParserFieldValue != null) {
					contactAccountView.setWorkerContactRoles(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ContactRoleSerDes.toDTO((String)object)
						).toArray(
							size -> new ContactRole[size]
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