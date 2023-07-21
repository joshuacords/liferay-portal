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
		terminal: false
	};
	config.client.mocha = {
		timeout: 10000
	};
	config.singleRun = true;

	var resourcesPath = 'src/main/resources/META-INF/resources/';
	var resourcesTestPath = 'src/test/resources/';
	var testPath = 'src/test/testJS/';

	config.files.push(
		{
			included: true,
			pattern: resourcesPath + '/**/!(config)*.js'
		},
		{
			included: false,
			pattern: resourcesTestPath + '/*.xml',
			served: true
		},
		{
			included: true,
			pattern: testPath + '/*_util.js'
		},
		'src/test/testJS/*_test.js'
	);
};
