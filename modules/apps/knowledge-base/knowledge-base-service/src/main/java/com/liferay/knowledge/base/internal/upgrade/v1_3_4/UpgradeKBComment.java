/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_3_4;

import com.liferay.knowledge.base.constants.KBCommentConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Adolfo Pérez
 */
public class UpgradeKBComment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeSchema();
		upgradeKBComments();
	}

	protected void upgradeKBComments() throws Exception {
		if (!hasColumn("KBComment", "helpful")) {
			return;
		}

		runSQL(
			"update KBComment set userRating = " +
				KBCommentConstants.USER_RATING_LIKE +
					" where helpful = [$TRUE$]");

		runSQL(
			"update KBComment set userRating = " +
				KBCommentConstants.USER_RATING_DISLIKE +
					" where helpful = [$FALSE$]");

		runSQL("alter table KBComment drop column helpful");
	}

	protected void upgradeSchema() throws Exception {
		String template = StringUtil.read(
			UpgradeKBComment.class.getResourceAsStream(
				"dependencies/update.sql"));

		runSQLTemplateString(template, false, false);
	}

}