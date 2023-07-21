/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export default class ReloadButton extends React.Component {
	reloadPage() {
		location.reload();
	}

	render() {
		return (
			<button
				className="btn btn-link btn-sm"
				onClick={this.reloadPage.bind(this)}
			>
				{Liferay.Language.get('reload-page')}
			</button>
		);
	}
}
