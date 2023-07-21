/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import ReactDOM from 'react-dom';
import renderer from 'react-test-renderer';

import PortalComponent from '../../../../src/main/resources/META-INF/resources/js/shared/components/header-controller/PortalComponent.es';

test('Should not render component without container', () => {
	const component = renderer.create(
		<PortalComponent>
			<span>{'Portal Component'}</span>
		</PortalComponent>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should render component on container', () => {
	const vbody = document.createElement('div');

	vbody.innerHTML = '<div id="workflow"></div>';
	document.body.appendChild(vbody);

	ReactDOM.createPortal = jest.fn(element => {
		return element;
	});

	const container = document.getElementById('workflow');

	const component = renderer.create(
		<PortalComponent container={container}>
			<span>{'Portal Component'}</span>
		</PortalComponent>
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});
