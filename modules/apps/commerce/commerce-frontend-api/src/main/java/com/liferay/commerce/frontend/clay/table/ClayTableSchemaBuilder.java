/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.clay.table;

/**
 * @author Marco Leo
 */
public interface ClayTableSchemaBuilder {

	public void addField(ClayTableSchemaField clayTableSchemaField);

	public ClayTableSchemaField addField(String fieldName);

	public ClayTableSchemaField addField(String fieldName, String label);

	public ClayTableSchema build();

	public void removeField(String fieldName);

	public void setClayTableSchema(ClayTableSchema clayTableSchema);

}