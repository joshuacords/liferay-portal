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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {usePermissions} from '../../hooks/permissions';
import {
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_TEXT
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';

function ExternalAccountKeys({details}) {
	const {updatePermission} = usePermissions();
	const fieldType = updatePermission
		? FIELD_TYPE_TEXT
		: FIELD_TYPE_NONEDITABLE;

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('external-account-keys')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('analytics-cloud-group')}
				fieldName="entityId"
				formAction={details.updateAnalyticsCloudGroupURL}
				formData={{
					domain: 'analytics-cloud',
					entityId: convertDashToEmptyString(
						details.analyticsCloudGroupId
					),
					entityName: 'group'
				}}
				type={fieldType}
				value={details.analyticsCloudGroupId}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('dossiera-account')}
				fieldName="entityId"
				formAction={details.updateDossieraAccountURL}
				formData={{
					domain: 'dossiera',
					entityId: convertDashToEmptyString(
						details.dossieraAccountKey
					),
					entityName: 'account'
				}}
				type={fieldType}
				value={details.dossieraAccountKey}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('dossiera-project')}
				fieldName="entityId"
				formAction={details.updateDossieraProjectURL}
				formData={{
					domain: 'dossiera',
					entityId: convertDashToEmptyString(
						details.dossieraProjectKey
					),
					entityName: 'project'
				}}
				type={fieldType}
				value={details.dossieraProjectKey}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('dxp-cloud-project')}
				fieldName="entityId"
				formAction={details.updateDxpCloudProjectURL}
				formData={{
					domain: 'dxp-cloud',
					entityId: convertDashToEmptyString(
						details.dxpCloudProjectId
					),
					entityName: 'project'
				}}
				type={fieldType}
				value={details.dxpCloudProjectId}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('salesforce-project')}
				fieldName="entityId"
				formAction={details.updateSalesforceProjectURL}
				formData={{
					domain: 'salesforce',
					entityId: convertDashToEmptyString(
						details.salesforceProjectKey
					),
					entityName: 'project'
				}}
				type={fieldType}
				value={details.salesforceProjectKey}
			/>
		</ClayList>
	);
}

ExternalAccountKeys.propTypes = {
	details: PropTypes.shape({
		dossieraAccountKey: PropTypes.string,
		dossieraProjectKey: PropTypes.string,
		key: PropTypes.string,
		salesforceProjectKey: PropTypes.string,
		updateAnalyticsCloudGroupURL: PropTypes.string,
		updateDossieraAccountURL: PropTypes.string,
		updateDossieraProjectURL: PropTypes.string,
		updateDxpCloudProjectURL: PropTypes.string,
		updateSalesforceProjectURL: PropTypes.string
	})
};

export default ExternalAccountKeys;
