/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default function throttle(fn, limit) {
	let lastFunction, lastRan;

	return () => {
		const context = this,
			args = arguments;

		if (!lastRan) {
			fn.apply(context, args);

			lastRan = Date.now();
		}
		else {
			clearTimeout(lastFunction);

			lastFunction = setTimeout(() => {
				if (Date.now() - lastRan >= limit) {
					fn.apply(context, args);

					lastRan = Date.now();
				}
			}, limit - (Date.now() - lastRan));
		}
	};
}
