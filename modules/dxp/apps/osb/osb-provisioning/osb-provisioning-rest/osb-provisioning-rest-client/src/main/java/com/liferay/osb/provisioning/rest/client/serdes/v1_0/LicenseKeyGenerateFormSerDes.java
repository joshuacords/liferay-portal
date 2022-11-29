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

package com.liferay.osb.provisioning.rest.client.serdes.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.LicenseKeyGenerateForm;
import com.liferay.osb.provisioning.rest.client.dto.v1_0.SubscriptionTerm;
import com.liferay.osb.provisioning.rest.client.dto.v1_0.Version;
import com.liferay.osb.provisioning.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class LicenseKeyGenerateFormSerDes {

	public static LicenseKeyGenerateForm toDTO(String json) {
		LicenseKeyGenerateFormJSONParser licenseKeyGenerateFormJSONParser =
			new LicenseKeyGenerateFormJSONParser();

		return licenseKeyGenerateFormJSONParser.parseToDTO(json);
	}

	public static LicenseKeyGenerateForm[] toDTOs(String json) {
		LicenseKeyGenerateFormJSONParser licenseKeyGenerateFormJSONParser =
			new LicenseKeyGenerateFormJSONParser();

		return licenseKeyGenerateFormJSONParser.parseToDTOs(json);
	}

	public static String toJSON(LicenseKeyGenerateForm licenseKeyGenerateForm) {
		if (licenseKeyGenerateForm == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (licenseKeyGenerateForm.getAllowComplimentary() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowComplimentary\": ");

			sb.append(licenseKeyGenerateForm.getAllowComplimentary());
		}

		if (licenseKeyGenerateForm.getAllowPermanentLicenses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"allowPermanentLicenses\": ");

			sb.append(licenseKeyGenerateForm.getAllowPermanentLicenses());
		}

		if (licenseKeyGenerateForm.getSubscriptionTerms() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subscriptionTerms\": ");

			sb.append("[");

			for (int i = 0;
				 i < licenseKeyGenerateForm.getSubscriptionTerms().length;
				 i++) {

				sb.append(
					String.valueOf(
						licenseKeyGenerateForm.getSubscriptionTerms()[i]));

				if ((i + 1) <
						licenseKeyGenerateForm.getSubscriptionTerms().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (licenseKeyGenerateForm.getVersions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"versions\": ");

			sb.append("[");

			for (int i = 0; i < licenseKeyGenerateForm.getVersions().length;
				 i++) {

				sb.append(
					String.valueOf(licenseKeyGenerateForm.getVersions()[i]));

				if ((i + 1) < licenseKeyGenerateForm.getVersions().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		LicenseKeyGenerateFormJSONParser licenseKeyGenerateFormJSONParser =
			new LicenseKeyGenerateFormJSONParser();

		return licenseKeyGenerateFormJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		LicenseKeyGenerateForm licenseKeyGenerateForm) {

		if (licenseKeyGenerateForm == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (licenseKeyGenerateForm.getAllowComplimentary() == null) {
			map.put("allowComplimentary", null);
		}
		else {
			map.put(
				"allowComplimentary",
				String.valueOf(licenseKeyGenerateForm.getAllowComplimentary()));
		}

		if (licenseKeyGenerateForm.getAllowPermanentLicenses() == null) {
			map.put("allowPermanentLicenses", null);
		}
		else {
			map.put(
				"allowPermanentLicenses",
				String.valueOf(
					licenseKeyGenerateForm.getAllowPermanentLicenses()));
		}

		if (licenseKeyGenerateForm.getSubscriptionTerms() == null) {
			map.put("subscriptionTerms", null);
		}
		else {
			map.put(
				"subscriptionTerms",
				String.valueOf(licenseKeyGenerateForm.getSubscriptionTerms()));
		}

		if (licenseKeyGenerateForm.getVersions() == null) {
			map.put("versions", null);
		}
		else {
			map.put(
				"versions",
				String.valueOf(licenseKeyGenerateForm.getVersions()));
		}

		return map;
	}

	public static class LicenseKeyGenerateFormJSONParser
		extends BaseJSONParser<LicenseKeyGenerateForm> {

		@Override
		protected LicenseKeyGenerateForm createDTO() {
			return new LicenseKeyGenerateForm();
		}

		@Override
		protected LicenseKeyGenerateForm[] createDTOArray(int size) {
			return new LicenseKeyGenerateForm[size];
		}

		@Override
		protected void setField(
			LicenseKeyGenerateForm licenseKeyGenerateForm,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "allowComplimentary")) {
				if (jsonParserFieldValue != null) {
					licenseKeyGenerateForm.setAllowComplimentary(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "allowPermanentLicenses")) {

				if (jsonParserFieldValue != null) {
					licenseKeyGenerateForm.setAllowPermanentLicenses(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "subscriptionTerms")) {
				if (jsonParserFieldValue != null) {
					licenseKeyGenerateForm.setSubscriptionTerms(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> SubscriptionTermSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new SubscriptionTerm[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "versions")) {
				if (jsonParserFieldValue != null) {
					licenseKeyGenerateForm.setVersions(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> VersionSerDes.toDTO((String)object)
						).toArray(
							size -> new Version[size]
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