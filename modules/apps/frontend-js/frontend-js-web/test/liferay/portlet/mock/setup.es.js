/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {portlet} from './portlet_data.es';

function fetchMock(data) {
	global.fetch = jest.fn().mockImplementation(() => {
		return Promise.resolve({
			text: jest
				.fn()
				.mockImplementation(() => Promise.resolve(JSON.stringify(data)))
		});
	});
}

global.fetchMock = fetchMock;
global.portlet = {...portlet};
