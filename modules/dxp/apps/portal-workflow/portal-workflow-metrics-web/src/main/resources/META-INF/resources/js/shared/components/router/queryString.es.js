/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import qs from 'qs';

const options = {allowDots: true, arrayFormat: 'bracket'};

export function parse(queryString) {
	if (queryString && queryString.length) {
		return qs.parse(queryString.substr(1), options);
	}

	return {};
}

export function stringify(query) {
	if (query) {
		return `?${qs.stringify(query, options)}`;
	}
}
