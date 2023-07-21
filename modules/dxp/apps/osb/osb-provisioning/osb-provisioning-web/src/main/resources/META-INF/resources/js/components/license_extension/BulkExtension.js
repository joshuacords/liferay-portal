/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {groupByAll} from '../../utilities/helpers';
import TableDivider from '../TableDivider';
import DetailsGroup from './DetailsGroup';
import ExtensionDetails from './ExtensionDetails';

export default function BulkExtension({extensionURL, licenses}) {
	const [permanent, temporary] = partition(
		licenses,
		({indefinite}) => indefinite
	);

	const permanentOrderedByProduct = permanent.length
		? groupByAll(permanent, ({productName}) => productName)
		: permanent;
	const temporaryOrderedByProduct = temporary.length
		? groupByAll(temporary, ({productName}) => productName)
		: temporary;

	return (
		<>
			{!!temporary.length &&
				temporaryOrderedByProduct.map((temp, index) => (
					<DetailsGroup
						extensionURL={extensionURL}
						key={index}
						licenses={temp}
					/>
				))}

			{!!permanent.length && (
				<>
					<tbody>
						<TableDivider
							colSpan={8}
							title={Liferay.Language.get('permanent-licenses')}
						/>
					</tbody>

					{permanentOrderedByProduct.map((perm, index) => (
						<ExtensionDetails key={index} licenses={perm} />
					))}
				</>
			)}
		</>
	);
}

BulkExtension.propTypes = {
	extensionURL: PropTypes.string.isRequired,
	licenses: PropTypes.array.isRequired
};
