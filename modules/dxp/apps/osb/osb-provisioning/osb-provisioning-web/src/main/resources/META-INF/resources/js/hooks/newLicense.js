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
