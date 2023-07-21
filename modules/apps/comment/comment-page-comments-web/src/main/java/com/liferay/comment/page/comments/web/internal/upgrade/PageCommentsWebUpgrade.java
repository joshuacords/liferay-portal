/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.comment.page.comments.web.internal.upgrade;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.comment.page.comments.web.internal.constants.PageCommentsPortletKeys;
import com.liferay.comment.upgrade.UpgradeDiscussionSubscriptionClassName;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.subscription.service.SubscriptionLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class PageCommentsWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "2.0.0", new DummyUpgradeStep());

		UpgradeStep upgradePortletId = new BaseUpgradePortletId() {

			@Override
			protected String[][] getRenamePortletIdsArray() {
				return new String[][] {
					{"107", PageCommentsPortletKeys.PAGE_COMMENTS}
				};
			}

		};

		registry.register("0.0.1", "1.0.0", upgradePortletId);

		registry.register(
			"1.0.0", "1.0.1",
			new UpgradeDiscussionSubscriptionClassName(
				_assetEntryLocalService, _classNameLocalService,
				_subscriptionLocalService, Layout.class.getName(),
				UpgradeDiscussionSubscriptionClassName.DeletionMode.UPDATE));

		registry.register(
			"1.0.1", "2.0.0",
			new UpgradeDiscussionSubscriptionClassName(
				_assetEntryLocalService, _classNameLocalService,
				_subscriptionLocalService, Layout.class.getName(),
				UpgradeDiscussionSubscriptionClassName.DeletionMode.
					DELETE_OLD));
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

}