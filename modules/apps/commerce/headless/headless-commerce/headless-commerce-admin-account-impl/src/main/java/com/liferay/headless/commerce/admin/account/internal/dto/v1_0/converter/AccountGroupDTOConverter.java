/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.account.internal.dto.v1_0.converter;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountGroup;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccountGroup",
	service = {AccountGroupDTOConverter.class, DTOConverter.class}
)
public class AccountGroupDTOConverter
	implements DTOConverter<CommerceAccountGroup, AccountGroup> {

	@Override
	public String getContentType() {
		return AccountGroup.class.getSimpleName();
	}

	public AccountGroup toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupService.getCommerceAccountGroup(
				(Long)dtoConverterContext.getId());

		ExpandoBridge expandoBridge = commerceAccountGroup.getExpandoBridge();

		return new AccountGroup() {
			{
				customFields = expandoBridge.getAttributes();
				externalReferenceCode =
					commerceAccountGroup.getExternalReferenceCode();
				id = commerceAccountGroup.getCommerceAccountGroupId();
				name = commerceAccountGroup.getName();
			}
		};
	}

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

}