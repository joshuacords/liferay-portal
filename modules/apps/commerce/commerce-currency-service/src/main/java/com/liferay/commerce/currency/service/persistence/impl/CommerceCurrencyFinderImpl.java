/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.currency.service.persistence.impl;

import com.liferay.commerce.currency.service.persistence.CommerceCurrencyFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCurrencyFinderImpl
	extends CommerceCurrencyFinderBaseImpl implements CommerceCurrencyFinder {

	public static final String GET_COMPANY_IDS =
		CommerceCurrencyFinder.class.getName() + ".getCompanyIds";

	@Override
	public List<Long> getCompanyIds() {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), GET_COMPANY_IDS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			return (List<Long>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}