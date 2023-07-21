/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.contacts.internal.upgrade;

import com.liferay.contacts.internal.upgrade.v2_0_0.UpgradeEntry;
import com.liferay.contacts.internal.upgrade.v2_0_2.UpgradeEmailAddress;
import com.liferay.contacts.internal.upgrade.v3_0_0.util.EntryTable;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Lee
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ContactsServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		// See LPS-65946

		registry.register(
			"1.0.0", "2.0.1", new UpgradeEntry(_userLocalService));

		registry.register("2.0.0", "2.0.1", new DummyUpgradeStep());

		registry.register("2.0.1", "2.0.2", new UpgradeEmailAddress());

		registry.register(
			"2.0.2", "3.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {EntryTable.class}));
	}

	@Reference
	private UserLocalService _userLocalService;

}