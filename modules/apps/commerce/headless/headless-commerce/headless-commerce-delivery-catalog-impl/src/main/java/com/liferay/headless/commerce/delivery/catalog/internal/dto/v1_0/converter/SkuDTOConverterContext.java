/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;

import java.util.Locale;

/**
 * @author Andrea Sbarra
 */
public class SkuDTOConverterContext extends DefaultDTOConverterContext {

	public SkuDTOConverterContext(
		Locale locale, long resourcePrimKey, CPDefinition cpDefinition,
		long companyId, CommerceContext commerceContext) {

		super(resourcePrimKey, locale);

		_cpDefinition = cpDefinition;
		_companyId = companyId;
		_commerceContext = commerceContext;
	}

	public CommerceContext getCommerceContext() {
		return _commerceContext;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public CPDefinition getCPDefinition() {
		return _cpDefinition;
	}

	private final CommerceContext _commerceContext;
	private final long _companyId;
	private final CPDefinition _cpDefinition;

}