/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {NoCommentsMessageIcon} from './NoCommentsMessageIcon.es';

const NoCommentsMessage = () => (
	<div className="p-4 text-center">
		<NoCommentsMessageIcon />

		<h1 className="h4">
			{Liferay.Language.get('there-are-no-comments-yet')}
		</h1>

		<p className="small text-secondary">
			{Liferay.Language.get('select-a-fragment-to-add-a-comment')}
		</p>
	</div>
);

export {NoCommentsMessage};
export default NoCommentsMessage;
