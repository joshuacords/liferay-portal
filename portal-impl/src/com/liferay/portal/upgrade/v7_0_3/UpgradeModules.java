/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_3;

/**
 * @author Shuyang Zhou
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
		"com.liferay.portal.reports.engine.console.web",
		"com.liferay.portal.workflow.kaleo.forms.web"
	};

	private static final String[][] _CONVERTED_LEGACY_MODULES = {
		{
			"reports-portlet",
			"com.liferay.portal.reports.engine.console.service", "Reports"
		}
	};

}