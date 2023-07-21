/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.upgrade;

import com.liferay.fragment.internal.upgrade.v2_0_0.util.FragmentCollectionTable;
import com.liferay.fragment.internal.upgrade.v2_0_0.util.FragmentEntryLinkTable;
import com.liferay.fragment.internal.upgrade.v2_0_0.util.FragmentEntryTable;
import com.liferay.fragment.internal.upgrade.v2_1_0.UpgradeSchema;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.step.util.UpgradeStepFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author José Ángel Jiménez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class FragmentServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.0.1",
			UpgradeStepFactory.alterColumnTypes(
				com.liferay.fragment.internal.upgrade.v1_0_1.util.
					FragmentEntryTable.class,
				"TEXT null", "css", "html", "js"),
			UpgradeStepFactory.alterColumnTypes(
				com.liferay.fragment.internal.upgrade.v1_0_1.util.
					FragmentEntryLinkTable.class,
				"TEXT null", "css", "html", "js", "editableValues"));

		registry.register("1.0.1", "1.0.2", new DummyUpgradeStep());

		registry.register(
			"1.0.2", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {
					FragmentCollectionTable.class, FragmentEntryLinkTable.class,
					FragmentEntryTable.class
				}));

		registry.register("2.0.0", "2.1.0", new UpgradeSchema());

		registry.register("2.1.0", "2.1.1", new DummyUpgradeStep());

		registry.register(
			"2.1.1", "2.1.2",
			new com.liferay.fragment.internal.upgrade.v2_1_2.UpgradeSchema());

		registry.register(
			"2.1.2", "2.1.3",
			new com.liferay.fragment.internal.upgrade.v2_1_3.UpgradeSchema());

		registry.register(
			"2.1.3", "2.2.0",
			new UpgradeMVCCVersion() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"FragmentCollection", "FragmentEntry",
						"FragmentEntryLink"
					};
				}

			});
	}

}