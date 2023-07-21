/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

/**
 * @class
 * @memberof open-processes-summary
 */
export default class SummaryCard extends React.Component {
	render() {
		const {description, total} = this.props;

		return (
			<div
				className="border col-2 summary-card"
				style={{marginLeft: '16px'}}
			>
				<span className="regular-text semi-bold text-secondary">
					{description}
				</span>

				<div className="">
					<span
						className="font-weight-normal"
						style={{fontSize: '2.5rem'}}
					>
						{total}
					</span>

					<span className="regular-text text-secondary">
						{Liferay.Language.get('items')}
					</span>
				</div>
			</div>
		);
	}
}
