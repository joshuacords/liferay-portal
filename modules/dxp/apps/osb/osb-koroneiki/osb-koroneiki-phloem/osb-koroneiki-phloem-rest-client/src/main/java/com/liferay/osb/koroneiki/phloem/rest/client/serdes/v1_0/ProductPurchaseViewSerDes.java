/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
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
public class ProductPurchaseViewSerDes {

	public static ProductPurchaseView toDTO(String json) {
		ProductPurchaseViewJSONParser productPurchaseViewJSONParser =
			new ProductPurchaseViewJSONParser();

		return productPurchaseViewJSONParser.parseToDTO(json);
	}

	public static ProductPurchaseView[] toDTOs(String json) {
		ProductPurchaseViewJSONParser productPurchaseViewJSONParser =
			new ProductPurchaseViewJSONParser();

		return productPurchaseViewJSONParser.parseToDTOs(json);
	}

	public static String toJSON(ProductPurchaseView productPurchaseView) {
		if (productPurchaseView == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (productPurchaseView.getProduct() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"product\": ");

			sb.append(String.valueOf(productPurchaseView.getProduct()));
		}

		if (productPurchaseView.getProductConsumptions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productConsumptions\": ");

			sb.append("[");

			for (int i = 0;
				 i < productPurchaseView.getProductConsumptions().length; i++) {

				sb.append(
					String.valueOf(
						productPurchaseView.getProductConsumptions()[i]));

				if ((i + 1) <
						productPurchaseView.getProductConsumptions().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (productPurchaseView.getProductPurchases() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchases\": ");

			sb.append("[");

			for (int i = 0;
				 i < productPurchaseView.getProductPurchases().length; i++) {

				sb.append(
					String.valueOf(
						productPurchaseView.getProductPurchases()[i]));

				if ((i + 1) <
						productPurchaseView.getProductPurchases().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ProductPurchaseViewJSONParser productPurchaseViewJSONParser =
			new ProductPurchaseViewJSONParser();

		return productPurchaseViewJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		ProductPurchaseView productPurchaseView) {

		if (productPurchaseView == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (productPurchaseView.getProduct() == null) {
			map.put("product", null);
		}
		else {
			map.put(
				"product", String.valueOf(productPurchaseView.getProduct()));
		}

		if (productPurchaseView.getProductConsumptions() == null) {
			map.put("productConsumptions", null);
		}
		else {
			map.put(
				"productConsumptions",
				String.valueOf(productPurchaseView.getProductConsumptions()));
		}

		if (productPurchaseView.getProductPurchases() == null) {
			map.put("productPurchases", null);
		}
		else {
			map.put(
				"productPurchases",
				String.valueOf(productPurchaseView.getProductPurchases()));
		}

		return map;
	}

	public static class ProductPurchaseViewJSONParser
		extends BaseJSONParser<ProductPurchaseView> {

		@Override
		protected ProductPurchaseView createDTO() {
			return new ProductPurchaseView();
		}

		@Override
		protected ProductPurchaseView[] createDTOArray(int size) {
			return new ProductPurchaseView[size];
		}

		@Override
		protected void setField(
			ProductPurchaseView productPurchaseView, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "product")) {
				if (jsonParserFieldValue != null) {
					productPurchaseView.setProduct(
						ProductSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "productConsumptions")) {

				if (jsonParserFieldValue != null) {
					productPurchaseView.setProductConsumptions(
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
			else if (Objects.equals(jsonParserFieldName, "productPurchases")) {
				if (jsonParserFieldValue != null) {
					productPurchaseView.setProductPurchases(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ProductPurchaseSerDes.toDTO(
								(String)object)
						).toArray(
							size -> new ProductPurchase[size]
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