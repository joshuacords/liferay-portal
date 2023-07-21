/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import OrgChartContainer from 'components/OrgChartContainer';
import {shallow} from 'enzyme';
import React from 'react';
import * as Utils from 'utils/utils.es';

import fakeJSON from './fakeData/0.json';

jest.mock('utils/utils.es', () => {
	const payload = require('./fakeData/0.json');

	return {
		bindAll: jest.fn(() => {}),
		callApi: jest.fn(() => Promise.resolve(payload)),
		setupDataset: jest.fn(payload => payload),
		truncateTextNode: jest.fn()
	};
});

describe('OrgChartContainer', () => {
	describe('Instantiation', () => {
		let wrapper, component;

		beforeEach(() => {
			wrapper = shallow(<OrgChartContainer />);
			component = wrapper.instance();
		});

		afterEach(() => {
			jest.clearAllMocks();
		});

		it('calls the API and stores the correct initial payload in the state', () => {
			return component.$didMount.then(() => {
				expect(wrapper.state()).toMatchObject({
					rootData: fakeJSON,
					selectedId: 0
				});
			});
		});

		it('renders correctly without MembersPane', () => {
			return component.$didMount.then(() => {
				expect(wrapper.find('div').some('.organization-network')).toBe(
					true
				);
				expect(wrapper.find('div').some('.pane')).toBe(false);
			});
		});
	});

	describe('setSelection', () => {
		let wrapper, component;

		beforeEach(() => {
			wrapper = shallow(<OrgChartContainer />);
			component = wrapper.instance();
		});

		it('changes the state to the selected organization ID and its color identifier', () => {
			const someId = 42,
				colorIdentifier = 'hsl(0,100%,100%)';

			component.setSelection(someId, colorIdentifier);

			expect(wrapper.state()).toMatchObject({
				colorIdentifier: expect.any(String),
				selectedId: someId
			});
		});

		it('renders also the MembersPane if selectedId > 0', () => {
			const someId = 43;

			return component.$didMount.then(() => {
				wrapper.setState({selectedId: someId}, () => {
					expect(wrapper.debug().includes('MembersPane')).toBe(true);
					expect(wrapper.state()).toMatchObject({
						rootData: fakeJSON,
						selectedId: someId
					});
				});
			});
		});
	});

	describe('handleNodeClick', () => {
		let wrapper, component;

		beforeEach(() => {
			wrapper = shallow(<OrgChartContainer />);
			component = wrapper.instance();
		});

		it('calls the API with a specified organization ID if an OrgChart node is selected', () => {
			const someId = 45,
				someApiURL = 'http://someDomain.com';

			wrapper.setProps({apiURL: someApiURL});

			component.handleNodeClick(someId);

			expect(Utils.callApi).toHaveBeenCalledWith({
				baseURL: someApiURL,
				id: someId
			});
		});
	});
});
