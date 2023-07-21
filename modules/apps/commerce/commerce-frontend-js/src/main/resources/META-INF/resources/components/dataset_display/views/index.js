/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Cards from './cards/Cards';
import EmailsList from './emails_list/EmailsList';
import List from './list/List';
import SelectableTable from './selectable_table/SelectableTable';
import Table from './table/Table';
import Timeline from './timeline/Timeline';

const views = [
	{
		component: Table,
		id: 'table'
	},
	{
		component: SelectableTable,
		id: 'selectableTable'
	},
	{
		component: Timeline,
		id: 'timeline'
	},
	{
		component: EmailsList,
		id: 'emailsList'
	},
	{
		component: List,
		id: 'list'
	},
	{
		component: Cards,
		id: 'cards'
	}
];

export function getViewById(requestedContentRendererId) {
	return new Promise(resolve => {
		views.forEach(view => {
			if (view.id === requestedContentRendererId) {
				resolve(view.component);
			}
		});
		throw new Error(
			`No content renderer found with the ID: "${requestedContentRendererId}"`
		);
	});
}
