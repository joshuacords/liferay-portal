/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.frontend;

import com.liferay.commerce.frontend.DefaultFilterImpl;

/**
 * @author Alessio Antonio Rendina
 */
public class OrganizationFilterImpl extends DefaultFilterImpl {

	public long getOrganizationId() {
		return _accountId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setOrganizationId(long accountId) {
		_accountId = accountId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _accountId;
	private long _userId;

}