/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PaneHeader from 'components/PaneHeader';
import {shallow} from 'enzyme';
import React from 'react';

describe('PaneHeader', () => {
	it('renders the pane header with the PaneOrgInfo, PaneViewSelector and PaneSearchBar components', () => {
		const inputProps = {
			colorIdentifier: 'hsl(0,100%,100%)',
			listBy: 'user',
			onLookUp: expect.any(Function),
			onViewSelected: expect.any(Function),
			orgName: 'Org name',
			spritemap: expect.any(String),
			totalAccounts: 1,
			totalSubOrg: 1,
			totalUsers: 1
		};

		const wrapper = shallow(<PaneHeader {...inputProps} />);

		expect(wrapper.children('PaneOrgInfo').props()).toMatchObject({
			childrenNo: inputProps.totalSubOrg,
			colorIdentifier: inputProps.colorIdentifier,
			orgName: inputProps.orgName,
			showMenu: expect.any(Function)
		});
		expect(wrapper.children('PaneViewSelector').props()).toMatchObject({
			listBy: inputProps.listBy,
			onViewSelected: inputProps.onViewSelected,
			totalAccounts: inputProps.totalAccounts,
			totalUsers: inputProps.totalUsers
		});
		expect(wrapper.children('PaneSearchBar').props()).toMatchObject({
			onLookUp: inputProps.onLookUp,
			spritemap: inputProps.spritemap
		});
	});
});
