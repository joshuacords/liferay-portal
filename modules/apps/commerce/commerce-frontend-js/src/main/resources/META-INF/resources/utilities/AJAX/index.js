/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {fetchParams} from '../index';

const BASE_OPTIONS = {
	...fetchParams,
	method: 'GET'
};

function _fetch(url, options = {}) {
	return fetch(url, {...BASE_OPTIONS, ...options})
		.then(response => {
			if (!response.ok) {
				return response
					.json()
					.catch(parseError => Promise.reject(new Error(parseError)))
					.then(reason => Promise.reject(reason));
			}

			if (response.status === 204) {
				return Promise.resolve();
			}

			return response.json();
		})
		.catch(error => Promise.reject(error));
}

const AJAX = {
	DELETE(apiUrl, customOptions = {}) {
		const options = {
			method: 'DELETE',
			...customOptions
		};

		return _fetch(apiUrl, options);
	},

	GET(apiUrl, customOptions = {}) {
		return _fetch(apiUrl, customOptions);
	},

	PATCH(apiUrl, jsonProps = {}, customOptions = {}) {
		const options = {
			body: JSON.stringify(jsonProps),
			method: 'PATCH',
			...customOptions
		};

		return _fetch(apiUrl, options);
	},

	POST(apiUrl, json = {}, customOptions = {}) {
		const options = {
			body: JSON.stringify(json),
			method: 'POST',
			...customOptions
		};

		return _fetch(apiUrl, options);
	},

	PUT(apiUrl, json = {}, customOptions = {}) {
		const options = {
			body: JSON.stringify(json),
			method: 'PUT',
			...customOptions
		};

		return _fetch(apiUrl, options);
	}
};

export default AJAX;
