/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import client from '../../../shared/rest/fetch.es';

class CalendarStore {
	constructor(client) {
		this.client = client;
		this.state = {
			calendars: []
		};
	}

	fetchCalendars() {
		return this.client.get('/calendars').then(({data}) =>
			this.setState({
				calendars: data.items
			})
		);
	}

	get defaultCalendar() {
		const defaultCalendars = this.state.calendars.filter(
			calendar => calendar.defaultCalendar
		);

		return defaultCalendars.length ? defaultCalendars[0] : {};
	}

	getState() {
		return this.state;
	}

	setState(props) {
		this.state = {...this.getState(), ...props};
	}
}

export default new CalendarStore(client);
export {CalendarStore};
