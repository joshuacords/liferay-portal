/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Map, Record} from 'immutable';
import React, {createContext, useContext, useState} from 'react';

import {PRODUCT_PURCHASE_STATUS_CANCELLED} from '../utilities/constants';
import {getDetachedLicenseDates} from '../utilities/license';

export const LicenseRecord = Record({
	accountName: '',
	expirationDate: '',
	indefinite: false,
	licenseKeyId: '',
	licenseKeysAllowed: 0,
	licenseKeysGenerated: 0,
	licenseType: '',
	productName: '',
	productPurchaseKey: '',
	readyToExtend: false,
	startDate: '',
	terms: null
});

const ExtendLicensesContext = createContext();

function createLicenseRecord(license) {
	if (!license.terms) {
		const licenseDates = getDetachedLicenseDates(license.licenseType);
		const {
			licenseExpirationDate: expirationDate,
			licenseStartDate: startDate
		} = licenseDates;

		return new LicenseRecord({
			...license,
			expirationDate,
			startDate
		});
	}

	const approvedTerms = license.terms.filter(
		term => term.status !== PRODUCT_PURCHASE_STATUS_CANCELLED
	);

	const newRecord = {
		...license,
		expirationDate: new Date(license.expirationDate),
		startDate: new Date(license.startDate),
		terms: approvedTerms
	};

	if (approvedTerms.length === 1) {
		const term = approvedTerms[0];

		return new LicenseRecord({
			...newRecord,
			productPurchaseKey: term.productPurchaseKey
		});
	}

	return new LicenseRecord(newRecord);
}

export function ExtendLicensesProvider({initialLicenses = [], children}) {
	const processedLicenses = initialLicenses.map(license => {
		return [license.licenseKeyId, createLicenseRecord(license)];
	});

	const [licenses, setLicenses] = useState(Map(processedLicenses));

	return (
		<ExtendLicensesContext.Provider
			value={[
				licenses,
				{
					batchFieldUpdateByIds(ids, fieldName, value) {
						setLicenses(
							licenses.map(license => {
								const id = ids.find(
									id => id === license.licenseKeyId
								);

								return id
									? license.set(fieldName, value)
									: license;
							})
						);
					},
					removeLicense(key) {
						setLicenses(licenses.delete(key));
					},
					updateLicense(key, updater) {
						setLicenses(licenses.update(key, updater));
					}
				}
			]}
		>
			{children}
		</ExtendLicensesContext.Provider>
	);
}

export function useExtendLicenses() {
	return useContext(ExtendLicensesContext);
}

// Bulk Extension Field Data

export class FieldData extends Record({
	expirationDate: null,
	hasTerm: false,
	licenseKeyId: '',
	productPurchaseKey: '',
	startDate: null
}) {
	hasMissingTerm() {
		if (this.hasTerm) {
			return this.productPurchaseKey ? false : true;
		}

		return false;
	}

	hasValidDates() {
		const expiration = Date.parse(this.expirationDate);
		const start = Date.parse(this.startDate);

		return start < expiration;
	}
}
