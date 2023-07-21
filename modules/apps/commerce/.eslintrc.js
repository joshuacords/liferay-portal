/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const path = require('path');

module.exports = {
	extends: [path.join(__dirname, '../../.eslintrc.js')],
	overrides : [
		{
			files: [
				'commerce-theme-minium/commerce-theme-minium/src/js/intersection-observer.js',
				'commerce-theme-speedwell/commerce-theme-speedwell/src/js/intersection-observer.js'
			],
			rules: {
				'notice/notice': [
					'error',
					{
						templateFile: path.join(__dirname, 'google-copyright.js')
					}
				]
			}
		}
	]
};