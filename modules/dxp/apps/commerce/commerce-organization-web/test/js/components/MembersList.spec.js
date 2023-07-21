/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import MembersList from 'components/MembersList';
import {shallow} from 'enzyme';
import React from 'react';

describe('MembersList', () => {
	it('renders the members list if there are members', () => {
		const props = {
				isLoading: false,
				members: [{}, {}, {}]
			},
			wrapper = shallow(<MembersList {...props} />);

		expect(wrapper.find('div').some('.pane-members-list')).toBe(true);
		expect(wrapper.find('ul').children().length).toEqual(3);
	});

	it('renders NoMembers component if there are no members', () => {
		const props = {
				isLoading: false,
				members: []
			},
			wrapper = shallow(<MembersList {...props} />);

		expect(wrapper.find('div').some('.pane-members-list')).toBe(true);
		expect(wrapper.children('Member').length).toEqual(0);
		expect(wrapper.children('NoMembers').length).toEqual(1);
	});
});
