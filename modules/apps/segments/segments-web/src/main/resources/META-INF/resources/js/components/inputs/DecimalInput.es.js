/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClaySelectWithOption} from '@clayui/form';
import propTypes from 'prop-types';
import React from 'react';

class DecimalInput extends React.Component {
	static propTypes = {
		disabled: propTypes.bool,
		onChange: propTypes.func.isRequired,
		options: propTypes.array,
		value: propTypes.oneOfType([propTypes.string, propTypes.number])
	};

	static defaultProps = {
		options: []
	};

	_handleDecimalBlur = event => {
		const value = Number.parseFloat(event.target.value).toFixed(2);

		this.props.onChange({value});
	};

	_handleDecimalChange = event => {
		this.props.onChange({value: event.target.value});
	};

	render() {
		const {disabled, options, value} = this.props;

		return options.length === 0 ? (
			<input
				className="criterion-input form-control"
				data-testid="decimal-number"
				disabled={disabled}
				onBlur={this._handleDecimalBlur}
				onChange={this._handleDecimalChange}
				step="0.01"
				type="number"
				value={value}
			/>
		) : (
			<ClaySelectWithOption
				className="criterion-input form-control"
				data-testid="options-decimal"
				disabled={disabled}
				onChange={this._handleDecimalChange}
				options={options.map(o => ({
					disabled: o.disabled,
					label: o.label,
					value: o.value
				}))}
				value={value}
			/>
		);
	}
}

export default DecimalInput;
