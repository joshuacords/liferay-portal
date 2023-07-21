/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClaySelectWithOption} from '@clayui/form';
import getCN from 'classnames';
import propTypes from 'prop-types';
import React from 'react';

import {BOOLEAN_OPTIONS} from '../../utils/constants.es';

class BooleanInput extends React.Component {
	static propTypes = {
		className: propTypes.string,
		disabled: propTypes.bool,
		onChange: propTypes.func.isRequired,
		value: propTypes.string
	};

	_handleChange = event => {
		this.props.onChange({value: event.target.value});
	};

	render() {
		const {className, disabled, value} = this.props;

		const classes = getCN('criterion-input', 'form-control', className);

		return (
			<ClaySelectWithOption
				className={classes}
				data-testid="options-boolean"
				disabled={disabled}
				onChange={this._handleChange}
				options={BOOLEAN_OPTIONS}
				value={value}
			/>
		);
	}
}

export default BooleanInput;
