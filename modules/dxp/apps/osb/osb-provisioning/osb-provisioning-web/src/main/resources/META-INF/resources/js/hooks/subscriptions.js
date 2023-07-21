/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Map, Record} from 'immutable';
import React, {useContext, useEffect, useState} from 'react';

import {PRODUCT_PURCHASE_STATUS_APPROVED} from '../utilities/constants';
import {generateNewDateByYear} from '../utilities/date';

// Subscriptions Context

export class Subscription extends Record({
	endDate: null,
	externalLinkKey: null,
	index: 0,
	key: null,
	originalEndDate: generateNewDateByYear(),
	perpetual: false,
	productKey: null,
	productName: '',
	quantity: 1,
	salesforceOpportunityKey: '',
	sizing: 1,
	startDate: new Date(),
	status: PRODUCT_PURCHASE_STATUS_APPROVED
}) {
	validateAllDates() {
		return (
			this.validateEndDate() &&
			this.validateGracePeriodStartDate() &&
			this.validateEndDate()
		);
	}

	validateEndDate() {
		if (this.perpetual) {
			return true;
		}

		if (this.endDate) {
			return (
				this.startDate < this.endDate &&
				this.originalEndDate <= this.endDate
			);
		}
		else if (this.endDate === '') {
			return false;
		}
		else {
			return true;
		}
	}

	validateGracePeriodStartDate() {
		if (this.perpetual) {
			return true;
		}

		if (!this.originalEndDate) {
			return false;
		}
		else if (this.endDate) {
			return (
				this.startDate < this.originalEndDate &&
				this.originalEndDate <= this.endDate
			);
		}
		else {
			return this.startDate < this.originalEndDate;
		}
	}

	validateStartDate() {
		if (this.perpetual) {
			return true;
		}

		if (!this.startDate) {
			return false;
		}
		else if (this.endDate) {
			return (
				this.startDate < this.originalEndDate &&
				this.startDate < this.endDate
			);
		}
		else {
			return this.startDate < this.originalEndDate;
		}
	}
}

const SubscriptionsContext = React.createContext();

function assignKey(subscription) {
	return subscription.key ? subscription.key : subscription.productKey;
}

export function SubscriptionsProvider({initialSubscriptions = [], children}) {
	const duplicateSubscriptions = {};

	const processedSubscriptions = initialSubscriptions.map(subscription => {
		const key = assignKey(subscription);

		if (duplicateSubscriptions[key] !== undefined) {
			duplicateSubscriptions[key] = duplicateSubscriptions[key] + 1;
		}
		else {
			duplicateSubscriptions[key] = 0;
		}

		const index = duplicateSubscriptions[key];

		return [
			`${key}_${index}`,
			new Subscription({
				...subscription,
				endDate: subscription.endDate
					? new Date(subscription.endDate)
					: null,
				index,
				originalEndDate: new Date(subscription.originalEndDate),
				startDate: new Date(subscription.startDate)
			})
		];
	});

	const [subscriptions, setSubscriptions] = useState(
		Map(processedSubscriptions)
	);

	return (
		<SubscriptionsContext.Provider
			value={[
				subscriptions,
				{
					addSubscription(subscription) {
						setSubscriptions(
							subscriptions.set(
								subscription.productKey,
								subscription
							)
						);
					},
					deleteSubscription(key) {
						setSubscriptions(subscriptions.delete(key));
					},
					updateAllValues(updater) {
						setSubscriptions(
							subscriptions.map(subscription =>
								updater(subscription)
							)
						);
					},
					updateSubscription(key, updater) {
						setSubscriptions(subscriptions.update(key, updater));
					}
				}
			]}
		>
			{children}
		</SubscriptionsContext.Provider>
	);
}

export function useSubscriptions() {
	return useContext(SubscriptionsContext);
}

// View Subscriptions Hooks

class DateFormatValidator extends Record({
	endDate: true,
	originalEndDate: true,
	startDate: true
}) {
	isValid() {
		return this.endDate && this.originalEndDate && this.startDate;
	}
}

export function useInitialDateFormatValidators(subscriptions) {
	const initial = subscriptions
		.keySeq()
		.map(key => [key, new DateFormatValidator()]);

	return Map(initial.toList());
}

export function useSetDisplayAlert(callback, subscriptions) {
	return useEffect(() => {
		function validateDateFields() {
			return subscriptions.every(subscription =>
				subscription.validateAllDates()
			);
		}

		if (validateDateFields()) {
			callback(false);
		}
		else {
			callback(true);
		}
	}, [callback, subscriptions]);
}
