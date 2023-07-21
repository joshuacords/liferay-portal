/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.document.library;

import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.util.LocaleUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Pedro Queiroz
 */
@RunWith(PowerMockRunner.class)
public class DocumentLibraryDDMFormFieldValueAccessorTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpDocumentLibraryDDMFormFieldValueAccessor();
	}

	@Test
	public void testEmpty() {
		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"documentLibrary", new UnlocalizedValue("{}"));

		Assert.assertTrue(
			_documentLibraryDDMFormFieldValueAccessor.isEmpty(
				ddmFormFieldValue, LocaleUtil.US));
	}

	@Test
	public void testNotEmpty() {
		StringBundler sb = new StringBundler(4);

		sb.append("{\"groupId\":\"32964\",");
		sb.append("\"title\":\"Welcome to Liferay Forms!\",");
		sb.append("\"type\":\"document\",");
		sb.append("\"uuid\":\"f85c8ae1-603b-04eb-1132-12645d73519e\"}");

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"documentLibrary", new UnlocalizedValue(sb.toString()));

		Assert.assertFalse(
			_documentLibraryDDMFormFieldValueAccessor.isEmpty(
				ddmFormFieldValue, LocaleUtil.US));
	}

	protected void setUpDocumentLibraryDDMFormFieldValueAccessor()
		throws Exception {

		_documentLibraryDDMFormFieldValueAccessor =
			new DocumentLibraryDDMFormFieldValueAccessor();

		field(
			DocumentLibraryDDMFormFieldValueAccessor.class, "jsonFactory"
		).set(
			_documentLibraryDDMFormFieldValueAccessor, _jsonFactory
		);
	}

	private DocumentLibraryDDMFormFieldValueAccessor
		_documentLibraryDDMFormFieldValueAccessor;
	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}