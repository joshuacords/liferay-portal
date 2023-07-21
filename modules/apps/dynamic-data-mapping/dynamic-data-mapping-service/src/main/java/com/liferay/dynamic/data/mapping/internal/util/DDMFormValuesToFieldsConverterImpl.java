/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.util;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.FieldConstants;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesToFieldsConverter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = DDMFormValuesToFieldsConverter.class)
public class DDMFormValuesToFieldsConverterImpl
	implements DDMFormValuesToFieldsConverter {

	@Override
	public Fields convert(
			DDMStructure ddmStructure, DDMFormValues ddmFormValues)
		throws PortalException {

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmStructure.getFullHierarchyDDMFormFieldsMap(true);

		Fields ddmFields = createDDMFields(ddmStructure);

		for (DDMFormFieldValue ddmFormFieldValue :
				ddmFormValues.getDDMFormFieldValues()) {

			addDDMFields(
				ddmStructure.getStructureId(), ddmFormFieldsMap,
				ddmFormFieldValue, ddmFields, ddmFormValues.getDefaultLocale());
		}

		return ddmFields;
	}

	protected void addDDMField(
			long ddmStructureId, DDMFormField ddmFormField,
			DDMFormFieldValue ddmFormFieldValue, Fields ddmFields,
			Locale defaultLocale)
		throws PortalException {

		if ((ddmFormField == null) || ddmFormField.isTransient()) {
			return;
		}

		Field ddmField = createDDMField(
			ddmStructureId, ddmFormField, ddmFormFieldValue, defaultLocale);

		Field existingDDMField = ddmFields.get(ddmField.getName());

		if (existingDDMField == null) {
			ddmFields.put(ddmField);
		}
		else {
			addDDMFieldValues(existingDDMField, ddmField);
		}
	}

	protected void addDDMFields(
			long ddmStructureId, Map<String, DDMFormField> ddmFormFieldsMap,
			DDMFormFieldValue ddmFormFieldValue, Fields ddmFields,
			Locale defaultLocale)
		throws PortalException {

		DDMFormField ddmFormField = ddmFormFieldsMap.get(
			ddmFormFieldValue.getName());

		addDDMField(
			ddmStructureId, ddmFormField, ddmFormFieldValue, ddmFields,
			defaultLocale);

		addFieldDisplayValue(
			ddmFields.get(DDMImpl.FIELDS_DISPLAY_NAME),
			getFieldDisplayValue(ddmFormFieldValue));

		for (DDMFormFieldValue nestedDDMFormFieldValue :
				ddmFormFieldValue.getNestedDDMFormFieldValues()) {

			addDDMFields(
				ddmStructureId, ddmFormFieldsMap, nestedDDMFormFieldValue,
				ddmFields, defaultLocale);
		}
	}

	protected void addDDMFieldValues(
		Field existingDDMField, Field newDDMField) {

		for (Locale availableLocale : newDDMField.getAvailableLocales()) {
			existingDDMField.addValues(
				availableLocale, newDDMField.getValues(availableLocale));
		}
	}

	protected void addFieldDisplayValue(
		Field ddmFieldsDisplayField, String fieldDisplayValue) {

		String[] fieldsDisplayValues = StringUtil.split(
			(String)ddmFieldsDisplayField.getValue());

		fieldsDisplayValues = ArrayUtil.append(
			fieldsDisplayValues, fieldDisplayValue);

		ddmFieldsDisplayField.setValue(StringUtil.merge(fieldsDisplayValues));
	}

	protected Field createDDMField(
			long ddmStructureId, DDMFormField ddmFormField,
			DDMFormFieldValue ddmFormFieldValue, Locale defaultLocale)
		throws PortalException {

		Field ddmField = new Field();

		ddmField.setDDMStructureId(ddmStructureId);
		ddmField.setDefaultLocale(defaultLocale);
		ddmField.setName(ddmFormFieldValue.getName());

		String type = ddmFormField.getDataType();

		setDDMFieldValue(
			ddmField, type, ddmFormFieldValue.getValue(), defaultLocale);

		return ddmField;
	}

	protected Fields createDDMFields(DDMStructure ddmStructure) {
		Fields ddmFields = new Fields();

		Field fieldsDisplayField = new Field(
			ddmStructure.getStructureId(), DDMImpl.FIELDS_DISPLAY_NAME,
			StringPool.BLANK);

		ddmFields.put(fieldsDisplayField);

		return ddmFields;
	}

	protected String getFieldDisplayValue(DDMFormFieldValue ddmFormFieldValue) {
		String fieldName = ddmFormFieldValue.getName();

		return fieldName.concat(
			DDMImpl.INSTANCE_SEPARATOR
		).concat(
			ddmFormFieldValue.getInstanceId()
		);
	}

	protected void setDDMFieldLocalizedValue(
		Field ddmField, String type, Value value) {

		for (Locale availableLocale : value.getAvailableLocales()) {
			Serializable serializable = _getSerializable(
				availableLocale, availableLocale, type, value);

			ddmField.addValue(availableLocale, serializable);
		}
	}

	protected void setDDMFieldUnlocalizedValue(
		Field ddmField, String type, Value value, Locale defaultLocale) {

		Serializable serializable = _getSerializable(
			defaultLocale, LocaleUtil.ROOT, type, value);

		ddmField.addValue(defaultLocale, serializable);
	}

	protected void setDDMFieldValue(
		Field ddmField, String type, Value value, Locale defaultLocale) {

		if (value.isLocalized()) {
			setDDMFieldLocalizedValue(ddmField, type, value);
		}
		else {
			setDDMFieldUnlocalizedValue(ddmField, type, value, defaultLocale);
		}
	}

	private static DecimalFormat _getArabicDecimalFormat(Locale locale) {
		DecimalFormat decimalFormat = (DecimalFormat)DecimalFormat.getInstance(
			locale);

		DecimalFormatSymbols decimalFormatSymbols =
			decimalFormat.getDecimalFormatSymbols();

		decimalFormatSymbols.setZeroDigit('0');

		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

		decimalFormat.setGroupingUsed(false);
		decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
		decimalFormat.setParseBigDecimal(true);

		return decimalFormat;
	}

	private Serializable _getSerializable(
		Locale defaultLocale, Locale locale, String type, Value value) {

		Serializable serializable = null;

		if (FieldConstants.isNumericType(type)) {
			NumberFormat numberFormat = null;

			if (StringUtil.equals(locale.getLanguage(), "ar")) {
				numberFormat = _getArabicDecimalFormat(locale);
			}
			else if (locale.equals(LocaleUtil.ROOT)) {
				numberFormat = NumberFormat.getInstance(defaultLocale);
			}
			else {
				numberFormat = NumberFormat.getInstance(locale);
			}

			if (type.equals(FieldConstants.DOUBLE) ||
				type.equals(FieldConstants.FLOAT)) {

				numberFormat.setMinimumFractionDigits(1);
			}

			try {
				Number number = numberFormat.parse(
					GetterUtil.getString(value.getString(locale)));

				serializable = FieldConstants.getSerializable(
					type, number.toString());
			}
			catch (ParseException parseException) {
				serializable = FieldConstants.getSerializable(
					type, value.getString(locale));
			}
		}
		else {
			serializable = FieldConstants.getSerializable(
				type, value.getString(locale));
		}

		return serializable;
	}

}