/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_3_3;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Adolfo Pérez
 */
public class UpgradeKBFolder extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Map<Long, String> urlTitles = _getInitialUrlTitles(connection);

		for (Map.Entry<Long, String> entry : urlTitles.entrySet()) {
			String uniqueUrlTitle = _findUniqueUrlTitle(
				connection, entry.getValue());

			for (int i = 1; uniqueUrlTitle == null; i++) {
				uniqueUrlTitle = _findUniqueUrlTitle(
					connection, entry.getValue() + StringPool.DASH + i);
			}

			_updateKBFolder(connection, entry.getKey(), uniqueUrlTitle);
		}
	}

	private String _findUniqueUrlTitle(Connection con, String urlTitle)
		throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(
				"select count(*) from KBFolder where KBFolder.urlTitle like ?");

			ps.setString(1, urlTitle + "%");

			rs = ps.executeQuery();

			if (!rs.next()) {
				return urlTitle;
			}

			int kbFolderCount = rs.getInt(1);

			if (kbFolderCount == 0) {
				return urlTitle;
			}

			return null;
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(rs);
		}
	}

	private Map<Long, String> _getInitialUrlTitles(Connection con)
		throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(
				"select kbFolderId, name from KBFolder where " +
					"(KBFolder.urlTitle is null) or (KBFolder.urlTitle = '')");

			rs = ps.executeQuery();

			Map<Long, String> urlTitles = new HashMap<>();

			while (rs.next()) {
				long kbFolderId = rs.getLong(1);
				String name = rs.getString(2);

				String urlTitle = _getUrlTitle(kbFolderId, name);

				urlTitles.put(kbFolderId, urlTitle);
			}

			return urlTitles;
		}
		finally {
			DataAccess.cleanUp(ps);
			DataAccess.cleanUp(rs);
		}
	}

	/**
	 * See {@link
	 * com.liferay.knowledge.base.util.KnowledgeBaseUtil#getUrlTitle(long,
	 * String)}
	 */
	private String _getUrlTitle(long id, String title) {
		if (title == null) {
			return String.valueOf(id);
		}

		title = StringUtil.toLowerCase(title.trim());

		if (Validator.isNull(title) || Validator.isNumber(title) ||
			title.equals("rss")) {

			title = String.valueOf(id);
		}
		else {
			title = FriendlyURLNormalizerUtil.normalize(
				title, _normalizationFriendlyUrlPattern);
		}

		return title.substring(0, 75);
	}

	private void _updateKBFolder(
			Connection con, long kbFolderId, String urlTitle)
		throws SQLException {

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
				"update KBFolder set KBFolder.urlTitle = ? where " +
					"KBFolder.kbFolderId = ?");

			ps.setString(1, urlTitle);
			ps.setLong(2, kbFolderId);

			ps.execute();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	private static final Pattern _normalizationFriendlyUrlPattern =
		Pattern.compile("[^a-z0-9_-]");

}