/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.ResourceBlockIdsBag;
import com.liferay.portal.kernel.service.persistence.ResourceBlockFinder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;

/**
 * @author     Connor McKay
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ResourceBlockFinderImpl
	extends ResourceBlockFinderBaseImpl implements ResourceBlockFinder {

	public static final String FIND_BY_C_G_N_R =
		ResourceBlockFinder.class.getName() + ".findByC_G_N_R";

	@Override
	public ResourceBlockIdsBag findByC_G_N_R(
		long companyId, long groupId, String name, long[] roleIds) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_N_R);

			sql = StringUtil.replace(
				sql, "[$ROLE_ID$]", StringUtil.merge(roleIds));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("resourceBlockId", Type.LONG);
			q.addScalar("actionIds", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(name);

			ResourceBlockIdsBag resourceBlockIdsBag = new ResourceBlockIdsBag();

			Iterator<Object[]> itr = q.iterate();

			while (itr.hasNext()) {
				Object[] array = itr.next();

				Long resourceBlockId = (Long)array[0];
				Long actionIdsLong = (Long)array[1];

				resourceBlockIdsBag.addResourceBlockId(
					resourceBlockId, actionIdsLong);
			}

			return resourceBlockIdsBag;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

}