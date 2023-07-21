/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import propTypes from 'prop-types';
import React from 'react';

import {PROPERTY_TYPES} from '../../utils/constants.es';
import {jsDatetoYYYYMMDD} from '../../utils/utils.es';

class DateInput extends React.Component {
	static propTypes = {
		disabled: propTypes.bool,
		onChange: propTypes.func.isRequired,
		value: propTypes.string
	};

	state = {};

	static getDerivedStateFromProps(props, state) {
		let returnVal = null;

		if (props.value != state.initialValue) {
			returnVal = {
				initialValue: props.value,
				value: props.value
			};
		}

		return returnVal;
	}

	_handleDateChange = event => {
		const value = event.target.value;

		this.setState({value});
	};

	_handleDateBlur = event => {
		const date = jsDatetoYYYYMMDD(event.target.value);

		if (date !== 'Invalid Date') {
			this.setState({value: date}, () => {
				this.props.onChange({
					type: PROPERTY_TYPES.DATE,
					value: date
				});
			});
		}
		else {
			this.setState({value: jsDatetoYYYYMMDD(new Date())}, () => {
				this.props.onChange({
					type: PROPERTY_TYPES.DATE,
					value: jsDatetoYYYYMMDD(new Date())
				});
			});
		}
	};

	render() {
		const {disabled} = this.props;
		const {value} = this.state;

		return (
			<div className="criterion-input date-input">
				<input
					className="form-control"
					data-testid="date-input"
					disabled={disabled}
					onBlur={this._handleDateBlur}
					onChange={this._handleDateChange}
					type="date"
					value={value}
				/>
			</div>
		);
	}
}

export default DateInput;
