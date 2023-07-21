/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Icon from '../../shared/components/Icon.es';
import {ChildLink} from '../../shared/components/router/routerWrapper.es';
import {formatDuration} from '../../shared/util/duration.es';
import moment from '../../shared/util/moment.es';
import SLAListCardContext from './SLAListCardContext.es';

class SLAListItem extends React.Component {
	showConfirmDialog() {
		const {id} = this.props;

		this.context.showConfirmDialog(id);
	}

	render() {
		const {
			dateModified,
			description,
			duration,
			id,
			name,
			processId,
			status
		} = this.props;

		const blocked = status === 2;
		const durationString = formatDuration(duration);

		const blockedStatusClass = blocked ? 'text-danger' : '';

		const statusText = blocked
			? Liferay.Language.get('blocked')
			: Liferay.Language.get('running');

		return (
			<tr>
				<td className="table-cell-expand">
					<div className="table-list-title">
						{blocked && (
							<Icon
								elementClasses="text-danger"
								iconName="exclamation-full"
							/>
						)}{' '}
						<ChildLink to={`/sla/edit/${processId}/${id}`}>
							{name}
						</ChildLink>
					</div>
				</td>

				<td>{description}</td>

				<td className={blockedStatusClass}>{statusText}</td>

				<td>{durationString}</td>

				<td>
					{moment
						.utc(dateModified)
						.format(Liferay.Language.get('mmm-dd'))}
				</td>

				<td>
					<div className="dropdown dropdown-action">
						<a
							aria-expanded="false"
							aria-haspopup="true"
							className="component-action dropdown-toggle"
							data-toggle="dropdown"
							href="#1"
							id="dropdownAction1"
							role="button"
						>
							<Icon iconName="ellipsis-v" />
						</a>

						<ul
							aria-labelledby=""
							className="dropdown-menu dropdown-menu-right"
						>
							<li>
								<ChildLink
									className="dropdown-item"
									to={`/sla/edit/${processId}/${id}`}
								>
									{Liferay.Language.get('edit')}
								</ChildLink>
							</li>

							<li>
								<button
									className="dropdown-item"
									onClick={this.showConfirmDialog.bind(this)}
								>
									{Liferay.Language.get('delete')}
								</button>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		);
	}
}

SLAListItem.contextType = SLAListCardContext;
export default SLAListItem;
