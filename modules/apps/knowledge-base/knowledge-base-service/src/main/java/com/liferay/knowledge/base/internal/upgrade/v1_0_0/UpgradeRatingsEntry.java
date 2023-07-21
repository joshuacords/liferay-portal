/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeRatingsEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateRatingsEntries();
	}

	protected long getClassNameId(String className) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select classNameId from ClassName_ where value = ?");

			ps.setString(1, className);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("classNameId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateRatingsEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			long classNameId = getClassNameId(_ARTICLE_CLASS_NAME);

			ps = connection.prepareStatement(
				"select entryId, score from RatingsEntry where classNameId = " +
					classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long entryId = rs.getLong("entryId");
				double score = rs.getDouble("score");

				StringBundler sb = new StringBundler(4);

				sb.append("update RatingsEntry set score = ");
				sb.append(score * 2);
				sb.append(" where entryId = ");
				sb.append(entryId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final String _ARTICLE_CLASS_NAME =
		"com.liferay.knowledgebase.model.Article";

}