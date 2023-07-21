/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {ChildLink} from '../../shared/components/router/routerWrapper.es';
import {AppContext} from '../AppContext.es';

/**
 * @class
 * @memberof process-list
 */
class ProcessListItem extends React.Component {
	render() {
		const {
			id,
			instanceCount = '-',
			onTimeInstanceCount = '-',
			overdueInstanceCount = '-',
			title
		} = this.props;

		return (
			<tr>
				<td className="lfr-title-column table-cell-expand table-cell-minw-200 table-title">
					<ChildLink to={`/metrics/${id}`}>
						<span>{title}</span>
					</ChildLink>
				</td>

				<td>{overdueInstanceCount}</td>

				<td>{onTimeInstanceCount}</td>

				<td>{instanceCount}</td>
			</tr>
		);
	}
}

ProcessListItem.contextType = AppContext;
export default ProcessListItem;
