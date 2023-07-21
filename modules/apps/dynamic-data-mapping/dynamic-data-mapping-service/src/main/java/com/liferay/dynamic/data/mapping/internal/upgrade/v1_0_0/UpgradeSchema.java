/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0;

import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.util.DDMContentTable;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.util.DDMStructureTable;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.util.DDMTemplateTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Marcellus Tavares
 */
public class UpgradeSchema extends UpgradeProcess {

	protected void alterTables() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			alter(
				DDMContentTable.class,
				new AlterColumnName("xml", "data_ TEXT null"));
			alter(
				DDMStructureTable.class,
				new AlterColumnName("xsd", "definition TEXT null"),
				new AlterColumnType("description", "TEXT null"));
			alter(
				DDMTemplateTable.class,
				new AlterColumnType("description", "TEXT null"));
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateSQL();

		alterTables();
	}

	protected void updateSQL() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			String template = StringUtil.read(
				UpgradeSchema.class.getResourceAsStream(
					"dependencies/update.sql"));

			runSQLTemplateString(template, false, false);
		}
	}

}