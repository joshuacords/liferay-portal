/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.internal.upgrade;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.segments.internal.upgrade.v2_0_0.UpgradeSchema;
import com.liferay.segments.internal.upgrade.v2_0_0.UpgradeSegmentsExperience;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Arques
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class SegmentsServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("1.0.0", "1.0.1", new UpgradeSchema());

		registry.register(
			"1.0.1", "2.0.0",
			new UpgradeSegmentsExperience(_counterLocalService));

		registry.register(
			"2.0.0", "2.1.0",
			new UpgradeMVCCVersion() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"SegmentsEntry", "SegmentsEntryRel",
						"SegmentsExperience", "SegmentsExperiment",
						"SegmentsExperimentRel"
					};
				}

			});

		registry.register(
			"2.1.0", "2.2.0",
			new com.liferay.segments.internal.upgrade.v2_2_0.UpgradeSchema());
	}

	@Reference
	private CounterLocalService _counterLocalService;

}