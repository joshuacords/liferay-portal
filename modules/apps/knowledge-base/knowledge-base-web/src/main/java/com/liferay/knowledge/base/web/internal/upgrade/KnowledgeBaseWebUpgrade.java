/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.upgrade;

import com.liferay.knowledge.base.web.internal.upgrade.v1_0_0.UpgradePortletId;
import com.liferay.knowledge.base.web.internal.upgrade.v1_0_0.UpgradePortletSettings;
import com.liferay.knowledge.base.web.internal.upgrade.v1_1_0.UpgradePortletPreferences;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeWebModuleRelease;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 * @author Roberto Díaz
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class KnowledgeBaseWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		BaseUpgradeWebModuleRelease upgradeWebModuleRelease =
			new BaseUpgradeWebModuleRelease() {

				@Override
				protected String getBundleSymbolicName() {
					return "com.liferay.knowledge.base.web";
				}

				@Override
				protected String[] getPortletIds() {
					return new String[] {
						"1_WAR_knowledgebaseportlet",
						"2_WAR_knowledgebaseportlet",
						"3_WAR_knowledgebaseportlet",
						"4_WAR_knowledgebaseportlet",
						"5_WAR_knowledgebaseportlet"
					};
				}

			};

		try {
			upgradeWebModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register("0.0.0", "1.2.0", new DummyUpgradeStep());

		registry.register(
			"0.0.1", "1.0.0", new UpgradePortletId(),
			new UpgradePortletSettings(_settingsFactory));

		registry.register("1.0.0", "1.1.0", new UpgradePortletPreferences());

		registry.register(
			"1.1.0", "1.2.0",
			new com.liferay.knowledge.base.web.internal.upgrade.v1_2_0.
				UpgradePortletPreferences());
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	private SettingsFactory _settingsFactory;

}