/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.reading.time.internal.upgrade;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.reading.time.internal.upgrade.v2_0_0.util.ReadingTimeEntryTable;

import org.osgi.service.component.annotations.Component;

/**
 * @author José Ángel Jiménez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ReadingTimeServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		DB db = DBManagerUtil.getDB();

		if (db.getDBType() == DBType.SQLSERVER) {
			registry.register(
				"1.0.0", "2.0.0",
				new BaseUpgradeSQLServerDatetime(
					new Class<?>[] {ReadingTimeEntryTable.class}));
		}
		else {
			registry.register("1.0.0", "2.0.0", new DummyUpgradeStep());
		}
	}

}