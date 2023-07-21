/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_1_x;

/**
 * @author Alberto Chaparro
 */
public class UpgradeModules
	extends com.liferay.portal.upgrade.v7_0_0.UpgradeModules {

	@Override
	public String[] getBundleSymbolicNames() {
		return _BUNDLE_SYMBOLIC_NAMES;
	}

	@Override
	public String[][] getConvertedLegacyModules() {
		return _CONVERTED_LEGACY_MODULES;
	}

	private static final String[] _BUNDLE_SYMBOLIC_NAMES = {
		"com.liferay.asset.category.property.service",
		"com.liferay.asset.entry.rel.service",
		"com.liferay.asset.tag.stats.service", "com.liferay.blogs.service",
		"com.liferay.document.library.content.service",
		"com.liferay.document.library.file.rank.service",
		"com.liferay.document.library.sync.service",
		"com.liferay.layout.page.template.service",
		"com.liferay.message.boards.service",
		"com.liferay.subscription.service", "com.liferay.trash.service"
	};

	private static final String[][] _CONVERTED_LEGACY_MODULES = {};

}