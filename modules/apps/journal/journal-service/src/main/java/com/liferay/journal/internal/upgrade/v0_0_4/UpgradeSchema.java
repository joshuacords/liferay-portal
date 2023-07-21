/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v0_0_4;

import com.liferay.journal.internal.upgrade.v0_0_4.util.JournalArticleTable;
import com.liferay.journal.internal.upgrade.v0_0_4.util.JournalFeedTable;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Eduardo García
 */
public class UpgradeSchema extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		String template = StringUtil.read(
			UpgradeSchema.class.getResourceAsStream("dependencies/update.sql"));

		runSQLTemplateString(template, false, false);

		upgrade(new UpgradeMVCCVersion());

		alter(
			JournalArticleTable.class,
			new AlterColumnName(
				"structureId", "DDMStructureKey VARCHAR(75) null"),
			new AlterColumnName(
				"templateId", "DDMTemplateKey VARCHAR(75) null"),
			new AlterColumnType("description", "TEXT null"));

		alter(
			JournalFeedTable.class,
			new AlterColumnName("structureId", "DDMStructureKey TEXT null"),
			new AlterColumnName("templateId", "DDMTemplateKey TEXT null"),
			new AlterColumnName(
				"rendererTemplateId", "DDMRendererTemplateKey TEXT null"),
			new AlterColumnType("targetPortletId", "VARCHAR(200) null"));
	}

}