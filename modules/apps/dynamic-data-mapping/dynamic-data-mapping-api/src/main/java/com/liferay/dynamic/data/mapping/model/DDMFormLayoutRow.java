/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class DDMFormLayoutRow implements Serializable {

	public DDMFormLayoutRow() {
	}

	public DDMFormLayoutRow(DDMFormLayoutRow ddmFormLayoutRow) {
		for (DDMFormLayoutColumn ddmFormLayoutColumn :
				ddmFormLayoutRow._ddmFormLayoutColumns) {

			addDDMFormLayoutColumn(
				new DDMFormLayoutColumn(ddmFormLayoutColumn));
		}
	}

	public void addDDMFormLayoutColumn(
		DDMFormLayoutColumn ddmFormLayoutColumn) {

		_ddmFormLayoutColumns.add(ddmFormLayoutColumn);
	}

	public DDMFormLayoutColumn getDDMFormLayoutColumn(int index) {
		return _ddmFormLayoutColumns.get(index);
	}

	public List<DDMFormLayoutColumn> getDDMFormLayoutColumns() {
		return _ddmFormLayoutColumns;
	}

	public void setDDMFormLayoutColumns(
		List<DDMFormLayoutColumn> ddmFormLayoutColumns) {

		_ddmFormLayoutColumns = ddmFormLayoutColumns;
	}

	private List<DDMFormLayoutColumn> _ddmFormLayoutColumns = new ArrayList<>();

}