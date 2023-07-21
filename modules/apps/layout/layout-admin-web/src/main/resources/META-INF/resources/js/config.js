/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function() {
	AUI().applyConfig({
		groups: {
			layoutcustomizationsettings: {
				base: MODULE_PATH + '/',
				combine: Liferay.AUI.getCombine(),
				filter: Liferay.AUI.getFilterConfig(),
				modules: {
					'liferay-layout-customization-settings': {
						path: 'js/layout_customization_settings.js',
						requires: [
							'aui-base',
							'aui-overlay-mask-deprecated',
							'liferay-portlet-base'
						]
					}
				},
				root: MODULE_PATH + '/'
			}
		}
	});
})();
