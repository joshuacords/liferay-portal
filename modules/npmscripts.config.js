/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const CHECK_AND_FIX_GLOBS = [
	'/{,dxp/}*.js',
	'/{,dxp/}apps/*/*/*.js',
	'/{,dxp/}apps/*/*/{src,test}/**/*.{js,scss}',
	'/{,dxp/}apps/*/*/{src,test}/**/*.{jsp,jspf}'
];

module.exports = {
	check: CHECK_AND_FIX_GLOBS,
	fix: CHECK_AND_FIX_GLOBS,
	preset: 'liferay-npm-scripts/src/presets/standard'
};
