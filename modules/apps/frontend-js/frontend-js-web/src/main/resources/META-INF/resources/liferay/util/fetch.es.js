/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const DEFAULT_INIT = {
	credentials: 'include'
};

/**
 * Fetches a resource. A thin wrapper around ES6 Fetch API, with standardized
 * default configuration.
 * @param {!string|!Request} resource The URL to the resource, or a Resource
 * object.
 * @param {Object=} init An optional object containing custom configuration.
 * @return {Promise} A Promise that resolves to a Response object.
 */

export default function defaultFetch(resource, init = {}) {
	const headers = new Headers({'x-csrf-token': Liferay.authToken});

	new Headers(init.headers || {}).forEach((value, key) => {
		headers.set(key, value);
	});

	const mergedInit = {
		...DEFAULT_INIT,
		...init
	};

	mergedInit.headers = headers;

	// eslint-disable-next-line liferay-portal/no-global-fetch
	return fetch(resource, mergedInit);
}
