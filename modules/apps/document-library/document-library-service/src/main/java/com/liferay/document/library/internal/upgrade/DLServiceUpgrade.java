/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.upgrade;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.comment.upgrade.UpgradeDiscussionSubscriptionClassName;
import com.liferay.document.library.internal.upgrade.v1_0_0.UpgradeDocumentLibrary;
import com.liferay.document.library.internal.upgrade.v1_0_1.UpgradeDLConfiguration;
import com.liferay.document.library.internal.upgrade.v1_0_1.UpgradeDLFileEntryConfiguration;
import com.liferay.document.library.internal.upgrade.v1_0_2.UpgradeDLFileShortcut;
import com.liferay.document.library.internal.upgrade.v1_1_0.UpgradeSchema;
import com.liferay.document.library.internal.upgrade.v1_1_2.UpgradeDLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgradeHelper;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.subscription.service.SubscriptionLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Miguel Pastor
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class DLServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.1", "1.0.0", new UpgradeDocumentLibrary(_store));

		registry.register("1.0.0", "1.0.1", new UpgradeDLFileShortcut());

		registry.register(
			"1.0.1", "1.0.2",
			new UpgradeDLConfiguration(_prefsPropsToConfigurationUpgradeHelper),
			new UpgradeDLFileEntryConfiguration(
				_prefsPropsToConfigurationUpgradeHelper));

		registry.register("1.0.2", "1.1.0", new UpgradeSchema());

		registry.register(
			"1.1.0", "1.1.1",
			new UpgradeDiscussionSubscriptionClassName(
				_assetEntryLocalService, _classNameLocalService,
				_subscriptionLocalService, DLFileEntry.class.getName(),
				UpgradeDiscussionSubscriptionClassName.DeletionMode.UPDATE));

		registry.register(
			"1.1.1", "1.1.2",
			new UpgradeDLFileEntryType(_resourceLocalService));
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private PrefsPropsToConfigurationUpgradeHelper
		_prefsPropsToConfigurationUpgradeHelper;

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference(target = "(dl.store.upgrade=true)")
	private Store _store;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

}