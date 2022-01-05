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

import {Map, Record} from 'immutable';
import React, {createContext, useContext, useState} from 'react';

import {PRODUCT_PURCHASE_STATUS_CANCELLED} from '../utilities/constants';
import {getDetachedLicenseDates} from '../utilities/license';

export const LicenseRecord = Record({
	accountName: '',
	expirationDate: '',
	indefinite: false,
	licenseKeyId: '',
	licenseKeysGenerated: '',
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
		const licenseDates = getDetachedLicenseDates();
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
		const expiration = Date.parse(new Date(this.expirationDate));
		const start = Date.parse(new Date(this.startDate));

		return start < expiration;
	}
}
