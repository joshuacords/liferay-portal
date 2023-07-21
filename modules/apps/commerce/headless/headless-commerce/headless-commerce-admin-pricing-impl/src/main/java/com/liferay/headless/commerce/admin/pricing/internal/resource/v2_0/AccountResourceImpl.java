/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.headless.commerce.admin.pricing.dto.v2_0.Account;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountAccount;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListAccount;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.AccountDTOConverter;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.AccountResource;
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
	properties = "OSGI-INF/liferay/rest/v2_0/account.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {AccountResource.class, NestedFieldSupport.class}
)
public class AccountResourceImpl
	extends BaseAccountResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = DiscountAccount.class, value = "account")
	@Override
	public Account getDiscountIdAccount(
			@NestedFieldId(value = "accountId") @NotNull Long id)
		throws Exception {

		return _accountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@NestedField(parentClass = PriceListAccount.class, value = "account")
	@Override
	public Account getPriceListIdAccount(
			@NestedFieldId(value = "accountId") @NotNull Long id)
		throws Exception {

		return _accountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@Reference
	private AccountDTOConverter _accountDTOConverter;

}