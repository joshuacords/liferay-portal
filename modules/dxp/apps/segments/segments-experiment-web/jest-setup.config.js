/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const REGEX_SUB = /(?<=-|^)x(?=-|\s)/g;

window.Liferay.Util.sub = function(string, data) {
	if (
		arguments.length > 2 ||
		(typeof data !== 'object' && typeof data !== 'function')
	) {
		data = Array.prototype.slice.call(arguments, 1);
	}

	return string.replace(REGEX_SUB, (match, key) => {
		return data[key] === undefined ? match : data[key];
	});
};

window.Liferay.Util.openToast = () => true;
