/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CURRENT_TIME} from './constants';

/**
 * Takes the possible incoming value, date or string, and converts
 * it to a Date object. Except in the case of an empty string, in which case it
 * passes through.
 * @param {Object|string} value Date value from user input, could be a date
 * object from the dropdown date picker or string from input field.
 * @returns {Object|string} Date object or empty string.
 */
export function convertInputToDate(value) {
	return value === '' ? '' : new Date(value);
}

/**
 * Displays a date object in the MDY format.
 * @param {Object} date Date to be formatted.
 * @returns {string} String representation of the date in MDY format
 */
export function displayInMDYDateFormat(date) {
	return new Intl.DateTimeFormat('en-US', {
		day: 'numeric',
		month: 'long',
		year: 'numeric'
	}).format(date);
}

/**
 * Format s a date object into ISO 8601 format.
 * @param {Object} date Date object to be formatted.
 * @returns {string} String representation of the date in YYYY-MM-DD format
 */
export function formatDate(date) {
	return JSON.stringify(date)
		.replace(/T(.*)Z/g, '')
		.replace(/"/g, '');
}

/**
 * Generates a new date based on the starting point and the offset in days
 * indicated.
 * @param {Object|string} startDate Starting point in which to generate the new
 * date from. If invalid or missing, will default to today's date.
 * @param {number} offset Offset in days (positive or negative) from the
 * starting date.
 * @returns {Object} Date object.
 */
export function generateNewDateByDay(startDate = CURRENT_TIME, offset = 30) {
	let startDateCopy = new Date(startDate);

	if (isNaN(startDateCopy)) {
		startDateCopy = CURRENT_TIME;
	}

	const offsetInMS = offset * 1000 * 60 * 60 * 24;

	return new Date(Date.parse(startDateCopy) + offsetInMS);
}

/**
 * Generates a new date based on the starting point and the offset in years
 * indicated.
 * @param {Object|string} startDate Starting point in which to generate the new
 * date from. If invalid or missing, will default to today's date.
 * @param {number} offset Offset in years (positive or negative) from the
 * starting date.
 * @returns {Object} Date object.
 */
export function generateNewDateByYear(startDate = CURRENT_TIME, offset = 1) {
	let startDateCopy = new Date(startDate);

	if (isNaN(startDateCopy)) {
		startDateCopy = CURRENT_TIME;
	}

	const offsetInMS = offset * 1000 * 60 * 60 * 24 * 365;

	return new Date(Date.parse(startDateCopy) + offsetInMS);
}

/**
 * Calculates the interval between two dates in increments of days.
 * @param {Object|string} startDate Starting date to make the comparison.
 * @param {Object|string} endDate Ending date for making the comparison.
 * @returns {number} Number of days between start and end dates.
 */
export function getIntervalInDays(startDate, endDate) {
	const start = new Date(startDate);
	const end = new Date(endDate);

	const interval = Date.parse(end) - Date.parse(start);

	return interval / (1000 * 60 * 60 * 24);
}

/**
 * Generates a new date adjusted for UTC.
 * @param {*} value Any value intended to represent a date.
 * @returns {*} New date object adjusted for UTC or the original input value if
 * not a valid Date object.
 */
export function getUTCAdjustedDate(value) {
	if (value instanceof Date) {
		const utcAdjustedDate = new Date(value.getTime());

		utcAdjustedDate.setHours(
			utcAdjustedDate.getHours() +
				utcAdjustedDate.getTimezoneOffset() / 60
		);

		return utcAdjustedDate;
	}

	return value;
}

/**
 * Source formatter locks @clayui/date-picker at version 3.0.7, which does not
 * provide an API for disabling/enabling date picker while later versions do.
 * This helper manually disables/enables the date picker.
 * @param {string} identifier The target to disable.
 * @param {boolean} attributeValue The value, whether to disable or enable.
 */
export function setDisabledAttribute(identifier, attributeValue) {
	const dates = document.querySelectorAll(`#${identifier} .date-picker`);

	dates.forEach(date => {
		const dateBtn = date.querySelector('.date-picker-dropdown-toggle');
		const dateInput = date.querySelector('input.form-control');

		if (dateBtn && dateInput) {
			if (attributeValue) {
				dateBtn.setAttribute('disabled', attributeValue);
				dateInput.setAttribute('disabled', attributeValue);
			}
			else {
				dateBtn.removeAttribute('disabled');
				dateInput.removeAttribute('disabled');
			}
		}
	});
}

/**
 * Validates user input from the Date picker, which could be either manual
 * input (string) in the format of YYYY-MM-DD or selection through the date
 * picker (date object).
 * This helper is used to verify the user has finished inputting the expected
 * the date in the input fields before attempting to save.
 * @param {Object|string} value Date value from user input.
 * @returns {boolean}
 */
export function validateDateFieldFormat(value) {
	if (typeof value === 'string') {
		return /\d{4}-\d{2}-\d{2}/.test(value);
	}
	else {
		return true;
	}
}
