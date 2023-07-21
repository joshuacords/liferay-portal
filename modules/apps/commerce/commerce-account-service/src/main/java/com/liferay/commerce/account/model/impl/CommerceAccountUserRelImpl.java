/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountUserRelImpl extends CommerceAccountUserRelBaseImpl {

	public CommerceAccountUserRelImpl() {
	}

	@Override
	public User getUser() throws PortalException {
		return UserLocalServiceUtil.getUser(getCommerceAccountUserId());
	}

	@Override
	public List<UserGroupRole> getUserGroupRoles() throws PortalException {
		CommerceAccount commerceAccount =
			CommerceAccountLocalServiceUtil.getCommerceAccount(
				getCommerceAccountId());

		return UserGroupRoleLocalServiceUtil.getUserGroupRoles(
			getCommerceAccountUserId(),
			commerceAccount.getCommerceAccountGroupId());
	}

}