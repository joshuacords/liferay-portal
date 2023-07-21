/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import ReactDOM from 'react-dom';
import renderer from 'react-test-renderer';

import HeaderMenuBackItem from '../../../../src/main/resources/META-INF/resources/js/shared/components/header-controller/HeaderMenuBackItem.es';
import {MockRouter} from '../../../mock/MockRouter.es';

beforeAll(() => {
	const vbody = document.createElement('div');

	vbody.innerHTML = '<div id="workflow"></div>';
	document.body.appendChild(vbody);

	ReactDOM.createPortal = jest.fn(element => {
		return element;
	});
});

test('Should render component on container', () => {
	const container = document.getElementById('workflow');

	const component = renderer.create(
		<MockRouter>
			<HeaderMenuBackItem
				basePath="/"
				container={container}
				location={{
					pathname: '/slas',
					search: 'backPath=%2Fprocesses'
				}}
			/>
		</MockRouter>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
