/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {
	NAMESPACE,
	PATTERN_IP_ADDRESS_V4,
	PATTERN_IP_ADDRESS_V6,
	PATTERN_MAC_ADDRESS
} from '../utilities/constants';

const DEFAULT_INIT = {
	credentials: 'include'
};

/**
 * Takes an input string and returns a new capitalized string.
 * @param {string} input The input string to be capitalized.
 * @returns {string} The output string that has been capitalized.
 */
export function capitalize(input) {
	return input[0].toUpperCase() + input.slice(1).toLowerCase();
}

/**
 * Certain empty values are represented by a dash in the UI.
 * This helper converts that value from its dash representation to its actual
 * value.
 * @param {string} value The value to be evaluated
 * @returns {string} The value after it's checked
 */
export function convertDashToEmptyString(value) {
	return value === '-' ? '' : value;
}

// Ported from frontend-js-web to avid adding it as a dependency
/**
 * Fetches a resource. A thin wrapper around ES6 Fetch API, with standardized
 * default configuration.
 * @param {!string|!Request} resource The URL to the resource, or a Resource
 * object.
 * @param {Object=} init An optional object containing custom configuration.
 * @return {Promise} A Promise that resolves to a Response object.
 */

function defaultFetch(resource, init = {}) {
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

/**
 * Custom groupby function
 * @param {Array} items The collection to be iterated over
 * @callback fn Callback to transform the keys
 * @returns {Object} The composed object
 */
export function groupBy(items, fn) {
	const result = {};

	// Suppress eslint false alarm for unused var
	/* eslint-disable no-unused-vars */

	/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
	for (const item of Object.values(items)) {
		const key = fn(item);

		if (!result[key]) {
			result[key] = [];
		}

		result[key].push(item);
	}
	/* eslint-enable no-unused-vars */

	return result;
}

/**
 * Generalized recursive grouping algorithm that groups the input based on the
 * callbacks provided.
 * @param {Array} items An array of objects to be grouped
 * @callback groupFns Callbacks to group the inputs
 * @returns {Array} The grouped result
 */
export function groupByAll(items, ...groupFns) {
	if (groupFns.length === 0) {
		return [items];
	}

	const [groupFn, ...restGroupFns] = groupFns;
	const grouped = groupBy(items, groupFn);
	const result = [];

	// Suppress eslint false alarm for unused var
	/* eslint-disable no-unused-vars */

	/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
	for (const group of Object.values(grouped)) {
		result.push(...groupByAll(group, ...restGroupFns));
	}
	/* eslint-enable no-unused-vars */

	return result;
}

/**
 * Returns a promise of the request data
 * @param {string} endpoint The endpoint to post to
 * @param {object} params The parameters object to post with
 * @param {string} encoding The data encoding for the request. Defaults to JSON.
 * @param {string} method The desired action to be performed for a given resource. Defaults to the GET method.
 * @returns {Promise} A Promise of the object that results from the Request
 */
export function request(endpoint, params, encoding = 'json', method = 'post') {
	let namespacedParams = {};

	if (encoding === 'json') {
		if (method.toLowerCase() === 'post') {
			Object.entries(params)
				.map(([key, value]) => [`${NAMESPACE}${key}`, value])
				.forEach(([key, value]) => {
					namespacedParams[key] = value;
				});
		}
		else {
			namespacedParams = Object.entries(params)
				.map(([key, value]) => `${NAMESPACE}${key}=${value}`)
				.join('&');
		}
	}

	let namespacedData = null;

	if (encoding === 'formData') {
		namespacedData = new FormData();

		Object.entries(params).forEach(([key, value]) =>
			namespacedData.append(`${NAMESPACE}${key}`, value)
		);
	}

	let init = {};
	let resource = new Request(endpoint);

	if (method.toLowerCase() === 'post') {
		init = {
			body: namespacedData || namespacedParams,
			method
		};
	}
	else {
		resource = new Request(`${endpoint}&${namespacedParams}`);
	}

	return defaultFetch(resource, init).then(response => {
		if (!response.ok) {
			throw new Error(`Request responded with ${response.statusText}`);
		}

		return response.json();
	});
}

/**
 * Submits a form when the user presses the Enter key
 * @param {object} event The event to check the key that was pressed.
 * @param {object} formRef Ref of the form to be submitted.
 */
export function submitOnEnter(event, formRef) {
	if (event.keyCode === 13) {
		formRef.current.submit();
	}
}

/**
 * Takes an input and evaluates whether the input contains a single or multiple
 * valid IPv4 or IPv6 addresses.
 * @param {string} input The value to be evaluated
 * @returns {boolean} Whether the input is valid or not
 */
export function validateAllIPAddresses(input) {
	if (input) {
		const chuncks = input.trim().split(/\s*,\s*|\s+/);

		return chuncks.every(
			chunck =>
				chunck.match(PATTERN_IP_ADDRESS_V4) ||
				chunck.match(PATTERN_IP_ADDRESS_V6)
		);
	}
	else {
		return false;
	}
}

/**
 * Takes an input and evaluates whether the input contains a single or multiple
 * valid IPv6 addresses in the standard, compact, or mixed formats.
 * @param {string} input The value to be evaluated
 * @returns {boolean} Whether the input is valid or not
 */
export function validateIPv6s(input) {
	if (input) {
		const chuncks = input.trim().split(/\s*,\s*|\s+/);

		return chuncks.every(chunck => chunck.match(PATTERN_IP_ADDRESS_V6));
	}
	else {
		return false;
	}
}

/**
 * Takes an input and evaluates whether the input contains a single or multiple
 * valid MAC addresses.
 * @param {string} input The value to be evaluated
 * @returns {boolean} Whether the input is valid or not
 */
export function validateMAC(input) {
	if (input) {
		const chuncks = input.trim().split(/\s*,\s*|\s+/);

		return chuncks.every(chunck => chunck.match(PATTERN_MAC_ADDRESS));
	}
	else {
		return false;
	}
}
