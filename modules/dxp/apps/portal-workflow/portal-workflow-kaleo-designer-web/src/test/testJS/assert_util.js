/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI().use('aui', A => {
	Liferay.Test = Liferay.Test || {};

	var assertIsValue = function(value, message) {
		assert(A.Lang.isValue(value), message);
	};

	Liferay.Test.assertIsValue = assertIsValue;

	var assertIsNotValue = function(value, message) {
		assert(!A.Lang.isValue(value), message);
	};

	Liferay.Test.assertIsNotValue = assertIsNotValue;
});
