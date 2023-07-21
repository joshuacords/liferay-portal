/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {usePermissions} from '../../hooks/permissions';
import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TOGGLE,
	NAMESPACE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';

function GeneralDetails({
	assignParentAccountURL,
	dataRegions,
	details,
	liferayVersions,
	parentAccountName,
	tiers
}) {
	const {updatePermission} = usePermissions();

	const formData = {
		allowComplimentary: details.allowComplimentary,
		allowPermanentLicenses: details.allowPermanentLicenses,
		allowSelfProvisioning: details.allowSelfProvisioning,
		code: convertDashToEmptyString(details.code),
		dataRegion: convertDashToEmptyString(details.dataRegion),
		liferayVersion: convertDashToEmptyString(details.liferayVersion),
		name: convertDashToEmptyString(details.name),
		region: convertDashToEmptyString(details.region),
		tier: convertDashToEmptyString(details.tier),
		updateAccount: true
	};

	function createSelectOptions(options) {
		return options.map(option => ({
			label: option,
			value: option
		}));
	}

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('general-details')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('account-name')}
				fieldName="name"
				formAction={details.editAccountURL}
				formData={formData}
				type={
					updatePermission ? FIELD_TYPE_TEXT : FIELD_TYPE_NONEDITABLE
				}
				value={details.name}
			/>

			<DetailField
				displayAs="label"
				fieldLabel={Liferay.Language.get('state')}
				inputStyle={details.subscriptionStateStyle}
				value={details.subscriptionState}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('code')}
				fieldName="code"
				formAction={details.editAccountURL}
				formData={formData}
				type={
					updatePermission ? FIELD_TYPE_TEXT : FIELD_TYPE_NONEDITABLE
				}
				value={details.code}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('created')}
				value={details.dateCreated}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('tier')}
				fieldName="tier"
				formAction={details.editAccountURL}
				formData={formData}
				options={createSelectOptions(tiers)}
				type={
					updatePermission
						? FIELD_TYPE_SELECT
						: FIELD_TYPE_NONEDITABLE
				}
				value={details.tier}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('last-modified')}
				value={details.dateModified}
			/>

			<DetailField
				externalData={{
					formField: 'parentAccountKey',
					formName: `${NAMESPACE}editAccountHierarchyFm`,
					title: Liferay.Language.get('select-parent-account'),
					url: assignParentAccountURL
				}}
				fieldLabel={Liferay.Language.get('parent')}
				formAction={details.editAccountHierarchyURL}
				formData={{parentAccountKey: ''}}
				type={
					updatePermission
						? FIELD_TYPE_EXTERNAL
						: FIELD_TYPE_NONEDITABLE
				}
				value={parentAccountName}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('data-region')}
				fieldName="dataRegion"
				formAction={details.editAccountURL}
				formData={formData}
				options={createSelectOptions(dataRegions)}
				type={
					updatePermission
						? FIELD_TYPE_SELECT
						: FIELD_TYPE_NONEDITABLE
				}
				value={details.dataRegion}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('liferay-version')}
				fieldName="liferayVersion"
				formAction={details.editAccountURL}
				formData={formData}
				options={createSelectOptions(liferayVersions)}
				type={
					updatePermission
						? FIELD_TYPE_SELECT
						: FIELD_TYPE_NONEDITABLE
				}
				value={details.liferayVersion}
			/>

			<DetailField
				displayAs="toggle"
				fieldLabel={Liferay.Language.get('complimentary')}
				fieldName="allowComplimentary"
				formAction={details.editAccountURL}
				formData={formData}
				type={
					updatePermission
						? FIELD_TYPE_TOGGLE
						: FIELD_TYPE_NONEDITABLE
				}
				value={details.allowComplimentary}
			/>

			<DetailField
				displayAs="toggle"
				fieldLabel={Liferay.Language.get('permanent-licenses')}
				fieldName="allowPermanentLicenses"
				formAction={details.editAccountURL}
				formData={formData}
				type={
					updatePermission
						? FIELD_TYPE_TOGGLE
						: FIELD_TYPE_NONEDITABLE
				}
				value={details.allowPermanentLicenses}
			/>

			<DetailField
				displayAs="toggle"
				fieldLabel={Liferay.Language.get('self-provisioning')}
				fieldName="allowSelfProvisioning"
				formAction={details.editAccountURL}
				formData={formData}
				type={
					updatePermission
						? FIELD_TYPE_TOGGLE
						: FIELD_TYPE_NONEDITABLE
				}
				value={details.allowSelfProvisioning}
			/>
		</ClayList>
	);
}

GeneralDetails.propTypes = {
	assignParentAccountURL: PropTypes.string,
	dataRegions: PropTypes.arrayOf(PropTypes.string).isRequired,
	details: PropTypes.shape({
		allowComplimentary: PropTypes.bool,
		allowPermanentLicenses: PropTypes.bool,
		allowSelfProvisioning: PropTypes.bool,
		code: PropTypes.string,
		dataRegion: PropTypes.string,
		dateCreated: PropTypes.string,
		dateModified: PropTypes.string,
		editAccountHierarchyURL: PropTypes.string,
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		liferayVersion: PropTypes.string,
		name: PropTypes.string,
		parterTeamName: PropTypes.string,
		region: PropTypes.string,
		subscriptionState: PropTypes.string,
		subscriptionStateStyle: PropTypes.string,
		tier: PropTypes.string
	}),
	liferayVersions: PropTypes.arrayOf(PropTypes.string).isRequired,
	parentAccountName: PropTypes.string,
	tiers: PropTypes.arrayOf(PropTypes.string).isRequired
};

export default GeneralDetails;
