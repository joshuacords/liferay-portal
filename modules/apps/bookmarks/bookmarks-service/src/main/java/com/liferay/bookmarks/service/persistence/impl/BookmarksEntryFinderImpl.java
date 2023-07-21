/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.service.persistence.impl;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.impl.BookmarksEntryImpl;
import com.liferay.bookmarks.service.persistence.BookmarksEntryFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Portal;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Component(service = BookmarksEntryFinder.class)
@Deprecated
public class BookmarksEntryFinderImpl
	extends BookmarksEntryFinderBaseImpl implements BookmarksEntryFinder {

	public static final String FIND_BY_NO_ASSETS =
		BookmarksEntryFinder.class.getName() + ".findByNoAssets";

	@Override
	public List<BookmarksEntry> findByNoAssets() {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_NO_ASSETS);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("BookmarksEntry", BookmarksEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(_portal.getClassNameId(BookmarksEntry.class));

			return q.list(true);
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

	@Reference
	private Portal _portal;

}