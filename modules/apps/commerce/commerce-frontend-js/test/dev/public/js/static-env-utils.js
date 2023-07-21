/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

window.Liferay = {
	Language: {
		get(v) {
			const charZero = v.charAt(0).toUpperCase(),
				rest = v
					.substring(1, v.length)
					.split('-')
					.join(' ');

			return `${charZero}${rest}`;
		}
	},
	ThemeDisplay: {
		getCanonicalURL: () => '/',
		getDefaultLanguageId: () => 'en_US',
		getLanguageId: () => 'it_IT',
		getPathThemeImages: () => '/assets'
	},
	component: () => {},
	detach: (name, fn) => {
		window.removeEventListener(name, fn);
	},
	fire: (name, payload) => {
		var e = document.createEvent('CustomEvent');
		e.initCustomEvent(name);
		if (payload) {
			Object.keys(payload).forEach(key => {
				e[key] = payload[key];
			});
		}
		window.dispatchEvent(e);
	},
	on: (name, fn) => {
		window.addEventListener(name, fn);
	},
	staticEnvHeaders: new Headers({
		Accept: 'application/json',
		Authorization: `Basic ${window.btoa('test@liferay.com:test')}`,
		'Content-Type': 'application/json'
	})
};
