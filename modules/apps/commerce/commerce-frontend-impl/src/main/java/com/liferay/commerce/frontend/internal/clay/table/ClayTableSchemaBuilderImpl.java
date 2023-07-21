/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.table;

import com.liferay.commerce.frontend.clay.table.ClayTableSchema;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaField;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class ClayTableSchemaBuilderImpl implements ClayTableSchemaBuilder {

	public ClayTableSchemaBuilderImpl() {
		_clayTableSchema = new ClayTableSchema();
		_fields = new LinkedHashMap<>();
	}

	@Override
	public void addField(ClayTableSchemaField clayTableSchemaField) {
		_fields.put(clayTableSchemaField.getFieldName(), clayTableSchemaField);
	}

	@Override
	public ClayTableSchemaField addField(String fieldName) {
		ClayTableSchemaField clayTableSchemaField = new ClayTableSchemaField();

		clayTableSchemaField.setFieldName(fieldName);

		_fields.put(fieldName, clayTableSchemaField);

		return clayTableSchemaField;
	}

	@Override
	public ClayTableSchemaField addField(String fieldName, String label) {
		ClayTableSchemaField clayTableSchemaField = addField(fieldName);

		clayTableSchemaField.setLabel(label);

		return clayTableSchemaField;
	}

	@Override
	public ClayTableSchema build() {
		_clayTableSchema.setFields(_fields);

		return _clayTableSchema;
	}

	@Override
	public void removeField(String fieldName) {
		_fields.remove(fieldName);
	}

	@Override
	public void setClayTableSchema(ClayTableSchema clayTableSchema) {
		_clayTableSchema = clayTableSchema;
	}

	private ClayTableSchema _clayTableSchema;
	private final Map<String, ClayTableSchemaField> _fields;

}