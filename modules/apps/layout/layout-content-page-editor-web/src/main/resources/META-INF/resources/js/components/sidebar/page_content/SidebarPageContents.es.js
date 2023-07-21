/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import useSelector from '../../../store/hooks/useSelector.es';
import SidebarHeader from '../SidebarHeader.es';
import {NoPageContents} from './NoPageContents.es';
import {PageContents} from './PageContents.es';

const SidebarPageContents = () => {
	const pageContents = useSelector(state => state.pageContents);
	let view = <NoPageContents />;

	if (pageContents.length) {
		view = <PageContents pageContents={pageContents} />;
	}

	return (
		<>
			<SidebarHeader>{Liferay.Language.get('contents')}</SidebarHeader>

			{view}
		</>
	);
};

export {SidebarPageContents};
export default SidebarPageContents;
