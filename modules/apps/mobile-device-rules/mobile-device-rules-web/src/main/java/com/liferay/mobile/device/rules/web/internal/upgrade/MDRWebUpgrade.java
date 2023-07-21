/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.upgrade;

import com.liferay.mobile.device.rules.web.internal.upgrade.v1_0_0.UpgradePortletId;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class MDRWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.2", new DummyUpgradeStep());

		registry.register("0.0.1", "1.0.0", new UpgradePortletId());

		registry.register(
			"1.0.0", "1.0.1",
			new MDRActionUpgrade(
				"com.liferay.portal.mobile.device.rulegroup.action.impl",
				"com.liferay.mobile.device.rules.rule.group.action"),
			new MDRRuleUpgrade(
				"com.liferay.portal.mobile.device.rulegroup.rule.impl." +
					"SimpleRuleHandler",
				"com.liferay.mobile.device.rules.rule.group.rule." +
					"SimpleRuleHandler"));

		registry.register(
			"1.0.1", "1.0.2",
			new MDRActionUpgrade(
				"com.liferay.mobile.device.rules.rule.group.action",
				"com.liferay.mobile.device.rules.web.internal.rule.group." +
					"action"),
			new MDRRuleUpgrade(
				"com.liferay.mobile.device.rules.rule.group.rule." +
					"SimpleRuleHandler",
				"com.liferay.mobile.device.rules.web.internal.rule.group." +
					"rule.SimpleRuleHandler"));
	}

	@Reference(
		target = "(release.bundle.symbolic.name=com.liferay.mobile.device.rules.service)",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

}