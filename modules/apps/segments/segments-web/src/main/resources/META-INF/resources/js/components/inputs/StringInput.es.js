/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClaySelectWithOption} from '@clayui/form';
import propTypes from 'prop-types';
import React from 'react';

class StringInput extends React.Component {
	static propTypes = {
		disabled: propTypes.bool,
		onChange: propTypes.func.isRequired,
		options: propTypes.array,
		value: propTypes.oneOfType([propTypes.string, propTypes.number])
	};

	static defaultProps = {
		options: []
	};

	_handleChange = event => {
		this.props.onChange({value: event.target.value});
	};

	render() {
		const {disabled, options, value} = this.props;

		return options.length === 0 ? (
			<input
				className="criterion-input form-control"
				data-testid="simple-string"
				disabled={disabled}
				onChange={this._handleChange}
				type="text"
				value={value}
			/>
		) : (
			<ClaySelectWithOption
				className="criterion-input form-control"
				data-testid="options-string"
				disabled={disabled}
				onChange={this._handleChange}
				options={options.map(o => ({
					label: o.label,
					value: o.value
				}))}
				value={value}
			/>
		);
	}
}

export default StringInput;
