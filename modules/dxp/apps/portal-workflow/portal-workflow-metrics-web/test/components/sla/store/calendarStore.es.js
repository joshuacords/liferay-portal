/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {CalendarStore} from '../../../../src/main/resources/META-INF/resources/js/components/sla/store/calendarStore.es';
import client from '../../../mock/fetch.es';

test('Should fetch calendars', () => {
	const data = {
		items: [
			{
				defaultCalendar: true,
				key: 'working-hours',
				title: 'Working Hours'
			}
		]
	};

	const calendarStore = new CalendarStore(client(data));

	return calendarStore.fetchCalendars().then(() => {
		expect(calendarStore.getState().calendars).toMatchObject(data.items);
	});
});

test('Should get default calendar', () => {
	const calendarStore = new CalendarStore(client());

	const calendars = [
		{
			defaultCalendar: true,
			key: 'full-hours',
			title: 'Full Hours'
		},
		{
			defaultCalendar: false,
			key: 'working-hours',
			title: 'Working Hours'
		}
	];

	calendarStore.setState({calendars});

	expect(calendarStore.defaultCalendar).toMatchObject(calendars[0]);
});

test('Should init with default state', () => {
	const calendarStore = new CalendarStore(client());

	const defaultState = {
		calendars: []
	};

	expect(calendarStore.getState()).toMatchObject(defaultState);
});

test('Should return empty json when there is no default calendar', () => {
	const calendarStore = new CalendarStore(client());

	const calendars = [
		{
			defaultCalendar: false,
			key: 'working-hours',
			title: 'Working Hours'
		}
	];

	calendarStore.setState({calendars});

	expect(calendarStore.defaultCalendar).toEqual({});
});

test('Should set state', () => {
	const calendarStore = new CalendarStore(client());

	const newState = {
		calendars: [
			{
				defaultCalendar: true,
				key: 'working-hours',
				title: 'Working Hours'
			}
		]
	};

	calendarStore.setState(newState);

	expect(calendarStore.getState()).toMatchObject(newState);
});
