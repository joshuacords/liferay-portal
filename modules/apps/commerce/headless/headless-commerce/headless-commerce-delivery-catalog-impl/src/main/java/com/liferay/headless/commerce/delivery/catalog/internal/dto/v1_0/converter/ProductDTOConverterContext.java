/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;

import java.util.Locale;

/**
 * @author Andrea Sbarra
 */
public class ProductDTOConverterContext extends DefaultDTOConverterContext {

	public ProductDTOConverterContext(
		Locale locale, long resourcePrimKey, CPCatalogEntry cpCatalogEntry) {

		super(resourcePrimKey, locale);

		_cpCatalogEntry = cpCatalogEntry;
	}

	public ProductDTOConverterContext(
		Locale locale, long resourcePrimKey, CPDefinition cpDefinition) {

		super(resourcePrimKey, locale);

		_cpDefinition = cpDefinition;
	}

	public CPCatalogEntry getCpCatalogEntry() {
		return _cpCatalogEntry;
	}

	public CPDefinition getCPDefinition() {
		return _cpDefinition;
	}

	private CPCatalogEntry _cpCatalogEntry;
	private CPDefinition _cpDefinition;

}