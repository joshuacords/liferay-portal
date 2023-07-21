/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.model.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupRelImpl
	extends CommerceAccountGroupRelBaseImpl {

	public CommerceAccountGroupRelImpl() {
	}

	@Override
	public CommerceAccountGroup getCommerceAccountGroup()
		throws PortalException {

		return CommerceAccountGroupLocalServiceUtil.getCommerceAccountGroup(
			getCommerceAccountGroupId());
	}

}