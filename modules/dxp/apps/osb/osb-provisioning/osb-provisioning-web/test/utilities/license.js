/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CURRENT_TIME} from '../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	formatDate,
	generateNewDateByDay,
	generateNewDateByYear
} from '../../src/main/resources/META-INF/resources/js/utilities/date';
import {
	deriveLicenseDates,
	getDetachedLicenseDates
} from '../../src/main/resources/META-INF/resources/js/utilities/license';

const ALLOW_PERMANENT_LICENSES = true;

const license = {
	endDate: '2020-04-16',
	perpetual: false,
	startDate: '2020-03-17'
};

const perpetualLicense = {
	endDate: '',
	perpetual: true,
	startDate: ''
};

describe('Dates for license associated with a Subscription', () => {
	describe('Perpetual Subscription', () => {
		it('displays the Start Date as Today at midnight', () => {
			const dates = deriveLicenseDates(
				perpetualLicense,
				'developer',
				ALLOW_PERMANENT_LICENSES
			);

			expect(formatDate(dates.licenseStartDate)).toMatch(
				formatDate(CURRENT_TIME)
			);
		});

		describe('when Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster ', () => {
			describe('when Permanent Licenses are allowed', () => {
				it('displays the Expiration Date as 100 years from Today at midnight', () => {
					const dates = deriveLicenseDates(
						perpetualLicense,
						'developer',
						ALLOW_PERMANENT_LICENSES
					);
					const {
						licenseExpirationDate: expirationDate,
						licenseStartDate: startDate
					} = dates;

					const derivedEXpirationDate = generateNewDateByYear(
						startDate,
						100
					);

					expect(derivedEXpirationDate).toStrictEqual(expirationDate);
				});
			});

			describe('when Permanent Licenses are not allowed', () => {
				it('displays the Expiration Date as 395 days (365 days + 30 days of grace period) from Today at midnight', () => {
					const dates = deriveLicenseDates(
						perpetualLicense,
						'developer',
						!ALLOW_PERMANENT_LICENSES
					);
					const {
						licenseExpirationDate: expirationDate,
						licenseStartDate: startDate
					} = dates;

					const derivedEXpirationDate = generateNewDateByYear(
						generateNewDateByDay(startDate)
					);

					expect(derivedEXpirationDate).toStrictEqual(expirationDate);
				});
			});
		});

		describe('when Type is Enterpirse, Limited, OEM, or Virtual Cluster', () => {
			it('displays the Expiration Date as 395 days (365 days + 30 days of grace period) from Today at midnight', () => {
				const dates = deriveLicenseDates(
					perpetualLicense,
					'oem',
					ALLOW_PERMANENT_LICENSES
				);
				const {
					licenseExpirationDate: expirationDate,
					licenseStartDate: startDate
				} = dates;

				const derivedEXpirationDate = generateNewDateByYear(
					generateNewDateByDay(startDate)
				);

				expect(derivedEXpirationDate).toStrictEqual(expirationDate);
			});
		});
	});

	describe('for a non Perpetual Subscription', () => {
		it('displays the license Start Date as the subscription start date', () => {
			const dates = deriveLicenseDates(
				license,
				'developer',
				ALLOW_PERMANENT_LICENSES
			);

			expect(formatDate(dates.licenseStartDate)).toMatch('2020-03-17');
		});

		describe('when Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster', () => {
			describe('when Permanent Licenses are allowed', () => {
				it('displays the Expiration Date as 100 years from the grace period end date', () => {
					const dates = deriveLicenseDates(
						license,
						'developer',
						ALLOW_PERMANENT_LICENSES
					);

					expect(formatDate(dates.licenseExpirationDate)).toMatch(
						'2120-03-23'
					);
				});
			});

			describe('when Permanent Licenses are not allowed', () => {
				it('displays the Expiration Date as the grace period end date', () => {
					const dates = deriveLicenseDates(
						license,
						'developer',
						!ALLOW_PERMANENT_LICENSES
					);

					expect(formatDate(dates.licenseExpirationDate)).toMatch(
						'2020-09-13'
					);
				});
			});
		});

		describe('when Type is Enterpirse, Limited, OEM, or Virtual Cluster', () => {
			it('displays the Expiration Date as the grace period end date', () => {
				const dates = deriveLicenseDates(
					license,
					'oem',
					ALLOW_PERMANENT_LICENSES
				);

				expect(formatDate(dates.licenseExpirationDate)).toMatch(
					'2020-09-13'
				);
			});
		});
	});
});

describe('Dates for Detached licenses', () => {
	it('displays Start Date as Today at midnight', () => {
		const dates = getDetachedLicenseDates();

		expect(formatDate(dates.licenseStartDate)).toMatch(
			formatDate(CURRENT_TIME)
		);
	});

	it('displays Expiration Date as 100 years after the Start Date', () => {
		const dates = getDetachedLicenseDates();
		const {
			licenseExpirationDate: expirationDate,
			licenseStartDate: startDate
		} = dates;

		const derivedExpirationDate = generateNewDateByYear(startDate, 100);

		expect(derivedExpirationDate).toStrictEqual(expirationDate);
	});
});
