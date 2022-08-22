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

import PropTypes from 'prop-types';
import React from 'react';

import {PermissionsProvider} from './../../hooks/permissions';
import AccountAddresses from './AccountAddresses';
import ExternalAccountKeys from './ExternalAccountKeys';
import GeneralDetails from './GeneralDetails';
import PartnerInfo from './PartnerInfo';

function AccountDetails({
	assignFirstLineSupportTeamURL,
	assignParentAccountURL,
	assignPartnerTeamURL,
	countryOptions,
	dataRegionNames,
	details,
	hasManageAccountsPermission,
	hasUpdateExternalLinksPermission,
	liferayVersionNames,
	parentAccountName,
	tierNames
}) {
	return (
		<>
			<PermissionsProvider
				permissions={{updatePermission: hasManageAccountsPermission}}
			>
				<GeneralDetails
					assignParentAccountURL={assignParentAccountURL}
					dataRegions={dataRegionNames}
					details={details}
					liferayVersions={liferayVersionNames}
					parentAccountName={parentAccountName}
					tiers={tierNames}
				/>

				<PartnerInfo
					assignFirstLineSupportTeamURL={
						assignFirstLineSupportTeamURL
					}
					assignPartnerTeamURL={assignPartnerTeamURL}
					details={details}
				/>

				<AccountAddresses
					accountKey={details.key}
					addAddressURL={details.addPostalAddressURL}
					addresses={details.postalAddressDisplays}
					countryOptions={countryOptions}
				/>
			</PermissionsProvider>

			<PermissionsProvider
				permissions={{
					updatePermission: hasUpdateExternalLinksPermission
				}}
			>
				<ExternalAccountKeys details={details} />
			</PermissionsProvider>
		</>
	);
}

AccountDetails.propTypes = {
	assignFirstLineSupportTeamURL: PropTypes.string,
	assignParentAccountURL: PropTypes.string,
	assignPartnerTeamURL: PropTypes.string,
	countryOptions: PropTypes.arrayOf(
		PropTypes.shape({
			active: PropTypes.bool,
			countryRegions: PropTypes.arrayOf(
				PropTypes.shape({
					active: PropTypes.bool,
					countryName: PropTypes.string,
					name: PropTypes.string
				})
			),
			name: PropTypes.string,
			zipRequired: PropTypes.bool
		})
	),
	dataRegionNames: PropTypes.arrayOf(PropTypes.string),
	details: PropTypes.shape({
		addPostalAddressURL: PropTypes.string,
		allowPermanentLicenses: PropTypes.bool,
		allowSelfProvisioning: PropTypes.bool,
		code: PropTypes.string,
		dataRegion: PropTypes.string,
		dateCreated: PropTypes.string,
		dateModified: PropTypes.string,
		analyticsCloudGroupId: PropTypes.string,
		dossieraAccountKey: PropTypes.string,
		dossieraProjectKey: PropTypes.string,
		dxpCloudProjectId: PropTypes.string,
		editAccountHierarchyURL: PropTypes.string,
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		liferayVersion: PropTypes.string,
		name: PropTypes.string,
		parterTeamName: PropTypes.string,
		postalAddressDisplays: PropTypes.arrayOf(
			PropTypes.shape({
				addressCountry: PropTypes.string,
				addressLocality: PropTypes.string,
				addressRegion: PropTypes.string,
				deletePostalAddressURL: PropTypes.string,
				editPostalAddressURL: PropTypes.string,
				id: PropTypes.string,
				postalCode: PropTypes.string,
				primary: PropTypes.bool,
				streetAddressLine1: PropTypes.string,
				streetAddressLine2: PropTypes.string,
				streetAddressLine3: PropTypes.string
			})
		),
		region: PropTypes.string,
		salesforceProjectKey: PropTypes.string,
		subscriptionState: PropTypes.string,
		subscriptionStateStyle: PropTypes.string,
		tier: PropTypes.string,
		updateAnalyticsCloudGroupURL: PropTypes.string,
		updateDossieraAccountURL: PropTypes.string,
		updateDossieraProjectURL: PropTypes.string,
		updateDXPCloudProjectURL: PropTypes.string,
		updateSalesforceProjectURL: PropTypes.string
	}),
	hasManageAccountsPermission: PropTypes.bool,
	hasUpdateExternalLinksPermission: PropTypes.bool,
	liferayVersionNames: PropTypes.arrayOf(PropTypes.string),
	parentAccountName: PropTypes.string,
	tierNames: PropTypes.arrayOf(PropTypes.string)
};

export default AccountDetails;
