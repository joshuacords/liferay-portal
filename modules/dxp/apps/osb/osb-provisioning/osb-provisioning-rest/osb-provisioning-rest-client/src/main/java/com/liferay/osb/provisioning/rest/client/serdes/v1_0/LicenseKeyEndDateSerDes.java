/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.serdes.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.LicenseKeyEndDate;
import com.liferay.osb.provisioning.rest.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class LicenseKeyEndDateSerDes {

	public static LicenseKeyEndDate toDTO(String json) {
		LicenseKeyEndDateJSONParser licenseKeyEndDateJSONParser =
			new LicenseKeyEndDateJSONParser();

		return licenseKeyEndDateJSONParser.parseToDTO(json);
	}

	public static LicenseKeyEndDate[] toDTOs(String json) {
		LicenseKeyEndDateJSONParser licenseKeyEndDateJSONParser =
			new LicenseKeyEndDateJSONParser();

		return licenseKeyEndDateJSONParser.parseToDTOs(json);
	}

	public static String toJSON(LicenseKeyEndDate licenseKeyEndDate) {
		if (licenseKeyEndDate == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (licenseKeyEndDate.getEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(licenseKeyEndDate.getEndDate()));

			sb.append("\"");
		}

		if (licenseKeyEndDate.getLicenseEntryType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryType\": ");

			sb.append("\"");

			sb.append(_escape(licenseKeyEndDate.getLicenseEntryType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		LicenseKeyEndDateJSONParser licenseKeyEndDateJSONParser =
			new LicenseKeyEndDateJSONParser();

		return licenseKeyEndDateJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		LicenseKeyEndDate licenseKeyEndDate) {

		if (licenseKeyEndDate == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (licenseKeyEndDate.getEndDate() == null) {
			map.put("endDate", null);
		}
		else {
			map.put(
				"endDate",
				liferayToJSONDateFormat.format(licenseKeyEndDate.getEndDate()));
		}

		if (licenseKeyEndDate.getLicenseEntryType() == null) {
			map.put("licenseEntryType", null);
		}
		else {
			map.put(
				"licenseEntryType",
				String.valueOf(licenseKeyEndDate.getLicenseEntryType()));
		}

		return map;
	}

	public static class LicenseKeyEndDateJSONParser
		extends BaseJSONParser<LicenseKeyEndDate> {

		@Override
		protected LicenseKeyEndDate createDTO() {
			return new LicenseKeyEndDate();
		}

		@Override
		protected LicenseKeyEndDate[] createDTOArray(int size) {
			return new LicenseKeyEndDate[size];
		}

		@Override
		protected void setField(
			LicenseKeyEndDate licenseKeyEndDate, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "endDate")) {
				if (jsonParserFieldValue != null) {
					licenseKeyEndDate.setEndDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseEntryType")) {
				if (jsonParserFieldValue != null) {
					licenseKeyEndDate.setLicenseEntryType(
						(String)jsonParserFieldValue);
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