/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.field.type.internal.numeric;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldTypeSettingsTestCase;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Marcellus Tavares
 */
@PrepareForTest({PortalClassLoaderUtil.class, ResourceBundleUtil.class})
@RunWith(PowerMockRunner.class)
public class NumericDDMFormFieldTemplateContextContributorTest
	extends BaseDDMFormFieldTypeSettingsTestCase {

	@Test
	public void testGetDataType1() {
		String fieldName = "field";

		DDMFormField ddmFormField = new DDMFormField(fieldName, "numeric");

		ddmFormField.setProperty("dataType", "double");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setProperty("changedProperties", null);

		Assert.assertEquals(
			"double",
			_numericDDMFormFieldTemplateContextContributor.getDataType(
				ddmFormField, ddmFormFieldRenderingContext));
	}

	@Test
	public void testGetDataType2() {
		DDMFormField ddmFormField = new DDMFormField("field", "numeric");

		ddmFormField.setProperty("dataType", "integer");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setProperty(
			"changedProperties", new HashMap<String, Object>());

		Assert.assertEquals(
			"integer",
			_numericDDMFormFieldTemplateContextContributor.getDataType(
				ddmFormField, ddmFormFieldRenderingContext));
	}

	@Test
	public void testGetDataType3() {
		DDMFormField ddmFormField = new DDMFormField("field", "numeric");

		ddmFormField.setProperty("dataType", "integer");

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext =
			new DDMFormFieldRenderingContext();

		Map<String, Object> changedProperties =
			HashMapBuilder.<String, Object>put(
				"dataType", "double"
			).build();

		ddmFormFieldRenderingContext.setProperty(
			"changedProperties", changedProperties);

		Assert.assertEquals(
			"double",
			_numericDDMFormFieldTemplateContextContributor.getDataType(
				ddmFormField, ddmFormFieldRenderingContext));
	}

	@Test
	public void testGetDecimalSymbolsWithAmericanLocale() {
		Map<String, String> separatorSymbolsMap =
			_numericDDMFormFieldTemplateContextContributor.getSymbolsMap(
				LocaleUtil.US);

		Assert.assertEquals(".", separatorSymbolsMap.get("decimalSymbol"));
		Assert.assertEquals(",", separatorSymbolsMap.get("thousandsSeparator"));
	}

	@Test
	public void testGetDecimalSymbolsWithBrazilianLocale() {
		Map<String, String> separatorSymbolsMap =
			_numericDDMFormFieldTemplateContextContributor.getSymbolsMap(
				LocaleUtil.BRAZIL);

		Assert.assertEquals(",", separatorSymbolsMap.get("decimalSymbol"));
		Assert.assertEquals(".", separatorSymbolsMap.get("thousandsSeparator"));
	}

	private final NumericDDMFormFieldTemplateContextContributor
		_numericDDMFormFieldTemplateContextContributor =
			new NumericDDMFormFieldTemplateContextContributor();

}