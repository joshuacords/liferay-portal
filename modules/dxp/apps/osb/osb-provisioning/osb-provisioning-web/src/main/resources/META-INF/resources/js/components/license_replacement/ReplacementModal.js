/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {CURRENT_TIME} from '../../utilities/constants';
import {
	formatDate,
	generateNewDateByDay,
	validateDateFieldFormat
} from '../../utilities/date';
import DatePicker from '../DatePicker';

function ReplacementModal({
	closeFn,
	expirationDate = '',
	replaceFn,
	startDate = ''
}) {
	const defaultExpirationDate =
		expirationDate === ''
			? formatDate(generateNewDateByDay())
			: expirationDate;
	const defaultStartDate =
		startDate === '' ? formatDate(CURRENT_TIME) : startDate;

	const [currentExpirationDate, setCurrentExpirationDate] = useState(
		defaultExpirationDate
	);
	const [currentStartDate, setCurrentStartDate] = useState(defaultStartDate);
	const [disableReplace, setDisableReplace] = useState(true);

	const {observer, onClose} = useModal({
		onClose: closeFn
	});

	useEffect(() => {
		if (
			!isNaN(new Date(currentExpirationDate)) &&
			!isNaN(new Date(currentStartDate)) &&
			validateDateFieldFormat(currentExpirationDate) &&
			validateDateFieldFormat(currentStartDate) &&
			(expirationDate !== currentExpirationDate ||
				startDate !== currentStartDate) &&
			new Date(currentExpirationDate) > new Date(currentStartDate)
		) {
			setDisableReplace(false);
		}
		else {
			setDisableReplace(true);
		}
	}, [currentExpirationDate, currentStartDate, expirationDate, startDate]);

	function handleExpirationDateChange(val) {
		setCurrentExpirationDate(val);
	}

	function handleReplace() {
		replaceFn(currentStartDate, currentExpirationDate);
	}

	function handleStartDateChange(val) {
		setCurrentStartDate(val);
	}

	return (
		<ClayModal observer={observer} size="full-screen">
			<ClayModal.Header>
				{Liferay.Language.get('replace')}
			</ClayModal.Header>
			<ClayModal.Body>
				<div className="add-items-sheet sheet sheet-lg">
					<div
						className={`form-group form-inline input-text-wrapper ${
							isNaN(new Date(currentStartDate)) ? 'has-error' : ''
						}`}
					>
						<label className="control-label" htmlFor="startDate">
							{Liferay.Language.get('start-date')}
						</label>

						<DatePicker
							defaultValue={defaultStartDate}
							id="startDate"
							inputName="startDate"
							updateFn={handleStartDateChange}
						/>
					</div>

					<div
						className={`form-group form-inline input-text-wrapper ${
							isNaN(new Date(currentExpirationDate))
								? 'has-error'
								: ''
						}`}
					>
						<label
							className="control-label"
							htmlFor="expirationDate"
						>
							{Liferay.Language.get('expiration-date')}
						</label>

						<DatePicker
							defaultValue={defaultExpirationDate}
							id="expirationDate"
							inputName="expirationDate"
							updateFn={handleExpirationDateChange}
						/>
					</div>
				</div>
			</ClayModal.Body>
			<ClayModal.Footer
				last={
					<ClayButton.Group spaced>
						<ClayButton displayType="secondary" onClick={onClose}>
							{Liferay.Language.get('cancel')}
						</ClayButton>
						<ClayButton
							disabled={disableReplace}
							onClick={handleReplace}
						>
							{Liferay.Language.get('replace')}
						</ClayButton>
					</ClayButton.Group>
				}
			/>
		</ClayModal>
	);
}

ReplacementModal.propTypes = {
	closeFn: PropTypes.func.isRequired,
	expirationDate: PropTypes.string,
	replaceFn: PropTypes.func.isRequired,
	startDate: PropTypes.string
};

export default ReplacementModal;
