/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.client.serdes.v1_0;

import com.liferay.headless.admin.user.client.dto.v1_0.ContactInformation;
import com.liferay.headless.admin.user.client.dto.v1_0.EmailAddress;
import com.liferay.headless.admin.user.client.dto.v1_0.Phone;
import com.liferay.headless.admin.user.client.dto.v1_0.PostalAddress;
import com.liferay.headless.admin.user.client.dto.v1_0.WebUrl;
import com.liferay.headless.admin.user.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class ContactInformationSerDes {

	public static ContactInformation toDTO(String json) {
		ContactInformationJSONParser contactInformationJSONParser =
			new ContactInformationJSONParser();

		return contactInformationJSONParser.parseToDTO(json);
	}

	public static ContactInformation[] toDTOs(String json) {
		ContactInformationJSONParser contactInformationJSONParser =
			new ContactInformationJSONParser();

		return contactInformationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ContactInformation contactInformation) {
		if (contactInformation == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (contactInformation.getEmailAddresses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"emailAddresses\": ");

			sb.append("[");

			for (int i = 0; i < contactInformation.getEmailAddresses().length;
				 i++) {

				sb.append(
					String.valueOf(contactInformation.getEmailAddresses()[i]));

				if ((i + 1) < contactInformation.getEmailAddresses().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (contactInformation.getFacebook() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"facebook\": ");

			sb.append("\"");

			sb.append(_escape(contactInformation.getFacebook()));

			sb.append("\"");
		}

		if (contactInformation.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(contactInformation.getId());
		}

		if (contactInformation.getJabber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"jabber\": ");

			sb.append("\"");

			sb.append(_escape(contactInformation.getJabber()));

			sb.append("\"");
		}

		if (contactInformation.getPostalAddresses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"postalAddresses\": ");

			sb.append("[");

			for (int i = 0; i < contactInformation.getPostalAddresses().length;
				 i++) {

				sb.append(
					String.valueOf(contactInformation.getPostalAddresses()[i]));

				if ((i + 1) < contactInformation.getPostalAddresses().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (contactInformation.getSkype() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"skype\": ");

			sb.append("\"");

			sb.append(_escape(contactInformation.getSkype()));

			sb.append("\"");
		}

		if (contactInformation.getSms() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sms\": ");

			sb.append("\"");

			sb.append(_escape(contactInformation.getSms()));

			sb.append("\"");
		}

		if (contactInformation.getTelephones() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"telephones\": ");

			sb.append("[");

			for (int i = 0; i < contactInformation.getTelephones().length;
				 i++) {

				sb.append(
					String.valueOf(contactInformation.getTelephones()[i]));

				if ((i + 1) < contactInformation.getTelephones().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (contactInformation.getTwitter() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"twitter\": ");

			sb.append("\"");

			sb.append(_escape(contactInformation.getTwitter()));

			sb.append("\"");
		}

		if (contactInformation.getWebUrls() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"webUrls\": ");

			sb.append("[");

			for (int i = 0; i < contactInformation.getWebUrls().length; i++) {
				sb.append(String.valueOf(contactInformation.getWebUrls()[i]));

				if ((i + 1) < contactInformation.getWebUrls().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ContactInformationJSONParser contactInformationJSONParser =
			new ContactInformationJSONParser();

		return contactInformationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ContactInformation contactInformation) {

		if (contactInformation == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (contactInformation.getEmailAddresses() == null) {
			map.put("emailAddresses", null);
		}
		else {
			map.put(
				"emailAddresses",
				String.valueOf(contactInformation.getEmailAddresses()));
		}

		if (contactInformation.getFacebook() == null) {
			map.put("facebook", null);
		}
		else {
			map.put(
				"facebook", String.valueOf(contactInformation.getFacebook()));
		}

		if (contactInformation.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(contactInformation.getId()));
		}

		if (contactInformation.getJabber() == null) {
			map.put("jabber", null);
		}
		else {
			map.put("jabber", String.valueOf(contactInformation.getJabber()));
		}

		if (contactInformation.getPostalAddresses() == null) {
			map.put("postalAddresses", null);
		}
		else {
			map.put(
				"postalAddresses",
				String.valueOf(contactInformation.getPostalAddresses()));
		}

		if (contactInformation.getSkype() == null) {
			map.put("skype", null);
		}
		else {
			map.put("skype", String.valueOf(contactInformation.getSkype()));
		}

		if (contactInformation.getSms() == null) {
			map.put("sms", null);
		}
		else {
			map.put("sms", String.valueOf(contactInformation.getSms()));
		}

		if (contactInformation.getTelephones() == null) {
			map.put("telephones", null);
		}
		else {
			map.put(
				"telephones",
				String.valueOf(contactInformation.getTelephones()));
		}

		if (contactInformation.getTwitter() == null) {
			map.put("twitter", null);
		}
		else {
			map.put("twitter", String.valueOf(contactInformation.getTwitter()));
		}

		if (contactInformation.getWebUrls() == null) {
			map.put("webUrls", null);
		}
		else {
			map.put("webUrls", String.valueOf(contactInformation.getWebUrls()));
		}

		return map;
	}

	public static class ContactInformationJSONParser
		extends BaseJSONParser<ContactInformation> {

		@Override
		protected ContactInformation createDTO() {
			return new ContactInformation();
		}

		@Override
		protected ContactInformation[] createDTOArray(int size) {
			return new ContactInformation[size];
		}

		@Override
		protected void setField(
			ContactInformation contactInformation, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "emailAddresses")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					EmailAddress[] emailAddressesArray =
						new EmailAddress[jsonParserFieldValues.length];

					for (int i = 0; i < emailAddressesArray.length; i++) {
						emailAddressesArray[i] = EmailAddressSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					contactInformation.setEmailAddresses(emailAddressesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "facebook")) {
				if (jsonParserFieldValue != null) {
					contactInformation.setFacebook(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					contactInformation.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "jabber")) {
				if (jsonParserFieldValue != null) {
					contactInformation.setJabber((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "postalAddresses")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					PostalAddress[] postalAddressesArray =
						new PostalAddress[jsonParserFieldValues.length];

					for (int i = 0; i < postalAddressesArray.length; i++) {
						postalAddressesArray[i] = PostalAddressSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					contactInformation.setPostalAddresses(postalAddressesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "skype")) {
				if (jsonParserFieldValue != null) {
					contactInformation.setSkype((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "sms")) {
				if (jsonParserFieldValue != null) {
					contactInformation.setSms((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "telephones")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Phone[] telephonesArray =
						new Phone[jsonParserFieldValues.length];

					for (int i = 0; i < telephonesArray.length; i++) {
						telephonesArray[i] = PhoneSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					contactInformation.setTelephones(telephonesArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "twitter")) {
				if (jsonParserFieldValue != null) {
					contactInformation.setTwitter((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "webUrls")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					WebUrl[] webUrlsArray =
						new WebUrl[jsonParserFieldValues.length];

					for (int i = 0; i < webUrlsArray.length; i++) {
						webUrlsArray[i] = WebUrlSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					contactInformation.setWebUrls(webUrlsArray);
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