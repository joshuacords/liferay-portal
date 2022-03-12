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

import com.liferay.osb.provisioning.rest.client.dto.v1_0.LicenseKey;
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
public class LicenseKeySerDes {

	public static LicenseKey toDTO(String json) {
		LicenseKeyJSONParser licenseKeyJSONParser = new LicenseKeyJSONParser();

		return licenseKeyJSONParser.parseToDTO(json);
	}

	public static LicenseKey[] toDTOs(String json) {
		LicenseKeyJSONParser licenseKeyJSONParser = new LicenseKeyJSONParser();

		return licenseKeyJSONParser.parseToDTOs(json);
	}

	public static String toJSON(LicenseKey licenseKey) {
		if (licenseKey == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (licenseKey.getAccountKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountKey\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getAccountKey()));

			sb.append("\"");
		}

		if (licenseKey.getAccountName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountName\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getAccountName()));

			sb.append("\"");
		}

		if (licenseKey.getActive() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(licenseKey.getActive());
		}

		if (licenseKey.getAdditionalInfo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"additionalInfo\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getAdditionalInfo()));

			sb.append("\"");
		}

		if (licenseKey.getAssetReceiptLicenseUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assetReceiptLicenseUuid\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getAssetReceiptLicenseUuid()));

			sb.append("\"");
		}

		if (licenseKey.getClusterId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"clusterId\": ");

			sb.append(licenseKey.getClusterId());
		}

		if (licenseKey.getComplimentary() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"complimentary\": ");

			sb.append(licenseKey.getComplimentary());
		}

		if (licenseKey.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(licenseKey.getCreateDate()));

			sb.append("\"");
		}

		if (licenseKey.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getDescription()));

			sb.append("\"");
		}

		if (licenseKey.getExpirationDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"expirationDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(licenseKey.getExpirationDate()));

			sb.append("\"");
		}

		if (licenseKey.getHostName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hostName\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getHostName()));

			sb.append("\"");
		}

		if (licenseKey.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(licenseKey.getId());
		}

		if (licenseKey.getIpAddresses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ipAddresses\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getIpAddresses()));

			sb.append("\"");
		}

		if (licenseKey.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getKey()));

			sb.append("\"");
		}

		if (licenseKey.getLicenseEntryName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryName\": ");

			sb.append("\"");

			sb.append(licenseKey.getLicenseEntryName());

			sb.append("\"");
		}

		if (licenseKey.getLicenseEntryType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryType\": ");

			sb.append("\"");

			sb.append(licenseKey.getLicenseEntryType());

			sb.append("\"");
		}

		if (licenseKey.getLicenseVersion() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseVersion\": ");

			sb.append(licenseKey.getLicenseVersion());
		}

		if (licenseKey.getMacAddresses() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"macAddresses\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getMacAddresses()));

			sb.append("\"");
		}

		if (licenseKey.getMaxClusterNodes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxClusterNodes\": ");

			sb.append(licenseKey.getMaxClusterNodes());
		}

		if (licenseKey.getMaxHttpSessions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxHttpSessions\": ");

			sb.append(licenseKey.getMaxHttpSessions());
		}

		if (licenseKey.getMaxServers() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxServers\": ");

			sb.append(licenseKey.getMaxServers());
		}

		if (licenseKey.getModifiedDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(licenseKey.getModifiedDate()));

			sb.append("\"");
		}

		if (licenseKey.getModifiedUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserName\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getModifiedUserName()));

			sb.append("\"");
		}

		if (licenseKey.getModifiedUserUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserUuid\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getModifiedUserUuid()));

			sb.append("\"");
		}

		if (licenseKey.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getName()));

			sb.append("\"");
		}

		if (licenseKey.getOwner() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"owner\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getOwner()));

			sb.append("\"");
		}

		if (licenseKey.getProductId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getProductId()));

			sb.append("\"");
		}

		if (licenseKey.getProductKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getProductKey()));

			sb.append("\"");
		}

		if (licenseKey.getProductName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productName\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getProductName()));

			sb.append("\"");
		}

		if (licenseKey.getProductPurchaseKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchaseKey\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getProductPurchaseKey()));

			sb.append("\"");
		}

		if (licenseKey.getProductVersion() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productVersion\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getProductVersion()));

			sb.append("\"");
		}

		if (licenseKey.getServerId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"serverId\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getServerId()));

			sb.append("\"");
		}

		if (licenseKey.getSizing() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sizing\": ");

			sb.append("\"");

			sb.append(licenseKey.getSizing());

			sb.append("\"");
		}

		if (licenseKey.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(licenseKey.getStartDate()));

			sb.append("\"");
		}

		if (licenseKey.getUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getUserName()));

			sb.append("\"");
		}

		if (licenseKey.getUserUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userUuid\": ");

			sb.append("\"");

			sb.append(_escape(licenseKey.getUserUuid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		LicenseKeyJSONParser licenseKeyJSONParser = new LicenseKeyJSONParser();

		return licenseKeyJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(LicenseKey licenseKey) {
		if (licenseKey == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (licenseKey.getAccountKey() == null) {
			map.put("accountKey", null);
		}
		else {
			map.put("accountKey", String.valueOf(licenseKey.getAccountKey()));
		}

		if (licenseKey.getAccountName() == null) {
			map.put("accountName", null);
		}
		else {
			map.put("accountName", String.valueOf(licenseKey.getAccountName()));
		}

		if (licenseKey.getActive() == null) {
			map.put("active", null);
		}
		else {
			map.put("active", String.valueOf(licenseKey.getActive()));
		}

		if (licenseKey.getAdditionalInfo() == null) {
			map.put("additionalInfo", null);
		}
		else {
			map.put(
				"additionalInfo",
				String.valueOf(licenseKey.getAdditionalInfo()));
		}

		if (licenseKey.getAssetReceiptLicenseUuid() == null) {
			map.put("assetReceiptLicenseUuid", null);
		}
		else {
			map.put(
				"assetReceiptLicenseUuid",
				String.valueOf(licenseKey.getAssetReceiptLicenseUuid()));
		}

		if (licenseKey.getClusterId() == null) {
			map.put("clusterId", null);
		}
		else {
			map.put("clusterId", String.valueOf(licenseKey.getClusterId()));
		}

		if (licenseKey.getComplimentary() == null) {
			map.put("complimentary", null);
		}
		else {
			map.put(
				"complimentary", String.valueOf(licenseKey.getComplimentary()));
		}

		if (licenseKey.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(licenseKey.getCreateDate()));
		}

		if (licenseKey.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put("description", String.valueOf(licenseKey.getDescription()));
		}

		if (licenseKey.getExpirationDate() == null) {
			map.put("expirationDate", null);
		}
		else {
			map.put(
				"expirationDate",
				liferayToJSONDateFormat.format(licenseKey.getExpirationDate()));
		}

		if (licenseKey.getHostName() == null) {
			map.put("hostName", null);
		}
		else {
			map.put("hostName", String.valueOf(licenseKey.getHostName()));
		}

		if (licenseKey.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(licenseKey.getId()));
		}

		if (licenseKey.getIpAddresses() == null) {
			map.put("ipAddresses", null);
		}
		else {
			map.put("ipAddresses", String.valueOf(licenseKey.getIpAddresses()));
		}

		if (licenseKey.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(licenseKey.getKey()));
		}

		if (licenseKey.getLicenseEntryName() == null) {
			map.put("licenseEntryName", null);
		}
		else {
			map.put(
				"licenseEntryName",
				String.valueOf(licenseKey.getLicenseEntryName()));
		}

		if (licenseKey.getLicenseEntryType() == null) {
			map.put("licenseEntryType", null);
		}
		else {
			map.put(
				"licenseEntryType",
				String.valueOf(licenseKey.getLicenseEntryType()));
		}

		if (licenseKey.getLicenseVersion() == null) {
			map.put("licenseVersion", null);
		}
		else {
			map.put(
				"licenseVersion",
				String.valueOf(licenseKey.getLicenseVersion()));
		}

		if (licenseKey.getMacAddresses() == null) {
			map.put("macAddresses", null);
		}
		else {
			map.put(
				"macAddresses", String.valueOf(licenseKey.getMacAddresses()));
		}

		if (licenseKey.getMaxClusterNodes() == null) {
			map.put("maxClusterNodes", null);
		}
		else {
			map.put(
				"maxClusterNodes",
				String.valueOf(licenseKey.getMaxClusterNodes()));
		}

		if (licenseKey.getMaxHttpSessions() == null) {
			map.put("maxHttpSessions", null);
		}
		else {
			map.put(
				"maxHttpSessions",
				String.valueOf(licenseKey.getMaxHttpSessions()));
		}

		if (licenseKey.getMaxServers() == null) {
			map.put("maxServers", null);
		}
		else {
			map.put("maxServers", String.valueOf(licenseKey.getMaxServers()));
		}

		if (licenseKey.getModifiedDate() == null) {
			map.put("modifiedDate", null);
		}
		else {
			map.put(
				"modifiedDate",
				liferayToJSONDateFormat.format(licenseKey.getModifiedDate()));
		}

		if (licenseKey.getModifiedUserName() == null) {
			map.put("modifiedUserName", null);
		}
		else {
			map.put(
				"modifiedUserName",
				String.valueOf(licenseKey.getModifiedUserName()));
		}

		if (licenseKey.getModifiedUserUuid() == null) {
			map.put("modifiedUserUuid", null);
		}
		else {
			map.put(
				"modifiedUserUuid",
				String.valueOf(licenseKey.getModifiedUserUuid()));
		}

		if (licenseKey.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(licenseKey.getName()));
		}

		if (licenseKey.getOwner() == null) {
			map.put("owner", null);
		}
		else {
			map.put("owner", String.valueOf(licenseKey.getOwner()));
		}

		if (licenseKey.getProductId() == null) {
			map.put("productId", null);
		}
		else {
			map.put("productId", String.valueOf(licenseKey.getProductId()));
		}

		if (licenseKey.getProductKey() == null) {
			map.put("productKey", null);
		}
		else {
			map.put("productKey", String.valueOf(licenseKey.getProductKey()));
		}

		if (licenseKey.getProductName() == null) {
			map.put("productName", null);
		}
		else {
			map.put("productName", String.valueOf(licenseKey.getProductName()));
		}

		if (licenseKey.getProductPurchaseKey() == null) {
			map.put("productPurchaseKey", null);
		}
		else {
			map.put(
				"productPurchaseKey",
				String.valueOf(licenseKey.getProductPurchaseKey()));
		}

		if (licenseKey.getProductVersion() == null) {
			map.put("productVersion", null);
		}
		else {
			map.put(
				"productVersion",
				String.valueOf(licenseKey.getProductVersion()));
		}

		if (licenseKey.getServerId() == null) {
			map.put("serverId", null);
		}
		else {
			map.put("serverId", String.valueOf(licenseKey.getServerId()));
		}

		if (licenseKey.getSizing() == null) {
			map.put("sizing", null);
		}
		else {
			map.put("sizing", String.valueOf(licenseKey.getSizing()));
		}

		if (licenseKey.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(licenseKey.getStartDate()));
		}

		if (licenseKey.getUserName() == null) {
			map.put("userName", null);
		}
		else {
			map.put("userName", String.valueOf(licenseKey.getUserName()));
		}

		if (licenseKey.getUserUuid() == null) {
			map.put("userUuid", null);
		}
		else {
			map.put("userUuid", String.valueOf(licenseKey.getUserUuid()));
		}

		return map;
	}

	public static class LicenseKeyJSONParser
		extends BaseJSONParser<LicenseKey> {

		@Override
		protected LicenseKey createDTO() {
			return new LicenseKey();
		}

		@Override
		protected LicenseKey[] createDTOArray(int size) {
			return new LicenseKey[size];
		}

		@Override
		protected void setField(
			LicenseKey licenseKey, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountKey")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setAccountKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "accountName")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setAccountName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "active")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setActive((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "additionalInfo")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setAdditionalInfo((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "assetReceiptLicenseUuid")) {

				if (jsonParserFieldValue != null) {
					licenseKey.setAssetReceiptLicenseUuid(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "clusterId")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setClusterId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "complimentary")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setComplimentary((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "expirationDate")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setExpirationDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "hostName")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setHostName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ipAddresses")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setIpAddresses((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseEntryName")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setLicenseEntryName(
						LicenseKey.LicenseEntryName.create(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseEntryType")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setLicenseEntryType(
						LicenseKey.LicenseEntryType.create(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "licenseVersion")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setLicenseVersion(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "macAddresses")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setMacAddresses((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "maxClusterNodes")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setMaxClusterNodes(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "maxHttpSessions")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setMaxHttpSessions(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "maxServers")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setMaxServers(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setModifiedDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedUserName")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setModifiedUserName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedUserUuid")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setModifiedUserUuid(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "owner")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setOwner((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productId")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setProductId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productKey")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setProductKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productName")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setProductName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productPurchaseKey")) {

				if (jsonParserFieldValue != null) {
					licenseKey.setProductPurchaseKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productVersion")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setProductVersion((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "serverId")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setServerId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "sizing")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setSizing(
						LicenseKey.Sizing.create((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setStartDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userName")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setUserName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userUuid")) {
				if (jsonParserFieldValue != null) {
					licenseKey.setUserUuid((String)jsonParserFieldValue);
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