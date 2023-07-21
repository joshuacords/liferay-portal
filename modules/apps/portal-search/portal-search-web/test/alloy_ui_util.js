/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

var withAlloyUI = function(testCase, dependencies) {
	return function(done) {
		AUI().use(['aui-base', 'aui-node'].concat(dependencies || []), A => {
			testCase(done, A);
		});
	};
};

Liferay.namespace('Test').withAlloyUI = withAlloyUI;
