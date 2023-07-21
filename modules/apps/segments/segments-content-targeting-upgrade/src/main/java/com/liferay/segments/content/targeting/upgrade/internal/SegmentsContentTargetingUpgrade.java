/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.content.targeting.upgrade.internal;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.segments.content.targeting.upgrade.internal.v1_0_0.UpgradeContentTargeting;
import com.liferay.segments.content.targeting.upgrade.internal.v1_0_0.util.RuleConverterRegistry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	service = {
		SegmentsContentTargetingUpgrade.class, UpgradeStepRegistrator.class
	}
)
public class SegmentsContentTargetingUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.0", "1.0.0",
			new UpgradeContentTargeting(
				_ruleConverterRegistry, _segmentsEntryLocalService));
	}

	@Reference
	private RuleConverterRegistry _ruleConverterRegistry;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

}