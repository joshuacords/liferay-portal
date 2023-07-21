/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import moment from 'moment';

export function durationAsMilliseconds(days, fullHours) {
	const [hours, minutes] = fullHours.split(':');

	return moment
		.duration({
			days,
			hours,
			minutes
		})
		.asMilliseconds();
}

export function formatDuration(millisecondsDuration) {
	const duration = getDurationValues(millisecondsDuration);

	const durationParts = [
		{
			label: Liferay.Language.get('days-abbreviation'),
			value: duration.days
		},
		{
			label: Liferay.Language.get('hours-abbreviation'),
			value: duration.hours
		},
		{
			label: Liferay.Language.get('minutes-abbreviation'),
			value: duration.minutes
		}
	];

	return durationParts
		.filter(part => part.value > 0)
		.map(part => `${part.value}${part.label}`)
		.join(' ');
}

export function formatHours(hours, minutes) {
	const padHours = value =>
		(value && value.toString().padStart(2, '0')) || '00';

	if (hours || minutes) {
		return [hours, minutes].map(padHours).join(':');
	}

	return '';
}

export function getDurationValues(durationValue) {
	const fullDuration = moment.duration(durationValue);

	return {
		// eslint-disable-next-line radix
		days: parseInt(fullDuration.asDays()) || null,
		hours: fullDuration.hours() || null,
		minutes: fullDuration.minutes() || null
	};
}
