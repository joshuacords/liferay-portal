/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.upgrade;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_0.UpgradeKernelPackage;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_0.UpgradeLastPublishDate;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_0.UpgradeSchema;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_1.UpgradeRecordGroup;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_1_1.UpgradeVersionUserId;
import com.liferay.dynamic.data.lists.internal.upgrade.v2_0_0.util.DDLRecordSetTable;
import com.liferay.dynamic.data.lists.internal.upgrade.v2_0_0.util.DDLRecordSetVersionTable;
import com.liferay.dynamic.data.lists.internal.upgrade.v2_0_0.util.DDLRecordTable;
import com.liferay.dynamic.data.lists.internal.upgrade.v2_0_0.util.DDLRecordVersionTable;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	service = {DDLServiceUpgrade.class, UpgradeStepRegistrator.class}
)
public class DDLServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "0.0.2", new UpgradeSchema());

		registry.register("0.0.2", "0.0.3", new UpgradeKernelPackage());

		registry.register("0.0.3", "1.0.0", new UpgradeLastPublishDate());

		registry.register("1.0.0", "1.0.1", new UpgradeRecordGroup());

		registry.register(
			"1.0.1", "1.0.2",
			new com.liferay.dynamic.data.lists.internal.upgrade.v1_0_2.
				UpgradeSchema());

		registry.register(
			"1.0.2", "1.1.0",
			new com.liferay.dynamic.data.lists.internal.upgrade.v1_1_0.
				UpgradeDDLRecord(),
			new com.liferay.dynamic.data.lists.internal.upgrade.v1_1_0.
				UpgradeDDLRecordSet(),
			new com.liferay.dynamic.data.lists.internal.upgrade.v1_1_0.
				UpgradeDDLRecordSetVersion(_counterLocalService));

		registry.register("1.1.0", "1.1.1", new UpgradeVersionUserId());

		registry.register(
			"1.1.1", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {
					DDLRecordSetTable.class, DDLRecordSetVersionTable.class,
					DDLRecordTable.class, DDLRecordVersionTable.class
				}));

		registry.register(
			"2.0.0", "2.1.0",
			new com.liferay.dynamic.data.lists.internal.upgrade.v2_1_0.
				UpgradeSchema());
	}

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

}