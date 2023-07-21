/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTable from '@clayui/table';
import {Map} from 'immutable';
import PropTypes from 'prop-types';
import React, {useCallback, useRef, useState} from 'react';

import {FieldData, useExtendLicenses} from '../../hooks/extendLicenses';
import {
	convertInputToDate,
	formatDate,
	validateDateFieldFormat
} from '../../utilities/date';
import * as BulkInput from '../bulk_inputs/BulkInput';
import ExtendButton from './ExtendButton';
import ExtensionDetails from './ExtensionDetails';

export default function DetailsGroup({extensionURL, licenses}) {
	const [, {batchFieldUpdateByIds}] = useExtendLicenses();
	const formRef = useRef();

	const [fieldData, setFieldData] = useState(
		Map(
			licenses.map(license => [
				license.licenseKeyId,
				new FieldData({...license, ...{hasTerm: !!license.terms}})
			])
		)
	);

	const ids = licenses.map(({licenseKeyId}) => licenseKeyId);
	const productName = licenses[0].productName;
	const singleLicense = licenses.length === 1;

	const fieldValueSet = useCallback(
		fieldName =>
			new Set(
				fieldData.toList().map(license => {
					const field = license[fieldName];

					return field instanceof Date ? field.toJSON() : field;
				})
			),
		[fieldData]
	);

	const getDisplayValue = useCallback(
		fieldName => {
			const set = fieldValueSet(fieldName);

			if (set.size === 1) {
				return set.values().next().value;
			}

			return '';
		},
		[fieldValueSet]
	);

	function deriveExtendDisabledState() {
		return fieldData
			.toList()
			.every(data => data.hasValidDates() && !data.hasMissingTerm());
	}

	function getFieldData() {
		return fieldData
			.toList()
			.toJS()
			.map(data => ({
				...data,
				...{expirationDate: formatDate(data.expirationDate)},
				...{startDate: formatDate(data.startDate)}
			}));
	}

	function handleBulkSaveExpirationDate(value) {
		const validation = validateDateFieldFormat(value);

		if (validation) {
			const date = convertInputToDate(value);

			// update submission data
			setFieldData(
				fieldData.map(data => data.set('expirationDate', date))
			);

			// update display data
			batchFieldUpdateByIds(ids, 'expirationDate', date);
		}
	}

	function handleBulkSaveStartDate(value) {
		const validation = validateDateFieldFormat(value);

		if (validation) {
			const date = convertInputToDate(value);

			// update submission data
			setFieldData(fieldData.map(data => data.set('startDate', date)));

			// update display data
			batchFieldUpdateByIds(ids, 'startDate', date);
		}
	}

	function handleFieldChange(keyPath, value) {
		setFieldData(fieldData.setIn(keyPath, value));
	}

	function handleOnSubmit() {
		if (formRef.current) {
			formRef.current.submit();
		}
	}

	function handleRemove(key) {
		setFieldData(fieldData.delete(key));
	}

	return (
		<>
			{!singleLicense && (
				<ClayTable.Body>
					<ClayTable.Row className="bulk-input">
						<ClayTable.Cell className="input-title semi-bold">
							{Liferay.Language.get('bulk-input')}
						</ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>

						<BulkInput.Date
							editHandler={handleBulkSaveStartDate}
							fieldName={`startDateBulkInput-${productName}`}
							value={getDisplayValue('startDate')}
						/>
						<BulkInput.Date
							editHandler={handleBulkSaveExpirationDate}
							fieldName={`expirationDateBulkInput-${productName}`}
							value={getDisplayValue('expirationDate')}
						/>

						<ClayTable.Cell></ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Body>
			)}

			<ExtensionDetails
				extensionURL={extensionURL}
				licenses={licenses}
				removalCallback={handleRemove}
				updater={handleFieldChange}
			/>

			{licenses.length !== 1 && (
				<ClayTable.Body>
					<ClayTable.Row>
						<ClayTable.Cell colSpan={6}></ClayTable.Cell>
						<ClayTable.Cell>
							<ExtendButton
								disabled={!deriveExtendDisabledState()}
								fields={{
									licenseKeys: JSON.stringify(getFieldData())
								}}
								formAction={extensionURL}
								ref={formRef}
								submitHandler={handleOnSubmit}
							/>
						</ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Body>
			)}
		</>
	);
}

DetailsGroup.propTypes = {
	extensionURL: PropTypes.string,
	licenses: PropTypes.array
};
