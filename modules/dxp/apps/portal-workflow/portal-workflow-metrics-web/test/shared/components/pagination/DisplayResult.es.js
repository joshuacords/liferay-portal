/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import renderer from 'react-test-renderer';

import DisplayResult from '../../../../src/main/resources/META-INF/resources/js/shared/components/pagination/DisplayResult.es';

test('Should render component', () => {
	const component = renderer.create(
		<DisplayResult page={1} pageCount={10} pageSize={10} totalCount={12} />
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component to second page', () => {
	const component = renderer.create(
		<DisplayResult page={2} pageCount={2} pageSize={10} totalCount={12} />
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
