/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export const withParams = (...args) => ({
	location: {search},
	match: {params}
}) =>
	args.map((Component, index) => (
		<Component {...params} key={index} query={search} />
	));
