/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Icon from '../../shared/components/Icon.es';

export default class AlertMessage extends React.Component {
	render() {
		const {children, iconName, type = 'danger'} = this.props;
		let typeText = Liferay.Language.get('warning');

		if (type === 'danger') {
			typeText = Liferay.Language.get('error');
		}

		return (
			<div className="container-fluid-1280">
				<div
					className={`alert alert-dismissible alert-${type}`}
					role="alert"
				>
					<span className="alert-indicator">
						<Icon iconName={iconName} />
					</span>

					<strong className="lead">{typeText}</strong>

					{children}

					<button
						aria-label="Close"
						className="close"
						data-dismiss="alert"
						type="button"
					>
						<Icon iconName="times" />
					</button>
				</div>
			</div>
		);
	}
}
