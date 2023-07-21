/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {usePermissions} from '../../hooks/permissions';
import {
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';
import SupportField from './SupportField';

function SupportDetails({
	account,
	language,
	languageList,
	regionNames,
	updateAccountURL,
	updateLanguageIdURL
}) {
	const {updatePermission} = usePermissions();
	const fieldType = updatePermission
		? FIELD_TYPE_SELECT
		: FIELD_TYPE_NONEDITABLE;

	const formData = {
		code: convertDashToEmptyString(account.code),
		name: convertDashToEmptyString(account.name),
		region: convertDashToEmptyString(account.region),
		status: convertDashToEmptyString(account.status),
		tier: convertDashToEmptyString(account.tier),
		updateAccount: true
	};

	function createSelectOptions(array) {
		return array.map(value => {
			return {
				label: value.name ? value.name : value,
				value: value.id ? value.id : value
			};
		});
	}

	function handleUpdateSupportLanguage(languageId) {
		return {languageId};
	}

	return (
		<ClayList className="support-details">
			<ClayList.Header>{Liferay.Language.get('details')}</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('support-region')}
				fieldName="region"
				formAction={updateAccountURL}
				formData={formData}
				options={createSelectOptions(regionNames)}
				type={fieldType}
				value={account.region}
			/>

			{!!updateLanguageIdURL && (
				<SupportField
					displayValue={language.name}
					fieldLabel={Liferay.Language.get('support-language')}
					fieldName="languageId"
					formAction={updateLanguageIdURL}
					options={createSelectOptions(languageList)}
					type={fieldType}
					updateFormData={handleUpdateSupportLanguage}
					value={language.id}
				/>
			)}

			{!updateLanguageIdURL && (
				<ClayList.Item flex>
					<div className="detail-field">
						<ClayList.ItemTitle>
							{Liferay.Language.get('support-language')}
						</ClayList.ItemTitle>
						<div className="list-group-text text-muted">
							{Liferay.Language.get(
								'support-project-does-not-exist'
							)}
						</div>
					</div>
				</ClayList.Item>
			)}
		</ClayList>
	);
}

SupportDetails.propTypes = {
	account: PropTypes.shape({
		code: PropTypes.string,
		editAccountURL: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		region: PropTypes.string,
		status: PropTypes.string,
		tier: PropTypes.string
	}),
	language: PropTypes.shape({
		id: PropTypes.string,
		name: PropTypes.string
	}),
	languageList: PropTypes.arrayOf(
		PropTypes.shape({
			id: PropTypes.string,
			name: PropTypes.string
		})
	),
	regionNames: PropTypes.arrayOf(PropTypes.string),
	updateAccountURL: PropTypes.string,
	updateLanguageIdURL: PropTypes.string
};

export default SupportDetails;
