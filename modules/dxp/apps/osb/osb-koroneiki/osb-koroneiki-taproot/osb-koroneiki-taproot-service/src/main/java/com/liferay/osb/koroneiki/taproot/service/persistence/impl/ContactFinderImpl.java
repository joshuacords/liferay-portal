/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.impl.ContactImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactFinder;
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
@Component(service = ContactFinder.class)
public class ContactFinderImpl
	extends ContactFinderBaseImpl implements ContactFinder {

	public static final String COUNT_BY_FN_MN_LN_E =
		ContactFinder.class.getName() + ".countByFN_MN_LN_E";

	public static final String FIND_BY_FN_MN_LN_E =
		ContactFinder.class.getName() + ".findByFN_MN_LN_E";

	public static final String JOIN_BY_ACCOUNT =
		ContactFinder.class.getName() + ".joinByAccount";

	public static final String JOIN_BY_ACCOUNT_CONTACT_ROLE =
		ContactFinder.class.getName() + ".joinByAccountContactRole";

	public static final String JOIN_BY_TEAM =
		ContactFinder.class.getName() + ".joinByTeam";

	@Override
	public int countByFN_MN_LN_E(
		String firstName, String middleName, String lastName,
		String emailAddress, LinkedHashMap<String, Object> params) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_FN_MN_LN_E);

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
			sql = _customSQL.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(firstName);
			qPos.add(firstName);
			qPos.add(middleName);
			qPos.add(middleName);
			qPos.add(lastName);
			qPos.add(lastName);
			qPos.add(emailAddress);
			qPos.add(emailAddress);

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
	public List<Contact> findByFN_MN_LN_E(
		String firstName, String middleName, String lastName,
		String emailAddress, LinkedHashMap<String, Object> params, int start,
		int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_FN_MN_LN_E);

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
			sql = _customSQL.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Koroneiki_Contact", ContactImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params);

			qPos.add(firstName);
			qPos.add(firstName);
			qPos.add(middleName);
			qPos.add(middleName);
			qPos.add(lastName);
			qPos.add(lastName);
			qPos.add(emailAddress);
			qPos.add(emailAddress);

			return (List<Contact>)QueryUtil.list(q, getDialect(), start, end);
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

		if (key.equals("account")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACCOUNT);
		}
		else if (key.equals("accountContactRole")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACCOUNT_CONTACT_ROLE);
		}
		else if (key.equals("team")) {
			join = _customSQL.get(getClass(), JOIN_BY_TEAM);
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

		if (key.equals("account")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACCOUNT);
		}
		else if (key.equals("accountContactRole")) {
			join = _customSQL.get(getClass(), JOIN_BY_ACCOUNT_CONTACT_ROLE);
		}
		else if (key.equals("team")) {
			join = _customSQL.get(getClass(), JOIN_BY_TEAM);
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

			if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
			else if (value instanceof String) {
				String valueString = (String)value;

				qPos.add(valueString);
			}
		}
	}

	@Reference
	private CustomSQL _customSQL;

}