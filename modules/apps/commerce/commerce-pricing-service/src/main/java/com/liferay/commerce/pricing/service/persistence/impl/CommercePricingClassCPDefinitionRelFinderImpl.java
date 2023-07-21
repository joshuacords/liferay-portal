/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.service.persistence.impl;

import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.model.impl.CommercePricingClassCPDefinitionRelImpl;
import com.liferay.commerce.pricing.service.persistence.CommercePricingClassCPDefinitionRelFinder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Riccardo Alberti
 */
public class CommercePricingClassCPDefinitionRelFinderImpl
	extends CommercePricingClassCPDefinitionRelFinderBaseImpl
	implements CommercePricingClassCPDefinitionRelFinder {

	public static final String COUNT_BY_COMMERCE_PRICING_CLASS_ID =
		CommercePricingClassCPDefinitionRelFinder.class.getName() +
			".countByCommercePricingClassId";

	public static final String FIND_BY_COMMERCE_PRICING_CLASS_ID =
		CommercePricingClassCPDefinitionRelFinder.class.getName() +
			".findByCommercePricingClassId";

	@Override
	public int countByCommercePricingClassId(
		long commercePricingClassId, String name, String languageId) {

		return countByCommercePricingClassId(
			commercePricingClassId, name, languageId, false);
	}

	@Override
	public int countByCommercePricingClassId(
		long commercePricingClassId, String name, String languageId,
		boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(
				getClass(), COUNT_BY_COMMERCE_PRICING_CLASS_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommercePricingClass.class.getName(),
					"CommercePricingClass.commercePricingClassId", null, null,
					new long[] {0}, null);
			}

			String[] keywords = _customSQL.keywords(name, true);

			if (Validator.isNotNull(name)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(CPDefinitionLocalization.name)",
					StringPool.LIKE, true, keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.replace(
					sql,
					" AND (LOWER(CPDefinitionLocalization.name) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])",
					StringPool.BLANK);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(_COUNT_VALUE, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(languageId);
			qPos.add(commercePricingClassId);

			if (Validator.isNotNull(name)) {
				qPos.add(keywords, 2);
			}

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
		findByCommercePricingClassId(
			long commercePricingClassId, String name, String languageId,
			int start, int end) {

		return findByCommercePricingClassId(
			commercePricingClassId, name, languageId, start, end, false);
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
		findByCommercePricingClassId(
			long commercePricingClassId, String name, String languageId,
			int start, int end, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String[] keywords = _customSQL.keywords(name, true);

			String sql = _customSQL.get(
				getClass(), FIND_BY_COMMERCE_PRICING_CLASS_ID);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommercePricingClass.class.getName(),
					"CommercePricingClass.commercePricingClassId", null, null,
					new long[] {0}, null);
			}

			if (Validator.isNotNull(name)) {
				sql = _customSQL.replaceKeywords(
					sql, "(LOWER(CPDefinitionLocalization.name)",
					StringPool.LIKE, true, keywords);
				sql = _customSQL.replaceAndOperator(sql, false);
			}
			else {
				sql = StringUtil.replace(
					sql,
					" AND (LOWER(CPDefinitionLocalization.name) LIKE ? " +
						"[$AND_OR_NULL_CHECK$])",
					StringPool.BLANK);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity(
				CommercePricingClassCPDefinitionRelImpl.TABLE_NAME,
				CommercePricingClassCPDefinitionRelImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(languageId);
			qPos.add(commercePricingClassId);

			if (Validator.isNotNull(name)) {
				qPos.add(keywords, 2);
			}

			return (List<CommercePricingClassCPDefinitionRel>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _COUNT_VALUE = "COUNT_VALUE";

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}