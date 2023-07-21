/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import CriteriaRow from '../../../../src/main/resources/META-INF/resources/js/components/criteria_builder/CriteriaRow.es';
import {PROPERTY_TYPES} from '../../../../src/main/resources/META-INF/resources/js/utils/constants.es';

const connectDnd = jest.fn(el => el);

describe('CriteriaRow', () => {
	afterEach(cleanup);

	it('renders', () => {
		const OriginalCriteriaRow = CriteriaRow.DecoratedComponent;

		const {asFragment} = render(
			<OriginalCriteriaRow
				connectDragPreview={connectDnd}
				connectDragSource={connectDnd}
				connectDropTarget={connectDnd}
				criterion={{
					operatorName: PROPERTY_TYPES.STRING,
					propertyName: 'test_prop',
					value: 'test_val'
				}}
				groupId="group_01"
				index={0}
				onAdd={jest.fn()}
				onChange={jest.fn()}
				onDelete={jest.fn()}
				onMove={jest.fn()}
				propertyKey="user"
				supportedProperties={[
					{
						label: 'Test Property',
						name: 'test_prop',
						type: PROPERTY_TYPES.STRING
					}
				]}
			/>
		);

		expect(asFragment()).toMatchSnapshot();
	});

	it('renders and inform there is an unknown property', () => {
		const OriginalCriteriaRow = CriteriaRow.DecoratedComponent;

		const {asFragment} = render(
			<OriginalCriteriaRow
				connectDragPreview={connectDnd}
				connectDragSource={connectDnd}
				connectDropTarget={connectDnd}
				criterion={{
					operatorName: PROPERTY_TYPES.STRING,
					propertyName: 'unknown_prop',
					value: 'test_val'
				}}
				groupId="group_01"
				index={0}
				onAdd={jest.fn()}
				onChange={jest.fn()}
				onDelete={jest.fn()}
				onMove={jest.fn()}
				propertyKey="user"
				supportedProperties={[
					{
						label: 'Test Property',
						name: 'test_prop',
						type: PROPERTY_TYPES.STRING
					}
				]}
			/>
		);

		expect(asFragment()).toMatchSnapshot();
	});
});
