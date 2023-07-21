/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import memoize from 'lodash.memoize';

const cached = fn => {
	return memoize(fn, (...args) => {
		return args.length > 1
			? Array.prototype.join.call(args, '_')
			: String(args[0]);
	});
};

const nsCached = cached((namespace, str) => {
	if (typeof str !== 'undefined' && !(str.lastIndexOf(namespace, 0) === 0)) {
		str = `${namespace}${str}`;
	}

	return str;
});

export default function(namespace, obj) {
	let value;

	if (typeof obj !== 'object') {
		value = nsCached(namespace, obj);
	}
	else {
		value = {};

		const keys = Object.keys(obj);

		keys.forEach(item => {
			const originalItem = item;

			item = nsCached(namespace, item);
			value[item] = obj[originalItem];
		});
	}

	return value;
}
