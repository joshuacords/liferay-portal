/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {mount} from 'enzyme';
import React from 'react';

import EditNumberForm from '../../../src/main/resources/META-INF/resources/js/components/EditNumberForm.es';

const spotFormDataEdit = {
	changed: false,
	id: 'SP01',
	number: 1,
	originalData: {
		changed: false,
		id: 'SP01',
		number: 1,
		originalData: {},
		position: {
			x: 75,
			y: 75
		},
		productId: 'PR01',
		query: 'Product 1'
	},
	position: {
		x: 75,
		y: 75
	},
	productId: 'PR01',
	query: 'Product 1',
	state: 'edit'
};

const spotFormDataCreate = {
	position: {
		x: 50,
		y: 50
	},
	state: 'create'
};

const mockedContext = {
	state: {
		app: {
			spritemap: '/spritemap.test.svg'
		},
		area: {
			availableProducts: [],
			highlightedDetail: {
				number: 1
			},
			mappedProducts: [
				{
					id: 'PR01',
					name: 'Product 1',
					price: '$ 12.99',
					sku: 'sku01'
				}
			],
			spotFormData: spotFormDataEdit,
			spots: [
				{
					id: 'SP01',
					number: 1,
					position: {
						x: 75,
						y: 75
					},
					productId: 'PR01'
				}
			]
		}
	}
};

describe('Edit number form', () => {
	jest.spyOn(React, 'useContext').mockImplementation(() => mockedContext);

	it('renders without crashing', () => {
		mount(<EditNumberForm />);
	});

	it('must be empty if spotFormData has no values', () => {
		jest.spyOn(React, 'useContext').mockImplementation(() => {
			const context = {
				...mockedContext
			};
			context.state.area.spotFormData = null;

			return context;
		});

		mount(<EditNumberForm />);
	});

	it('must not display a delete button if state !== "edit"', () => {
		jest.spyOn(React, 'useContext').mockImplementation(() => {
			const context = {
				...mockedContext
			};
			context.state.area.spotFormData = spotFormDataCreate;

			return context;
		});

		const form = mount(<EditNumberForm />);
		expect(form.find('.edit-number-form__delete-btn').length).toBe(0);
	});

	it('must display a delete button if state === "edit"', () => {
		jest.spyOn(React, 'useContext').mockImplementation(() => {
			const context = {
				...mockedContext
			};
			context.state.area.spotFormData = spotFormDataEdit;

			return context;
		});

		const form = mount(<EditNumberForm />);
		expect(form.find('.edit-number-form__delete-btn').length).toBe(1);
	});

	it('must be correctly positioned', () => {
		const form = mount(<EditNumberForm />);
		const formStyle = form.find('.edit-number-form-wrapper').getDOMNode()
			.style;
		expect(formStyle.left).toBe('75%');
		expect(formStyle.bottom).toBe('75%');
	});

	describe('Spot element', () => {
		const form = mount(<EditNumberForm />);
		const spot = form.find('.spot-number--placeholder');

		it('must display a spot element', () => {
			expect(spot.length).toBe(1);
		});

		it('must display the associated number', () => {
			expect(spot.text()).toBe('1');
		});
	});
});
