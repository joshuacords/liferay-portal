/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.upgrade;

import com.liferay.mobile.device.rules.internal.upgrade.v2_0_0.util.MDRActionTable;
import com.liferay.mobile.device.rules.internal.upgrade.v2_0_0.util.MDRRuleGroupInstanceTable;
import com.liferay.mobile.device.rules.internal.upgrade.v2_0_0.util.MDRRuleGroupTable;
import com.liferay.mobile.device.rules.internal.upgrade.v2_0_0.util.MDRRuleTable;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class MDRServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		registry.register("1.0.0", "1.0.1", new DummyUpgradeStep());

		registry.register(
			"1.0.1", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {
					MDRActionTable.class, MDRRuleGroupInstanceTable.class,
					MDRRuleGroupTable.class, MDRRuleTable.class
				}));

		registry.register(
			"2.0.0", "2.1.0",
			new UpgradeMVCCVersion() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"MDRAction", "MDRRule", "MDRRuleGroup",
						"MDRRuleGroupInstance"
					};
				}

			});
	}

}