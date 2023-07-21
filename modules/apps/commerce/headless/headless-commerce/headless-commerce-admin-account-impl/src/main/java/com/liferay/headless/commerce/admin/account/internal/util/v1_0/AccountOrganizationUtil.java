/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.account.internal.util.v1_0;

import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountOrganization;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alessio Antonio Rendina
 */
public class AccountOrganizationUtil {

	public static long getOrganizationId(
			OrganizationLocalService organizationLocalService,
			AccountOrganization accountOrganization, long companyId)
		throws PortalException {

		Organization organization;

		if (Validator.isNotNull(
				accountOrganization.getOrganizationExternalReferenceCode())) {

			organization =
				organizationLocalService.fetchOrganizationByReferenceCode(
					companyId,
					accountOrganization.getOrganizationExternalReferenceCode());

			if (organization == null) {
				throw new NoSuchOrganizationException(
					"Unable to find Organization with externalReferenceCode: " +
						accountOrganization.
							getOrganizationExternalReferenceCode());
			}
		}
		else {
			organization = organizationLocalService.getOrganization(
				accountOrganization.getOrganizationId());
		}

		return organization.getOrganizationId();
	}

}