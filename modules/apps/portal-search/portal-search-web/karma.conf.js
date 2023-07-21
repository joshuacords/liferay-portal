/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

var liferayKarmaAlloyConfig = require('liferay-karma-alloy-config');
var liferayKarmaConfig = require('liferay-karma-config');

module.exports = function(config) {
	liferayKarmaConfig(config);

	config.files = [];

	liferayKarmaAlloyConfig(config);

	config.autowatch = false;

	config.browserConsoleLogOptions = {
		format: '%b %T: %m',
		level: 'log',
		terminal: true
	};

	config.singleRun = true;

	config.files.push(
		{
			included: true,
			pattern: 'src/main/resources/META-INF/resources/**/!(config).js'
		},
		{
			included: true,
			pattern: 'test/*_util.js'
		},
		'test/*_test.js'
	);
};
