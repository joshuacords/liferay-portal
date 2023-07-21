/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import Icon from '../../../shared/components/Icon.es';
import moment from '../../../shared/util/moment.es';
import {InstanceListContext} from './store/InstanceListStore.es';

const getStatusIcon = status => {
	if (status === 'OnTime') {
		return {
			bgColor: 'bg-success-light',
			iconColor: 'text-success',
			iconName: 'check-circle'
		};
	}

	if (status === 'Overdue') {
		return {
			bgColor: 'bg-danger-light',
			iconColor: 'text-danger',
			iconName: 'exclamation-circle'
		};
	}

	if (status === 'Untracked') {
		return {
			bgColor: 'bg-info-light',
			iconColor: 'text-info',
			iconName: 'hr'
		};
	}

	return null;
};

const InstanceListItem = ({
	assetTitle,
	assetType,
	creatorUser,
	dateCreated,
	id,
	slaStatus,
	taskNames = []
}) => {
	const {setInstanceId} = useContext(InstanceListContext);
	const statusIcon = getStatusIcon(slaStatus);

	const updateInstanceId = () => setInstanceId(id);

	return (
		<tr data-testid="instanceRow">
			<td>
				{statusIcon && (
					<span
						className={`mr-3 sticker sticker-sm ${statusIcon.bgColor}`}
					>
						<span className="inline-item">
							<Icon
								elementClasses={statusIcon.iconColor}
								iconName={statusIcon.iconName}
							/>
						</span>
					</span>
				)}
			</td>

			<td className="lfr-title-column table-title">
				<a
					data-target="#instanceDetailModal"
					data-testid="instanceIdLink"
					data-toggle="modal"
					href="javascript:;"
					onClick={updateInstanceId}
					tabIndex="-1"
				>
					<strong>{id}</strong>
				</a>
			</td>

			<td data-testid="assetInfoCell">{`${assetType}: ${assetTitle}`}</td>

			<td data-testid="taskNamesCell">
				{taskNames.length
					? taskNames.join(', ')
					: Liferay.Language.get('completed')}
			</td>

			<td data-testid="creatorUserCell">
				{creatorUser ? creatorUser.name : ''}
			</td>

			<td className="pr-4 text-right" data-testid="dateCreatedCell">
				{moment
					.utc(dateCreated)
					.format(Liferay.Language.get('mmm-dd-yyyy-lt'))}
			</td>
		</tr>
	);
};

export default InstanceListItem;
