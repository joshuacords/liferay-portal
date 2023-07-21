/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';
import {Link} from 'react-router-dom';

import PageSizeItem from '../../../../src/main/resources/META-INF/resources/js/shared/components/pagination/PageSizeItem.es';
import {MockRouter as Router} from '../../../mock/MockRouter.es';

test('Should test component click', () => {
	const onChangePageSize = () => pageSize => pageSize;

	const component = mount(
		<Router>
			<PageSizeItem onChangePageSize={onChangePageSize()} pageSize="5" />
		</Router>
	);

	component.find(Link).simulate('click');
});
