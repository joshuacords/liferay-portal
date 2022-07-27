/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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