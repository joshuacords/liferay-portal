/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.persistence.impl;

import com.liferay.commerce.account.service.persistence.CommerceAccountGroupFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Collections;
import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceAccountGroupFinderImpl
	extends CommerceAccountGroupFinderBaseImpl
	implements CommerceAccountGroupFinder {

	public static final String FIND_ACCOUNT_USER_IDS_FROM_ACCOUNT_GROUP_IDS =
		CommerceAccountGroupFinder.class.getName() +
			".findAccountUserIdsFromAccountGroupIds";

	@Override
	public List<Long> findAccountUserIdsFromAccountGroupIds(
		long[] commerceAccountGroupIds, int start, int end) {

		if (ArrayUtil.isEmpty(commerceAccountGroupIds)) {
			return Collections.emptyList();
		}

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_ACCOUNT_USER_IDS_FROM_ACCOUNT_GROUP_IDS);

			sql = StringUtil.replace(
				sql, "[$ACCOUNT_GROUP_IDS$]",
				StringUtil.merge(commerceAccountGroupIds));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			return (List<Long>)QueryUtil.list(q, getDialect(), start, end);
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