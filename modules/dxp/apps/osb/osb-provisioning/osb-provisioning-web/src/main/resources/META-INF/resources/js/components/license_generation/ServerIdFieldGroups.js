/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {validateAllIPAddresses, validateMAC} from '../../utilities/helpers';
import IconButton from '../IconButton';
import RequiredFieldMarker from '../RequiredFieldMarker';

function ServerIdFieldGroups() {
	const [{serverIds}] = useNewLicense();

	return (
		<div className="col-md-12 form-group">
			<h4>
				{Liferay.Language.get('server-id-fields')}{' '}
				<RequiredFieldMarker />
			</h4>

			<div className="server-id-field-groups">
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get(
							'please-fill-out-at-lease-one-of-the-server-id-fields-for-each-field-group'
						)}
					</div>
				</div>

				{serverIds.map((group, index) => (
					<FieldGroup
						group={group}
						id={index}
						key={index}
						showDelete={serverIds.size > 1}
					/>
				))}
			</div>
		</div>
	);
}

function FieldGroup({group, id, showDelete = false}) {
	const [, {updateLicense}] = useNewLicense();

	function handleAdd() {
		updateLicense(license =>
			license.update('serverIds', serverIds =>
				serverIds.insert(id + 1, {
					hostName: '',
					ipAddresses: '',
					macAddresses: ''
				})
			)
		);
	}

	function handleHostNameChange(event) {
		const {currentTarget} = event;

		updateLicense(license =>
			license.update('serverIds', serverIds =>
				serverIds.update(id, index => {
					return {...index, hostName: currentTarget.value};
				})
			)
		);
	}

	function handleIpAddressChange(event) {
		updateLicense(license =>
			license.update('serverIds', serverIds =>
				serverIds.update(id, index => {
					return {...index, ipAddresses: event.currentTarget.value};
				})
			)
		);
	}

	function handleMacAddressChange(event) {
		updateLicense(license =>
			license.update('serverIds', serverIds =>
				serverIds.update(id, index => {
					return {...index, macAddresses: event.currentTarget.value};
				})
			)
		);
	}

	function handleRemove() {
		updateLicense(license =>
			license.update('serverIds', serverIds => serverIds.delete(id))
		);
	}

	function validateIpAddresses() {
		const {ipAddresses} = group;

		return ipAddresses ? validateAllIPAddresses(ipAddresses) : true;
	}

	function validateMacAddresses() {
		const {macAddresses} = group;

		return macAddresses ? validateMAC(macAddresses) : true;
	}

	return (
		<div className="server-id-field">
			<div className="col-md-12 form-group">
				<label
					className="form-control-label"
					htmlFor={`hostName-${id}`}
				>
					{Liferay.Language.get('host-name')}
				</label>

				<input
					className="form-control"
					id={`hostName-${id}`}
					onChange={handleHostNameChange}
					type="text"
					value={group.hostName}
				/>
			</div>

			<div
				className={`col-md-12 form-group ${
					!validateIpAddresses() ? 'has-error' : ''
				}`}
			>
				<label
					className="form-control-label"
					htmlFor={`ipAddresses-${id}`}
				>
					{Liferay.Language.get('ip-addresses')}
				</label>
				<textarea
					className="form-control"
					id={`ipAddresses-${id}`}
					onChange={handleIpAddressChange}
					rows={2}
					value={group.ipAddresses}
				/>
			</div>

			<div
				className={`col-md-12 form-group ${
					!validateMacAddresses() ? 'has-error' : ''
				}`}
			>
				<label
					className="form-control-label"
					htmlFor={`macAddresses-${id}`}
				>
					{Liferay.Language.get('mac-addresses')}
				</label>
				<textarea
					className="form-control"
					id={`macAddresses-${id}`}
					onChange={handleMacAddressChange}
					rows={2}
					value={group.macAddresses}
				/>
			</div>

			<div className="btn-group col-md-12" role="group">
				<div className="btn-group-item">
					<IconButton
						cssClass="add-fields btn-secondary nav-btn nav-btn-monospaced"
						labelName={Liferay.Language.get('add')}
						onClick={handleAdd}
						svgId="#plus"
						title={Liferay.Language.get('add')}
					/>
				</div>

				{showDelete && (
					<div className="btn-group-item">
						<IconButton
							cssClass="btn-secondary delete-fields nav-btn nav-btn-monospaced"
							labelName={Liferay.Language.get('delete')}
							onClick={handleRemove}
							svgId="#hr"
							title={Liferay.Language.get('delete')}
						/>
					</div>
				)}
			</div>
		</div>
	);
}

FieldGroup.propTypes = {
	group: PropTypes.shape({
		hostName: PropTypes.string,
		ipAddresses: PropTypes.string,
		macAddresses: PropTypes.string
	}),
	id: PropTypes.number.isRequired,

	showDelete: PropTypes.bool
};

export default ServerIdFieldGroups;
