/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import axios from 'axios';

/**
 * @module fetch
 * @description Basic alias of fetch client to perform consults in portal rest API.
 * @example
 * import fetch from '@/shared/rest/fetch';
 * fetch.get('/process').then(res => console.log(res));
 */
axios.defaults.headers.common[
	'Accept-Language'
] = Liferay.ThemeDisplay.getBCP47LanguageId();

axios.defaults.params = {
	['p_auth']: Liferay.authToken
};

const restClient = axios.create({
	baseURL: '/o/portal-workflow-metrics/v1.0'
});

export default restClient;
