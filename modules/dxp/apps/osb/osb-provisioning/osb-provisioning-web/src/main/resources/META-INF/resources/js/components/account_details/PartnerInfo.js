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
	NAMESPACE
} from '../../utilities/constants';
import DetailField from '../DetailField';

function PartnerInfo({
	assignFirstLineSupportTeamURL,
	assignPartnerTeamURL,
	details
}) {
	const {updatePermission} = usePermissions();
	const fieldType = updatePermission
		? FIELD_TYPE_EXTERNAL
		: FIELD_TYPE_NONEDITABLE;

	const firstLineSupportFormData = {
		firstLineSupportTeamKey: '',
		updateFirstLineSupport: true
	};
	const partnerFormData = {
		partnerTeamKey: '',
		updatePartner: true
	};

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>

			<DetailField
				externalData={{
					formField: 'partnerTeamKey',
					formName: `${NAMESPACE}updatePartnerFm`,
					title: Liferay.Language.get('select-partner-team'),
					url: assignPartnerTeamURL
				}}
				fieldLabel={Liferay.Language.get('partner-reseller-si')}
				formAction={details.editAccountURL}
				formData={partnerFormData}
				type={fieldType}
				value={details.partnerTeamName}
			/>

			<DetailField
				externalData={{
					formField: 'firstLineSupportTeamKey',
					formName: `${NAMESPACE}updateFirstLineSupportFm`,
					title: Liferay.Language.get(
						'select-first-line-support-team'
					),
					url: assignFirstLineSupportTeamURL
				}}
				fieldLabel={Liferay.Language.get('first-line-support')}
				formAction={details.editAccountURL}
				formData={firstLineSupportFormData}
				type={fieldType}
				value={details.firstLineSupportTeamName}
			/>
		</ClayList>
	);
}

PartnerInfo.propTypes = {
	assignFirstLineSupportTeamURL: PropTypes.string,
	assignPartnerTeamURL: PropTypes.string,
	details: PropTypes.shape({
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		partnerTeamName: PropTypes.string
	})
};

export default PartnerInfo;
