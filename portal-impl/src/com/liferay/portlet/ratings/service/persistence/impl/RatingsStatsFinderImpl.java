/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.ratings.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.ratings.model.impl.RatingsStatsImpl;
import com.liferay.portlet.ratings.model.impl.RatingsStatsModelImpl;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsFinder;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author     Shuyang Zhou
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class RatingsStatsFinderImpl
	extends RatingsStatsFinderBaseImpl implements RatingsStatsFinder {

	public static final String FIND_BY_C_C =
		RatingsStatsFinder.class.getName() + ".findByC_C";

	public static final FinderPath FINDER_PATH_FIND_BY_C_C = new FinderPath(
		RatingsStatsModelImpl.ENTITY_CACHE_ENABLED,
		RatingsStatsModelImpl.FINDER_CACHE_ENABLED, RatingsStatsImpl.class,
		RatingsStatsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
		"findByC_C", new String[] {Long.class.getName(), List.class.getName()});

	@Override
	public Map<Serializable, RatingsStats> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return RatingsStatsUtil.fetchByPrimaryKeys(primaryKeys);
	}

	@Override
	public List<RatingsStats> findByC_C(long classNameId, List<Long> classPKs) {
		Object[] finderArgs = {
			classNameId, StringUtil.merge(classPKs.toArray(new Long[0]))
		};

		List<RatingsStats> list = (List<RatingsStats>)FinderCacheUtil.getResult(
			FINDER_PATH_FIND_BY_C_C, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RatingsStats ratingsStats : list) {
				if ((classNameId != ratingsStats.getClassNameId()) ||
					!classPKs.contains(ratingsStats.getClassPK())) {

					list = null;

					break;
				}
			}
		}

		if (list != null) {
			return list;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_C);

			sql = StringUtil.replace(
				sql, "[$CLASS_PKS$]", StringUtil.merge(classPKs));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("RatingsStats", RatingsStatsImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);

			list = q.list(true);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			if (list == null) {
				FinderCacheUtil.removeResult(
					FINDER_PATH_FIND_BY_C_C, finderArgs);
			}
			else {
				FinderCacheUtil.putResult(
					FINDER_PATH_FIND_BY_C_C, finderArgs, list);
			}

			closeSession(session);
		}

		return list;
	}

}