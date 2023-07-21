/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.virtual.host.internal.activator;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Hai Yu
 */
public class PortalVirtualHostImplBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		UpgradeVirtualHost upgradeVirtualHost = new UpgradeVirtualHost();

		upgradeVirtualHost.upgrade();
	}

	@Override
	public void stop(BundleContext context) {
	}

	private static class UpgradeVirtualHost extends UpgradeProcess {

		@Override
		protected void doUpgrade() throws Exception {
			if (!hasTable("VirtualHost")) {
				return;
			}

			if (!hasColumn("VirtualHost", "defaultVirtualHost")) {
				runSQL(
					"alter table VirtualHost add defaultVirtualHost BOOLEAN");

				runSQL("update VirtualHost set defaultVirtualHost = [$TRUE$]");
			}

			if (!hasColumn("VirtualHost", "languageId")) {
				runSQL(
					"alter table VirtualHost add languageId VARCHAR(75) null");
			}
		}

	}

}