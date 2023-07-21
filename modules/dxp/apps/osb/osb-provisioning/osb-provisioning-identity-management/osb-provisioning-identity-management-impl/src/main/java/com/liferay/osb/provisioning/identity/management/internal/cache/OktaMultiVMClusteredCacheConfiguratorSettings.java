/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.identity.management.internal.cache;

import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.cache.configurator.PortalCacheConfiguratorSettings;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = "portal.cache.manager.name=" + PortalCacheManagerNames.MULTI_VM,
	service = PortalCacheConfiguratorSettings.class
)
public class OktaMultiVMClusteredCacheConfiguratorSettings
	extends PortalCacheConfiguratorSettings {

	public OktaMultiVMClusteredCacheConfiguratorSettings() {
		super(
			OktaMultiVMClusteredCacheConfiguratorSettings.class.
				getClassLoader(),
			"META-INF/module-multi-vm-clustered.xml");
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}