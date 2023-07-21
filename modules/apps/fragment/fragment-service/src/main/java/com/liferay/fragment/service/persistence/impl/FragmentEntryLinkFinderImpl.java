/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.service.persistence.impl;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.model.impl.FragmentEntryLinkImpl;
import com.liferay.fragment.service.persistence.FragmentEntryLinkFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = FragmentEntryLinkFinder.class)
public class FragmentEntryLinkFinderImpl
	extends FragmentEntryLinkFinderBaseImpl implements FragmentEntryLinkFinder {

	public static final String COUNT_BY_G_F =
		FragmentEntryLinkFinder.class.getName() + ".countByG_F";

	public static final String COUNT_BY_G_F_C =
		FragmentEntryLinkFinder.class.getName() + ".countByG_F_C";

	public static final String COUNT_BY_G_F_P =
		FragmentEntryLinkFinder.class.getName() + ".countByG_F_P";

	public static final String COUNT_BY_G_F_C_L =
		FragmentEntryLinkFinder.class.getName() + ".countByG_F_C_L";

	public static final String COUNT_BY_G_F_P_L =
		FragmentEntryLinkFinder.class.getName() + ".countByG_F_P_L";

	public static final String FIND_BY_G_F =
		FragmentEntryLinkFinder.class.getName() + ".findByG_F";

	public static final String FIND_BY_G_F_C =
		FragmentEntryLinkFinder.class.getName() + ".findByG_F_C";

	public static final String FIND_BY_G_F_P =
		FragmentEntryLinkFinder.class.getName() + ".findByG_F_P";

	public static final String FIND_BY_G_F_C_L =
		FragmentEntryLinkFinder.class.getName() + ".findByG_F_C_L";

	public static final String FIND_BY_G_F_P_L =
		FragmentEntryLinkFinder.class.getName() + ".findByG_F_P_L";

	@Override
	public int countByG_F(long groupId, long fragmentEntryId) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_F);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(fragmentEntryId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public int countByG_F_C(
		long groupId, long fragmentEntryId, long classNameId) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_F_C);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(fragmentEntryId);
			qPos.add(classNameId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public int countByG_F_C_L(
		long groupId, long fragmentEntryId, long classNameId,
		int layoutPageTemplateEntryType) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_F_C_L);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(fragmentEntryId);
			qPos.add(classNameId);
			qPos.add(layoutPageTemplateEntryType);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public int countByG_F_P_L(
		long groupId, long fragmentEntryId, int layoutPageTemplateEntryType) {

		Session session = null;

		try {
			session = openSession();

			String sql = null;

			if (layoutPageTemplateEntryType >= 0) {
				sql = _customSQL.get(getClass(), COUNT_BY_G_F_P_L);
			}
			else {
				sql = _customSQL.get(getClass(), COUNT_BY_G_F_P);
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (layoutPageTemplateEntryType >= 0) {
				queryPos.add(layoutPageTemplateEntryType);
			}

			queryPos.add(groupId);
			queryPos.add(fragmentEntryId);

			Iterator<Long> iterator = sqlQuery.iterate();

			if (iterator.hasNext()) {
				Long count = iterator.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<FragmentEntryLink> findByG_F(
		long groupId, long fragmentEntryId, int start, int end,
		OrderByComparator<FragmentEntryLink> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_F);

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("FragmentEntryLink", FragmentEntryLinkImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(fragmentEntryId);

			return (List<FragmentEntryLink>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<FragmentEntryLink> findByG_F_C(
		long groupId, long fragmentEntryId, long classNameId, int start,
		int end, OrderByComparator<FragmentEntryLink> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_F_C);

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("FragmentEntryLink", FragmentEntryLinkImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(fragmentEntryId);
			qPos.add(classNameId);

			return (List<FragmentEntryLink>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<FragmentEntryLink> findByG_F_C_L(
		long groupId, long fragmentEntryId, long classNameId,
		int layoutPageTemplateEntryType, int start, int end,
		OrderByComparator<FragmentEntryLink> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_F_C_L);

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("FragmentEntryLink", FragmentEntryLinkImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(fragmentEntryId);
			qPos.add(classNameId);
			qPos.add(layoutPageTemplateEntryType);

			return (List<FragmentEntryLink>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<FragmentEntryLink> findByG_F_P_L(
		long groupId, long fragmentEntryId, int layoutPageTemplateEntryType,
		int start, int end,
		OrderByComparator<FragmentEntryLink> orderByComparator) {

		Session session = null;

		try {
			session = openSession();

			String sql = null;

			if (layoutPageTemplateEntryType >= 0) {
				sql = _customSQL.get(getClass(), FIND_BY_G_F_P_L);
			}
			else {
				sql = _customSQL.get(getClass(), FIND_BY_G_F_P);
			}

			sql = _customSQL.replaceOrderBy(sql, orderByComparator);

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity(
				"FragmentEntryLink", FragmentEntryLinkImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (layoutPageTemplateEntryType >= 0) {
				queryPos.add(layoutPageTemplateEntryType);
			}

			queryPos.add(groupId);
			queryPos.add(fragmentEntryId);

			return (List<FragmentEntryLink>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
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