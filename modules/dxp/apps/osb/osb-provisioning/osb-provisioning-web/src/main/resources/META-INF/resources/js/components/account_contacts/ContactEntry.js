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

import {NAMESPACE} from '../../utilities/constants';
import DropdownMultiSelect from '../DropdownMultiSelect';

export default function ContactEntry({
	accountName,
	addFn,
	allRoles = [],
	contactFullName,
	emailAddress,
	knownContact,
	newRoles = [],
	removeFn,
	setEmailAddress
}) {
	function handleEmailChange(event) {
		setEmailAddress(event.currentTarget.value);
	}

	return (
		<tr className="contact-entry">
			{knownContact && (
				<td className="table-cell-expand">
					<span className="text-truncate-inline">
						<span className="semi-bold text-truncate">
							{contactFullName}
						</span>
					</span>
				</td>
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
	contactFullName: PropTypes.string,
	emailAddress: PropTypes.string,
	knownContact: PropTypes.bool,
	newRoles: PropTypes.arrayOf(PropTypes.string),
	removeFn: PropTypes.func,
	setEmailAddress: PropTypes.func
};
