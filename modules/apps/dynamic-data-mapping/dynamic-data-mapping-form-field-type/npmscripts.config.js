/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const standard = require('liferay-npm-scripts/src/presets/standard');

module.exports = Object.assign(standard, {
	build: Object.assign(standard.build, {
		dependencies: standard.build.dependencies.concat([
			'dynamic-data-mapping-form-renderer'
		])
	})
});
