/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint-env node */

module.exports = {
	collectCoverageFrom: [
		'**/*.{js,jsx}',
		'!**/node_modules/**',
		'!**/vendor/**'
	],
	moduleFileExtensions: ['js', 'jsx', 'html'],
	modulePathIgnorePatterns: [
		'<rootDir>[/\\\\](build|docs|node_modules|deploy|scripts)[/\\\\]'
	],
	setupFilesAfterEnv: ['./setupTests.js'],
	testMatch: ['<rootDir>/**/*.spec.js', '<rootDir>/**/*.spec.jsx'],
	transform: {
		'^.+\\.(js|jsx)?$': 'babel-jest',
		'^.+\\.html?$': 'html-loader-jest'
	},
	verbose: true
};
