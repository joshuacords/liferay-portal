/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {fetchParams} from '../index';

export function listenToBulkActionStatus(
	id,
	batchTasksStatusApiUrl,
	timeout = 10000
) {
	let interval;

	return Promise.race([
		new Promise((resolve, reject) => {
			interval = setInterval(function getBulkActionStatus() {
				return fetch(`${batchTasksStatusApiUrl}/${id}`, fetchParams)
					.then(response => response.json())
					.then(jsonResponse => {
						if (jsonResponse.executeStatus === 'COMPLETED') {
							clearInterval(interval);
							resolve('success');
						}
						if (jsonResponse.executeStatus === 'FAILED') {
							clearInterval(interval);
							reject(jsonResponse.errorMessage);
						}
					});
			}, 1000);
		}),
		new Promise((_, reject) =>
			setTimeout(() => {
				clearInterval(interval);
				reject(Liferay.Language.get('request-timeout'));
			}, timeout)
		)
	]);
}
