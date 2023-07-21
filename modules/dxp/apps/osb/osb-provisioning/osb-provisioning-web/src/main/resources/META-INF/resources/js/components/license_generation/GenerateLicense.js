/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {NewLicenseProvider, useNewLicense} from '../../hooks/newLicense';
import {PermissionsProvider} from '../../hooks/permissions';
import GeneralInformation from './GeneralInformation';
import SpecificDetails from './SpecificDetails';

function GenerateLicense(props) {
	const {allowPermanentLicenses, hasUpdateLicenseDatePermission} = props;

	return (
		<NewLicenseProvider initialLicense={{allowPermanentLicenses}}>
			<PermissionsProvider
				permissions={{
					updateDatePermission: hasUpdateLicenseDatePermission
				}}
			>
				<Generate {...props} />
			</PermissionsProvider>
		</NewLicenseProvider>
	);
}

function Generate(props) {
	const [license] = useNewLicense();

	return (
		<>
			{!license.showSpecificDetails && <GeneralInformation {...props} />}

			{license.showSpecificDetails && (
				<SpecificDetails
					addLicenseKeyURL={props.addLicenseKeyURL}
					redirect={props.redirect}
				/>
			)}
		</>
	);
}

GenerateLicense.propTypes = {
	allowPermanentLicenses: PropTypes.bool,
	hasUpdateLicenseDatePermission: PropTypes.bool.isRequired
};

export default GenerateLicense;
