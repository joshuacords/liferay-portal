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

import ClayAlert from '@clayui/alert';
import {Set} from 'immutable';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {CONTACT_ROLE_ADMINISTRATOR, NAMESPACE} from '../../utilities/constants';
import CancelLink from '../CancelLink';
import RequiredFieldMarker from '../RequiredFieldMarker';
import ContactEntry from './ContactEntry';

export default function AddContact({
	accountName,
	allRoles = [],
	currentRoles = [],
	emailAddress,
	firstName,
	lastName,
	newContact,
	redirect,
	uuid
}) {
	const [contactEmailAddress, setContactEmailAddress] = useState(
		emailAddress
	);
	const [contactFirstName, setContactFirstName] = useState(firstName);
	const [contactLastName, setContactLastName] = useState(lastName);
	const [newRoles, setNewRoles] = useState(currentRoles);
	const [valid, setValid] = useState(true);

	const knownContact = !!uuid;

	const validationRoleIds = {
		partner: allRoles
			.filter(role => role.name.startsWith('Partner'))
			.map(partner => partner.key),
		support: allRoles
			.filter(
				role =>
					role.name === CONTACT_ROLE_ADMINISTRATOR ||
					role.name.startsWith('Support')
			)
			.map(support => support.key)
	};

	useEffect(() => {
		const currentSelection = Set(newRoles);

		const partnerIntersection = currentSelection.intersect(
			validationRoleIds.partner
		);
		const supportIntersection = currentSelection.intersect(
			validationRoleIds.support
		);

		if (partnerIntersection.size > 1 || supportIntersection.size > 1) {
			setValid(false);
		}
		else {
			setValid(true);
		}
	}, [newRoles, validationRoleIds.partner, validationRoleIds.support]);

	function disableSave() {
		if (
			newRoles.length > 0 &&
			contactEmailAddress &&
			(!newContact || (contactFirstName && contactLastName)) &&
			valid
		) {
			return false;
		}

		return true;
	}

	function handleAdd(key) {
		if (!newRoles.includes(key)) {
			setNewRoles([...newRoles, key]);
		}
	}

	function handleRemove(key) {
		setNewRoles(newRoles.filter(item => !item.match(key)));
	}

	return (
		<>
			<input
				name={`${NAMESPACE}addContactRoleKeys`}
				type="hidden"
				value={newRoles.join(',')}
			/>
			<input
				name={`${NAMESPACE}deleteContactRoleKeys`}
				type="hidden"
				value={allRoles
					.map(item => item.key)
					.filter(key => !newRoles.includes(key))
					.join(',')}
			/>

			{!valid && (
				<ClayAlert
					displayType="danger"
					title={Liferay.Language.get('overlapping-roles')}
				>
					{Liferay.Language.get(
						'please-only-select-one-support-role-or-one-partner-role'
					)}
				</ClayAlert>
			)}

			<table className="table table-autofit table-list table-nowrap">
				<thead>
					<tr>
						{(knownContact || newContact) && (
							<>
								<th className="table-cell-expand">
									<span className="text-truncate-inline">
										<span className="text-secondary text-truncate">
											{Liferay.Language.get('first-name')}
											{!knownContact && (
												<RequiredFieldMarker />
											)}
										</span>
									</span>
								</th>
								<th className="table-cell-expand">
									<span className="text-truncate-inline">
										<span className="text-secondary text-truncate">
											{Liferay.Language.get('last-name')}
											{!knownContact && (
												<RequiredFieldMarker />
											)}
										</span>
									</span>
								</th>
							</>
						)}
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('email')}
									{!knownContact && <RequiredFieldMarker />}
								</span>
							</span>
						</th>
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('roles')}
									<RequiredFieldMarker />
								</span>
							</span>
						</th>
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('account')}
								</span>
							</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<ContactEntry
						accountName={accountName}
						addFn={handleAdd}
						allRoles={allRoles}
						emailAddress={contactEmailAddress}
						firstName={contactFirstName}
						knownContact={knownContact}
						lastName={contactLastName}
						newContact={newContact}
						newRoles={newRoles}
						removeFn={handleRemove}
						setEmailAddress={setContactEmailAddress}
						setFirstName={setContactFirstName}
						setLastName={setContactLastName}
						uuid={uuid}
					/>
				</tbody>
			</table>

			<div className="button-holder button-holder-lg" role="group">
				<button
					className="btn btn-primary save-btn"
					disabled={disableSave()}
					role="button"
					type="submit"
				>
					{Liferay.Language.get('save')}
				</button>

				<CancelLink redirect={redirect} />
			</div>
		</>
	);
}

AddContact.propTypes = {
	accountName: PropTypes.string,
	allRoles: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	currentRoles: PropTypes.arrayOf(PropTypes.string),
	emailAddress: PropTypes.string,
	firstName: PropTypes.string,
	lastName: PropTypes.string,
	newContact: PropTypes.bool,
	redirect: PropTypes.string,
	uuid: PropTypes.string
};
