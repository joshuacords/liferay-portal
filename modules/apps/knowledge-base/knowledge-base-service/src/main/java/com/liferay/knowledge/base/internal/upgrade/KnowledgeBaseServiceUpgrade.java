/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.knowledge.base.internal.upgrade.v2_0_2.UpgradeKBArticle;
import com.liferay.knowledge.base.internal.upgrade.v3_0_0.util.KBArticleTable;
import com.liferay.knowledge.base.internal.upgrade.v3_0_0.util.KBCommentTable;
import com.liferay.knowledge.base.internal.upgrade.v3_0_0.util.KBFolderTable;
import com.liferay.knowledge.base.internal.upgrade.v3_0_0.util.KBTemplateTable;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(service = UpgradeStepRegistrator.class)
public class KnowledgeBaseServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.1", "1.0.0",
			new com.liferay.knowledge.base.internal.upgrade.v1_0_0.
				UpgradeRatingsEntry(),
			new com.liferay.knowledge.base.internal.upgrade.v1_0_0.
				UpgradeRatingsStats());

		registry.register(
			"1.0.0", "1.1.0",
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeClassName(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeExpandoTable(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeKBArticle(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeKBComment(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeKBTemplate(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradePortletPreferences(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeResourceAction(),
			new com.liferay.knowledge.base.internal.upgrade.v1_1_0.
				UpgradeResourcePermission());

		registry.register(
			"1.1.0", "1.2.0",
			new com.liferay.knowledge.base.internal.upgrade.v1_2_0.
				UpgradeKBArticle(),
			new com.liferay.knowledge.base.internal.upgrade.v1_2_0.
				UpgradeKBStructure(),
			new com.liferay.knowledge.base.internal.upgrade.v1_2_0.
				UpgradeKBTemplate());

		registry.register(
			"1.2.0", "1.3.0",
			new com.liferay.knowledge.base.internal.upgrade.v1_3_0.
				UpgradeKBAttachments(_store),
			new com.liferay.knowledge.base.internal.upgrade.v1_3_0.
				UpgradePortletPreferences());

		registry.register(
			"1.3.0", "1.3.1",
			new com.liferay.knowledge.base.internal.upgrade.v1_3_1.
				UpgradeKBArticle(),
			new com.liferay.knowledge.base.internal.upgrade.v1_3_1.
				UpgradeKBComment());

		registry.register(
			"1.3.1", "1.3.2",
			new com.liferay.knowledge.base.internal.upgrade.v1_3_2.
				UpgradeKBArticle(),
			new com.liferay.knowledge.base.internal.upgrade.v1_3_2.
				UpgradeKBFolder());

		registry.register(
			"1.3.2", "1.3.3",
			new com.liferay.knowledge.base.internal.upgrade.v1_3_3.
				UpgradeKBFolder());

		registry.register(
			"1.3.3", "1.3.4",
			new com.liferay.knowledge.base.internal.upgrade.v1_3_4.
				UpgradeKBArticle(),
			new com.liferay.knowledge.base.internal.upgrade.v1_3_4.
				UpgradeKBComment(),
			new com.liferay.knowledge.base.internal.upgrade.v1_3_4.
				UpgradePortletPreferences(),
			new com.liferay.knowledge.base.internal.upgrade.v1_3_4.
				UpgradeResourceAction());

		registry.register(
			"1.3.4", "1.3.5",
			new com.liferay.knowledge.base.internal.upgrade.v1_3_5.
				UpgradeLastPublishDate());

		registry.register(
			"1.3.5", "2.0.0",
			new com.liferay.knowledge.base.internal.upgrade.v2_0_0.
				UpgradeClassNames(),
			new com.liferay.knowledge.base.internal.upgrade.v2_0_0.
				UpgradeKBComment(),
			new com.liferay.knowledge.base.internal.upgrade.v2_0_0.
				UpgradeRepository());

		registry.register(
			"2.0.0", "2.0.1",
			new com.liferay.knowledge.base.internal.upgrade.v2_0_1.
				UpgradePortletSettings(_settingsFactory));

		registry.register("2.0.1", "2.0.2", new UpgradeKBArticle());

		registry.register(
			"2.0.2", "3.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {
					KBArticleTable.class, KBCommentTable.class,
					KBFolderTable.class, KBTemplateTable.class
				}));

		registry.register(
			"3.0.0", "3.1.0",
			new UpgradeMVCCVersion() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"KBArticle", "KBComment", "KBFolder", "KBTemplate"
					};
				}

			});
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

	@Reference(target = "(dl.store.upgrade=true)")
	private Store _store;

}