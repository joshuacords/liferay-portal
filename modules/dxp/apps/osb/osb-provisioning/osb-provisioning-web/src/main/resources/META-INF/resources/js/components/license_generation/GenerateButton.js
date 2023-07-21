/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {formatDate} from '../../utilities/date';
import {
	request,
	validateAllIPAddresses,
	validateIPv6s,
	validateMAC
} from '../../utilities/helpers';

function GenerateButton({
	alertError,
	formAction,
	redirect,
	serverIdValidatable = false
}) {
	const [license] = useNewLicense();
	const {licenseEntryId, licenseEntryType} = license.licenseEntry;
	const {productKey} = license.product;
	const {serverIds} = license;

	function disableGenerate() {
		return !license.description || !license.owner || serverIdValidatable
			? !validateServerIds()
			: false;
	}

	function handleSubmit() {
		const params = {
			...license.toJS(),
			expirationDate: formatDate(license.expirationDate),
			licenseEntryId,
			licenseEntryType,
			productKey,
			productVersion: license.version,
			serverIds: JSON.stringify(trimHostnames()),
			startDate: formatDate(license.startDate)
		};

		if (
			!alertError ||
			(alertError &&
				confirm(
					Liferay.Language.get(
						'the-new-provisioned-keys-count-will-be-higher-than-the-purchased-subscriptions'
					)
				))
		) {
			request(formAction, params, 'formData')
				.then(data => {
					const {redirectURL} = data;

					location.assign(redirectURL ? redirectURL : redirect);
				})
				.catch(err =>
					console.error(
						`Request to generate new license failed with: ${err}`
					)
				);
		}
	}

	function trimHostnames() {
		return serverIds.map(server => ({
			...server,
			hostName: server.hostName.trim()
		}));
	}

	function validateIpAddresses() {
		return serverIds.every(({ipAddresses}) =>
			ipAddresses ? validateAllIPAddresses(ipAddresses) : true
		);
	}

	function validateMacAddresses() {
		return serverIds.every(({macAddresses}) =>
			macAddresses ? validateMAC(macAddresses) : true
		);
	}

	function validateFields() {
		return serverIds
			.filter(
				({hostName, ipAddresses, macAddresses}) =>
					!hostName &&
					!macAddresses &&
					(ipAddresses ? validateIPv6s(ipAddresses) : !ipAddresses)
			)
			.isEmpty();
	}

	function validateServerIds() {
		return (
			validateFields() && validateIpAddresses() && validateMacAddresses()
		);
	}

	return (
		<button
			className="btn btn-primary"
			disabled={disableGenerate()}
			onClick={handleSubmit}
			type="button"
		>
			{Liferay.Language.get('generate')}
		</button>
	);
}

GenerateButton.propTypes = {
	alertError: PropTypes.bool,
	formAction: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	serverIdValidatable: PropTypes.bool
};

export default GenerateButton;
