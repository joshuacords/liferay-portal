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
