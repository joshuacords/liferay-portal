/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.client.serdes.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.AppLicenseKey;
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
public class AppLicenseKeySerDes {

	public static AppLicenseKey toDTO(String json) {
		AppLicenseKeyJSONParser appLicenseKeyJSONParser =
			new AppLicenseKeyJSONParser();

		return appLicenseKeyJSONParser.parseToDTO(json);
	}

	public static AppLicenseKey[] toDTOs(String json) {
		AppLicenseKeyJSONParser appLicenseKeyJSONParser =
			new AppLicenseKeyJSONParser();

		return appLicenseKeyJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AppLicenseKey appLicenseKey) {
		if (appLicenseKey == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (appLicenseKey.getActive() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(appLicenseKey.getActive());
		}

		if (appLicenseKey.getComplimentary() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"complimentary\": ");

			sb.append(appLicenseKey.getComplimentary());
		}

		if (appLicenseKey.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(appLicenseKey.getCreateDate()));

			sb.append("\"");
		}

		if (appLicenseKey.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getDescription()));

			sb.append("\"");
		}

		if (appLicenseKey.getExpirationDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"expirationDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					appLicenseKey.getExpirationDate()));

			sb.append("\"");
		}

		if (appLicenseKey.getHostName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hostName\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getHostName()));

			sb.append("\"");
		}

		if (appLicenseKey.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(appLicenseKey.getId());
		}

		if (appLicenseKey.getIpAddresses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ipAddresses\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getIpAddresses()));

			sb.append("\"");
		}

		if (appLicenseKey.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getKey()));

			sb.append("\"");
		}

		if (appLicenseKey.getLicenseType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseType\": ");

			sb.append("\"");

			sb.append(appLicenseKey.getLicenseType());

			sb.append("\"");
		}

		if (appLicenseKey.getMacAddresses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"macAddresses\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getMacAddresses()));

			sb.append("\"");
		}

		if (appLicenseKey.getModifiedDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					appLicenseKey.getModifiedDate()));

			sb.append("\"");
		}

		if (appLicenseKey.getModifiedUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserName\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getModifiedUserName()));

			sb.append("\"");
		}

		if (appLicenseKey.getModifiedUserUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserUuid\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getModifiedUserUuid()));

			sb.append("\"");
		}

		if (appLicenseKey.getOrderId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"orderId\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getOrderId()));

			sb.append("\"");
		}

		if (appLicenseKey.getOwner() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"owner\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getOwner()));

			sb.append("\"");
		}

		if (appLicenseKey.getProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getProductId()));

			sb.append("\"");
		}

		if (appLicenseKey.getProductName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productName\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getProductName()));

			sb.append("\"");
		}

		if (appLicenseKey.getProductVersion() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productVersion\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getProductVersion()));

			sb.append("\"");
		}

		if (appLicenseKey.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(appLicenseKey.getStartDate()));

			sb.append("\"");
		}

		if (appLicenseKey.getUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getUserName()));

			sb.append("\"");
		}

		if (appLicenseKey.getUserUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userUuid\": ");

			sb.append("\"");

			sb.append(_escape(appLicenseKey.getUserUuid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AppLicenseKeyJSONParser appLicenseKeyJSONParser =
			new AppLicenseKeyJSONParser();

		return appLicenseKeyJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AppLicenseKey appLicenseKey) {
		if (appLicenseKey == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (appLicenseKey.getActive() == null) {
			map.put("active", null);
		}
		else {
			map.put("active", String.valueOf(appLicenseKey.getActive()));
		}

		if (appLicenseKey.getComplimentary() == null) {
			map.put("complimentary", null);
		}
		else {
			map.put(
				"complimentary",
				String.valueOf(appLicenseKey.getComplimentary()));
		}

		if (appLicenseKey.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(appLicenseKey.getCreateDate()));
		}

		if (appLicenseKey.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(appLicenseKey.getDescription()));
		}

		if (appLicenseKey.getExpirationDate() == null) {
			map.put("expirationDate", null);
		}
		else {
			map.put(
				"expirationDate",
				liferayToJSONDateFormat.format(
					appLicenseKey.getExpirationDate()));
		}

		if (appLicenseKey.getHostName() == null) {
			map.put("hostName", null);
		}
		else {
			map.put("hostName", String.valueOf(appLicenseKey.getHostName()));
		}

		if (appLicenseKey.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(appLicenseKey.getId()));
		}

		if (appLicenseKey.getIpAddresses() == null) {
			map.put("ipAddresses", null);
		}
		else {
			map.put(
				"ipAddresses", String.valueOf(appLicenseKey.getIpAddresses()));
		}

		if (appLicenseKey.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(appLicenseKey.getKey()));
		}

		if (appLicenseKey.getLicenseType() == null) {
			map.put("licenseType", null);
		}
		else {
			map.put(
				"licenseType", String.valueOf(appLicenseKey.getLicenseType()));
		}

		if (appLicenseKey.getMacAddresses() == null) {
			map.put("macAddresses", null);
		}
		else {
			map.put(
				"macAddresses",
				String.valueOf(appLicenseKey.getMacAddresses()));
		}

		if (appLicenseKey.getModifiedDate() == null) {
			map.put("modifiedDate", null);
		}
		else {
			map.put(
				"modifiedDate",
				liferayToJSONDateFormat.format(
					appLicenseKey.getModifiedDate()));
		}

		if (appLicenseKey.getModifiedUserName() == null) {
			map.put("modifiedUserName", null);
		}
		else {
			map.put(
				"modifiedUserName",
				String.valueOf(appLicenseKey.getModifiedUserName()));
		}

		if (appLicenseKey.getModifiedUserUuid() == null) {
			map.put("modifiedUserUuid", null);
		}
		else {
			map.put(
				"modifiedUserUuid",
				String.valueOf(appLicenseKey.getModifiedUserUuid()));
		}

		if (appLicenseKey.getOrderId() == null) {
			map.put("orderId", null);
		}
		else {
			map.put("orderId", String.valueOf(appLicenseKey.getOrderId()));
		}

		if (appLicenseKey.getOwner() == null) {
			map.put("owner", null);
		}
		else {
			map.put("owner", String.valueOf(appLicenseKey.getOwner()));
		}

		if (appLicenseKey.getProductId() == null) {
			map.put("productId", null);
		}
		else {
			map.put("productId", String.valueOf(appLicenseKey.getProductId()));
		}

		if (appLicenseKey.getProductName() == null) {
			map.put("productName", null);
		}
		else {
			map.put(
				"productName", String.valueOf(appLicenseKey.getProductName()));
		}

		if (appLicenseKey.getProductVersion() == null) {
			map.put("productVersion", null);
		}
		else {
			map.put(
				"productVersion",
				String.valueOf(appLicenseKey.getProductVersion()));
		}

		if (appLicenseKey.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(appLicenseKey.getStartDate()));
		}

		if (appLicenseKey.getUserName() == null) {
			map.put("userName", null);
		}
		else {
			map.put("userName", String.valueOf(appLicenseKey.getUserName()));
		}

		if (appLicenseKey.getUserUuid() == null) {
			map.put("userUuid", null);
		}
		else {
			map.put("userUuid", String.valueOf(appLicenseKey.getUserUuid()));
		}

		return map;
	}

	public static class AppLicenseKeyJSONParser
		extends BaseJSONParser<AppLicenseKey> {

		@Override
		protected AppLicenseKey createDTO() {
			return new AppLicenseKey();
		}

		@Override
		protected AppLicenseKey[] createDTOArray(int size) {
			return new AppLicenseKey[size];
		}

		@Override
		protected void setField(
			AppLicenseKey appLicenseKey, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "active")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setActive((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "complimentary")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setComplimentary(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "expirationDate")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setExpirationDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "hostName")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setHostName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ipAddresses")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setIpAddresses((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseType")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setLicenseType(
						AppLicenseKey.LicenseType.create(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "macAddresses")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setMacAddresses((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setModifiedDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedUserName")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setModifiedUserName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedUserUuid")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setModifiedUserUuid(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "orderId")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setOrderId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "owner")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setOwner((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productId")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setProductId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productName")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setProductName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productVersion")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setProductVersion(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setStartDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userName")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setUserName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userUuid")) {
				if (jsonParserFieldValue != null) {
					appLicenseKey.setUserUuid((String)jsonParserFieldValue);
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