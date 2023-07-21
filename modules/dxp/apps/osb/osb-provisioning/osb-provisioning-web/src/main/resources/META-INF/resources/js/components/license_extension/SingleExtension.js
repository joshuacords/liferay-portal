/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import TableDivider from '../TableDivider';
import ExtensionDetails from './ExtensionDetails';

export default function SingleExtension({extensionURL, licenses}) {
	return (
		<>
			{licenses[0].indefinite && (
				<tbody>
					<TableDivider
						colSpan={8}
						title={Liferay.Language.get('permanent-licenses')}
					/>
				</tbody>
			)}

			<ExtensionDetails extensionURL={extensionURL} licenses={licenses} />
		</>
	);
}

SingleExtension.propTypes = {
	extensionURL: PropTypes.string.isRequired,
	licenses: PropTypes.array.isRequired
};
