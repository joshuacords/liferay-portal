/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {
	ACCOUNTS_PORTLET_NAMESPACE as NAMESPACE,
	FIELD_SIZE_SMALL
} from '../../../utilities/constants';
import {itemSelectorDialogSelection} from '../../../utilities/itemSelectorDialogHelper';
import ExternalSelectField from '../../ExternalSelectField';

function AccountDetails({
	countryNames,
	selectAccountURL,
	selectFirstLineSupportURL,
	selectPartnerURL
}) {
	return (
		<div className="panel-body">
			<div className="col-md-6 form-group">
				<label htmlFor="name">
					{Liferay.Language.get('account-name')}
				</label>
				<input
					className="form-control form-control-sm"
					id="name"
					name={`${NAMESPACE}name`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="code">{Liferay.Language.get('code')}</label>
				<input
					className="form-control form-control-sm"
					id="code"
					name={`${NAMESPACE}code`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('parent-account')}
				</h5>

				<ExternalSelect
					externalData={{
						formField: 'parentAccount',
						title: Liferay.Language.get('select-parent-account'),
						url: selectAccountURL
					}}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="workerContactEmailAddress">
					{Liferay.Language.get('project-worker')}
				</label>
				<input
					className="form-control form-control-sm"
					id="workerContactEmailAddress"
					name={`${NAMESPACE}workerContactEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-liferay-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('partner-reseller-si')}
				</h5>

				<ExternalSelect
					externalData={{
						formField: 'partnerTeam',
						title: Liferay.Language.get('select-partner-team'),
						url: selectPartnerURL
					}}
				/>
			</div>

			<div className="col-md-6 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('first-line-support')}
				</h5>

				<ExternalSelect
					externalData={{
						formField: 'flsTeam',
						title: Liferay.Language.get(
							'select-first-line-support-team'
						),
						url: selectFirstLineSupportURL
					}}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="countryName">
					{Liferay.Language.get('country')}
				</label>
				<select
					className="form-control form-control-sm"
					id="countryName"
					name={`${NAMESPACE}countryName`}
				>
					<option></option>
					{countryNames.map(name => (
						<option key={name} value={name}>
							{name}
						</option>
					))}
				</select>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="externalAccountKey">
					{Liferay.Language.get('external-account-key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="externalAccountKey"
					name={`${NAMESPACE}externalAccountKey`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="notes">{Liferay.Language.get('notes')}</label>
				<input
					className="form-control form-control-sm"
					id="notes"
					name={`${NAMESPACE}notes`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="salesInfo">
					{Liferay.Language.get('sales-info')}
				</label>
				<input
					className="form-control form-control-sm"
					id="salesInfo"
					name={`${NAMESPACE}salesInfo`}
					type="text"
				/>
			</div>
		</div>
	);
}

AccountDetails.propTypes = {
	countryNames: PropTypes.array.isRequired,
	selectAccountURL: PropTypes.string.isRequired,
	selectFirstLineSupportURL: PropTypes.string.isRequired,
	selectPartnerURL: PropTypes.string.isRequired
};

function ExternalSelect({externalData}) {
	const [fieldKey, setFieldKey] = useState('');
	const [fieldValue, setFieldValue] = useState('');

	function handleClick() {
		const assignInputValueFromDialog = fieldData => {
			const {key, name} = JSON.parse(fieldData);

			if (key) {
				setFieldKey(key);
			}

			if (name) {
				setFieldValue(name);
			}
		};

		itemSelectorDialogSelection(externalData, assignInputValueFromDialog);
	}

	return (
		<>
			<input
				name={`${NAMESPACE}${externalData.formField}Key`}
				type="hidden"
				value={fieldKey}
			/>
			<input
				name={`${NAMESPACE}${externalData.formField}Name`}
				type="hidden"
				value={fieldValue}
			/>

			<ExternalSelectField
				clickFn={handleClick}
				id={externalData.formField}
				inputSize={FIELD_SIZE_SMALL}
				value={fieldValue}
			/>
		</>
	);
}

export default AccountDetails;
