/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.service.persistence.AccountFieldFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = AccountFieldFinder.class)
public class AccountFieldFinderImpl
	extends AccountFieldFinderBaseImpl implements AccountFieldFinder {

	public static final String FIND_NAMES =
		AccountFieldFinder.class.getName() + ".findNames";

	@Override
	public List<String> findNames() {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_NAMES);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("name", Type.STRING);

			List<String> accountFieldNames = new ArrayList<>();

			Iterator<String> itr = q.iterate();

			while (itr.hasNext()) {
				String name = itr.next();

				if (Validator.isNotNull(name)) {
					accountFieldNames.add(name);
				}
			}

			return accountFieldNames;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Reference
	private CustomSQL _customSQL;

}