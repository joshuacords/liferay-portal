/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceAccountHelper {

	public int countUserCommerceAccounts(long userId, long channelGroupId)
		throws PortalException;

	public String getAccountManagementPortletURL(
			HttpServletRequest httpServletRequest)
		throws PortalException;

	public long[] getCommerceAccountGroupIds(long commerceAccountId);

	public CommerceAccount getCurrentCommerceAccount(
			HttpServletRequest httpServletRequest)
		throws PortalException;

	public CommerceAccount getCurrentCommerceAccount(
			long groupId, HttpServletRequest httpServletRequest)
		throws PortalException;

	public long[] getUserCommerceAccountIds(long userId, long channelGroupId)
		throws PortalException;

	public void setCurrentCommerceAccount(
			HttpServletRequest httpServletRequest, long groupId,
			long commerceAccountId)
		throws PortalException;

}