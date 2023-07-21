/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import PageSizeEntries from '../../../../src/main/resources/META-INF/resources/js/shared/components/pagination/PageSizeEntries.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';

test('Should change page size', () => {
	const component = shallow(
		<PageSizeEntries
			pageSizeEntries={[10, 20, 30, 40]}
			selectedPageSize={30}
		/>
	);

	expect(component).toMatchSnapshot();
});

test('Should render component', () => {
	const component = shallow(
		<PageSizeEntries
			pageSizeEntries={[10, 20, 30, 40]}
			selectedPageSize={10}
		/>
	);

	expect(component).toMatchSnapshot();
});

test('Should render with default deltas', () => {
	const component = mount(
		<Router>
			<PageSizeEntries selectedPageSize={30} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});
