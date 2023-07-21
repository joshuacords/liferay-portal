/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Map, Record} from 'immutable';
import React, {createContext, useContext, useState} from 'react';

export const LicenseRecord = Record({
	active: false,
	description: '',
	expirationDate: '',
	hostName: '',
	ipAddresses: '',
	licenseEntryDisplayName: '',
	licenseEntryName: '',
	licenseEntryType: '',
	licenseKeyId: '',
	licenseVersion: '',
	macAddresses: '',
	name: '',
	productId: '',
	productName: '',
	productVersion: '',
	sizing: 1,
	startDate: ''
});

const LicensesContext = createContext();

export function LicensesProvider({initialLicenses = [], children}) {
	const processedLicenses = initialLicenses.map(license => [
		license.licenseKeyId,
		LicenseRecord(license)
	]);

	const [licenses, setLicenses] = useState(Map(processedLicenses));

	return (
		<LicensesContext.Provider
			value={[
				licenses,
				{
					removeLicense(key) {
						setLicenses(licenses.delete(key));
					}
				}
			]}
		>
			{children}
		</LicensesContext.Provider>
	);
}

export function useLicenses() {
	return useContext(LicensesContext);
}
