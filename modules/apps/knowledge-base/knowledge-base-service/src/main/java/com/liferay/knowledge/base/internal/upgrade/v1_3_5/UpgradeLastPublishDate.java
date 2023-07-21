/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_3_5;

import com.liferay.portal.kernel.upgrade.BaseUpgradeLastPublishDate;

/**
 * @author Máté Thurzó
 */
public class UpgradeLastPublishDate extends BaseUpgradeLastPublishDate {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table KBArticle add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBArticle");

		runSQL("alter table KBComment add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBComment");

		runSQL("alter table KBFolder add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBFolder");

		runSQL("alter table KBTemplate add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBTemplate");
	}

}