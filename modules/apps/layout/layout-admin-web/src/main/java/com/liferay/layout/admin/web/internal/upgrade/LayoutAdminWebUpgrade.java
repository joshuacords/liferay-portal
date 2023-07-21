/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.upgrade;

import com.liferay.journal.service.JournalArticleResourceLocalService;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_0.UpgradeLayout;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_1.UpgradeLayoutType;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_2.UpgradeLayoutSetTypeSettings;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_3.UpgradeLayoutTemplateId;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import java.rmi.registry.Registry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class LayoutAdminWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.3", new DummyUpgradeStep());

		registry.register("0.0.1", "1.0.0", new UpgradeLayout());

		registry.register(
			"1.0.0", "1.0.1",
			new UpgradeLayoutType(_journalArticleResourceLocalService));

		registry.register(
			"1.0.1", "1.0.2",
			new UpgradeLayoutSetTypeSettings(
				_groupLocalService, _layoutSetLocalService));

		registry.register("1.0.2", "1.0.3", new UpgradeLayoutTemplateId());
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JournalArticleResourceLocalService
		_journalArticleResourceLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

}