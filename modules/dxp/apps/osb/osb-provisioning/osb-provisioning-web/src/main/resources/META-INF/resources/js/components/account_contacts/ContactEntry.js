/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {NAMESPACE} from '../../utilities/constants';
import DropdownMultiSelect from '../DropdownMultiSelect';

export default function ContactEntry({
	accountName,
	addFn,
	allRoles = [],
	emailAddress,
	firstName,
	knownContact,
	lastName,
	newContact,
	newRoles = [],
	removeFn,
	setEmailAddress,
	setFirstName,
	setLastName,
	uuid
}) {
	function handleEmailChange(event) {
		setEmailAddress(event.currentTarget.value);
	}

	function handleFirstNameChange(event) {
		setFirstName(event.currentTarget.value);
	}

	function handleLastNameChange(event) {
		setLastName(event.currentTarget.value);
	}

	return (
		<tr className="contact-entry">
			{(knownContact || newContact) && (
				<>
					<td className="table-cell-expand">
						{knownContact && (
							<span className="text-truncate-inline">
								<span className="text-truncate">
									{firstName}
								</span>
							</span>
						)}
						<input
							className="form-control"
							name={`${NAMESPACE}firstName`}
							onChange={handleFirstNameChange}
							type={knownContact ? 'hidden' : 'text'}
							value={firstName}
						/>
					</td>
					<td className="table-cell-expand">
						{knownContact && (
							<span className="text-truncate-inline">
								<span className="text-truncate">
									{lastName}
								</span>
							</span>
						)}
						<input
							className="form-control"
							name={`${NAMESPACE}lastName`}
							onChange={handleLastNameChange}
							type={knownContact ? 'hidden' : 'text'}
							value={lastName}
						/>
					</td>
				</>
			)}
			<td className="table-cell-expand">
				{knownContact && (
					<span className="text-truncate-inline">
						<span className="text-truncate">{emailAddress}</span>
					</span>
				)}
				<input
					className="form-control"
					name={`${NAMESPACE}emailAddress`}
					onChange={handleEmailChange}
					type={knownContact ? 'hidden' : 'text'}
					value={emailAddress}
				/>
				<input name={`${NAMESPACE}uuid`} type="hidden" value={uuid} />
			</td>
			<td className="table-cell-expand">
				<DropdownMultiSelect
					addFn={addFn}
					allOptions={allRoles}
					newOptions={newRoles}
					removeFn={removeFn}
				/>
			</td>
			<td className="table-cell-expand">
				<span className="text-truncate-inline">
					<span className="text-truncate">{accountName}</span>
				</span>
			</td>
		</tr>
	);
}

ContactEntry.propTypes = {
	accountName: PropTypes.string,
	addFn: PropTypes.func,
	allRoles: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	emailAddress: PropTypes.string,
	firstName: PropTypes.string,
	knownContact: PropTypes.bool,
	lastName: PropTypes.string,
	newContact: PropTypes.bool,
	newRoles: PropTypes.arrayOf(PropTypes.string),
	removeFn: PropTypes.func,
	setEmailAddress: PropTypes.func,
	setFirstName: PropTypes.func,
	setLastName: PropTypes.func,
	uuid: PropTypes.func
};
