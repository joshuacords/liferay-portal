/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
public class ProductPurchaseSerDes {

	public static ProductPurchase toDTO(String json) {
		ProductPurchaseJSONParser productPurchaseJSONParser =
			new ProductPurchaseJSONParser();

		return productPurchaseJSONParser.parseToDTO(json);
	}

	public static ProductPurchase[] toDTOs(String json) {
		ProductPurchaseJSONParser productPurchaseJSONParser =
			new ProductPurchaseJSONParser();

		return productPurchaseJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductPurchase productPurchase) {
		if (productPurchase == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (productPurchase.getAccountKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountKey\": ");

			sb.append("\"");

			sb.append(_escape(productPurchase.getAccountKey()));

			sb.append("\"");
		}

		if (productPurchase.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					productPurchase.getDateCreated()));

			sb.append("\"");
		}

		if (productPurchase.getEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(productPurchase.getEndDate()));

			sb.append("\"");
		}

		if (productPurchase.getExternalLinks() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalLinks\": ");

			sb.append("[");

			for (int i = 0; i < productPurchase.getExternalLinks().length;
				 i++) {

				sb.append(
					String.valueOf(productPurchase.getExternalLinks()[i]));

				if ((i + 1) < productPurchase.getExternalLinks().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productPurchase.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(productPurchase.getKey()));

			sb.append("\"");
		}

		if (productPurchase.getOriginalEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"originalEndDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					productPurchase.getOriginalEndDate()));

			sb.append("\"");
		}

		if (productPurchase.getPerpetual() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"perpetual\": ");

			sb.append(productPurchase.getPerpetual());
		}

		if (productPurchase.getProduct() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"product\": ");

			sb.append(String.valueOf(productPurchase.getProduct()));
		}

		if (productPurchase.getProductConsumptions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productConsumptions\": ");

			sb.append("[");

			for (int i = 0; i < productPurchase.getProductConsumptions().length;
				 i++) {

				sb.append(
					String.valueOf(
						productPurchase.getProductConsumptions()[i]));

				if ((i + 1) < productPurchase.getProductConsumptions().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productPurchase.getProductKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(productPurchase.getProductKey()));

			sb.append("\"");
		}

		if (productPurchase.getProperties() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"properties\": ");

			sb.append(_toJSON(productPurchase.getProperties()));
		}

		if (productPurchase.getQuantity() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"quantity\": ");

			sb.append(productPurchase.getQuantity());
		}

		if (productPurchase.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(productPurchase.getStartDate()));

			sb.append("\"");
		}

		if (productPurchase.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append("\"");

			sb.append(productPurchase.getStatus());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductPurchaseJSONParser productPurchaseJSONParser =
			new ProductPurchaseJSONParser();

		return productPurchaseJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(ProductPurchase productPurchase) {
		if (productPurchase == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (productPurchase.getAccountKey() == null) {
			map.put("accountKey", null);
		}
		else {
			map.put(
				"accountKey", String.valueOf(productPurchase.getAccountKey()));
		}

		if (productPurchase.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(
					productPurchase.getDateCreated()));
		}

		if (productPurchase.getEndDate() == null) {
			map.put("endDate", null);
		}
		else {
			map.put(
				"endDate",
				liferayToJSONDateFormat.format(productPurchase.getEndDate()));
		}

		if (productPurchase.getExternalLinks() == null) {
			map.put("externalLinks", null);
		}
		else {
			map.put(
				"externalLinks",
				String.valueOf(productPurchase.getExternalLinks()));
		}

		if (productPurchase.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(productPurchase.getKey()));
		}

		if (productPurchase.getOriginalEndDate() == null) {
			map.put("originalEndDate", null);
		}
		else {
			map.put(
				"originalEndDate",
				liferayToJSONDateFormat.format(
					productPurchase.getOriginalEndDate()));
		}

		if (productPurchase.getPerpetual() == null) {
			map.put("perpetual", null);
		}
		else {
			map.put(
				"perpetual", String.valueOf(productPurchase.getPerpetual()));
		}

		if (productPurchase.getProduct() == null) {
			map.put("product", null);
		}
		else {
			map.put("product", String.valueOf(productPurchase.getProduct()));
		}

		if (productPurchase.getProductConsumptions() == null) {
			map.put("productConsumptions", null);
		}
		else {
			map.put(
				"productConsumptions",
				String.valueOf(productPurchase.getProductConsumptions()));
		}

		if (productPurchase.getProductKey() == null) {
			map.put("productKey", null);
		}
		else {
			map.put(
				"productKey", String.valueOf(productPurchase.getProductKey()));
		}

		if (productPurchase.getProperties() == null) {
			map.put("properties", null);
		}
		else {
			map.put(
				"properties", String.valueOf(productPurchase.getProperties()));
		}

		if (productPurchase.getQuantity() == null) {
			map.put("quantity", null);
		}
		else {
			map.put("quantity", String.valueOf(productPurchase.getQuantity()));
		}

		if (productPurchase.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(productPurchase.getStartDate()));
		}

		if (productPurchase.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put("status", String.valueOf(productPurchase.getStatus()));
		}

		return map;
	}

	public static class ProductPurchaseJSONParser
		extends BaseJSONParser<ProductPurchase> {

		@Override
		protected ProductPurchase createDTO() {
			return new ProductPurchase();
		}

		@Override
		protected ProductPurchase[] createDTOArray(int size) {
			return new ProductPurchase[size];
		}

		@Override
		protected void setField(
			ProductPurchase productPurchase, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountKey")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setAccountKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "endDate")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setEndDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "externalLinks")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setExternalLinks(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ExternalLinkSerDes.toDTO((String)object)
						).toArray(
							size -> new ExternalLink[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "originalEndDate")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setOriginalEndDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "perpetual")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setPerpetual((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "product")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setProduct(
						ProductSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productConsumptions")) {

				if (jsonParserFieldValue != null) {
					productPurchase.setProductConsumptions(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ProductConsumptionSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new ProductConsumption[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productKey")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setProductKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "properties")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setProperties(
						(Map)ProductPurchaseSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "quantity")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setQuantity(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setStartDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					productPurchase.setStatus(
						ProductPurchase.Status.create(
							(String)jsonParserFieldValue));
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