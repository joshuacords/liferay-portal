/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import getCN from 'classnames';
import {PropTypes} from 'prop-types';
import React, {Component} from 'react';

class ClayToggle extends Component {
	static propTypes = {
		checked: PropTypes.bool,
		className: PropTypes.string,
		iconOff: PropTypes.string,
		iconOn: PropTypes.string,
		label: PropTypes.string,
		labelLeft: PropTypes.string,
		labelOff: PropTypes.string,
		labelOn: PropTypes.string,
		labelRight: PropTypes.string,
		name: PropTypes.string,
		onChange: PropTypes.func
	};

	static defaultProps = {
		checked: false,
		label: '',
		labelLeft: '',
		labelOff: '',
		labelOn: '',
		labelRight: ''
	};

	render() {
		const {
			checked,
			className,
			disabled,
			iconOff,
			iconOn,
			label,
			labelLeft,
			labelOff,
			labelOn,
			labelRight,
			name,
			onChange,
			...otherProps
		} = this.props;

		const classes = getCN('toggle-switch', className);

		return (
			<label className={classes} {...otherProps}>
				<input
					checked={checked}
					className="toggle-switch-check"
					disabled={disabled}
					name={name}
					onChange={onChange}
					type="checkbox"
				/>

				{label && <span className="toggle-switch-label">{label}</span>}

				{labelLeft && (
					<span className="toggle-switch-text toggle-switch-text-left">
						{labelLeft}
					</span>
				)}

				{labelRight && (
					<span className="toggle-switch-text toggle-switch-text-right">
						{labelRight}
					</span>
				)}

				<span aria-hidden="true" className="toggle-switch-bar">
					<span
						className="toggle-switch-handle"
						data-label-off={labelOff}
						data-label-on={labelOn}
					>
						{iconOff && (
							<span className="button-icon button-icon-off toggle-switch-icon">
								<ClayIcon symbol={iconOff} />
							</span>
						)}

						{iconOn && (
							<span className="button-icon button-icon-on toggle-switch-icon">
								<ClayIcon symbol={iconOn} />
							</span>
						)}
					</span>
				</span>
			</label>
		);
	}
}

export default ClayToggle;
