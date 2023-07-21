/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import ReloadButton from '../../../../src/main/resources/META-INF/resources/js/shared/components/list/ReloadButton.es';

test('Should component reload page', () => {
	Object.defineProperty(window, 'location', {
		value: {reload: jest.fn()},
		writable: true
	});

	const component = shallow(<ReloadButton />);

	const instance = component.instance();

	instance.reloadPage();

	expect(window.location.reload).toHaveBeenCalled();
});
