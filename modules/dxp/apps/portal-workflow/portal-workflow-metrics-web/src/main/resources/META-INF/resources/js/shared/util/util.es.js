/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Returns the percent number passing as
 * parameter the current number and total number.
 * @param {number} number1
 * @param {number} number2
 * @returns {number}
 */
const getPercentage = (number1, number2) => {
	const result = number1 / number2;

	return isValidNumber(result) ? result : 0;
};

/**
 * Return true if number is valid
 * @param {number} number
 */
const isValidNumber = number => {
	return !isNaN(number) && number !== Infinity ? true : false;
};

export {getPercentage, isValidNumber};
