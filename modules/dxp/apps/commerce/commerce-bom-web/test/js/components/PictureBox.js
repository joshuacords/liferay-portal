/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {mount, shallow} from 'enzyme';
import React from 'react';

import appActions from '../../../src/main/resources/META-INF/resources/js/actions/app.es';
import areaActions from '../../../src/main/resources/META-INF/resources/js/actions/area.es';
import PictureBox, {
	PartDetail
} from '../../../src/main/resources/META-INF/resources/js/components/areas/PictureBox.es';

const mockedContext = {
	actions: {...areaActions, ...appActions},
	state: {
		app: {
			spritemap: '/spritemap.test.svg'
		},
		area: {
			highlightedDetail: {
				number: 1
			},
			imageUrl: '/testImg.jpg',
			name: 'test name',
			products: [
				{
					id: 'PR01',
					name: 'Product 1',
					price: '$ 12.99',
					sku: 'sku01',
					thumbnailUrl: '/test-product',
					url: '/test-product'
				}
			],
			spotFormData: null,
			spots: [
				{
					id: 'SP01',
					number: 1,
					position: {
						x: 75,
						y: 75
					},
					productId: 'PR01'
				},
				{
					id: 'SP02',
					number: 1,
					position: {
						x: 25,
						y: 25
					},
					productId: 'PR01'
				}
			]
		}
	}
};

describe('PictureBox', () => {
	jest.spyOn(React, 'useContext').mockImplementation(() => mockedContext);

	it('renders without crashing', () => {
		mount(<PictureBox />);
	});

	it('must display the picture', () => {
		const pictureBox = shallow(<PictureBox />);
		expect(pictureBox.find('.picture-box__image').prop('src')).toEqual(
			mockedContext.state.area.imageUrl
		);
	});

	describe('Spots', () => {
		const pictureBox = mount(<PictureBox />);
		const partDetails = pictureBox.find(PartDetail);

		it('must display the parts details', () => {
			expect(partDetails.length).toEqual(2);
		});

		it('must receive the correct props', () => {
			const firstPartDetailProps = partDetails.first().props();

			expect(firstPartDetailProps.id).toEqual('SP01');
			expect(firstPartDetailProps.number).toEqual(1);
			expect(firstPartDetailProps.position).toEqual({x: 75, y: 75});
			expect(firstPartDetailProps.productId).toEqual('PR01');
		});
	});
});
