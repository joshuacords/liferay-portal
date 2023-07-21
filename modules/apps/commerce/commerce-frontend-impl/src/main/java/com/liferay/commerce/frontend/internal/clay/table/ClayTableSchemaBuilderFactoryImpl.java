/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.clay.table;

import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.clay.table.ClayTableSchemaBuilderFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(service = ClayTableSchemaBuilderFactory.class)
public class ClayTableSchemaBuilderFactoryImpl
	implements ClayTableSchemaBuilderFactory {

	public ClayTableSchemaBuilder clayTableSchemaBuilder() {
		return new ClayTableSchemaBuilderImpl();
	}

}