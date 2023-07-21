/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPInstanceOptionValueRel;
import com.liferay.commerce.product.model.impl.CPInstanceOptionValueRelImpl;
import com.liferay.commerce.product.service.persistence.CPInstanceOptionValueRelFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Igor Beslic
 */
public class CPInstanceOptionValueRelFinderImpl
	extends CPInstanceOptionValueRelFinderBaseImpl
	implements CPInstanceOptionValueRelFinder {

	public static final String FIND_BY_CP_DEFINITION_ID =
		CPInstanceOptionValueRelFinder.class.getName() +
			".findByCPDefinitionId";

	@Override
	public List<CPInstanceOptionValueRel> findByCPDefinitionId(
		long cpDefinitionId, QueryDefinition<CPInstance> queryDefinition) {

		return doFindByCPDefinitionId(cpDefinitionId, queryDefinition);
	}

	protected List<CPInstanceOptionValueRel> doFindByCPDefinitionId(
		long cpDefinitionId, QueryDefinition<CPInstance> queryDefinition) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), FIND_BY_CP_DEFINITION_ID, queryDefinition,
				CPInstanceOptionValueRelImpl.TABLE_NAME);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				CPInstanceOptionValueRelImpl.TABLE_NAME,
				CPInstanceOptionValueRelImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(cpDefinitionId);
			qPos.add(queryDefinition.getStatus());

			return (List<CPInstanceOptionValueRel>)QueryUtil.list(
				q, getDialect(), queryDefinition.getStart(),
				queryDefinition.getEnd());
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