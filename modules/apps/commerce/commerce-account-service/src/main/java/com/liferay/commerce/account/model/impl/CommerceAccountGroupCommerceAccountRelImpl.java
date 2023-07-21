/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupCommerceAccountRelImpl
	extends CommerceAccountGroupCommerceAccountRelBaseImpl {

	public CommerceAccountGroupCommerceAccountRelImpl() {
	}

	@Override
	public CommerceAccount getCommerceAccount() throws PortalException {
		return CommerceAccountLocalServiceUtil.getCommerceAccount(
			getCommerceAccountId());
	}

}