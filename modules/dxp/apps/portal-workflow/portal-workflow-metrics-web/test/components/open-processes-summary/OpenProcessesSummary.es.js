/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import OpenProcessesSummary from '../../../src/main/resources/META-INF/resources/js/components/open-processes-summary/OpenProcessesSummary.es';

test('Should render component', () => {
	const component = renderer.create(<OpenProcessesSummary />);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
