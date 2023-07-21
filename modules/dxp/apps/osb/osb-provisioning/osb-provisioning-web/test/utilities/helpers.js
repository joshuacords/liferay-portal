/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	groupBy,
	groupByAll,
	validateAllIPAddresses,
	validateIPv6s,
	validateMAC
} from '../../src/main/resources/META-INF/resources/js/utilities/helpers';

describe('groupBy', () => {
	it('groups the input correctly', () => {
		const data = [
			{a: 3, b: 1},
			{a: 3, b: 2}
		];

		expect(groupBy(data, item => item.a)).toEqual({
			3: [
				{a: 3, b: 1},
				{a: 3, b: 2}
			]
		});
	});
});

describe('groupByAll', () => {
	it('groups the input appropriately', () => {
		const data = [
			{a: 3, b: 2, c: 5},
			{a: 3, b: 2, c: 6},
			{a: 3, b: 1, c: 6},
			{a: 3, b: 1, c: 6},
			{a: 3, b: 1, c: 2},
			{a: 2, b: 3, c: 1, d: 10},
			{a: 2, b: 3, c: 1, d: 12}
		];

		expect(
			groupByAll(
				data,
				item => item.a,
				item => item.b,
				item => item.c
			)
		).toEqual([
			[
				{a: 2, b: 3, c: 1, d: 10},
				{a: 2, b: 3, c: 1, d: 12}
			],
			[{a: 3, b: 1, c: 2}],
			[
				{a: 3, b: 1, c: 6},
				{a: 3, b: 1, c: 6}
			],
			[{a: 3, b: 2, c: 5}],
			[{a: 3, b: 2, c: 6}]
		]);
	});
});

describe('validateAllIPAddresses', () => {
	it('validates multiple valid IPv4 addresses deliminated via comma, space, or new line', () => {
		expect(
			validateAllIPAddresses(
				`127.0.0.1,\n123.1.2.3, 192.168.1.101\n\n0.0.0.0,172.16.254.1 98.139.180.149\n,8.8.8.8\r255.255.255.0 `
			)
		).toBeTruthy();
	});

	it('validates multiple IPv4 addresses containing invalid values correctly', () => {
		expect(validateAllIPAddresses('0.0.0.0.0, 127.0.0.1')).toBeFalsy();
	});

	it('validates a single valid IPv4 address correctly', () => {
		expect(validateAllIPAddresses('127.0.0.1')).toBeTruthy();
	});

	it('validates a single invalid IPv4 address correctly', () => {
		expect(validateAllIPAddresses('257.0.0.2')).toBeFalsy();

		expect(validateAllIPAddresses('2343.22.22')).toBeFalsy();
	});

	it('validates multiple valid IP addresses containing a mixture of IPv4 and IPv6', () => {
		expect(
			validateAllIPAddresses(`127.0.0.1,\n1762:0:0:0:0:B03:1:AF18`)
		).toBeTruthy();
	});

	it('validates multiple IP addresses containing a mixture of a valid IPv4 and an invalid IPv6', () => {
		expect(
			validateAllIPAddresses(`127.0.0.1,\n1762:0:0:0:0:B03:1:AG18`)
		).toBeFalsy();
	});

	it('validates multiple IP addresses containing a mixture of an invalid IPv4 and a valid IPv6', () => {
		expect(
			validateAllIPAddresses(`127.0.0.257,\n1762:0:0:0:0:B03:1:AF18`)
		).toBeFalsy();
	});

	it('validates multiple invalid IP addresses containing a mixture of IPv4 and IPv6 addresses', () => {
		expect(
			validateAllIPAddresses(`127.0.0.257,\n1762:0:0:0:0:B03:1:AG18`)
		).toBeFalsy();
	});

	it('handles empty input', () => {
		expect(validateAllIPAddresses('')).toBeFalsy();
	});

	it('handles null input', () => {
		expect(validateAllIPAddresses(null)).toBeFalsy();
	});

	it('handles undefined input', () => {
		expect(validateAllIPAddresses(undefined)).toBeFalsy();
	});
});

describe('validateIPv6s', () => {
	it('validates a single valid IPv6 address written in standard notation correctly', () => {
		expect(
			validateIPv6s('1200:0000:AB00:1234:0000:2552:7777:1313')
		).toBeTruthy();
	});

	it('validates a single valid IPv6 address in compact notation correctly', () => {
		expect(validateIPv6s('1762:0:0:0:0:B03:1:AF18')).toBeTruthy();
	});

	it('validates a single valid IPv6 address in mixed notation correctly', () => {
		expect(validateIPv6s('FE80:0:0000:0:0202:B3FF:FE1E:8329')).toBeTruthy();
	});

	it('validates a single valid IPv6 address written in lower case correctly', () => {
		expect(validateIPv6s('1762:0:0:0:0:b03:1:af18')).toBeTruthy();
	});

	it('validates a single invalid IPv6 address correctly', () => {
		expect(
			validateIPv6s('1200:0000:AB00:1234:O000:2552:7777:1313')
		).toBeFalsy();
	});

	it('validates multiple IPv6 addresses with a mixture of valid and invalid addresses correctly', () => {
		expect(
			validateIPv6s('1762:0:0:0:0:b03:1:aF18, 1762:0:0:0:0:G03:1:AF18')
		).toBeFalsy();
	});

	it('validates multiple valid IPv6 addresses correctly', () => {
		expect(
			validateIPv6s(
				'684D:1111:222:3333:4444:5555:6:77\n1762:0:0:0:0:b03:1:aF18'
			)
		).toBeTruthy();
	});

	it('handles empty input', () => {
		expect(validateIPv6s('')).toBeFalsy();
	});

	it('handles null input', () => {
		expect(validateIPv6s(null)).toBeFalsy();
	});

	it('handles undefined input', () => {
		expect(validateIPv6s(undefined)).toBeFalsy();
	});
});

describe('validateMAC', () => {
	it('validates a MAC address seperated with two digit octets correctly', () => {
		expect(validateMAC('00:00:0A:BB:28:FC')).toBeTruthy();
		expect(validateMAC('00.00.0A.BB.28.FC')).toBeTruthy();
		expect(validateMAC('01-02-03-04-ab-cd')).toBeTruthy();
	});

	it('validates a MAC address separated with four digit octets correctly', () => {
		expect(validateMAC('0000:0ABB:28FC')).toBeTruthy();
		expect(validateMAC('0000.0ABB.28FC')).toBeTruthy();
		expect(validateMAC('0000-0ABB-28FC')).toBeTruthy();
	});

	it('validates multiple MAC address with different formats correctly', () => {
		expect(validateMAC('00:00:0A:BB:28:FC, 0000-0ABB-28FC')).toBeTruthy();
	});

	it('validates invalid MAC address correctly', () => {
		expect(validateMAC('zz-00-34-xx-35-64')).toBeFalsy();
	});

	it('handles empty input', () => {
		expect(validateMAC('')).toBeFalsy();
	});

	it('handles null input', () => {
		expect(validateMAC(null)).toBeFalsy();
	});

	it('handles undefined input', () => {
		expect(validateMAC(undefined)).toBeFalsy();
	});
});
