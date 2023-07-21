/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.upgrade;

import com.liferay.batch.engine.internal.upgrade.v4_0_0.UpgradeVersion;
import com.liferay.batch.engine.internal.upgrade.v4_0_1.UpgradeClassName;
import com.liferay.batch.engine.internal.upgrade.v4_1_0.UpgradeTaskItemDelegateName;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class BatchEngineServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("2.0.0", "3.0.0", new DummyUpgradeStep());

		registry.register("3.0.0", "4.0.0", new UpgradeVersion());

		registry.register("4.0.0", "4.0.1", new UpgradeClassName());

		registry.register("4.0.1", "4.1.0", new UpgradeTaskItemDelegateName());
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}