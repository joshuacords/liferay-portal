/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as actions from '../../../src/main/resources/META-INF/resources/js/actions/actions.es';
import {reducers} from '../../../src/main/resources/META-INF/resources/js/reducers/reducers.es';

describe('reducers', () => {
	it('combines all existing reducers', () => {
		Object.values(actions).forEach(action => {
			expect(reducers).toHaveProperty(action);
		});
	});
});
