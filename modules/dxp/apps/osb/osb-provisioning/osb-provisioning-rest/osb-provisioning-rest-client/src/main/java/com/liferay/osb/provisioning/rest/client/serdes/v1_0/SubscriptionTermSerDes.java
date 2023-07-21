/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.serdes.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.SubscriptionTerm;
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
public class SubscriptionTermSerDes {

	public static SubscriptionTerm toDTO(String json) {
		SubscriptionTermJSONParser subscriptionTermJSONParser =
			new SubscriptionTermJSONParser();

		return subscriptionTermJSONParser.parseToDTO(json);
	}

	public static SubscriptionTerm[] toDTOs(String json) {
		SubscriptionTermJSONParser subscriptionTermJSONParser =
			new SubscriptionTermJSONParser();

		return subscriptionTermJSONParser.parseToDTOs(json);
	}

	public static String toJSON(SubscriptionTerm subscriptionTerm) {
		if (subscriptionTerm == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (subscriptionTerm.getEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(subscriptionTerm.getEndDate()));

			sb.append("\"");
		}

		if (subscriptionTerm.getInstanceSize() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"instanceSize\": ");

			sb.append(subscriptionTerm.getInstanceSize());
		}

		if (subscriptionTerm.getLicenseKeyEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseKeyEndDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					subscriptionTerm.getLicenseKeyEndDate()));

			sb.append("\"");
		}

		if (subscriptionTerm.getPerpetual() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"perpetual\": ");

			sb.append(subscriptionTerm.getPerpetual());
		}

		if (subscriptionTerm.getProductKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(subscriptionTerm.getProductKey()));

			sb.append("\"");
		}

		if (subscriptionTerm.getProductPurchaseKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchaseKey\": ");

			sb.append("\"");

			sb.append(_escape(subscriptionTerm.getProductPurchaseKey()));

			sb.append("\"");
		}

		if (subscriptionTerm.getProvisionedCount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"provisionedCount\": ");

			sb.append(subscriptionTerm.getProvisionedCount());
		}

		if (subscriptionTerm.getQuantity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"quantity\": ");

			sb.append(subscriptionTerm.getQuantity());
		}

		if (subscriptionTerm.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					subscriptionTerm.getStartDate()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SubscriptionTermJSONParser subscriptionTermJSONParser =
			new SubscriptionTermJSONParser();

		return subscriptionTermJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(SubscriptionTerm subscriptionTerm) {
		if (subscriptionTerm == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (subscriptionTerm.getEndDate() == null) {
			map.put("endDate", null);
		}
		else {
			map.put(
				"endDate",
				liferayToJSONDateFormat.format(subscriptionTerm.getEndDate()));
		}

		if (subscriptionTerm.getInstanceSize() == null) {
			map.put("instanceSize", null);
		}
		else {
			map.put(
				"instanceSize",
				String.valueOf(subscriptionTerm.getInstanceSize()));
		}

		if (subscriptionTerm.getLicenseKeyEndDate() == null) {
			map.put("licenseKeyEndDate", null);
		}
		else {
			map.put(
				"licenseKeyEndDate",
				liferayToJSONDateFormat.format(
					subscriptionTerm.getLicenseKeyEndDate()));
		}

		if (subscriptionTerm.getPerpetual() == null) {
			map.put("perpetual", null);
		}
		else {
			map.put(
				"perpetual", String.valueOf(subscriptionTerm.getPerpetual()));
		}

		if (subscriptionTerm.getProductKey() == null) {
			map.put("productKey", null);
		}
		else {
			map.put(
				"productKey", String.valueOf(subscriptionTerm.getProductKey()));
		}

		if (subscriptionTerm.getProductPurchaseKey() == null) {
			map.put("productPurchaseKey", null);
		}
		else {
			map.put(
				"productPurchaseKey",
				String.valueOf(subscriptionTerm.getProductPurchaseKey()));
		}

		if (subscriptionTerm.getProvisionedCount() == null) {
			map.put("provisionedCount", null);
		}
		else {
			map.put(
				"provisionedCount",
				String.valueOf(subscriptionTerm.getProvisionedCount()));
		}

		if (subscriptionTerm.getQuantity() == null) {
			map.put("quantity", null);
		}
		else {
			map.put("quantity", String.valueOf(subscriptionTerm.getQuantity()));
		}

		if (subscriptionTerm.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(
					subscriptionTerm.getStartDate()));
		}

		return map;
	}

	public static class SubscriptionTermJSONParser
		extends BaseJSONParser<SubscriptionTerm> {

		@Override
		protected SubscriptionTerm createDTO() {
			return new SubscriptionTerm();
		}

		@Override
		protected SubscriptionTerm[] createDTOArray(int size) {
			return new SubscriptionTerm[size];
		}

		@Override
		protected void setField(
			SubscriptionTerm subscriptionTerm, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "endDate")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setEndDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "instanceSize")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setInstanceSize(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseKeyEndDate")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setLicenseKeyEndDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "perpetual")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setPerpetual(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productKey")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setProductKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productPurchaseKey")) {

				if (jsonParserFieldValue != null) {
					subscriptionTerm.setProductPurchaseKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "provisionedCount")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setProvisionedCount(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "quantity")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setQuantity(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					subscriptionTerm.setStartDate(
						toDate((String)jsonParserFieldValue));
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