/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {PermissionsProvider} from './../../hooks/permissions';
import Instructions from './Instructions';
import SupportDetails from './SupportDetails';

function SupportInformation({
	account,
	accountAttachmentURL,
	hasManageAccountsPermission,
	hasUpdateInstructionsPermission,
	instructions,
	language,
	languageList,
	oemInstructionsFileName,
	regionNames,
	updateAccountAttachmentURL,
	updateAccountURL,
	updateInstructionsURL,
	updateLanguageIdURL
}) {
	return (
		<>
			<PermissionsProvider
				permissions={{updatePermission: hasManageAccountsPermission}}
			>
				<SupportDetails
					account={account}
					language={language}
					languageList={languageList}
					regionNames={regionNames}
					updateAccountURL={updateAccountURL}
					updateLanguageIdURL={updateLanguageIdURL}
				/>
			</PermissionsProvider>

			<PermissionsProvider
				permissions={{
					updatePermission: hasUpdateInstructionsPermission
				}}
			>
				<Instructions
					accountAttachmentURL={accountAttachmentURL}
					accountKey={account.key}
					fileName={oemInstructionsFileName}
					instructions={instructions}
					updateAccountAttachmentURL={updateAccountAttachmentURL}
					updateInstructionsURL={updateInstructionsURL}
				/>
			</PermissionsProvider>
		</>
	);
}

SupportInformation.propTypes = {
	account: PropTypes.shape({
		code: PropTypes.string,
		editAccountURL: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		region: PropTypes.string,
		status: PropTypes.string,
		tier: PropTypes.string
	}),
	accountAttachmentURL: PropTypes.string,
	hasManageAccountsPermission: PropTypes.bool,
	hasUpdateInstructionsPermission: PropTypes.bool,
	instructions: PropTypes.string,
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
	oemInstructionsFileName: PropTypes.string,
	regionNames: PropTypes.arrayOf(PropTypes.string),
	updateAccountAttachmentURL: PropTypes.string,
	updateAccountURL: PropTypes.string,
	updateInstructionsURL: PropTypes.string,
	updateLanguageIdURL: PropTypes.string
};

export default SupportInformation;
