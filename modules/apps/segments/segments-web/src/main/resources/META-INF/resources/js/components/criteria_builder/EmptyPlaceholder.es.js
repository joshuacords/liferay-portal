/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {sub} from '../../utils/utils.es';

export default function EmptyPlaceholder() {
	return (
		<div className="empty-contributors mb-0 p-5 rounded taglib-empty-result-message">
			<div className="taglib-empty-result-message-header" />
			<div className="sheet-text text-center">
				<h1 className="mb-3 mt-4">
					{sub(Liferay.Language.get('no-x-yet'), [
						Liferay.Language.get('conditions')
					])}
				</h1>
				<p>{Liferay.Language.get('empty-conditions-message')}</p>
			</div>
		</div>
	);
}
