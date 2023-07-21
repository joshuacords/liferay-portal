/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {setLocalizedValue} from '../../../src/main/resources/META-INF/resources/js/util/i18n.es';

describe('Internationlization', () => {
	it('creates a localized property for a specific object string value', () => {
		const obj = {
			title: ''
		};
		const value = 'the title will be localized';

		setLocalizedValue(obj, 'en_US', 'title', value);

		expect(obj).toMatchObject({
			localizedTitle: {
				en_US: value
			},
			title: value
		});
	});

	it('replaces a localized value for a specific object string', () => {
		const obj = {
			localizedTitle: {
				en_US: ''
			},
			title: ''
		};
		const value = 'the title will be localized';

		setLocalizedValue(obj, 'en_US', 'title', value);

		expect(obj).toMatchObject({
			localizedTitle: {
				en_US: value
			},
			title: value
		});
	});
});
