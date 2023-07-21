/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.storage;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.List;

/**
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
public class FieldConstants {

	public static final String BOOLEAN = "boolean";

	public static final String DATA_TYPE = "dataType";

	public static final String DATE = "date";

	public static final String DOCUMENT_LIBRARY = "document-library";

	public static final String DOUBLE = "double";

	public static final String EDITABLE = "editable";

	public static final String FLOAT = "float";

	public static final String HTML = "html";

	public static final String IMAGE = "image";

	public static final String INTEGER = "integer";

	public static final String LABEL = "label";

	public static final String LONG = "long";

	public static final String NAME = "name";

	public static final String NUMBER = "number";

	public static final String PREDEFINED_VALUE = "predefinedValue";

	public static final String PRIVATE = "private";

	public static final String REQUIRED = "required";

	public static final String SHORT = "short";

	public static final String SHOW = "showLabel";

	public static final String SORTABLE = "sortable";

	public static final String STRING = "string";

	public static final String TYPE = "type";

	public static final String VALUE = "value";

	public static final Serializable getSerializable(
		String type, List<Serializable> values) {

		if (Validator.isNull(type)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Invalid type " + type);
			}

			return values.toArray(new String[0]);
		}

		if (isNumericType(type)) {
			values.removeAll(Collections.singleton(StringPool.BLANK));
		}

		if (type.equals(FieldConstants.BOOLEAN)) {
			return values.toArray(new Boolean[0]);
		}
		else if (type.equals(FieldConstants.DATE)) {
			return values.toArray(new String[0]);
		}
		else if (type.equals(FieldConstants.DOUBLE) ||
				 type.equals(FieldConstants.INTEGER)) {

			return ListUtil.toArray(
				values,
				new Accessor<Object, Number>() {

					@Override
					public Number get(Object value) {
						return GetterUtil.getNumber(value);
					}

					@Override
					public Class<Number> getAttributeClass() {
						return Number.class;
					}

					@Override
					public Class<Object> getTypeClass() {
						return Object.class;
					}

				});
		}
		else if (type.equals(FieldConstants.FLOAT)) {
			return values.toArray(new Float[0]);
		}
		else if (type.equals(FieldConstants.LONG)) {
			return values.toArray(new Long[0]);
		}
		else if (type.equals(FieldConstants.NUMBER)) {
			return values.toArray(new Number[0]);
		}
		else if (type.equals(FieldConstants.SHORT)) {
			return values.toArray(new Short[0]);
		}

		return values.toArray(new String[0]);
	}

	public static final Serializable getSerializable(
		String type, String value) {

		if (Validator.isNull(type)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Invalid type " + type);
			}

			return value;
		}

		if (isNumericType(type) && Validator.isNull(value)) {
			return StringPool.BLANK;
		}

		if (type.equals(BOOLEAN)) {
			return GetterUtil.getBoolean(value);
		}
		else if (type.equals(DATE) && Validator.isNotNull(value)) {
			return value;
		}
		else if (type.equals(DOUBLE)) {
			try {
				return new BigDecimal(value.trim());
			}
			catch (NumberFormatException numberFormatException) {
				return GetterUtil.getDouble(value);
			}
		}
		else if (type.equals(FLOAT)) {
			return GetterUtil.getFloat(value);
		}
		else if (type.equals(INTEGER)) {
			return GetterUtil.getInteger(value);
		}
		else if (type.equals(LONG)) {
			return GetterUtil.getLong(value);
		}
		else if (type.equals(NUMBER)) {
			return GetterUtil.getNumber(value);
		}
		else if (type.equals(SHORT)) {
			return GetterUtil.getShort(value);
		}

		return value;
	}

	public static final boolean isNumericType(String type) {
		if (type.equals(DOUBLE) || type.equals(FLOAT) || type.equals(INTEGER) ||
			type.equals(LONG) || type.equals(NUMBER) || type.equals(SHORT)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(FieldConstants.class);

}