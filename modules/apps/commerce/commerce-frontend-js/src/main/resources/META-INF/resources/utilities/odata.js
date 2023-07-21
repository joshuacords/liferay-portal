/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default function createOdataFilter(filters) {
	if (!filters.length) {
		return null;
	}

	return filters
		.map(filter => filter.odataFilterString)
		.map(filterString => `(${filterString})`)
		.join(' and ');
}
