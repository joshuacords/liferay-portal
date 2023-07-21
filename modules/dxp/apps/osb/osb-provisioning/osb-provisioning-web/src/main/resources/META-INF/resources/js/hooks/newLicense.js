/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {List, Record} from 'immutable';
import React, {useContext, useState} from 'react';

export const License = Record({
	accountKey: '',
	accountName: '',
	allowPermanentLicenses: true,
	complimentary: false,
	description: '',
	expirationDate: null,
	licenseEntry: {
		licenseEntryId: '',
		licenseEntryName: '',
		licenseEntryType: ''
	},
	licenseKeysAllowed: 0,
	licenseKeysGenerated: 0,
	maxClusterNodes: 0,
	maxHttpSessions: 0,
	maxServers: 1,
	name,
	owner: '',
	product: {productKey: '', productName: ''},
	productPurchaseKey: '',
	serverIds: List.of({hostName: '', ipAddresses: '', macAddresses: ''}),
	showSpecificDetails: false,
	sizing: '',
	startDate: null,
	version: ''
});

const NewLicenseContext = React.createContext();

export function NewLicenseProvider({initialLicense = {}, children}) {
	const [license, setLicense] = useState(new License(initialLicense));

	return (
		<NewLicenseContext.Provider
			value={[
				license,
				{
					updateLicense(updater) {
						setLicense(updater(license));
					}
				}
			]}
		>
			{children}
		</NewLicenseContext.Provider>
	);
}

export function useNewLicense() {
	return useContext(NewLicenseContext);
}
