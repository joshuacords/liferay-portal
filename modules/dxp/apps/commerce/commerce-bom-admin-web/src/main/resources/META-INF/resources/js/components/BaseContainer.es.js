/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

function BaseContainer() {
	return (
		<div className="panel panel-secondary">
			<div className="panel-body">
				<h4>{Liferay.Language.get('loading')}</h4>
			</div>
		</div>
	);
}

export default BaseContainer;
