/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.table;

import java.util.Map;

/**
 * @author Marco Leo
 */
public class ClayTableSchema {

	public Map<String, ClayTableSchemaField> getFields() {
		return _fields;
	}

	public void setFields(Map<String, ClayTableSchemaField> fields) {
		_fields = fields;
	}

	private Map<String, ClayTableSchemaField> _fields;

}