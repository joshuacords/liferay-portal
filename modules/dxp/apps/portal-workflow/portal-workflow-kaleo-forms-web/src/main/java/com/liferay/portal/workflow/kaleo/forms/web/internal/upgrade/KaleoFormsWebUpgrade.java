/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.web.internal.upgrade;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.workflow.kaleo.forms.web.internal.upgrade.v1_0_2.UpgradePortletId;
import com.liferay.portal.workflow.kaleo.forms.web.internal.upgrade.v1_0_3.UpgradeLayoutTypeSettings;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class KaleoFormsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.portal.workflow.kaleo.forms.web", "0.0.0", "1.0.2",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.portal.workflow.kaleo.forms.web", "0.0.1", "1.0.2",
			new UpgradePortletId());

		registry.register(
			"com.liferay.portal.workflow.kaleo.forms.web", "1.0.2", "1.0.3",
			new UpgradeLayoutTypeSettings());
	}

}