/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.rest.client.serdes.v1_0;

import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Histogram;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Metric;
import com.liferay.portal.workflow.metrics.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public class MetricSerDes {

	public static Metric toDTO(String json) {
		MetricJSONParser metricJSONParser = new MetricJSONParser();

		return metricJSONParser.parseToDTO(json);
	}

	public static Metric[] toDTOs(String json) {
		MetricJSONParser metricJSONParser = new MetricJSONParser();

		return metricJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Metric metric) {
		if (metric == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (metric.getHistograms() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"histograms\": ");

			sb.append("[");

			for (int i = 0; i < metric.getHistograms().length; i++) {
				sb.append(String.valueOf(metric.getHistograms()[i]));

				if ((i + 1) < metric.getHistograms().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (metric.getUnit() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"unit\": ");

			sb.append("\"");

			sb.append(metric.getUnit());

			sb.append("\"");
		}

		if (metric.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append(metric.getValue());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		MetricJSONParser metricJSONParser = new MetricJSONParser();

		return metricJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Metric metric) {
		if (metric == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (metric.getHistograms() == null) {
			map.put("histograms", null);
		}
		else {
			map.put("histograms", String.valueOf(metric.getHistograms()));
		}

		if (metric.getUnit() == null) {
			map.put("unit", null);
		}
		else {
			map.put("unit", String.valueOf(metric.getUnit()));
		}

		if (metric.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(metric.getValue()));
		}

		return map;
	}

	public static class MetricJSONParser extends BaseJSONParser<Metric> {

		@Override
		protected Metric createDTO() {
			return new Metric();
		}

		@Override
		protected Metric[] createDTOArray(int size) {
			return new Metric[size];
		}

		@Override
		protected void setField(
			Metric metric, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "histograms")) {
				if (jsonParserFieldValue != null) {
					Object[] jsonParserFieldValues =
						(Object[])jsonParserFieldValue;

					Histogram[] histogramsArray =
						new Histogram[jsonParserFieldValues.length];

					for (int i = 0; i < histogramsArray.length; i++) {
						histogramsArray[i] = HistogramSerDes.toDTO(
							(String)jsonParserFieldValues[i]);
					}

					metric.setHistograms(histogramsArray);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "unit")) {
				if (jsonParserFieldValue != null) {
					metric.setUnit(
						Metric.Unit.create((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					metric.setValue(
						Double.valueOf((String)jsonParserFieldValue));
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