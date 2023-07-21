/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	CONJUNCTIONS,
	RELATIONAL_OPERATORS
} from '../../src/main/resources/META-INF/resources/js/utils/constants.es';

const {AND, OR} = CONJUNCTIONS;
const {EQ} = RELATIONAL_OPERATORS;

function generateItems(times) {
	const items = [];

	for (let i = 0; i < times; i++) {
		items.push({
			operatorName: EQ,
			propertyName: 'firstName',
			value: 'test'
		});
	}

	return items;
}

export function mockCriteria(numOfItems) {
	return {
		conjunctionName: AND,
		groupId: 'group_01',
		items: generateItems(numOfItems)
	};
}

export function mockCriteriaNested() {
	return {
		conjunctionName: AND,
		groupId: 'group_01',
		items: [
			{
				conjunctionName: OR,
				groupId: 'group_02',
				items: [
					{
						conjunctionName: AND,
						groupId: 'group_03',
						items: [
							{
								conjunctionName: OR,
								groupId: 'group_04',
								items: generateItems(2)
							},
							...generateItems(1)
						]
					},
					...generateItems(1)
				]
			},
			...generateItems(1)
		]
	};
}
