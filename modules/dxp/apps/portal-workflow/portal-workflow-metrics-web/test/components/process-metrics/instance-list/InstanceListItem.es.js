/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import InstanceListItem from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/instance-list/InstanceListItem.es';
import {InstanceListContext} from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/instance-list/store/InstanceListStore.es';

const instance = {
	assetTitle: 'New Post',
	assetType: 'Blog',
	creatorUser: {
		name: 'User 1'
	},
	dateCreated: new Date('2019-01-01'),
	id: 1,
	taskNames: ['Review', 'Update']
};

describe('The instance list item should', () => {
	afterEach(cleanup);

	test('Be rendered with "User 1", "Jan 01, 2019, 12:00 AM", and "Review, Update" columns', () => {
		const {getByTestId} = render(
			<InstanceListContext.Provider value={{setInstanceId: jest.fn()}}>
				<InstanceListItem {...instance} />
			</InstanceListContext.Provider>
		);

		const creatorUserCell = getByTestId('creatorUserCell');
		const dateCreatedCell = getByTestId('dateCreatedCell');
		const taskNamesCell = getByTestId('taskNamesCell');

		expect(creatorUserCell.innerHTML).toBe('User 1');
		expect(dateCreatedCell.innerHTML).toBe('Jan 01, 2019, 12:00 AM');
		expect(taskNamesCell.innerHTML).toBe('Review, Update');
	});

	test('Be rendered with check icon when the slaStatus is "OnTime"', () => {
		const {getByTestId} = render(
			<InstanceListContext.Provider value={{setInstanceId: jest.fn()}}>
				<InstanceListItem {...instance} slaStatus="OnTime" />
			</InstanceListContext.Provider>
		);

		const instanceStatusIcon = getByTestId('icon');

		expect([...instanceStatusIcon.classList]).toContain(
			'lexicon-icon-check-circle'
		);
	});

	test('Be rendered with exclamation icon when the slaStatus is "Overdue"', () => {
		const {getByTestId} = render(
			<InstanceListContext.Provider value={{setInstanceId: jest.fn()}}>
				<InstanceListItem {...instance} slaStatus="Overdue" />
			</InstanceListContext.Provider>
		);

		const instanceStatusIcon = getByTestId('icon');

		expect([...instanceStatusIcon.classList]).toContain(
			'lexicon-icon-exclamation-circle'
		);
	});

	test('Be rendered with hr icon when the slaStatus is "Untracked"', () => {
		const {getByTestId} = render(
			<InstanceListContext.Provider value={{setInstanceId: jest.fn()}}>
				<InstanceListItem {...instance} slaStatus="Untracked" />
			</InstanceListContext.Provider>
		);

		const instanceStatusIcon = getByTestId('icon');

		expect([...instanceStatusIcon.classList]).toContain('lexicon-icon-hr');
	});

	test('Call setInstanceId with "1" as instance id param', () => {
		const contextMock = {setInstanceId: jest.fn()};

		const {getByTestId} = render(
			<InstanceListContext.Provider value={contextMock}>
				<InstanceListItem {...instance} />
			</InstanceListContext.Provider>
		);

		const instanceIdLink = getByTestId('instanceIdLink');

		fireEvent.click(instanceIdLink);

		expect(contextMock.setInstanceId).toBeCalledWith(1);
	});
});
