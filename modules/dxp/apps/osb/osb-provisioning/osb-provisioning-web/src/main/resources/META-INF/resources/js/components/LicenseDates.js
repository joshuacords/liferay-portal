/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayTableCell from '@clayui/table/lib/Cell';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import DatePicker from '../components/DatePicker';
import {usePermissions} from '../hooks/permissions';
import {formatDate} from '../utilities/date';

const YEAR_IN_MS = 1000 * 60 * 60 * 24 * 365;

export default function LicenseDates({
	detached,
	expirationDate,
	id,
	restricted,
	startDate,
	updateExpirationDate,
	updateStartDate,
	updateValidation,
	validDates
}) {
	const {updateDatePermission} = usePermissions();
	const [selectedExpirationDate, setSelectedExpirationDate] = useState(
		expirationDate
	);
	const [selectedStartDate, setSelectedStartDate] = useState(startDate);

	function handleExpirationDateChange(val) {
		const expiration = Date.parse(new Date(val));
		const start = Date.parse(new Date(selectedStartDate));

		setSelectedExpirationDate(val);
		updateExpirationDate(val);
		updateValidation(start < expiration);
	}

	function handleStartDateChange(val) {
		const expiration = Date.parse(new Date(selectedExpirationDate));
		const start = Date.parse(new Date(val));

		setSelectedStartDate(val);
		updateStartDate(val);
		updateValidation(start < expiration);
	}

	function validateExpirationDateChange(val) {
		const expiration = Date.parse(new Date(val));
		const start = Date.parse(new Date(selectedStartDate));

		setSelectedExpirationDate(val);
		updateExpirationDate(val);
		updateValidation(
			expiration - start <= YEAR_IN_MS && start < expiration
		);
	}

	return (
		<>
			<ClayTableCell
				className={`input-group-sm ${!validDates ? 'has-error' : ''}`}
			>
				<label htmlFor={`startDate-${id}`}>
					<DatePicker
						defaultValue={startDate}
						id={`startDate-${id}`}
						inputName="startDate"
						updateFn={handleStartDateChange}
					/>
				</label>
			</ClayTableCell>

			{(updateDatePermission ||
				(!updateDatePermission && !restricted)) && (
				<ClayTableCell
					className={`input-group-sm ${
						!validDates ? 'has-error' : ''
					}`}
				>
					<label htmlFor={`expirationDate-${id}`}>
						<DatePicker
							defaultValue={expirationDate}
							id={`expirationDate-${id}`}
							inputName="expirationDate"
							updateFn={handleExpirationDateChange}
						/>
					</label>
				</ClayTableCell>
			)}

			{!updateDatePermission && restricted && (
				<>
					{!detached && (
						<ClayTableCell>
							{formatDate(expirationDate)}
						</ClayTableCell>
					)}

					{detached && (
						<ClayTableCell
							className={`input-group-sm ${
								!validDates ? 'has-error' : ''
							}`}
						>
							<label htmlFor={`expirationDate-${id}`}>
								<DatePicker
									defaultValue={expirationDate}
									id={`expirationDate-${id}`}
									inputName="expirationDate"
									updateFn={validateExpirationDateChange}
								/>
							</label>
						</ClayTableCell>
					)}
				</>
			)}
		</>
	);
}

LicenseDates.propTypes = {
	detached: PropTypes.bool.isRequired,
	expirationDate: PropTypes.oneOfType([
		PropTypes.instanceOf(Date),
		PropTypes.string
	]),
	id: PropTypes.string,
	restricted: PropTypes.bool.isRequired,
	startDate: PropTypes.oneOfType([
		PropTypes.instanceOf(Date),
		PropTypes.string
	]),
	updateExpirationDate: PropTypes.func.isRequired,
	updateStartDate: PropTypes.func.isRequired,
	updateValidation: PropTypes.func.isRequired,
	validDates: PropTypes.bool.isRequired
};
