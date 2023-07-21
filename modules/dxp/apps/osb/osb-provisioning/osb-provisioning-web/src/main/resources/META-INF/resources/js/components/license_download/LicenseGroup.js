/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React from 'react';

import {useLicenses} from '../../hooks/licenses';
import {NAMESPACE} from '../../utilities/constants';
import IconButton from '../IconButton';

function LicenseGroup({downloadURL, items}) {
	const [licenses] = useLicenses();
	const disableDelete = licenses.size === 1;

	return items.map((group, index) => {
		let value = [];

		return (
			<ClayTable.Body key={index}>
				{group.map(item => {
					value = [...value, item.licenseKeyId];

					return (
						<License
							disableDelete={disableDelete}
							key={item.licenseKeyId}
							license={item}
						/>
					);
				})}
				<Download actionURL={downloadURL} value={value.join()} />
			</ClayTable.Body>
		);
	});
}

function Download({actionURL, value}) {
	return (
		<ClayTable.Row>
			<ClayTable.Cell>
				<form action={actionURL} method="post" name="downloadLicenses">
					<input
						name={`${NAMESPACE}licenseKeyIds`}
						type="hidden"
						value={value}
					/>
					<button className="btn btn-secondary btn-sm" type="submit">
						{Liferay.Language.get('download')}
					</button>
				</form>
			</ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
		</ClayTable.Row>
	);
}

function License({disableDelete, license}) {
	const [, {removeLicense}] = useLicenses();

	const {
		active,
		description,
		expirationDate,
		hostName,
		ipAddresses,
		licenseEntryDisplayName,
		licenseKeyId,
		macAddresses,
		name,
		productName,
		productVersion,
		startDate
	} = license;

	function handleDeleteLicense(event) {
		const {currentTarget} = event;

		const ancestor = currentTarget.closest('tr');

		if (ancestor) {
			removeLicense(ancestor.id);
		}
	}

	return (
		<ClayTable.Row id={licenseKeyId}>
			<ClayTable.Cell className="semi-bold">
				{name}
				<div className="secondary-information">{description}</div>
			</ClayTable.Cell>
			<ClayTable.Cell>{productName}</ClayTable.Cell>
			<ClayTable.Cell>{productVersion}</ClayTable.Cell>
			<ClayTable.Cell>{licenseEntryDisplayName}</ClayTable.Cell>
			<ClayTable.Cell>{startDate}</ClayTable.Cell>
			<ClayTable.Cell>{expirationDate}</ClayTable.Cell>
			<ClayTable.Cell>{hostName}</ClayTable.Cell>
			<ClayTable.Cell>{ipAddresses}</ClayTable.Cell>
			<ClayTable.Cell>{macAddresses}</ClayTable.Cell>
			<ClayTable.Cell>
				<Status active={active} expirationDate={expirationDate} />
			</ClayTable.Cell>
			<ClayTable.Cell>
				<IconButton
					cssClass="btn-icon btn-sm"
					disabled={disableDelete}
					labelName={Liferay.Language.get('delete-license-icon')}
					onClick={handleDeleteLicense}
					svgId="#delete-icon"
					title={Liferay.Language.get('delete')}
				/>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

function Status({active, expirationDate}) {
	if (active) {
		const expired = new Date(expirationDate) < new Date();

		if (expired) {
			return (
				<span className="label label-warning">
					{Liferay.Language.get('expired')}
				</span>
			);
		}

		return (
			<span className="label label-success">
				{Liferay.Language.get('active')}
			</span>
		);
	}

	return (
		<span className="label label-danger">
			{Liferay.Language.get('deactivated')}
		</span>
	);
}

LicenseGroup.propTypes = {
	downloadURL: PropTypes.string.isRequired,
	items: PropTypes.arrayOf(
		PropTypes.arrayOf(
			PropTypes.shape({
				active: PropTypes.bool,
				description: PropTypes.string,
				expirationDate: PropTypes.string,
				hostName: PropTypes.string,
				ipAddresses: PropTypes.string,
				licenseEntryDisplayName: PropTypes.string,
				licenseEntryName: PropTypes.string,
				licenseEntryType: PropTypes.string,
				licenseKeyId: PropTypes.string,
				licenseVersion: PropTypes.number,
				macAddresses: PropTypes.string,
				name: PropTypes.string,
				productId: PropTypes.string,
				productName: PropTypes.string,
				productVersion: PropTypes.string,
				startDate: PropTypes.string
			})
		)
	).isRequired
};

export default LicenseGroup;
