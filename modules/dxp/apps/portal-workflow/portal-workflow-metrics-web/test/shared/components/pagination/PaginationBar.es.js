/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import PaginationBar from '../../../../src/main/resources/META-INF/resources/js/shared/components/pagination/PaginationBar.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';

jest.mock(
	'../../../../src/main/resources/META-INF/resources/js/components/AppContext.es'
);

test('Should render component', () => {
	const component = renderer.create(
		<Router>
			<PaginationBar
				page={1}
				pageCount={5}
				pageSize={5}
				totalCount={22}
			/>
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component with total count minor than last page', () => {
	const component = renderer.create(
		<Router>
			<PaginationBar page={1} pageCount={5} pageSize={5} totalCount={5} />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
