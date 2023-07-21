/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import CriteriaSidebarItem from '../../../../src/main/resources/META-INF/resources/js/components/criteria_sidebar/CriteriaSidebarItem.es';

const connectDnd = jest.fn(el => el);

describe('CriteriaSidebarItem', () => {
	afterEach(cleanup);

	it('renders', () => {
		const OriginalCriteriaSidebarItem =
			CriteriaSidebarItem.DecoratedComponent;

		const {asFragment} = render(
			<OriginalCriteriaSidebarItem
				connectDragSource={connectDnd}
				propertyKey="user"
			/>
		);

		expect(asFragment()).toMatchSnapshot();
	});
});
