/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export function closest(element, selector) {
	var matches = window.document.querySelectorAll(selector);
	var i;
	do {
		i = matches.length;
		// eslint-disable-next-line no-empty
		while (--i >= 0 && matches.item(i) !== element) {}
	} while (i < 0 && (element = element.parentElement));

	return element;
}
