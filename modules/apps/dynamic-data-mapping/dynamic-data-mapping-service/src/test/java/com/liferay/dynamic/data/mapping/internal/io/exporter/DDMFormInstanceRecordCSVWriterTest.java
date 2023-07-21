/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.io.exporter;

import com.liferay.dynamic.data.mapping.io.exporter.DDMFormInstanceRecordWriterRequest;
import com.liferay.dynamic.data.mapping.io.exporter.DDMFormInstanceRecordWriterResponse;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leonardo Barros
 */
public class DDMFormInstanceRecordCSVWriterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testWrite() throws Exception {
		Map<String, String> ddmFormFieldsLabel =
			new LinkedHashMap<String, String>() {
				{
					put("field1", "Field 1");
					put("field2", "Field 2");
					put("field3", "Field 3");
					put("field4", "Field 4");
				}
			};

		List<Map<String, String>> ddmFormFieldValues =
			new ArrayList<Map<String, String>>() {
				{
					Map<String, String> map1 =
						new LinkedHashMap<String, String>() {
							{
								put("field1", "2");
								put("field2", "esta é uma 'string'");
								put("field3", "false");
								put("field4", "11.7");
							}
						};

					add(map1);

					Map<String, String> map2 =
						new LinkedHashMap<String, String>() {
							{
								put("field1", "1");
								put("field2", "esta é uma 'string'");
								put("field3", "");
								put("field4", "10");
							}
						};

					add(map2);
				}
			};

		DDMFormInstanceRecordWriterRequest.Builder builder =
			DDMFormInstanceRecordWriterRequest.Builder.newBuilder(
				ddmFormFieldsLabel, ddmFormFieldValues);

		DDMFormInstanceRecordCSVWriter ddmFormInstanceRecordCSVWriter =
			new DDMFormInstanceRecordCSVWriter();

		DDMFormInstanceRecordWriterResponse
			ddmFormInstanceRecordWriterResponse =
				ddmFormInstanceRecordCSVWriter.write(builder.build());

		StringBundler sb = new StringBundler(3);

		sb.append("Field 1,Field 2,Field 3,Field 4\n");
		sb.append("2,esta é uma 'string',false,11.7\n");
		sb.append("1,esta é uma 'string',,10");

		String expected = sb.toString();

		Assert.assertArrayEquals(
			expected.getBytes(),
			ddmFormInstanceRecordWriterResponse.getContent());
	}

	@Test
	public void testWriteAfterChangeFieldName() throws Exception {
		Map<String, String> ddmFormFieldsLabel =
			new LinkedHashMap<String, String>() {
				{
					put("field1", "Field 1");
					put("field1AfterChangeName", "Field 1");
					put("field2", "Field 2");
				}
			};

		List<Map<String, String>> ddmFormFieldValues =
			new ArrayList<Map<String, String>>() {
				{
					Map<String, String> map1 = new HashMap<String, String>() {
						{
							put("field1", "1");
							put("field1AfterChangeName", "");
							put("field2", "esta é uma 'string'");
						}
					};

					add(map1);

					Map<String, String> map2 = new HashMap<String, String>() {
						{
							put("field1", "");
							put("field1AfterChangeName", "2");
							put("field2", "esta é uma 'string'");
						}
					};

					add(map2);
				}
			};

		DDMFormInstanceRecordWriterRequest.Builder builder =
			DDMFormInstanceRecordWriterRequest.Builder.newBuilder(
				ddmFormFieldsLabel, ddmFormFieldValues);

		DDMFormInstanceRecordCSVWriter ddmFormInstanceRecordCSVWriter =
			new DDMFormInstanceRecordCSVWriter();

		DDMFormInstanceRecordWriterResponse
			ddmFormInstanceRecordWriterResponse =
				ddmFormInstanceRecordCSVWriter.write(builder.build());

		StringBundler sb = new StringBundler(3);

		sb.append("Field 1 (field1),Field 1 (field1AfterChangeName),Field 2\n");
		sb.append("1,,esta é uma 'string'\n");
		sb.append(",2,esta é uma 'string'");

		String expected = sb.toString();

		Assert.assertArrayEquals(
			expected.getBytes(),
			ddmFormInstanceRecordWriterResponse.getContent());
	}

	@Test
	public void testWriteRecords() {
		DDMFormInstanceRecordCSVWriter ddmFormInstanceRecordCSVWriter =
			new DDMFormInstanceRecordCSVWriter();

		List<Map<String, String>> ddmFormFieldValues =
			new ArrayList<Map<String, String>>() {
				{
					Map<String, String> map1 =
						new LinkedHashMap<String, String>() {
							{
								put("field1", "value1");
								put("field2", "false");
								put("field3", "134.5");
							}
						};

					add(map1);

					Map<String, String> map2 =
						new LinkedHashMap<String, String>() {
							{
								put("field1", "");
								put("field2", "true");
								put("field3", "45");
							}
						};

					add(map2);
				}
			};

		StringBundler sb = new StringBundler(2);

		sb.append("value1,false,134.5\n");
		sb.append(",true,45");

		String actual = ddmFormInstanceRecordCSVWriter.writeRecords(
			ddmFormFieldValues);

		Assert.assertEquals(sb.toString(), actual);
	}

	@Test
	public void testWriteValues() {
		DDMFormInstanceRecordCSVWriter ddmFormInstanceRecordCSVWriter =
			new DDMFormInstanceRecordCSVWriter();

		List<String> values = new ArrayList<String>() {
			{
				add("value1");
				add("2");
				add("true");
				add("this is a \"string\"");
			}
		};

		String actualValue = ddmFormInstanceRecordCSVWriter.writeValues(values);

		Assert.assertEquals(
			"value1,2,true,\"this is a \"\"string\"\"\"", actualValue);
	}

}