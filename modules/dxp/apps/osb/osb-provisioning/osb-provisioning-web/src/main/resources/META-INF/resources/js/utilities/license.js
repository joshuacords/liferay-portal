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

import {RESTRICTED_EXPIRATION_DATE_TYPES, CURRENT_TIME} from './constants';
import {generateNewDateByDay, generateNewDateByYear} from './date';

/**
 * Generates the start and expiration dates for a license associated with a
 * purchased product. The dates are determined by the type of the subscription
 * (perpetual or not) and the type of the license (one of the limited access
 * types or not).
 * @param {object} license The license object that contains properties such as
 * start date, end date, and whether it's perpetual.
 * @param {string} type The license type
 * @param {boolean} allowPermanentLicenses The property on the Account that
 * flags a special agreement.
 * @returns {Object} An object of dates representing the start and expiration
 * dates of a detached license.
 */
export function deriveLicenseDates(
	license,
	type,
	allowPermanentLicenses = true
) {
	const restricted = RESTRICTED_EXPIRATION_DATE_TYPES.find(
		restrictedType => restrictedType === type
	);
	const isUnrestrictedPermanentLicenseType =
		!restricted && allowPermanentLicenses;

	if (license.perpetual) {
		let expirationDate = generateNewDateByDay(generateNewDateByYear());

		if (isUnrestrictedPermanentLicenseType) {
			expirationDate = generateNewDateByYear(CURRENT_TIME, 100);
		}

		return {
			licenseExpirationDate: expirationDate,
			licenseStartDate: CURRENT_TIME
		};
	}

	let expirationDate = new Date(license.endDate);

	if (isUnrestrictedPermanentLicenseType) {
		expirationDate = generateNewDateByYear(expirationDate, 100);
	}

	return {
		licenseExpirationDate: expirationDate,
		licenseStartDate: new Date(license.startDate)
	};
}

/**
 * Generates the start and expiration dates for a detached license (license not
 * associated with any purchased product). The start date should always be the
 * current date at midnight to match the selection results from the date
 * picker. The expiration date should be 365 days from the start date at
 * midnight.
 * @returns {Object} An object of dates representing the start and expiration
 * dates of a detached license.
 */
export function getDetachedLicenseDates() {
	return {
		licenseExpirationDate: generateNewDateByYear(CURRENT_TIME),
		licenseStartDate: CURRENT_TIME
	};
}
