/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.version.Version;

import java.util.TreeMap;

/**
 * @author Alberto Chaparro
 */
public interface PortalUpgradeProcessRegistry {

	public void registerUpgradeProcesses(
		TreeMap<Version, UpgradeProcess> upgradeProcesses);

}