/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTable from '@clayui/table';
import ClayTableCell from '@clayui/table/lib/Cell';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {
	DASH,
	RESTRICTED_EXPIRATION_DATE_TYPES
} from '../../utilities/constants';
import LicenseDates from '../LicenseDates';

function Purchase({
	detached = false,
	instanceSize = '',
	instanceSizes,
	licenseExpirationDate,
	licenseKeysAllowed = 0,
	licenseKeysGenerated = 0,
	licenseStartDate,
	productPurchaseKey = ''
}) {
	const [disableChoose, setDisableChoose] = useState(true);
	const [selectedExpirationDate, setSelectedExpirationDate] = useState(
		licenseExpirationDate
	);
	const [selectedStartDate, setSelectedStartDate] = useState(
		licenseStartDate
	);
	const [sizing, setSizing] = useState(instanceSize);

	const [validDates, setValidDates] = useState(
		!isNaN(new Date(licenseExpirationDate)) &&
			!isNaN(new Date(licenseStartDate))
	);

	const [{licenseEntry}, {updateLicense}] = useNewLicense();

	const restricted = !!RESTRICTED_EXPIRATION_DATE_TYPES.find(
		restrictedType => restrictedType === licenseEntry.licenseEntryType
	);

	useEffect(() => {
		setSelectedExpirationDate(licenseExpirationDate);
	}, [licenseExpirationDate]);

	useEffect(() => {
		setDisableChoose(!sizing || !validDates);
	}, [sizing, validDates]);

	function handleChoosePurchase() {
		updateLicense(license =>
			license
				.set('expirationDate', selectedExpirationDate)
				.set('startDate', selectedStartDate)

				.set('licenseKeysAllowed', licenseKeysAllowed)
				.set('licenseKeysGenerated', licenseKeysGenerated)
				.set('productPurchaseKey', productPurchaseKey)
				.set('sizing', sizing)

				.set('showSpecificDetails', true)
		);
	}

	function handleSelectedExpirationDateChange(val) {
		setSelectedExpirationDate(val);
	}

	function handleSelectedStartDateChange(val) {
		setSelectedStartDate(val);
	}

	function handleSizingChange(event) {
		setSizing(event.currentTarget.value);
	}

	function handleValidateDates(bool) {
		setValidDates(bool);
	}

	return (
		<ClayTable.Row id={productPurchaseKey}>
			<LicenseDates
				detached={detached}
				expirationDate={licenseExpirationDate}
				id={productPurchaseKey}
				restricted={restricted}
				startDate={licenseStartDate}
				updateExpirationDate={handleSelectedExpirationDateChange}
				updateStartDate={handleSelectedStartDateChange}
				updateValidation={handleValidateDates}
				validDates={validDates}
			/>

			<ClayTableCell>
				{instanceSizes ? (
					<label htmlFor="instanceSize">
						<select
							aria-label={Liferay.Language.get('instance-size')}
							className="form-control form-control-sm"
							disabled={!instanceSizes.length}
							id="instanceSize"
							onChange={handleSizingChange}
							value={sizing}
						>
							{instanceSizes.map(size => (
								<option
									key={size}
									value={size === 0 ? '' : size}
								>
									{size === 0 ? DASH : size}
								</option>
							))}
						</select>
					</label>
				) : sizing === 0 ? (
					DASH
				) : (
					sizing
				)}
			</ClayTableCell>
			<ClayTableCell>
				{licenseKeysGenerated}
				{' / '}
				{licenseKeysAllowed}
			</ClayTableCell>
			<ClayTableCell>
				<button
					className="btn btn-secondary btn-sm"
					disabled={disableChoose}
					onClick={handleChoosePurchase}
					role="button"
					type="button"
				>
					{Liferay.Language.get('choose')}
				</button>
			</ClayTableCell>
		</ClayTable.Row>
	);
}

Purchase.protoType = {
	detached: PropTypes.bool,
	instanceSize: PropTypes.number,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	licenseExpirationDate: PropTypes.oneOfType([
		PropTypes.instanceOf(Date),
		PropTypes.string
	]),
	licenseKeysAllowed: PropTypes.number,
	licenseKeysGenerated: PropTypes.number,
	licenseStartDate: PropTypes.oneOfType([
		PropTypes.instanceOf(Date),
		PropTypes.string
	]),
	productPurchaseKey: PropTypes.string
};

export default Purchase;
