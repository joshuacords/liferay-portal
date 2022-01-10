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

import ClayTable from '@clayui/table';
import ClayTableCell from '@clayui/table/lib/Cell';
import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {CURRENT_TIME} from '../../utilities/constants';
import {
	deriveLicenseDates,
	getDetachedLicenseDates
} from '../../utilities/license';
import TableDivider from '../TableDivider';
import Purchase from './Purchase';

function Purchases({detached, purchased}) {
	return (
		<div className="choose-purchase">
			<h4>{Liferay.Language.get('choose-purchase')}</h4>

			<ClayTable>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTableCell headingCell>
							{Liferay.Language.get('start-date')}
						</ClayTableCell>
						<ClayTableCell headingCell>
							{Liferay.Language.get('expiration-date')}
						</ClayTableCell>
						<ClayTableCell className="field-required" headingCell>
							{Liferay.Language.get('instance-size')}
						</ClayTableCell>
						<ClayTableCell headingCell>
							{Liferay.Language.get('licenses-generated')}
						</ClayTableCell>
						<ClayTableCell headingCell>{''}</ClayTableCell>
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>
					<Purchased purchased={purchased} />
					<Detached detached={detached} />
				</ClayTable.Body>
			</ClayTable>
		</div>
	);
}

function Detached({detached}) {
	let licenseDates = {};

	if (detached) {
		licenseDates = getDetachedLicenseDates();
	}

	return (
		<>
			<TableDivider
				colSpan={5}
				title={Liferay.Language.get('detached')}
			/>

			<Purchase detached={true} {...detached} {...licenseDates} />
		</>
	);
}

function Purchased({purchased}) {
	const [{allowPermanentLicenses, licenseEntry}] = useNewLicense();

	const processedPurchased = purchased
		? purchased.map(item => {
				return {
					...item,
					...deriveLicenseDates(
						item,
						licenseEntry.licenseEntryType,
						allowPermanentLicenses
					),
					expired: getExpired(item)
				};
		  })
		: [];
	const [active, expired] = partition(
		processedPurchased,
		({expired}) => !expired
	);

	function getExpired(item) {
		if (item.perpetual) {
			return false;
		}

		return new Date(item.endDate) < CURRENT_TIME;
	}

	return (
		<>
			{!!active.length && (
				<>
					<TableDivider
						colSpan={5}
						title={Liferay.Language.get('active-subscriptions')}
					/>

					{active.map((item, index) => (
						<Purchase
							key={item.productPurchaseKey || index}
							{...item}
						/>
					))}
				</>
			)}

			{!!expired.length && (
				<>
					<TableDivider
						colSpan={5}
						title={Liferay.Language.get('expired-subscriptions')}
					/>

					{expired.map((item, index) => (
						<Purchase
							key={item.productPurchaseKey || index}
							{...item}
						/>
					))}
				</>
			)}
		</>
	);
}

Purchases.protoType = {
	detached: PropTypes.shape({
		instanceSize: PropTypes.arrayOf(PropTypes.number),
		licenseKeysGenerated: PropTypes.string,
		startDate: PropTypes.string
	}),
	purchased: PropTypes.arrayOf(
		PropTypes.shape({
			endDate: PropTypes.string,
			licenseKeysGenerated: PropTypes.string,
			perpetual: PropTypes.bool,
			productPurchaseKey: PropTypes.string,
			sizing: PropTypes.number,
			startDate: PropTypes.string
		})
	)
};

export default Purchases;
