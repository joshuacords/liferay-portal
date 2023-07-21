/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_2_x;

/**
 * @author Cristina González
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
		"com.liferay.document.library.repository.cmis.impl"
	};

	private static final String[][] _CONVERTED_LEGACY_MODULES = {};

}