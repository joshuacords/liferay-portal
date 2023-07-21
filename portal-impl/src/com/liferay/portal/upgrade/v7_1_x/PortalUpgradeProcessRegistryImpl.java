/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_1_x;

import com.liferay.portal.kernel.upgrade.DummyUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.version.Version;
import com.liferay.portal.upgrade.util.PortalUpgradeProcessRegistry;

import java.util.TreeMap;

/**
 * @author Alberto Chaparro
 */
public class PortalUpgradeProcessRegistryImpl
	implements PortalUpgradeProcessRegistry {

	@Override
	public void registerUpgradeProcesses(
		TreeMap<Version, UpgradeProcess> upgradeProcesses) {

		upgradeProcesses.put(new Version(1, 0, 0), new UpgradeSchema());

		upgradeProcesses.put(new Version(1, 1, 0), new UpgradeModules());

		upgradeProcesses.put(new Version(1, 1, 1), new UpgradeCounter());

		upgradeProcesses.put(new Version(1, 1, 2), new UpgradeDB2());

		upgradeProcesses.put(
			new Version(2, 0, 0), new UpgradeAssetTagsPermission());

		upgradeProcesses.put(
			new Version(2, 0, 1), new UpgradeDocumentLibrary());

		upgradeProcesses.put(
			new Version(2, 0, 2), new UpgradePasswordPolicyRegex());

		upgradeProcesses.put(
			new Version(2, 0, 3), new UpgradePortalPreferences());

		upgradeProcesses.put(new Version(2, 0, 4), new UpgradeUserGroup());

		upgradeProcesses.put(
			new Version(2, 0, 5), new UpgradeAnnouncementsPortletId());

		upgradeProcesses.put(
			new Version(2, 0, 6), new UpgradeAnnouncementsPortletPreferences());

		upgradeProcesses.put(
			new Version(2, 0, 7),
			new UpgradeCalendarTimeFormatPortletPreferences());

		upgradeProcesses.put(
			new Version(2, 0, 8),
			new UpgradeCalendarClassNameIdsPortletPreferences());

		upgradeProcesses.put(new Version(2, 0, 9), new DummyUpgradeProcess());

		upgradeProcesses.put(new Version(2, 0, 10), new DummyUpgradeProcess());

		upgradeProcesses.put(new Version(2, 0, 11), new DummyUpgradeProcess());
	}

}