/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const capitalize = text => {
	return text.charAt(0).toUpperCase() + text.slice(1);
};

const SPLIT_REGEX = /({\d+})/g;

const SPLIT_WORDS_REGEX = /({\w+})/g;

export function sub(langKey, args) {
	const keyArray = langKey.split(SPLIT_REGEX).filter(val => val.length !== 0);

	for (let i = 0; i < args.length; i++) {
		const arg = args[i];

		const indexKey = `{${i}}`;

		let argIndex = keyArray.indexOf(indexKey);

		while (argIndex >= 0) {
			keyArray.splice(argIndex, 1, arg);

			argIndex = keyArray.indexOf(indexKey);
		}
	}

	return keyArray.join('');
}

export function subWords(langKey, args) {
	const keyArray = langKey
		.split(SPLIT_WORDS_REGEX)
		.filter(val => val.length !== 0);

	Object.keys(args).forEach(arg => {
		const indexKey = `{${arg}}`;

		let argIndex = keyArray.indexOf(indexKey);

		while (argIndex >= 0) {
			keyArray.splice(argIndex, 1, args[arg]);

			argIndex = keyArray.indexOf(indexKey);
		}
	});

	return keyArray.join('');
}

const AMPERSAND_REGEX = /&/g;
const DOUBLE_QUOTE_REGEX = /"/g;
const GREATER_THAN_REGEX = />/g;
const LESS_THAN_REGEX = /</g;
const SINGLE_QUOTE_REGEX = /'/g;

export function htmlEscape(string) {
	return string
		.replace(AMPERSAND_REGEX, '&amp;')
		.replace(LESS_THAN_REGEX, '&lt;')
		.replace(GREATER_THAN_REGEX, '&gt;')
		.replace(DOUBLE_QUOTE_REGEX, '&quot;')
		.replace(SINGLE_QUOTE_REGEX, '&#39;');
}
