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

import flatten from 'lodash.flatten';
import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {useLicenses} from '../../hooks/licenses';
import {PRODUCT_ID_COMMERCE} from '../../utilities/constants';
import {groupBy, groupByAll} from '../../utilities/helpers';
import TableDivider from '../TableDivider';
import LicenseGroup from './LicenseGroup';

const COMMERCE_LICENSE_VERSION = 3;
const DXP_LICENSE_VERSION = 5;

function CombinedLicenses({downloadURL}) {
	const [licenses] = useLicenses();

	let combinedLicenses = [];

	const [activeCommerceDXPLicenses] = partition(
		licenses.toSet().toJS(),
		({active, licenseVersion, productId}) =>
			(licenseVersion >= DXP_LICENSE_VERSION ||
				(licenseVersion === COMMERCE_LICENSE_VERSION &&
					productId === PRODUCT_ID_COMMERCE)) &&
			active
	);

	const intersection = activeCommerceDXPLicenses.length
		? groupByAll(
				activeCommerceDXPLicenses,
				({startDate}) => startDate,
				({expirationDate}) => expirationDate,
				({sizing}) => sizing
		  )
		: [];

	const groupByProduct = intersection.length
		? intersection.map(subgroup =>
				partition(
					subgroup,
					({licenseVersion}) => licenseVersion >= DXP_LICENSE_VERSION
				)
		  )
		: [];

	groupByProduct.forEach(([dxp, commerce]) => {
		if (dxp.length && commerce.length) {
			const transformedCommerceGrouping = transformByGroupLength(
				groupByProductSpecificProperties(commerce)
			);
			const transformedDXPGrouping = transformByGroupLength(
				groupByProductSpecificProperties(dxp)
			);

			combineLicenses(
				transformedCommerceGrouping,
				transformedDXPGrouping
			);
		}
	});

	function combineLicenses(transform1, transform2) {
		const transformedIntersection = getTransformedIntersection(
			transform1,
			transform2
		);

		// Suppress eslint false alarm for unused var
		/* eslint-disable no-unused-vars */

		/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
		for (const value of transformedIntersection.values()) {
			combinedLicenses = [
				...combinedLicenses,
				[...flatten(transform1[value]), ...flatten(transform2[value])]
			];
		}
		/* eslint-enable no-unused-vars */
	}

	function getTransformedIntersection(transform1, transform2) {
		const set1 = new Set(Object.keys(transform1));
		const set2 = new Set(Object.keys(transform2));

		return new Set([...set1].filter(val => set2.has(val)));
	}

	function groupByProductSpecificProperties(licenses) {
		return groupByAll(
			licenses,
			({licenseEntryType}) => licenseEntryType,
			({productVersion}) => productVersion
		);
	}

	function transformByGroupLength(licenses) {
		return groupBy(licenses, license => license.length);
	}

	return (
		<>
			{!!combinedLicenses.length && (
				<>
					<tbody>
						<TableDivider
							colSpan={11}
							title={Liferay.Language.get(
								'dxp-commerce-combined-licenses'
							)}
						/>
					</tbody>

					<LicenseGroup
						downloadURL={downloadURL}
						items={combinedLicenses}
					/>
				</>
			)}
		</>
	);
}

CombinedLicenses.propTypes = {
	downloadURL: PropTypes.string.isRequired
};

export default CombinedLicenses;
