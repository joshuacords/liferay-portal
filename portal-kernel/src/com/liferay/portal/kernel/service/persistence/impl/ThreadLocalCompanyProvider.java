/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.persistence.impl;

import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;

/**
 * @author     Miguel Pastor
 * @author     Cristina González
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class ThreadLocalCompanyProvider implements CompanyProvider {

	@Override
	public long getCompanyId() {
		return CompanyThreadLocal.getCompanyId();
	}

	@Override
	public String getCompanyIdName() {
		return "companyId";
	}

}