/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-calendar-session-listener',
	A => {
		var CalendarSessionListener = A.Component.create({
			ATTRS: {
				calendars: {
					value: []
				},

				scheduler: {
					value: null
				}
			},

			NAME: 'calendar-session-listener',

			prototype: {
				_disableCalendars() {
					var instance = this;

					var calendars = instance.get('calendars');

					A.Object.each(calendars, calendar => {
						var permissions = calendar.get('permissions');

						permissions.DELETE = false;
						permissions.MANAGE_BOOKINGS = false;
						permissions.UPDATE = false;
						permissions.PERMISSIONS = false;
					});
				},

				_disableEvents() {
					var instance = this;

					var scheduler = instance.get('scheduler');

					scheduler.getEvents().forEach(event => {
						event.set('disabled', true);
					});
				},

				_disableScheduler() {
					var instance = this;

					var addEventButtons = A.all('.calendar-add-event-btn');

					var scheduler = instance.get('scheduler');

					addEventButtons.set('disabled', true);

					scheduler.set('eventRecorder', null);
				},

				_onSessionExpired() {
					var instance = this;

					instance._disableCalendars();

					instance._disableScheduler();

					instance._disableEvents();
				},

				initializer() {
					var instance = this;

					Liferay.on(
						'sessionExpired',
						A.bind(instance._onSessionExpired, instance)
					);
				}
			}
		});

		Liferay.CalendarSessionListener = CalendarSessionListener;
	},
	'',
	{
		requires: ['aui-base', 'aui-component']
	}
);
