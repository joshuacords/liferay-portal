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

import {ClayCheckbox} from '@clayui/form';
import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useRef, useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../utilities/constants';
import {
	convertInputToDate,
	generateNewDateByDay,
	getIntervalInDays,
	setDisabledAttribute,
	validateDateFieldFormat
} from '../../utilities/date';
import * as BulkInput from '../bulk_inputs/BulkInput';

function BulkInputs({
	accountName,
	dateFormatValidators,
	instanceSizes = [],
	statusOptions = [
		PRODUCT_PURCHASE_STATUS_APPROVED,
		PRODUCT_PURCHASE_STATUS_CANCELLED
	],
	subscriptionsType,
	updateBulkGracePeriod
}) {
	const [subscriptions, {updateAllValues}] = useSubscriptions();

	const gracePeriodRef = useRef();
	const quantityRef = useRef();
	const salesforceOpportunityKeyRef = useRef();
	const sizingRef = useRef();
	const statusRef = useRef();

	const fieldValueSet = useCallback(
		fieldName =>
			new Set(
				subscriptions.toList().map(subscription => {
					if (fieldName === 'gracePeriod') {
						return getIntervalInDays(
							subscription.originalEndDate,
							subscription.endDate
						);
					}

					const field = subscription[fieldName];

					return field instanceof Date ? field.toJSON() : field;
				})
			),
		[subscriptions]
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

	const identicalFieldValues = useCallback(
		fieldName => {
			const set = fieldValueSet(fieldName);

			return set.size === 1;
		},
		[fieldValueSet]
	);

	const [showField, setShowField] = useState({
		gracePeriod: identicalFieldValues('gracePeriod'),
		perpetual: identicalFieldValues('perpetual'),
		quantity: identicalFieldValues('quantity'),
		salesforceOpportunityKey: identicalFieldValues(
			'salesforceOpportunityKey'
		),
		sizing: identicalFieldValues('sizing'),
		status: identicalFieldValues('status')
	});

	const [gracePeriod, setGracePeriod] = useState(
		getDisplayValue('gracePeriod')
	);
	const [perpetual, setPerpetual] = useState(getDisplayValue('perpetual'));
	const [quantity, setQuantity] = useState(getDisplayValue('quantity'));
	const [salesforceOpportunityKey, setSalesforceOpportunityKey] = useState(
		getDisplayValue('salesforceOpportunityKey')
	);
	const [sizing, setSizing] = useState(getDisplayValue('sizing'));
	const [status, setStatus] = useState(getDisplayValue('status'));

	const [invalidDateFormat, setInvalidDateFormat] = useState({
		endDate: false,
		originalEndDate: false,
		startDate: false
	});

	useEffect(() => {
		setDisabledAttribute('bulkInput', perpetual);
	});

	useEffect(() => {
		setShowField({
			gracePeriod: identicalFieldValues('gracePeriod'),
			perpetual: identicalFieldValues('perpetual'),
			quantity: identicalFieldValues('quantity'),
			salesforceOpportunityKey: identicalFieldValues(
				'salesforceOpportunityKey'
			),
			sizing: identicalFieldValues('sizing'),
			status: identicalFieldValues('status')
		});
	}, [identicalFieldValues]);

	useEffect(() => {
		setGracePeriod(getDisplayValue('gracePeriod'));
		setPerpetual(getDisplayValue('perpetual'));
		setQuantity(getDisplayValue('quantity'));
		setSalesforceOpportunityKey(
			getDisplayValue('salesforceOpportunityKey')
		);
		setSizing(getDisplayValue('sizing'));
		setStatus(getDisplayValue('status'));
	}, [getDisplayValue]);

	useSetFocus(gracePeriodRef, showField.gracePeriod);
	useSetFocus(quantityRef, showField.quantity);
	useSetFocus(
		salesforceOpportunityKeyRef,
		showField.salesforceOpportunityKey
	);
	useSetFocus(sizingRef, showField.sizing);
	useSetFocus(statusRef, showField.status);

	function getDatePickerDisplayValue(fieldName) {
		if (identicalFieldValues('perpetual')) {
			return getDisplayValue(fieldName);
		}
		else {
			return '';
		}
	}

	function handleOnClickGracePeriod() {
		setShowField({...showField, gracePeriod: true});
	}

	function handleOnClickPerpetual() {
		setShowField({...showField, perpetual: true});
		setPerpetual(false);

		updateAllValues(subscription => subscription.set('perpetual', false));
	}

	function handleOnClickQuantity() {
		setShowField({...showField, quantity: true});
	}

	function handleOnClickSalesforceOpportunityKey() {
		setShowField({...showField, salesforceOpportunityKey: true});
	}

	function handleOnClickSizing() {
		setShowField({...showField, sizing: true});
	}

	function handleOnClickStatus() {
		setShowField({...showField, status: true});
	}

	function handleSaveEndDate(event) {
		const {value} = event.currentTarget;

		setGracePeriod(value);
		updateBulkGracePeriod(value);

		const validGracePeriod = validateCurrentGracePeriod(value);

		updateAllValues(subscription => {
			return subscription.update('endDate', endDate =>
				validGracePeriod
					? generateNewDateByDay(subscription.originalEndDate, value)
					: endDate
			);
		});

		dateFormatValidators(['bulk', 'endDate'], validGracePeriod);
		setInvalidDateFormat({
			...invalidDateFormat,
			endDate: !validGracePeriod
		});
	}

	function handleSaveGracePeriodStartDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			const newOriginalEndDate = convertInputToDate(value);

			updateAllValues(subscription =>
				subscription
					.set('originalEndDate', newOriginalEndDate)
					.update('endDate', endDate =>
						endDate && validateCurrentGracePeriod(gracePeriod)
							? generateNewDateByDay(
									newOriginalEndDate,
									gracePeriod
							  )
							: endDate
					)
			);
		}

		dateFormatValidators(['bulk', 'originalEndDate'], validDateFormat);

		setInvalidDateFormat({
			...invalidDateFormat,
			originalEndDate: !validDateFormat
		});
	}

	function handleSavePerpetual() {
		setDisabledAttribute('bulkInput', !perpetual);
		setPerpetual(!perpetual);

		updateAllValues(subscription =>
			subscription.set('perpetual', !perpetual)
		);
	}

	function handleSaveQuantity(event) {
		updateAllValues(subscription =>
			subscription.set('quantity', event.currentTarget.value)
		);
	}

	function handleSaveSalesforceOpportunityKey(event) {
		updateAllValues(subscription =>
			subscription.set(
				'salesforceOpportunityKey',
				event.currentTarget.value
			)
		);
	}

	function handleSaveSizing(event) {
		updateAllValues(subscription =>
			subscription.set('sizing', event.currentTarget.value)
		);
	}

	function handleSaveStartDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateAllValues(subscription =>
				subscription.set('startDate', convertInputToDate(value))
			);
		}

		dateFormatValidators(['bulk', 'startDate'], validDateFormat);
		setInvalidDateFormat({
			...invalidDateFormat,
			startDate: !validDateFormat
		});
	}

	function handleSaveStatus(event) {
		updateAllValues(subscription =>
			subscription.set('status', event.currentTarget.value)
		);
	}

	function validateCurrentGracePeriod(currentGracePeriod) {
		return currentGracePeriod !== '';
	}

	return (
		<ClayTable.Row className="bulk-input" id="bulkInput">
			<ClayTable.Cell className="input-title semi-bold">
				{Liferay.Language.get('bulk-input')}
			</ClayTable.Cell>
			<BulkInput.Text
				changeHandler={handleSaveSalesforceOpportunityKey}
				editHandler={handleOnClickSalesforceOpportunityKey}
				fieldName={Liferay.Language.get(
					'salesforce-opportunity-key-bulk-input'
				)}
				ref={salesforceOpportunityKeyRef}
				showField={showField.salesforceOpportunityKey}
				value={salesforceOpportunityKey}
			/>
			<BulkInput.Number
				changeHandler={handleSaveQuantity}
				editHandler={handleOnClickQuantity}
				fieldName={Liferay.Language.get('purchased-bulk-input')}
				min={1}
				ref={quantityRef}
				showField={showField.quantity}
				value={quantity}
			/>
			<ClayTable.Cell>
				{showField.perpetual && (
					<ClayCheckbox
						aria-checked={perpetual}
						aria-label={Liferay.Language.get(
							'perpetual-subscription-bulk-input'
						)}
						checked={perpetual}
						className="custom-control-input"
						id="perpetualBulkInput"
						onChange={handleSavePerpetual}
						role="checkbox"
					/>
				)}

				{!showField.perpetual && (
					<ClayCheckbox
						aria-label={Liferay.Language.get(
							'perpetual-subscription-bulk-input'
						)}
						className="custom-control-input"
						id="perpetualBulkInput"
						indeterminate
						onChange={handleOnClickPerpetual}
						role="checkbox"
					/>
				)}
			</ClayTable.Cell>
			<BulkInput.Date
				editHandler={handleSaveStartDate}
				fieldName={Liferay.Language.get('start-date-bulk-input')}
				isValid={!invalidDateFormat.startDate}
				value={getDatePickerDisplayValue('startDate')}
			/>
			<BulkInput.Date
				editHandler={handleSaveGracePeriodStartDate}
				fieldName={Liferay.Language.get('end-date-bulk-input')}
				isValid={!invalidDateFormat.originalEndDate}
				value={getDatePickerDisplayValue('originalEndDate')}
			/>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<BulkInput.Select
					changeHandler={handleSaveStatus}
					editHandler={handleOnClickStatus}
					fieldDisabled={!statusOptions.length}
					fieldName={Liferay.Language.get(
						'subscription-status-bulk-input'
					)}
					options={statusOptions}
					ref={statusRef}
					showField={showField.status}
					value={status}
				/>
			)}

			<BulkInput.Select
				changeHandler={handleSaveSizing}
				editHandler={handleOnClickSizing}
				fieldDisabled={!instanceSizes.length}
				fieldName={Liferay.Language.get('instance-size-bulk-input')}
				options={instanceSizes}
				ref={sizingRef}
				showField={showField.sizing}
				value={sizing}
			/>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<BulkInput.NumberWithLabel
					changeHandler={handleSaveEndDate}
					disableEdit={perpetual}
					editHandler={handleOnClickGracePeriod}
					fieldDisabled={perpetual}
					fieldName={Liferay.Language.get('grace-period-bulk-input')}
					isValid={!invalidDateFormat.endDate}
					labelName={Liferay.Language.get('days')}
					min={0}
					ref={gracePeriodRef}
					showField={showField.gracePeriod}
					value={gracePeriod}
				/>
			)}

			<ClayTable.Cell>{accountName}</ClayTable.Cell>
			<ClayTable.Cell>{''}</ClayTable.Cell>
		</ClayTable.Row>
	);
}

BulkInputs.protoTypes = {
	accountName: PropTypes.string.isRequired,
	dateFormatValidators: PropTypes.func,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	statusOptions: PropTypes.arrayOf(PropTypes.string),
	subscriptionsType: PropTypes.oneOf([ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS])
		.isRequired,
	updateBulkGracePeriod: PropTypes.func
};

function useSetFocus(ref, state) {
	return useEffect(() => {
		if (ref.current) {
			ref.current.focus();
		}
	}, [ref, state]);
}

export default BulkInputs;
