/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.account.internal.dto.v1_0.converter;

import com.liferay.commerce.account.model.CommerceAccountOrganizationRel;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelService;
import com.liferay.commerce.account.service.persistence.CommerceAccountOrganizationRelPK;
import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountOrganization;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccountOrganizationRel",
	service = {AccountOrganizationDTOConverter.class, DTOConverter.class}
)
public class AccountOrganizationDTOConverter
	implements DTOConverter
		<CommerceAccountOrganizationRel, AccountOrganization> {

	@Override
	public String getContentType() {
		return AccountOrganization.class.getSimpleName();
	}

	public AccountOrganization toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceAccountOrganizationRel commerceAccountOrganizationRel =
			_commerceAccountOrganizationRelService.
				getCommerceAccountOrganizationRel(
					(CommerceAccountOrganizationRelPK)
						dtoConverterContext.getId());

		Organization organization =
			commerceAccountOrganizationRel.getOrganization();

		return new AccountOrganization() {
			{
				accountId =
					commerceAccountOrganizationRel.getCommerceAccountId();
				name = organization.getName();
				organizationId = organization.getOrganizationId();
				treePath = organization.getTreePath();
			}
		};
	}

	@Reference
	private CommerceAccountOrganizationRelService
		_commerceAccountOrganizationRelService;

}