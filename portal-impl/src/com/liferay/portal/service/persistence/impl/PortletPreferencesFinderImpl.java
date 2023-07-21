/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesFinder;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.PortletPreferencesImpl;
import com.liferay.portal.model.impl.PortletPreferencesModelImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.io.Serializable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class PortletPreferencesFinderImpl
	extends PortletPreferencesFinderBaseImpl
	implements PortletPreferencesFinder {

	public static final String COUNT_BY_O_O_P =
		PortletPreferencesFinder.class.getName() + ".countByO_O_P";

	public static final String COUNT_BY_O_O_P_P_P =
		PortletPreferencesFinder.class.getName() + ".countByO_O_P_P_P";

	public static final String FIND_BY_PORTLET_ID =
		PortletPreferencesFinder.class.getName() + ".findByPortletId";

	public static final String FIND_BY_C_G_O_O_P_P =
		PortletPreferencesFinder.class.getName() + ".findByC_G_O_O_P_P";

	public static final FinderPath FINDER_PATH_FIND_BY_C_G_O_O_P_P =
		new FinderPath(
			PortletPreferencesModelImpl.ENTITY_CACHE_ENABLED,
			PortletPreferencesModelImpl.FINDER_CACHE_ENABLED,
			PortletPreferencesImpl.class,
			PortletPreferencesPersistenceImpl.
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_G_O_O_P_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

	@Override
	public long countByO_O_P(
		long ownerId, int ownerType, String portletId,
		boolean excludeDefaultPreferences) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_O_O_P);

			if (ownerId == -1) {
				sql = StringUtil.removeSubstring(sql, _OWNER_ID_SQL);
			}

			if (excludeDefaultPreferences) {
				sql = StringUtil.replace(
					sql, "[$PORTLET_PREFERENCES_PREFERENCES_DEFAULT$]",
					PortletConstants.DEFAULT_PREFERENCES);
			}
			else {
				sql = StringUtil.removeSubstring(sql, _PREFERENCES_SQL);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (ownerId != -1) {
				qPos.add(ownerId);
			}

			qPos.add(ownerType);
			qPos.add(portletId);
			qPos.add(portletId.concat("%_INSTANCE_%"));

			int count = 0;

			Iterator<Long> itr = q.iterate();

			while (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public long countByO_O_P_P_P(
		long ownerId, int ownerType, long plid, String portletId,
		boolean excludeDefaultPreferences) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_O_O_P_P_P);

			if (ownerId == -1) {
				sql = StringUtil.removeSubstring(sql, _OWNER_ID_SQL);
			}

			if (plid == -1) {
				sql = StringUtil.removeSubstring(sql, _PLID_SQL);
			}
			else {
				sql = StringUtil.removeSubstring(sql, _PORTLET_ID_INSTANCE_SQL);
			}

			if (excludeDefaultPreferences) {
				sql = StringUtil.replace(
					sql, "[$PORTLET_PREFERENCES_PREFERENCES_DEFAULT$]",
					PortletConstants.DEFAULT_PREFERENCES);
			}
			else {
				sql = StringUtil.removeSubstring(sql, _PREFERENCES_SQL);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (ownerId != -1) {
				qPos.add(ownerId);
			}

			qPos.add(ownerType);
			qPos.add(portletId);

			if (plid != -1) {
				qPos.add(plid);
			}
			else {
				qPos.add(portletId.concat("%_INSTANCE_%"));
			}

			int count = 0;

			Iterator<Long> itr = q.iterate();

			while (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count += l.intValue();
				}
			}

			return count;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public Map<Serializable, PortletPreferences> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return PortletPreferencesUtil.fetchByPrimaryKeys(primaryKeys);
	}

	@Override
	public List<PortletPreferences> findByPortletId(String portletId) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_PORTLET_ID);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("PortletPreferences", PortletPreferencesImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(portletId);

			return q.list(true);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<PortletPreferences> findByC_G_O_O_P_P(
		long companyId, long groupId, long ownerId, int ownerType,
		String portletId, boolean privateLayout) {

		Object[] finderArgs = {
			companyId, groupId, ownerId, ownerType, portletId, privateLayout
		};

		List<PortletPreferences> list =
			(List<PortletPreferences>)FinderCacheUtil.getResult(
				FINDER_PATH_FIND_BY_C_G_O_O_P_P, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PortletPreferences portletPreferences : list) {
				if ((ownerId != portletPreferences.getOwnerId()) ||
					(ownerType != portletPreferences.getOwnerType()) ||
					!isInstanceOf(
						portletPreferences.getPortletId(), portletId)) {

					list = null;

					break;
				}
			}
		}

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = CustomSQLUtil.get(FIND_BY_C_G_O_O_P_P);

				SQLQuery q = session.createSynchronizedSQLQuery(sql);

				q.addEntity("PortletPreferences", PortletPreferencesImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);
				qPos.add(groupId);
				qPos.add(ownerId);
				qPos.add(ownerType);
				qPos.add(portletId);
				qPos.add(portletId.concat("_INSTANCE_%"));
				qPos.add(privateLayout);

				list = q.list(true);

				PortletPreferencesUtil.cacheResult(list);

				FinderCacheUtil.putResult(
					FINDER_PATH_FIND_BY_C_G_O_O_P_P, finderArgs, list);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(
					FINDER_PATH_FIND_BY_C_G_O_O_P_P, finderArgs);

				throw new SystemException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	protected boolean isInstanceOf(
		String portletPreferencesPortletId, String portletId) {

		portletPreferencesPortletId = GetterUtil.getString(
			portletPreferencesPortletId);
		portletId = GetterUtil.getString(portletId);

		if (portletPreferencesPortletId.equals(portletId)) {
			return true;
		}

		String portletName = PortletIdCodec.decodePortletName(
			portletPreferencesPortletId);

		return Objects.equals(portletName, portletId);
	}

	private static final String _OWNER_ID_SQL =
		"(PortletPreferences.ownerId = ?) AND";

	private static final String _PLID_SQL = "AND (PortletPreferences.plid = ?)";

	private static final String _PORTLET_ID_INSTANCE_SQL =
		"OR (PortletPreferences.portletId LIKE ?)";

	private static final String _PREFERENCES_SQL =
		"AND (CAST_CLOB_TEXT(PortletPreferences.preferences) != " +
			"'[$PORTLET_PREFERENCES_PREFERENCES_DEFAULT$]')";

}