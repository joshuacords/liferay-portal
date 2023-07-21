/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Peter Shin
 */
public class UpgradeClassName extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateClassName(
			"com.liferay.knowledgebase.model.Article",
			"com.liferay.knowledgebase.model.KBArticle");
		updateClassName(
			"com.liferay.knowledgebase.model.Comment",
			"com.liferay.knowledgebase.model.KBComment");
		updateClassName(
			"com.liferay.knowledgebase.model.Template",
			"com.liferay.knowledgebase.model.KBTemplate");
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

	protected void updateClassName(String oldClassName, String newClassName)
		throws Exception {

		long oldClassNameId = getClassNameId(oldClassName);

		if (oldClassNameId != 0) {
			long newClassNameId = getClassNameId(newClassName);

			runSQL(
				"delete from ClassName_ where classNameId = " + newClassNameId);

			runSQL(
				StringBundler.concat(
					"update ClassName_ set value = '", newClassName,
					"' where classNameId = ", oldClassNameId));
		}
	}

}