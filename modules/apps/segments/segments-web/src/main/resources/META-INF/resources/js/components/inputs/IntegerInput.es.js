/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClaySelectWithOption} from '@clayui/form';
import propTypes from 'prop-types';
import React from 'react';

class IntegerInput extends React.Component {
	static propTypes = {
		disabled: propTypes.bool,
		onChange: propTypes.func.isRequired,
		options: propTypes.array,
		value: propTypes.oneOfType([propTypes.string, propTypes.number])
	};

	static defaultProps = {
		options: []
	};

	_handleIntegerChange = event => {
		const value = parseInt(event.target.value, 10);

		if (!isNaN(value)) {
			this.props.onChange({value: value.toString()});
		}
	};

	render() {
		const {disabled, options, value} = this.props;

		return options.length === 0 ? (
			<input
				className="criterion-input form-control"
				data-testid="integer-number"
				disabled={disabled}
				onChange={this._handleIntegerChange}
				type="number"
				value={value}
			/>
		) : (
			<ClaySelectWithOption
				className="criterion-input form-control"
				data-testid="options-integer"
				disabled={disabled}
				onChange={this._handleIntegerChange}
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

export default IntegerInput;
