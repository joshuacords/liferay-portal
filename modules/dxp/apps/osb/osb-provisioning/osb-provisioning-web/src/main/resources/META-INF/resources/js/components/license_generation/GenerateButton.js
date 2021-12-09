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

import {useNewLicense} from '../../hooks/newLicense';
import {formatDate} from '../../utilities/date';
import {
	request,
	validateAllIPAddresses,
	validateIPv6s,
	validateMAC
} from '../../utilities/helpers';

function GenerateButton({formAction, redirect, serverIdValidatable = false}) {
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

	function trimHostnames() {
		return serverIds.map(server => {
			return {...server, hostName: server.hostName.trim()};
		});
	}

	function validateIpAddresses() {
		return serverIds.every(({ipAddresses}) => {
			if (ipAddresses) {
				return validateAllIPAddresses(ipAddresses);
			}
			else {
				return true;
			}
		});
	}

	function validateMacAddresses() {
		return serverIds.every(({macAddresses}) => {
			if (macAddresses) {
				return validateMAC(macAddresses);
			}
			else {
				return true;
			}
		});
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

GenerateButton.propTypse = {
	formAction: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	serverIdValidatable: PropTypes.bool
};

export default GenerateButton;
