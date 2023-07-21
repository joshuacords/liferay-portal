/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayDatePicker from '@clayui/date-picker';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useState} from 'react';

import {convertInputToDate, getUTCAdjustedDate} from '../utilities/date';

const YEAR_OFFSET = 30;

function DatePicker({
	defaultValue = '',
	endYearOffset = YEAR_OFFSET,
	id,
	inputName,
	placeholder = 'YYYY-MM-DD',
	startYearOffset = YEAR_OFFSET,
	updateFn
}) {
	const initialValue = useCallback(convertInputToDate(defaultValue), [
		defaultValue
	]);

	const [value, setValue] = useState(initialValue);

	const currentYear = new Date().getFullYear();

	useEffect(() => {
		setValue(initialValue);
	}, [initialValue]);

	function handleOnValueChange(value) {
		const newVal = value instanceof Date ? setDateToUTC(value) : value;

		setValue(newVal);

		if (updateFn) {
			updateFn(newVal);
		}
	}

	// Date returned from date picker is in the user's timezone, however date from converting input string is in UTC. For the sake of uniformity, convert date picker value to UTC.

	function setDateToUTC(date) {
		const day = date.getDate();
		const month = date.getMonth();
		const year = date.getFullYear();

		return new Date(Date.UTC(year, month, day));
	}

	return (
		<ClayDatePicker
			id={id || inputName}
			inputName={inputName}
			onValueChange={handleOnValueChange}
			placeholder={placeholder}
			value={getUTCAdjustedDate(value)}
			years={{
				end: currentYear + endYearOffset,
				start: currentYear - startYearOffset
			}}
		/>
	);
}

DatePicker.propTypes = {
	defaultValue: PropTypes.oneOfType([PropTypes.string, PropTypes.object]),
	endYearOffset: PropTypes.number,
	id: PropTypes.string,
	inputName: PropTypes.string,
	placeholder: PropTypes.string,
	startYearOffset: PropTypes.number,
	updateFn: PropTypes.func
};

export default DatePicker;
