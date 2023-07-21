/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CURRENT_TIME, RESTRICTED_EXPIRATION_DATE_TYPES} from './constants';
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
	else {
		expirationDate = generateNewDateByDay(expirationDate, 150);
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
 * midnight if the license is a restricted type, or 1 year from the start date
 * if the license is an unrestricted type.
 * @param {string} type The license type
 * @returns {Object} An object of dates representing the start and expiration
 * dates of a detached license.
 */
export function getDetachedLicenseDates(type) {
	const restricted = RESTRICTED_EXPIRATION_DATE_TYPES.find(
		restrictedType => restrictedType === type
	);

	if (restricted) {
		return {
			licenseExpirationDate: generateNewDateByYear(CURRENT_TIME),
			licenseStartDate: CURRENT_TIME
		};
	}

	return {
		licenseExpirationDate: generateNewDateByYear(CURRENT_TIME, 100),
		licenseStartDate: CURRENT_TIME
	};
}
