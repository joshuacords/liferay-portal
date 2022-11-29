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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import GeneralDetails from '../../../src/main/resources/META-INF/resources/js/components/account_details/GeneralDetails';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {CURRENT_TIME} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderGeneralDetails(permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<GeneralDetails
				dataRegions={['Brazil', 'Hungary', 'Japan', 'United States']}
				details={{
					allowComplimentary: false,
					allowPermanentLicenses: true,
					allowSelfProvisioning: false,
					code: '123',
					dataRegion: 'Brazil',
					dateCreated: CURRENT_TIME.toLocaleString('en-US'),
					dateModified: CURRENT_TIME.toLocaleString('en-US'),
					firstLineSupportTeamName: 'Test Support Team',
					key: '123',
					liferayVersion: 'DXP 7.0',
					name: 'Test Account',
					parterTeamName: 'Test Partner Team',
					region: 'US',
					subscriptionState: 'Active',
					subscriptionStateStyle: 'label-success',
					tier: 'Regular'
				}}
				liferayVersions={[
					'DXP 7.0',
					'DXP 7.1',
					'DXP 7.2',
					'DXP 7.3',
					'DXP 7.4'
				]}
				parentAccountName="Parent Account Name"
				tiers={['1', '2', '3']}
			/>
		</PermissionsProvider>
	);
}

describe('GeneralDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderGeneralDetails();

		expect(container).toBeTruthy();
	});

	it('shows Account Name field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('account-name');
		getByText('Test Account');
	});

	it('shows State field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('state');
		getByText('Active');
	});

	it('shows Code field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('code');
		getByText('123');
	});

	it('shows Tier field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('tier');
		getByText('Regular');
	});

	it('shows Parent Account field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('parent');
		getByText('Parent Account Name');
	});

	it('shows Data Region field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('data-region');
		getByText('Brazil');
	});

	it('shows Liferay Version field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('liferay-version');
		getByText('DXP 7.0');
	});

	it('shows Complimentary field', () => {
		const {getByLabelText, getByText} = renderGeneralDetails();

		getByText('complimentary');
		expect(getByLabelText('allowComplimentary').checked).toBeFalsy();
	});

	it('shows Permanent Licenses field', () => {
		const {getByLabelText, getByText} = renderGeneralDetails();

		getByText('permanent-licenses');
		expect(getByLabelText('allowPermanentLicenses').checked).toBeTruthy();
	});

	it('shows Self Provisioning field', () => {
		const {getByLabelText, getByText} = renderGeneralDetails();

		getByText('self-provisioning');
		expect(getByLabelText('allowSelfProvisioning').checked).toBeFalsy();
	});

	describe('General Details with full editing privilege', () => {
		it('allows a permissioned field (Account Name) to be edited', () => {
			const {getByText} = renderGeneralDetails();

			fireEvent.click(getByText('Test Account'));

			getByText('save');
			getByText('cancel');
		});

		it('allows a permissioned toggle field (Permanent Licenses) to be edited', () => {
			const {getByLabelText, getByText} = renderGeneralDetails();

			fireEvent.click(getByLabelText('allowPermanentLicenses'));

			getByText('save');
		});

		it('allows a non permissioned field (Data Region) to be edited', () => {
			const {getByText} = renderGeneralDetails();

			fireEvent.click(getByText('Brazil'));

			getByText('Brazil');
			getByText('Hungary');
			getByText('Japan');
			getByText('United States');

			getByText('save');
			getByText('cancel');
		});

		it('does not allow an uneditable field to be edited', () => {
			const {getByText, queryByText} = renderGeneralDetails();

			fireEvent.click(getByText('Active'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});

	describe('General Details with limited editing privilege', () => {
		it('does not allow a permissioned field (Account Name) to be edited', () => {
			const {getByText, queryByText} = renderGeneralDetails(false);

			fireEvent.click(getByText('Test Account'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});

		it('allows a non permissioned field (Data Region) to be edited', () => {
			const {getByText, queryByText} = renderGeneralDetails(false);

			fireEvent.click(getByText('Brazil'));

			expect(queryByText('Hungary')).toBeFalsy();
			expect(queryByText('Japan')).toBeFalsy();
			expect(queryByText('United States')).toBeFalsy();

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});

		it('does not allow an uneditable field to be edited', () => {
			const {getByText, queryByText} = renderGeneralDetails(false);

			fireEvent.click(getByText('Active'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});
});
