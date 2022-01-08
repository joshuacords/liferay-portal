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

import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {useExtendLicenses} from '../../hooks/extendLicenses';
import {
	DASH,
	RESTRICTED_EXPIRATION_DATE_TYPES
} from '../../utilities/constants';
import {
	convertInputToDate,
	formatDate,
	validateDateFieldFormat
} from '../../utilities/date';
import IconButton from '../IconButton';
import LicenseDates from '../LicenseDates';
import ExtendButton from './ExtendButton';
import Terms from './Terms';

function Detail({
	disableDelete,
	disableIndividualExtend,
	extensionURL = '',
	license,
	removalCallback,
	updater
}) {
	const [, {removeLicense, updateLicense}] = useExtendLicenses();
	const formRef = useRef();

	const {
		accountName,
		expirationDate,
		licenseKeyId,
		licenseKeysGenerated,
		licenseType,
		productName,
		productPurchaseKey,
		readyToExtend,
		startDate,
		terms
	} = license;

	const [disableExtend, setDisableExtend] = useState(false);
	const [selectedExpirationDate, setSelectedExpirationDate] = useState(
		expirationDate
	);
	const [selectedStartDate, setSelectedStartDate] = useState(startDate);
	const [validDates, setValidDates] = useState(
		!isNaN(expirationDate) && !isNaN(startDate)
	);

	const missingTermSelection = terms && !productPurchaseKey;
	const restricted = !!RESTRICTED_EXPIRATION_DATE_TYPES.find(
		restrictedType => restrictedType === licenseType
	);

	useEffect(() => {
		setValidDates(
			!isNaN(expirationDate) &&
				!isNaN(startDate) &&
				startDate < expirationDate
		);
	}, [expirationDate, startDate]);

	useEffect(() => {
		if (readyToExtend && formRef.current) {
			formRef.current.submit();
		}
	}, [readyToExtend]);

	useEffect(() => {
		setDisableExtend(!validDates || missingTermSelection);
	}, [missingTermSelection, validDates]);

	function getLicenseKeysGenerated(productPurchaseKey) {
		const selectedTerm = terms.find(
			term => term.productPurchaseKey === productPurchaseKey
		);

		if (selectedTerm) {
			return selectedTerm.licenseKeysGenerated;
		}

		return DASH;
	}

	function handleExpirationDateChange(val) {
		const validDateFormat = validateDateFieldFormat(val);

		if (validDateFormat) {
			const newDate = convertInputToDate(val);

			setSelectedExpirationDate(newDate);

			if (updater) {
				updater([licenseKeyId, 'expirationDate'], newDate);
			}
		}
	}

	function handleOnSubmit() {
		updateLicense(licenseKeyId, license =>
			license
				.set('expirationDate', selectedExpirationDate)
				.set('startDate', selectedStartDate)

				.set('readyToExtend', true)
		);
	}

	function handleRemove() {
		removeLicense(licenseKeyId);

		if (removalCallback) {
			removalCallback(licenseKeyId);
		}
	}

	function handleStartDateChange(val) {
		const validDateFormat = validateDateFieldFormat(val);

		if (validDateFormat) {
			const newDate = convertInputToDate(val);

			setSelectedStartDate(newDate);

			if (updater) {
				updater([licenseKeyId, 'startDate'], newDate);
			}
		}
	}

	function handleTermsChange(val) {
		updateLicense(licenseKeyId, license =>
			license
				.set('licenseKeysGenerated', getLicenseKeysGenerated(val))
				.set('productPurchaseKey', val)
		);

		if (updater) {
			updater([licenseKeyId, 'productPurchaseKey'], val);
		}
	}

	function handleValidDates(bool) {
		setValidDates(bool);
	}

	return (
		<ClayTable.Body id={licenseKeyId}>
			<ClayTable.Row>
				<ClayTable.Cell>{accountName}</ClayTable.Cell>
				<ClayTable.Cell>{productName}</ClayTable.Cell>
				<ClayTable.Cell className="input-group-sm">
					<Terms
						terms={terms}
						termSelected={productPurchaseKey}
						updateTerms={handleTermsChange}
					/>
				</ClayTable.Cell>
				<LicenseDates
					detached={!!terms}
					expirationDate={expirationDate}
					id={licenseKeyId}
					restricted={restricted}
					startDate={startDate}
					updateExpirationDate={handleExpirationDateChange}
					updateStartDate={handleStartDateChange}
					updateValidation={handleValidDates}
					validDates={validDates}
				/>
				<ClayTable.Cell>{licenseKeysGenerated}</ClayTable.Cell>
				<ClayTable.Cell>
					{!disableIndividualExtend && (
						<ExtendButton
							disabled={disableExtend}
							fields={{
								expirationDate: formatDate(expirationDate),
								licenseKeyId,
								productPurchaseKey,
								startDate: formatDate(startDate)
							}}
							formAction={extensionURL}
							ref={formRef}
							submitHandler={handleOnSubmit}
						/>
					)}
				</ClayTable.Cell>
				<ClayTable.Cell>
					<IconButton
						cssClass="btn-icon btn-sm"
						disabled={disableDelete}
						labelName={Liferay.Language.get('delete-license-icon')}
						onClick={handleRemove}
						svgId="#delete-icon"
						title={Liferay.Language.get('delete')}
					/>
				</ClayTable.Cell>
			</ClayTable.Row>
		</ClayTable.Body>
	);
}

Detail.propTypes = {
	disableDelete: PropTypes.bool,
	extensionURL: PropTypes.string,
	license: PropTypes.object,
	updater: PropTypes.func
};

function ExtensionDetails({extensionURL, licenses, removalCallback, updater}) {
	const [extendLicenses] = useExtendLicenses();

	const singleLicense = licenses.length === 1;

	return licenses.map(license => (
		<Detail
			disableDelete={extendLicenses.size === 1}
			disableIndividualExtend={!singleLicense || license.indefinite}
			extensionURL={extensionURL}
			key={license.licenseKeyId}
			license={license}
			removalCallback={removalCallback}
			updater={updater}
		/>
	));
}

ExtensionDetails.propTypes = {
	extensionURL: PropTypes.string,
	licenses: PropTypes.array,
	removalCallback: PropTypes.func,
	updater: PropTypes.func
};

export default ExtensionDetails;
