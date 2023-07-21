/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.validator;

import com.liferay.dynamic.data.mapping.exception.StorageException;

/**
 * @author Marcellus Tavares
 */
public class DDMFormValuesValidationException extends StorageException {

	public DDMFormValuesValidationException() {
	}

	public DDMFormValuesValidationException(String msg) {
		super(msg);
	}

	public DDMFormValuesValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DDMFormValuesValidationException(Throwable cause) {
		super(cause);
	}

	public static class MustNotSetValue
		extends DDMFormValuesValidationException {

		public MustNotSetValue(String fieldName) {
			super(
				String.format(
					"Value should not be set for transient field name %s",
					fieldName));

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private String _fieldName;

	}

	public static class MustSetValidAvailableLocales
		extends DDMFormValuesValidationException {

		public MustSetValidAvailableLocales(String fieldName) {
			super(
				String.format(
					"Invalid available locales set for field name %s",
					fieldName));

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private String _fieldName;

	}

	public static class MustSetValidDefaultLocale
		extends DDMFormValuesValidationException {

		public MustSetValidDefaultLocale(String fieldName) {
			super(
				String.format(
					"Invalid default locale set for field name %s", fieldName));

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private String _fieldName;

	}

	public static class MustSetValidField
		extends DDMFormValuesValidationException {

		public MustSetValidField(String fieldName) {
			super(
				String.format(
					"There is no field name %s defined on form", fieldName));

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private String _fieldName;

	}

	public static class MustSetValidValue
		extends DDMFormValuesValidationException {

		public MustSetValidValue(String fieldName) {
			super(
				String.format(
					"Invalid value set for field name %s", fieldName));

			_fieldName = fieldName;
		}

		public MustSetValidValue(String fieldName, Throwable cause) {
			super(
				String.format("Invalid value set for field name %s", fieldName),
				cause);

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private String _fieldName;

	}

	public static class MustSetValidValuesSize
		extends DDMFormValuesValidationException {

		public MustSetValidValuesSize(String fieldName) {
			super(
				String.format(
					"Incorrect number of values set for field name %s",
					fieldName));

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private String _fieldName;

	}

	public static class RequiredValue extends DDMFormValuesValidationException {

		public RequiredValue(String fieldName) {
			super(
				String.format("No value defined for field name %s", fieldName));

			_fieldName = fieldName;
		}

		public String getFieldName() {
			return _fieldName;
		}

		private final String _fieldName;

	}

}