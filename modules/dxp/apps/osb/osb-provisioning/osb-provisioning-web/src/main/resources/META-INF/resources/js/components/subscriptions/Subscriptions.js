/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	DASH,
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
import DatePicker from '../DatePicker';
import IconButton from '../IconButton';
import BulkInputs from './BulkInputs';

function Subscriptions({
	accountName,
	instanceSizes = [],
	statusOptions = [
		PRODUCT_PURCHASE_STATUS_APPROVED,
		PRODUCT_PURCHASE_STATUS_CANCELLED
	],
	subscriptionsType,
	validateDateFormat
}) {
	const [subscriptions] = useSubscriptions();

	const [bulkGracePeriod, setBulkGracePeriod] = useState('');

	function getLicenseDateFormatValidator(keyPath, value) {
		validateDateFormat(keyPath, value);
	}

	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row className="subscriptions-table-heading">
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('product')}
					</ClayTable.Cell>
					<ClayTable.Cell
						className="field-required"
						expanded
						headingCell
					>
						{Liferay.Language.get('salesforce-opportunity-key')}
					</ClayTable.Cell>
					<ClayTable.Cell
						className={
							subscriptions.size > 1 ? 'table-cell-expand' : ''
						}
						headingCell
					>
						{Liferay.Language.get('purchased')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('perpetual-subscription')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('start-date')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('end-date')}
					</ClayTable.Cell>

					{subscriptionsType === EDIT_SUBSCRIPTIONS && (
						<ClayTable.Cell
							className="table-cell-expand-smallest"
							expanded
							headingCell
						>
							{Liferay.Language.get('status')}
						</ClayTable.Cell>
					)}

					<ClayTable.Cell
						className={
							subscriptions.size > 1 ? 'table-cell-expand' : ''
						}
						headingCell
					>
						{Liferay.Language.get('instance-size')}
					</ClayTable.Cell>

					{subscriptionsType === EDIT_SUBSCRIPTIONS && (
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('grace-period')}
						</ClayTable.Cell>
					)}

					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('account-name')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>
			<ClayTable.Body>
				{subscriptions.size > 1 && (
					<BulkInputs
						accountName={accountName}
						instanceSizes={instanceSizes}
						statusOptions={statusOptions}
						subscriptionsType={subscriptionsType}
						updateBulkGracePeriod={setBulkGracePeriod}
					/>
				)}

				{subscriptions.toList().map(subscription => (
					<Subscription
						accountName={accountName}
						bulkGracePeriodValue={bulkGracePeriod}
						dateFormatValidators={getLicenseDateFormatValidator}
						disableDelete={subscriptions.size === 1}
						initialGracePeriod={getIntervalInDays(
							subscription.originalEndDate,
							subscription.endDate
						)}
						instanceSizes={instanceSizes}
						key={
							subscriptionsType === EDIT_SUBSCRIPTIONS
								? `${subscription.key}_${subscription.index}`
								: `${subscription.productKey}_${subscription.index}`
						}
						statusOptions={statusOptions}
						subscription={subscription}
						subscriptionsType={subscriptionsType}
					/>
				))}
			</ClayTable.Body>
		</ClayTable>
	);
}

Subscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	statusOptions: PropTypes.arrayOf(PropTypes.string),
	subscriptionsType: PropTypes.oneOf([ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS])
		.isRequired,
	validateDateFormat: PropTypes.func.isRequired
};

