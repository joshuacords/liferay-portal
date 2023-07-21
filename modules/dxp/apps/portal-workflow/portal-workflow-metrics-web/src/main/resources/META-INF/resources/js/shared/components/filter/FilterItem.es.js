/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import getClassName from 'classnames';
import React from 'react';

export default class FilterItem extends React.Component {
	onChange(event) {
		const {multiple, onChange} = this.props;

		onChange(event);

		if (!multiple) {
			document.dispatchEvent(new Event('mousedown'));
		}
	}

	render() {
		const {
			active,
			description,
			dividerAfter,
			hideControl,
			itemKey,
			multiple,
			name,
			onClick
		} = this.props;

		const controlClassName = getClassName(
			'custom-control',
			multiple ? 'custom-checkbox' : 'custom-radio'
		);

		const dropDownClassName = getClassName(
			'dropdown-item',
			active && 'active',
			description && 'with-description',
			hideControl && 'control-hidden'
		);

		const inputProps = {
			type: 'checkbox'
		};

		if (!multiple) {
			inputProps.name = 'filter-item-radio-group';
			inputProps.type = 'radio';
		}

		return (
			<>
				<li className={dropDownClassName}>
					<label className={controlClassName}>
						<input
							{...inputProps}
							checked={!!active}
							className="custom-control-input"
							data-key={itemKey}
							data-testid="filterItemInput"
							onChange={this.onChange.bind(this)}
							onClick={onClick}
						/>

						<span className="custom-control-label" />

						<span className="dropdown-item-text">
							<span
								className="dropdown-item-name"
								data-testid="filterItemName"
							>
								{name}
							</span>

							{description && (
								<span className="dropdown-item-description">
									{description}
								</span>
							)}
						</span>
					</label>
				</li>

				{dividerAfter && <li className="dropdown-divider" />}
			</>
		);
	}
}
