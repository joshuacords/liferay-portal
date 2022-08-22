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
				allowPermanentLicenses: true,
				allowSelfProvisioning: true,
				code: '123',
				dataRegion: 'Brazil',
				dateCreated: CURRENT_TIME.toLocaleString('en-US'),
				dateModified: CURRENT_TIME.toLocaleString('en-US'),
				analyticsCloudGroupId: 'testAnalyticsCloudGroupId',
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
