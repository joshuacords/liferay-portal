/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import AccountDetails from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountDetails';
import {CURRENT_TIME} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderAccountDetails(props) {
	return render(
		<AccountDetails
			countryOptions={[
				{
					active: true,
					countryRegions: [],
					name: 'afghanistan',
					zipRequired: true
				},
				{
					active: true,
					countryRegions: [
						{
							active: true,
							code: 'NSW',
							countryName: 'australia',
							name: 'New South Wales'
						},
						{
							active: true,
							code: 'QLD',
							countryName: 'australia',
							name: 'Queensland'
						},
						{
							active: true,
							code: 'TAS',
							countryName: 'australia',
							name: 'Tasmania'
						},
						{
							active: true,
							code: 'VIC',
							countryName: 'australia',
							name: 'Victoria'
						}
					],
					name: 'australia',
					zipRequired: true
				}
			]}
			dataRegionNames={['Brazil', 'Hungary', 'Japan', 'United States']}
			details={{
				addPostalAddressURL: '/',
				allowComplimentary: false,
				allowPermanentLicenses: true,
				allowSelfProvisioning: true,
				analyticsCloudGroupId: 'testAnalyticsCloudGroupId',
				code: '123',
				dataRegion: 'Brazil',
				dateCreated: CURRENT_TIME.toLocaleString('en-US'),
				dateModified: CURRENT_TIME.toLocaleString('en-US'),
				dossieraAccountKey: 'testDossieraAccountKey',
				dossieraProjectKey: 'testDossieraProjectKey',
				dxpCloudProjectId: 'testDxpCloudProjectId',
				editAccountURL: 'edit/account/url',
				firstLineSupportTeamKey: 'first-line-123',
				firstLineSupportTeamName: 'Test Support Team',
				key: '123',
				liferayVersion: 'DXP 7.0',
				name: 'Test Account',
				partnerTeamKey: 'partner-123',
				partnerTeamName: 'Test Partner Team',
				postalAddressDisplays: [],
				region: 'US',
				salesforceProjectKey: 'TestSalesForceProjectKey',
				subscriptionState: 'Active',
				subscriptionStateStyle: 'label-success',
				tier: 'Regular',
				updateAnalyticsCloudGroupURL: '/update/analytics-cloud/group',
				updateDossieraAccountURL: '/update/dossiera/account',
				updateDossieraProjectURL: '/update/dossiera/project',
				updateDxpCloudProjectURL: '/update/dxp-cloud/project',
				updateSalesforceProjectURL: '/update/salesforce/project'
			}}
			liferayVersionNames={[
				'DXP 7.0',
				'DXP 7.1',
				'DXP 7.2',
				'DXP 7.3',
				'DXP 7.4'
			]}
			parentAccountName="parent"
			tierNames={['1', '2', '3']}
			{...props}
		/>
	);
}

describe('AccountDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountDetails();

		expect(container).toBeTruthy();
	});

	it('displays General Details section', () => {
		const {getByText} = renderAccountDetails();

		getByText('general-details');
	});

	it('displays Partner Info section', () => {
		const {getByText} = renderAccountDetails();

		getByText('partner-info');
	});

	it('displays Address 1 section', () => {
		const {getByText} = renderAccountDetails();

		getByText('address 1');
	});

	it('displays External Account Keys section', () => {
		const {getByText} = renderAccountDetails();

		getByText('external-account-keys');
	});
});
