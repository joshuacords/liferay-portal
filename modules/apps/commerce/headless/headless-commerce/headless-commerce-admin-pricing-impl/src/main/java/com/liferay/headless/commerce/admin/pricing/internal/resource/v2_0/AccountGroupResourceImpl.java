/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.headless.commerce.admin.pricing.dto.v2_0.AccountGroup;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountAccountGroup;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListAccountGroup;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.AccountGroupDTOConverter;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.AccountGroupResource;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;

import javax.validation.constraints.NotNull;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/account-group.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {AccountGroupResource.class, NestedFieldSupport.class}
)
public class AccountGroupResourceImpl
	extends BaseAccountGroupResourceImpl implements NestedFieldSupport {

	@NestedField(
		parentClass = DiscountAccountGroup.class, value = "accountGroup"
	)
	@Override
	public AccountGroup getDiscountIdAccountGroup(
			@NestedFieldId(value = "accountGroupId") @NotNull Long id)
		throws Exception {

		return _accountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@NestedField(
		parentClass = PriceListAccountGroup.class, value = "accountGroup"
	)
	@Override
	public AccountGroup getPriceListIdAccountGroup(
			@NestedFieldId(value = "accountGroupId") @NotNull Long id)
		throws Exception {

		return _accountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@Reference
	private AccountGroupDTOConverter _accountGroupDTOConverter;

}