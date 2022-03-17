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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
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
public class ProductConsumptionSerDes {

	public static ProductConsumption toDTO(String json) {
		ProductConsumptionJSONParser productConsumptionJSONParser =
			new ProductConsumptionJSONParser();

		return productConsumptionJSONParser.parseToDTO(json);
	}

	public static ProductConsumption[] toDTOs(String json) {
		ProductConsumptionJSONParser productConsumptionJSONParser =
			new ProductConsumptionJSONParser();

		return productConsumptionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductConsumption productConsumption) {
		if (productConsumption == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (productConsumption.getAccountKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountKey\": ");

			sb.append("\"");

			sb.append(_escape(productConsumption.getAccountKey()));

			sb.append("\"");
		}

		if (productConsumption.getDateCreated() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dateCreated\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					productConsumption.getDateCreated()));

			sb.append("\"");
		}

		if (productConsumption.getEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					productConsumption.getEndDate()));

			sb.append("\"");
		}

		if (productConsumption.getExternalLinks() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalLinks\": ");

			sb.append("[");

			for (int i = 0; i < productConsumption.getExternalLinks().length;
				 i++) {

				sb.append(
					String.valueOf(productConsumption.getExternalLinks()[i]));

				if ((i + 1) < productConsumption.getExternalLinks().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productConsumption.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(productConsumption.getKey()));

			sb.append("\"");
		}

		if (productConsumption.getProductKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(productConsumption.getProductKey()));

			sb.append("\"");
		}

		if (productConsumption.getProductPurchaseKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchaseKey\": ");

			sb.append("\"");

			sb.append(_escape(productConsumption.getProductPurchaseKey()));

			sb.append("\"");
		}

		if (productConsumption.getProperties() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"properties\": ");

			sb.append(_toJSON(productConsumption.getProperties()));
		}

		if (productConsumption.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					productConsumption.getStartDate()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductConsumptionJSONParser productConsumptionJSONParser =
			new ProductConsumptionJSONParser();

		return productConsumptionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductConsumption productConsumption) {

		if (productConsumption == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (productConsumption.getAccountKey() == null) {
			map.put("accountKey", null);
		}
		else {
			map.put(
				"accountKey",
				String.valueOf(productConsumption.getAccountKey()));
		}

		if (productConsumption.getDateCreated() == null) {
			map.put("dateCreated", null);
		}
		else {
			map.put(
				"dateCreated",
				liferayToJSONDateFormat.format(
					productConsumption.getDateCreated()));
		}

		if (productConsumption.getEndDate() == null) {
			map.put("endDate", null);
		}
		else {
			map.put(
				"endDate",
				liferayToJSONDateFormat.format(
					productConsumption.getEndDate()));
		}

		if (productConsumption.getExternalLinks() == null) {
			map.put("externalLinks", null);
		}
		else {
			map.put(
				"externalLinks",
				String.valueOf(productConsumption.getExternalLinks()));
		}

		if (productConsumption.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(productConsumption.getKey()));
		}

		if (productConsumption.getProductKey() == null) {
			map.put("productKey", null);
		}
		else {
			map.put(
				"productKey",
				String.valueOf(productConsumption.getProductKey()));
		}

		if (productConsumption.getProductPurchaseKey() == null) {
			map.put("productPurchaseKey", null);
		}
		else {
			map.put(
				"productPurchaseKey",
				String.valueOf(productConsumption.getProductPurchaseKey()));
		}

		if (productConsumption.getProperties() == null) {
			map.put("properties", null);
		}
		else {
			map.put(
				"properties",
				String.valueOf(productConsumption.getProperties()));
		}

		if (productConsumption.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(
					productConsumption.getStartDate()));
		}

		return map;
	}

	public static class ProductConsumptionJSONParser
		extends BaseJSONParser<ProductConsumption> {

		@Override
		protected ProductConsumption createDTO() {
			return new ProductConsumption();
		}

		@Override
		protected ProductConsumption[] createDTOArray(int size) {
			return new ProductConsumption[size];
		}

		@Override
		protected void setField(
			ProductConsumption productConsumption, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountKey")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setAccountKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "endDate")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setEndDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "externalLinks")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setExternalLinks(
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
					productConsumption.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "productKey")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setProductKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productPurchaseKey")) {

				if (jsonParserFieldValue != null) {
					productConsumption.setProductPurchaseKey(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "properties")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setProperties(
						(Map)ProductConsumptionSerDes.toMap(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					productConsumption.setStartDate(
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