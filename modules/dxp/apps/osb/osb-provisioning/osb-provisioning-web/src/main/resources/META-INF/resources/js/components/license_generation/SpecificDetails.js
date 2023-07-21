/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayAlert from '@clayui/alert';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {
	LICENSE_TYPE_CLUSTER,
	LICENSE_TYPE_DEVELOPER,
	LICENSE_TYPE_DEVELOPER_CLUSTER,
	LICENSE_TYPE_VIRTUAL_CLUSTER,
	NEW_LICENSE_DESCRIPTION_MAX_LENGTH,
	NEW_LICENSE_NAME_OWNER_MAX_LENGTH,
	NO_SERVER_ID_LICENSE_TYPES,
	PATTERN_IP_ADDRESS_V6
} from '../../utilities/constants';
import {displayInMDYDateFormat, getUTCAdjustedDate} from '../../utilities/date';
import {capitalize} from '../../utilities/helpers';
import CancelLink from '../CancelLink';
import RequiredFieldMarker from '../RequiredFieldMarker';
import GenerateButton from './GenerateButton';
import ServerIdFieldGroups from './ServerIdFieldGroups';

function SpecificDetails({addLicenseKeyURL, redirect}) {
	const [license, {updateLicense}] = useNewLicense();

	const [showIPv6Alert, setShowIPv6Alert] = useState(false);
	const [showProvisionedAlert, setShowProvisionedAlert] = useState(false);

	const {
		complimentary,
		description,
		expirationDate,
		licenseEntry,
		licenseKeysAllowed,
		licenseKeysGenerated,
		maxClusterNodes,
		maxHttpSessions,
		maxServers,
		name,
		owner,
		product,
		serverIds,
		startDate,
		version
	} = license;

	useEffect(() => {
		const ipv6Address = serverIds.find(serverId => {
			const {ipAddresses} = serverId;

			if (ipAddresses) {
				const chuncks = ipAddresses.trim().split(/\s*,\s*|\s+/);

				return chuncks.some(chunck =>
					chunck.match(PATTERN_IP_ADDRESS_V6)
				);
			}
			else {
				return false;
			}
		});

		setShowIPv6Alert(!!ipv6Address);

		const licenseKeysGeneratedTotal =
			+maxClusterNodes > 0
				? +maxClusterNodes + licenseKeysGenerated
				: serverIds.size + licenseKeysGenerated;

		if (
			licenseKeysGeneratedTotal > licenseKeysAllowed &&
			licenseKeysAllowed > 0
		) {
			setShowProvisionedAlert(true);
		}
		else {
			setShowProvisionedAlert(false);
		}
	}, [licenseKeysAllowed, licenseKeysGenerated, maxClusterNodes, serverIds]);

	function formatDate(date) {
		const utcAdjustedDate = getUTCAdjustedDate(new Date(date));

		return displayInMDYDateFormat(utcAdjustedDate);
	}

	function handleComplimentaryChange() {
		updateLicense(license =>
			license.update('complimentary', complimentary => !complimentary)
		);
	}

	function handleDescriptionChange(event) {
		updateLicense(license =>
			license.set('description', event.currentTarget.value)
		);
	}

	function handleDisplayPreviousPage() {
		updateLicense(license => license.set('showSpecificDetails', false));
	}

	function handleMaxClusterNodesChange(event) {
		updateLicense(license =>
			license.set('maxClusterNodes', event.currentTarget.value)
		);
	}

	function handleMaxServersChange(event) {
		updateLicense(license =>
			license.set('maxServers', event.currentTarget.value)
		);
	}

	function handleNameChange(event) {
		updateLicense(license =>
			license.set('name', event.currentTarget.value)
		);
	}

	function handleOwnerChange(event) {
		updateLicense(license =>
			license.set('owner', event.currentTarget.value)
		);
	}

	function isDeveloperOrDeveloperCluster() {
		return (
			licenseEntry.licenseEntryType === LICENSE_TYPE_DEVELOPER_CLUSTER ||
			licenseEntry.licenseEntryType === LICENSE_TYPE_DEVELOPER
		);
	}

	function isDisplayServerIDFields() {
		return !NO_SERVER_ID_LICENSE_TYPES.filter(
			type => type === licenseEntry.licenseEntryType
		).length;
	}

	return (
		<>
			<div className="page-steps">
				<span>{Liferay.Language.get('specific-details')}</span>

				<span>{Liferay.Language.get('step-2-of-2')}</span>
			</div>

			<div className="container-fluid-max-xl generate-license-sheet row">
				{showIPv6Alert && (
					<div className="col-md-12">
						<ClayAlert displayType="info">
							{Liferay.Language.get(
								'ipv6-addresses-in-activation-keys-are-currently-ignored-please-enter-a-hostname-or-mac-address-instead'
							)}
						</ClayAlert>
					</div>
				)}

				<div className="col-md-9 generate-license-container specific-details">
					<div className="specific-details-content">
						<h3>{Liferay.Language.get('specific-details')}</h3>

						<div className="row">
							<div className="col-md-6 form-group">
								<label htmlFor="name">
									{Liferay.Language.get('name')}
								</label>

								<CharacterLimitMessage
									message={Liferay.Language.get(
										'please-enter-no-more-than-75-characters'
									)}
								/>

								<input
									className="form-control"
									id="name"
									maxLength={
										NEW_LICENSE_NAME_OWNER_MAX_LENGTH
									}
									onChange={handleNameChange}
									type="text"
									value={name}
								/>
							</div>

							<div
								className={`col-md-6 form-group ${
									owner ? '' : 'has-error'
								}`}
							>
								<label htmlFor="owner">
									{Liferay.Language.get('owner')}{' '}
									<RequiredFieldMarker />
								</label>

								<CharacterLimitMessage
									message={Liferay.Language.get(
										'please-enter-no-more-than-75-characters'
									)}
								/>

								<input
									className="form-control"
									id="owner"
									maxLength={
										NEW_LICENSE_NAME_OWNER_MAX_LENGTH
									}
									onChange={handleOwnerChange}
									type="text"
									value={owner}
								/>
							</div>

							<div
								className={`col-md-12 form-group ${
									description ? '' : 'has-error'
								}`}
							>
								<label htmlFor="description">
									{Liferay.Language.get('description')}{' '}
									<RequiredFieldMarker />
								</label>

								<CharacterLimitMessage
									message={Liferay.Language.get(
										'please-enter-no-more-than-255-characters'
									)}
								/>

								<input
									className="form-control"
									id="description"
									maxLength={
										NEW_LICENSE_DESCRIPTION_MAX_LENGTH
									}
									onChange={handleDescriptionChange}
									type="text"
									value={description}
								/>
							</div>

							{isDisplayServerIDFields() && (
								<ServerIdFieldGroups />
							)}

							{licenseEntry.licenseEntryType ===
								LICENSE_TYPE_CLUSTER && (
								<div className="col-md-6 form-group">
									<label htmlFor="maxServers">
										{Liferay.Language.get(
											'maximum-servers'
										)}
									</label>

									<input
										className="form-control"
										id="maxServers"
										max={50}
										min={1}
										onChange={handleMaxServersChange}
										type="number"
										value={maxServers}
									/>
								</div>
							)}

							{isDeveloperOrDeveloperCluster() && (
								<div className="col-md-6 form-group">
									<label htmlFor="maxHttpSessions">
										{Liferay.Language.get(
											'maximum-connections'
										)}
									</label>

									<input
										className="form-control"
										id="maxHttpSessions"
										readOnly={true}
										type="number"
										value={maxHttpSessions}
									/>
								</div>
							)}

							{licenseEntry.licenseEntryType ===
								LICENSE_TYPE_VIRTUAL_CLUSTER && (
								<div className="col-md-6 form-group">
									<label htmlFor="maxClusterNodes">
										{Liferay.Language.get(
											'maximum-cluster-nodes'
										)}
									</label>

									<input
										className="form-control"
										id="maxClusterNodes"
										min={1}
										onChange={handleMaxClusterNodesChange}
										type="number"
										value={maxClusterNodes}
									/>
								</div>
							)}

							<div className="col-md-12 form-group">
								<label
									className="form-check-label"
									htmlFor="complimentary"
								>
									<input
										aria-checked={complimentary}
										aria-label={Liferay.Language.get(
											'complimentary'
										)}
										checked={complimentary}
										className="form-check-input"
										id="complimentary"
										onChange={handleComplimentaryChange}
										role="checkbox"
										type="checkbox"
									/>
									<span className="form-check-label-text">
										{Liferay.Language.get('complimentary')}{' '}
										<span
											title={Liferay.Language.get(
												'do-not-count-this-license-towards-the-customers-purchase'
											)}
										>
											<svg
												aria-label={Liferay.Language.get(
													'complimentary-tooltip'
												)}
												className="lexicon-icon"
												role="img"
												title={Liferay.Language.get(
													'complimentary-tooltip'
												)}
											>
												<use xlinkHref="#question-circle-full" />
											</svg>
										</span>
									</span>
								</label>
							</div>
						</div>
					</div>

					<div className="button-holder">
						<GenerateButton
							alertError={showProvisionedAlert}
							formAction={addLicenseKeyURL}
							redirect={redirect}
							serverIdValidatable={isDisplayServerIDFields()}
						/>

						<button
							className="btn btn-secondary"
							onClick={handleDisplayPreviousPage}
							type="button"
						>
							{Liferay.Language.get('previous-step')}
						</button>

						<CancelLink redirect={redirect} />
					</div>
				</div>

				<div className="additional-information col-md-3">
					<div className="specific-details-content">
						<h4>{Liferay.Language.get('general-information')}</h4>

						<dl>
							<div>
								<dt>{Liferay.Language.get('product')}</dt>
								<dd>{product.productName}</dd>
							</div>
							<div>
								<dt>{Liferay.Language.get('version')}</dt>
								<dd>{version}</dd>
							</div>
							<div>
								<dt>{Liferay.Language.get('type')}</dt>
								<dd>
									{capitalize(licenseEntry.licenseEntryType)}
								</dd>
							</div>
						</dl>

						<dl>
							<div>
								<dt>{Liferay.Language.get('start-date')}</dt>
								<dd>{formatDate(startDate)}</dd>
							</div>

							<div>
								<dt>
									{Liferay.Language.get('expiration-date')}
								</dt>
								<dd>{formatDate(expirationDate)}</dd>
							</div>
						</dl>

						<dl>
							<div>
								<dt>
									{Liferay.Language.get('licenses-generated')}
								</dt>
								<dd>
									{licenseKeysGenerated}
									{' / '}
									{licenseKeysAllowed}
								</dd>
							</div>
						</dl>
					</div>

					<div className="alert-messages-content">
						{showProvisionedAlert && (
							<ClayAlert displayType="danger">
								{Liferay.Language.get(
									'the-provisioned-keys-count-is-already-equal-to-or-higher-than-the-purchased-subscriptions'
								)}
							</ClayAlert>
						)}
					</div>
				</div>
			</div>
		</>
	);
}

SpecificDetails.propTypes = {
	addLicenseKeyURL: PropTypes.string,
	redirect: PropTypes.string
};

function CharacterLimitMessage({message}) {
	return (
		<div className="form-feedback-group">
			<div className="form-text">{message}</div>
		</div>
	);
}

export default SpecificDetails;
