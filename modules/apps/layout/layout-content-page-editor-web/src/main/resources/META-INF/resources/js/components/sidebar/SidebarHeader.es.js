/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classNames from 'classnames';
import React from 'react';

const SidebarHeader = props => (
	<h1
		{...props}
		className={classNames({
			'align-items-center': true,
			'd-flex': true,
			'fragments-editor-sidebar-section__title': true,
			[props.className || '']: true
		})}
	/>
);

export {SidebarHeader};
export default SidebarHeader;
