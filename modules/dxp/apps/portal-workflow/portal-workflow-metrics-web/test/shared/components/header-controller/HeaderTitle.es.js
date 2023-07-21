/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import ReactDOM from 'react-dom';
import renderer from 'react-test-renderer';

import HeaderTitle from '../../../../src/main/resources/META-INF/resources/js/shared/components/header-controller/HeaderTitle.es';

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
		<HeaderTitle container={container} title="Metrics" />
	);

	const tree = component.toJSON();

	expect(tree).toMatchSnapshot();
});

test('Should set document title', () => {
	document.title = 'Metrics';

	const container = document.getElementById('workflow');

	const component = shallow(
		<HeaderTitle container={container} title="Metrics" />
	);

	const instance = component.instance();

	instance.setDocumentTitle('Metrics', 'SLAs');

	expect(document.title).toEqual('SLAs');
});

test('Should set document title if title prop changed', () => {
	document.title = 'Metrics';

	const container = document.getElementById('workflow');

	const component = shallow(
		<HeaderTitle container={container} title="Metrics" />
	);

	component.setProps({title: 'SLAs'});

	expect(document.title).toEqual('SLAs');
});
