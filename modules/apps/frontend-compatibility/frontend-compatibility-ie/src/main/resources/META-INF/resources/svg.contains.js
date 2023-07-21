/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

SVGElement.prototype.contains = function(el) {
	while ((el = el.parentNode)) {
		if (el === this) {
			return true;
		}
	}

	return false;
};
