/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.test.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

/**
 * @author Riccardo Alberti
 */
public class CommerceAccountGroupTestUtil {

	public static CommerceAccountGroup addCommerceAccountGroup()
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		return CommerceAccountGroupLocalServiceUtil.addCommerceAccountGroup(
			serviceContext.getCompanyId(), RandomTestUtil.randomString(), 0,
			false, null, serviceContext);
	}

	public static CommerceAccountGroup addCommerceAccountToAccountGroup(
			CommerceAccount commerceAccount)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		CommerceAccountGroup commerceAccountGroup =
			CommerceAccountGroupLocalServiceUtil.addCommerceAccountGroup(
				serviceContext.getCompanyId(), RandomTestUtil.randomString(), 0,
				false, null, serviceContext);

		CommerceAccountGroupCommerceAccountRelLocalServiceUtil.
			addCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroup.getCommerceAccountGroupId(),
				commerceAccount.getCommerceAccountId(), serviceContext);

		return commerceAccountGroup;
	}

}