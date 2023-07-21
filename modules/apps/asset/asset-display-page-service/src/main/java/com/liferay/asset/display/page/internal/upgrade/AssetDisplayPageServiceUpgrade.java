/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.internal.upgrade;

import com.liferay.asset.display.page.internal.upgrade.v2_0_0.util.AssetDisplayPageEntryTable;
import com.liferay.asset.display.page.internal.upgrade.v2_1_0.UpgradeAssetDisplayLayout;
import com.liferay.asset.display.page.internal.upgrade.v2_1_1.UpgradeAssetDisplayPrivateLayout;
import com.liferay.asset.display.page.internal.upgrade.v2_2_1.UpgradeAssetDisplayLayoutFriendlyURLPrivateLayout;
import com.liferay.asset.display.page.internal.upgrade.v2_2_2.UpgradeAssetDisplayPageEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author José Ángel Jiménez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class AssetDisplayPageServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {AssetDisplayPageEntryTable.class}));

		registry.register(
			"2.0.0", "2.1.0",
			new UpgradeAssetDisplayLayout(
				_assetEntryLocalService, _layoutLocalService,
				_layoutPageTemplateEntryLocalService,
				_layoutPageTemplateEntryService));

		registry.register(
			"2.1.0", "2.1.1",
			new UpgradeAssetDisplayPrivateLayout(
				_layoutLocalService, _resourceLocalService));

		registry.register(
			"2.1.1", "2.2.0",
			new UpgradeMVCCVersion() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {"AssetDisplayPageEntry"};
				}

			});

		registry.register(
			"2.2.0", "2.2.1",
			new UpgradeAssetDisplayLayoutFriendlyURLPrivateLayout());

		registry.register("2.2.1", "2.2.2", new UpgradeAssetDisplayPageEntry());
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutPageTemplateEntryLocalService
		_layoutPageTemplateEntryLocalService;

	@Reference
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Reference
	private ResourceLocalService _resourceLocalService;

}