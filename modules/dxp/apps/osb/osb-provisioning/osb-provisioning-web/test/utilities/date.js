/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CURRENT_TIME} from '../../src/main/resources/META-INF/resources/js/utilities/constants';
import {
	formatDate,
	generateNewDateByDay,
	generateNewDateByYear,
	getIntervalInDays
} from '../../src/main/resources/META-INF/resources/js/utilities/date';

describe('generateNewDateByDay', () => {
	it('generates 30 days from now correctly', () => {
		expect(formatDate(generateNewDateByDay('2021-05-19'))).toBe(
			'2021-06-18'
		);
	});
});

describe('generateNewDateByYear', () => {
	it('generates a year from now correctly', () => {
		expect(formatDate(generateNewDateByYear('2021-05-19'))).toBe(
			'2022-05-19'
		);
	});

	it('generates a new date where the offset includes a leap year correctly', () => {
		expect(formatDate(generateNewDateByYear('2021-05-19', 4))).toBe(
			'2025-05-18'
		);
	});
});

describe('getIntervalInDays', () => {
	it('calculates the interval between two dates correctly', () => {
		expect(getIntervalInDays(CURRENT_TIME, generateNewDateByDay())).toBe(
			30
		);
	});

	it('calculates the duration between two string representation of dates correctly', () => {
		expect(getIntervalInDays('2021-01-01', '2021-01-02')).toBe(1);
	});
});
