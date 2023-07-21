/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI().use('aui', () => {
	Liferay.Test = Liferay.Test || {};

	var includes = function(array, value) {
		return array.indexOf(value) != -1;
	};

	var assertSameItems = function(expected, actual) {
		var message = 'Expected [' + expected + ']; got [' + actual + ']';

		assert.equal(expected.length, actual.length, message);

		expected.forEach(item => {
			assert(includes(actual, item), message);
		});
	};

	Liferay.Test.assertSameItems = assertSameItems;

	var assertEmpty = function(array) {
		assert.equal(0, array.length);
	};

	Liferay.Test.assertEmpty = assertEmpty;
});
