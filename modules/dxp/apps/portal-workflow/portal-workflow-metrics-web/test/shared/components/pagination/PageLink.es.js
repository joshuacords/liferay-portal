/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import PageLink from '../../../../src/main/resources/META-INF/resources/js/shared/components/pagination/PageLink.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';

test('Should render component as type default', () => {
	const component = renderer.create(
		<Router>
			<PageLink page={1} />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component as active', () => {
	const component = renderer.create(
		<Router>
			<PageLink disabled page={1} />
		</Router>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should change page', () => {
	const component = shallow(
		<Router>
			<PageLink page={2} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});

test('Should test change page when disabled', () => {
	const component = shallow(
		<Router>
			<PageLink disabled page={2} />
		</Router>
	);

	expect(component).toMatchSnapshot();
});
