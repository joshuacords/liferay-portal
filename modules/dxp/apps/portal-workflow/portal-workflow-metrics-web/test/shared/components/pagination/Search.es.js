/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import Search from '../../../../src/main/resources/META-INF/resources/js/shared/components/pagination/Search.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';

test('Should render component', () => {
	const component = renderer.create(
		<Router>
			<Search disabled />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should change search value', () => {
	const component = mount(
		<Router>
			<Search />
		</Router>
	);

	component.find('input').simulate('keyPress', {
		target: {value: 'test'}
	});
	component.find('form').simulate('submit', {
		preventDefault: () => {}
	});
	expect(component).toMatchSnapshot();
});
