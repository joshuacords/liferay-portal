/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.impl.ContactRoleImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactRoleFinder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = ContactRoleFinder.class)
public class ContactRoleFinderImpl
	extends ContactRoleFinderBaseImpl implements ContactRoleFinder {

	public static final String COUNT_BY_N_T =
		ContactRoleFinder.class.getName() + ".countByN_T";

	public static final String FIND_BY_N_T =
		ContactRoleFinder.class.getName() + ".findByN_T";

	public static final String JOIN_BY_ACCOUNT_CONTACT =
		ContactRoleFinder.class.getName() + ".joinByAccountContact";

	public static final String JOIN_BY_CONTACT =
		ContactRoleFinder.class.getName() + ".joinByContact";

	public static final String JOIN_BY_TEAM_CONTACT =
		ContactRoleFinder.class.getName() + ".joinByTeamContact";

	@Override
	public int countByN_T(
		String name, String[] types, LinkedHashMap<String, Object> params) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_N_T);

			sql = _customSQL.replaceKeywords(
				sql, "Koroneiki_ContactRole.type_", StringPool.EQUAL, true,
				types);
			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
			sql = _customSQL.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(name);
			qPos.add(name);
			qPos.add(types, 2);

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
	public List<ContactRole> findByN_T(
		String name, String[] types, LinkedHashMap<String, Object> params,
		int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_N_T);

			sql = _customSQL.replaceKeywords(
				sql, "Koroneiki_ContactRole.type_", StringPool.EQUAL, true,
				types);
			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
			sql = _customSQL.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Koroneiki_ContactRole", ContactRoleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(name);
			qPos.add(name);
			qPos.add(types, 2);

			return (List<ContactRole>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (Validator.isNotNull(entry.getValue())) {
				sb.append(getJoin(entry.getKey()));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key) {
		String join = StringPool.BLANK;

		if (key.equals("accountContact")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACCOUNT_CONTACT);
		}
		else if (key.equals("contact")) {
			join = _customSQL.get(getClass(), JOIN_BY_CONTACT);
		}
		else if (key.equals("teamContact")) {
			join = _customSQL.get(getClass(), JOIN_BY_TEAM_CONTACT);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (Validator.isNotNull(entry.getValue())) {
				sb.append(getWhere(entry.getKey()));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key) {
		String join = StringPool.BLANK;

		if (key.equals("accountContact")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACCOUNT_CONTACT);
		}
		else if (key.equals("contact")) {
			join = _customSQL.get(getClass(), JOIN_BY_CONTACT);
		}
		else if (key.equals("teamContact")) {
			join = _customSQL.get(getClass(), JOIN_BY_TEAM_CONTACT);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5);

				join = join.concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();

			if (value instanceof Long[]) {
				Long[] valueArray = (Long[])value;

				for (Long curValue : valueArray) {
					if (Validator.isNotNull(curValue)) {
						qPos.add(curValue);
					}
				}
			}
		}
	}

	@Reference
	private CustomSQL _customSQL;

}