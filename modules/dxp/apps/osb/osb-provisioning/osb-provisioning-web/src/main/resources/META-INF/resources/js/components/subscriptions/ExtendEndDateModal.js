/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import DatePicker from '../DatePicker';

export default function ExtendEndDateModal({
	closeFn,
	extendFn,
	latestActiveSubscriptionEndDate,
	newEndDate
}) {
	const [disableExtend, setDisableExtend] = useState(false);
	const [currentEndDate, setCurrentEndDate] = useState(newEndDate);

	const {observer, onClose} = useModal({
		onClose: closeFn
	});

	useEffect(() => {
		if (newEndDate !== currentEndDate) {
			if (
				!isNaN(currentEndDate) &&
				currentEndDate >= new Date(latestActiveSubscriptionEndDate)
			) {
				setDisableExtend(false);
			}
			else {
				setDisableExtend(true);
			}
		}
	}, [currentEndDate, latestActiveSubscriptionEndDate, newEndDate]);

	return (
		<ClayModal observer={observer} size="full-screen">
			<ClayModal.Header>
				{Liferay.Language.get('extend-all-active-subscriptions')}
			</ClayModal.Header>
			<ClayModal.Body>
				<div className="add-items-sheet sheet sheet-lg">
					<div
						className={`form-group form-inline input-text-wrapper ${
							isNaN(currentEndDate) ? 'has-error' : ''
						}`}
					>
						<label
							className="control-label"
							htmlFor={`${NAMESPACE}startDate`}
						>
							{Liferay.Language.get('grace-period-end-date')}
						</label>

						<DatePicker
							defaultValue={newEndDate}
							id={`${NAMESPACE}endDate`}
							inputName="endDate"
							updateFn={val => setCurrentEndDate(new Date(val))}
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
							disabled={disableExtend}
							onClick={() => extendFn(currentEndDate)}
						>
							{Liferay.Language.get('extend')}
						</ClayButton>
					</ClayButton.Group>
				}
			/>
		</ClayModal>
	);
}

ExtendEndDateModal.propTypes = {
	closeFn: PropTypes.func.isRequired,
	extendFn: PropTypes.func.isRequired,
	latestActiveSubscriptionEndDate: PropTypes.string.isRequired,
	newEndDate: PropTypes.object.isRequired
};
