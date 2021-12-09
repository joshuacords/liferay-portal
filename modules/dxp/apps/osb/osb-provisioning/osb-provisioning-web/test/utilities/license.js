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

import {
	formatDate,
	generateNewDateByDay,
	generateNewDateByYear
} from '../../src/main/resources/META-INF/resources/js/utilities/date';
import {
	deriveLicenseDates,
	getDetachedLicenseDates
} from '../../src/main/resources/META-INF/resources/js/utilities/license';

const TODAY = new Date();
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
		it('displays the Start Date as Today in UTC', () => {
			const dates = deriveLicenseDates(
				perpetualLicense,
				'developer',
				ALLOW_PERMANENT_LICENSES
			);

			expect(formatDate(dates.licenseStartDate)).toMatch(
				formatDate(TODAY)
			);
		});

		describe('when Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster ', () => {
			describe('when Permanent Licenses are allowed', () => {
				it('displays the Expiration Date as 100 years from Today in UTC', () => {
					const dates = deriveLicenseDates(
						perpetualLicense,
						'developer',
						ALLOW_PERMANENT_LICENSES
					);
					const {
						licenseExpirationDate: expirationDate,
						licenseStartDate: startDate
					} = dates;

					expect(
						expirationDate.getFullYear() - startDate.getFullYear()
					).toBe(100);
				});
			});

			describe('when Permanent Licenses are not allowed', () => {
				it('displays the Expiration Date as 395 days (365 days + 30 days of grace period) from Today in UTC', () => {
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
			it('displays the Expiration Date as 395 days (365 days + 30 days of grace period) from Today in UTC', () => {
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
						'2020-04-16'
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
					'2020-04-16'
				);
			});
		});
	});
});

describe('Dates for Detached licenses', () => {
	it('displays Start Date as Today in UTC', () => {
		const dates = getDetachedLicenseDates();

		expect(formatDate(dates.licenseStartDate)).toMatch(formatDate(TODAY));
	});

	it('displays Expiration Date as one year after the Start Date', () => {
		const dates = getDetachedLicenseDates();
		const {
			licenseExpirationDate: expirationDate,
			licenseStartDate: startDate
		} = dates;

		expect(expirationDate.getFullYear() - startDate.getFullYear()).toBe(1);
	});
});
