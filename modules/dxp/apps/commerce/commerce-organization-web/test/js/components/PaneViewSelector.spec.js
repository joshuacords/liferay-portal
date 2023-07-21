/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PaneViewSelector from 'components/PaneViewSelector';
import {mount} from 'enzyme';
import React from 'react';

describe('PaneViewSelector', () => {
	it('renders correctly with list-by USERS selected by default', () => {
		const inputProps = {
				listBy: 'user',
				onViewSelected: () => {},
				totalAccounts: 4,
				totalUsers: 5
			},
			wrapper = mount(<PaneViewSelector {...inputProps} />);

		expect(wrapper.find('span').get(0).props).toMatchObject({
			children: `${inputProps.listBy} (${inputProps.totalUsers})`,
			className: 'selected-pane',
			role: 'button',
			tabIndex: '-1'
		});
	});

	it('lists members by account if the related tab is selected', () => {
		const inputProps = {
				listBy: 'account',
				onViewSelected: () => {},
				totalAccounts: 4,
				totalUsers: 5
			},
			wrapper = mount(<PaneViewSelector {...inputProps} />);

		expect(wrapper.find('span').get(1).props).toMatchObject({
			children: `account (${inputProps.totalAccounts})`,
			className: 'selected-pane',
			role: 'button',
			tabIndex: '-1'
		});
	});
});
