/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Account;
import com.liferay.portal.service.base.AccountLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountLocalServiceImpl extends AccountLocalServiceBaseImpl {

	@Override
	public Account getAccount(long companyId, long accountId)
		throws PortalException {

		return accountPersistence.findByPrimaryKey(accountId);
	}

}