function Subscription({
	accountName,
	bulkGracePeriodValue,
	dateFormatValidators,
	disableDelete,
	initialGracePeriod,
	instanceSizes,
	statusOptions,
	subscription,
	subscriptionsType
}) {
	const {
		index,
		originalEndDate,
		perpetual,
		productName,
		quantity,
		salesforceOpportunityKey,
		sizing,
		startDate,
		status
	} = subscription;
	const [gracePeriod, setGracePeriod] = useState(initialGracePeriod);
	const [invalidDateFormat, setInvalidDateFormat] = useState({
		endDate: false,
		originalEndDate: false,
		startDate: false
	});

	const [, {deleteSubscription, updateSubscription}] = useSubscriptions();

	const key =
		subscriptionsType === EDIT_SUBSCRIPTIONS
			? `${subscription.key}_${index}`
			: `${subscription.productKey}_${index}`;

	useEffect(() => {
		setDisabledAttribute(key, perpetual);
	});

	useEffect(() => {
		if (bulkGracePeriodValue) {
			setGracePeriod(bulkGracePeriodValue);
		}
	}, [bulkGracePeriodValue]);

	function handleDeleteSubscription() {
		deleteSubscription(key);
	}

	function handleGracePeriodChange(event) {
		const {value} = event.currentTarget;

		setGracePeriod(value);

		updateEndDate(
			validateCurrentGracePeriod(value)
				? generateNewDateByDay(originalEndDate, value)
				: ''
		);
	}

	function handleGracePeriodStartDateChange(value) {
		const validGracePeriodStartDateFormat = validateDateFieldFormat(value);

		const newEndDate = validateCurrentGracePeriod(gracePeriod)
			? generateNewDateByDay(convertInputToDate(value), gracePeriod)
			: '';
		const validEndDateFormat = validateDateFieldFormat(newEndDate);

		updateSubscription(key, subscription =>
			subscription
				.update('originalEndDate', originalEndDate => {
					if (validGracePeriodStartDateFormat) {
						return convertInputToDate(value);
					}

					return originalEndDate;
				})
				.update('endDate', endDate => {
					if (endDate && validEndDateFormat) {
						return newEndDate;
					}

					return endDate;
				})
		);

		dateFormatValidators([key, 'endDate'], validEndDateFormat);
		dateFormatValidators(
			[key, 'originalEndDate'],
			validGracePeriodStartDateFormat
		);

		setInvalidDateFormat({
			...invalidDateFormat,
			endDate: !validEndDateFormat,
			originalEndDate: !validGracePeriodStartDateFormat
		});
	}

	function handlePerpetualChange() {
		updateSubscription(key, subscription =>
			subscription.set('perpetual', !perpetual)
		);

		setDisabledAttribute(key, !perpetual);
	}

	function handleQuantityChange(event) {
		updateSubscription(key, subscription =>
			subscription.set('quantity', event.currentTarget.value)
		);
	}

	function handleSalesforceOpportunityKeyChange(event) {
		updateSubscription(key, subscription =>
			subscription.set(
				'salesforceOpportunityKey',
				event.currentTarget.value
			)
		);
	}

	function handleSizingChange(event) {
		updateSubscription(key, subscription =>
			subscription.set('sizing', event.currentTarget.value)
		);
	}

	function handleStartDateChange(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateSubscription(key, subscription =>
				subscription.set('startDate', convertInputToDate(value))
			);
		}

		dateFormatValidators([key, 'startDate'], validDateFormat);
		setInvalidDateFormat({
			...invalidDateFormat,
			startDate: !validDateFormat
		});
	}

	function handleStatusChange(event) {
		updateSubscription(key, subscription =>
			subscription.set('status', event.currentTarget.value)
		);
	}

	function updateEndDate(newEndDate) {
		const validDateFormat = validateDateFieldFormat(newEndDate);

		if (validDateFormat) {
			updateSubscription(key, subscription =>
				subscription.set('endDate', newEndDate)
			);
		}

		dateFormatValidators([key, 'endDate'], validDateFormat);
		setInvalidDateFormat({...invalidDateFormat, endDate: !validDateFormat});
	}

	function validateCurrentGracePeriod(currentGracePeriod) {
		return currentGracePeriod !== '';
	}

	return (
		<ClayTable.Row id={key}>
			<ClayTable.Cell className="semi-bold">{productName}</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="salesforceOpportunityKey">
					<input
						aria-label={Liferay.Language.get(
							'salesforce-opportunity-key'
						)}
						className="form-control form-control-sm"
						id="salesforceOpportunityKey"
						onChange={handleSalesforceOpportunityKeyChange}
						type="text"
						value={salesforceOpportunityKey}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="quantity">
					<input
						aria-label={Liferay.Language.get('purchased')}
						className="form-control form-control-sm"
						id="quantity"
						min={1}
						onChange={handleQuantityChange}
						type="number"
						value={quantity}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label
					className="custom-checkbox custom-control"
					htmlFor="perpetual"
				>
					<input
						aria-checked={perpetual}
						aria-label={Liferay.Language.get(
							'perpetual-subscription'
						)}
						checked={perpetual}
						className="custom-control-input"
						id="perpetual"
						onChange={handlePerpetualChange}
						role="checkbox"
						type="checkbox"
					/>
					<span className="custom-control-label"></span>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell
				className={
					subscription.validateStartDate() &&
					!invalidDateFormat.startDate
						? ''
						: 'has-error'
				}
			>
				<label htmlFor="startDate">
					<DatePicker
						defaultValue={startDate}
						id="startDate"
						inputName="startDate"
						updateFn={handleStartDateChange}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell
				className={
					subscription.validateGracePeriodStartDate() &&
					!invalidDateFormat.originalEndDate
						? ''
						: 'has-error'
				}
			>
				<label htmlFor="gracePeriodStartDate">
					<DatePicker
						defaultValue={originalEndDate}
						id="gracePeriodStartDate"
						inputName="gracePeriodStartDate"
						updateFn={handleGracePeriodStartDateChange}
					/>
				</label>
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell>
					<label htmlFor="status">
						<select
							aria-label={Liferay.Language.get('status')}
							className="form-control form-control-sm"
							disabled={statusOptions.length === 0}
							id="status"
							onChange={handleStatusChange}
							value={status}
						>
							{statusOptions.map(option => (
								<option key={option} value={option}>
									{option}
								</option>
							))}
						</select>
					</label>
				</ClayTable.Cell>
			)}

			<ClayTable.Cell>
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
							<option key={size} value={size === 0 ? '' : size}>
								{size === 0 ? DASH : size}
							</option>
						))}
					</select>
				</label>
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell
					className={
						subscription.validateEndDate() &&
						!invalidDateFormat.endDate
							? ''
							: 'has-error'
					}
				>
					<label htmlFor={`${key}endDate`}>
						<div className="input-group" id={`${key}endDate`}>
							<div className="input-group-item">
								<input
									aria-label={Liferay.Language.get(
										'grace-period'
									)}
									className="form-control form-control-sm input-group-inset input-group-inset-after"
									disabled={perpetual}
									min={0}
									onChange={handleGracePeriodChange}
									type="number"
									value={gracePeriod}
								/>
								<div className="input-group-inset-item input-group-inset-item-after">
									{Liferay.Language.get('days')}
								</div>
							</div>
						</div>
					</label>
				</ClayTable.Cell>
			)}

			<ClayTable.Cell>{accountName}</ClayTable.Cell>
			<ClayTable.Cell>
				<IconButton
					cssClass="btn-icon btn-sm"
					disabled={disableDelete}
					labelName={Liferay.Language.get('delete-subscription-icon')}
					onClick={handleDeleteSubscription}
					svgId="#delete-icon"
					title={Liferay.Language.get('delete')}
				/>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

export default Subscriptions;
