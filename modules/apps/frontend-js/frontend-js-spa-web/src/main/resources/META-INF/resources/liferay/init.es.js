/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

'use strict';

import {async} from 'metal';
import {match} from 'metal-dom';
import {utils, version} from 'senna';
import globals from 'senna/lib/globals/globals';

import App from './app/App.es';
import ActionURLScreen from './screen/ActionURLScreen.es';
import RenderURLScreen from './screen/RenderURLScreen.es';

/**
 * Initializes a Senna App with routes that match both ActionURLs and RenderURLs.
 * It also overrides Liferay's default Liferay.Util.submitForm to makes sure
 * forms are properly submitted using SPA.
 * @return {!App} The Senna App initialized
 */

const initSPA = function() {
	const app = new App();

	app.addRoutes([
		{
			handler: ActionURLScreen,
			path(url) {
				let match = false;

				const uri = new URL(url, window.location.origin);

				const loginRedirect = new URL(
					Liferay.SPA.loginRedirect,
					window.location.origin
				);

				const host = loginRedirect.host || window.location.host;

				if (app.isLinkSameOrigin_(host)) {
					match = uri.searchParams.get('p_p_lifecycle') === '1';
				}

				return match;
			}
		},
		{
			handler: RenderURLScreen,
			path(url) {
				let match = false;

				if (
					(url + '/').indexOf(themeDisplay.getPathMain() + '/') !== 0
				) {
					const excluded = Liferay.SPA.excludedPaths.some(
						excludedPath => url.indexOf(excludedPath) === 0
					);

					if (!excluded) {
						const uri = new URL(url, window.location.origin);

						const lifecycle = uri.searchParams.get('p_p_lifecycle');

						match = lifecycle === '0' || !lifecycle;
					}
				}

				return match;
			}
		}
	]);

	Liferay.Util.submitForm = function(form) {
		async.nextTick(() => {
			const formElement = Object.isPrototypeOf.call(
				HTMLFormElement.prototype,
				form
			)
				? form
				: form.getDOM();
			const formSelector =
				'form' + Liferay.SPA.navigationExceptionSelectors;
			const url = formElement.action;

			if (
				match(formElement, formSelector) &&
				app.canNavigate(url) &&
				formElement.method !== 'get' &&
				!app.isInPortletBlacklist(formElement)
			) {
				Liferay.Util._submitLocked = false;

				globals.capturedFormElement = formElement;

				const buttonSelector =
					'button:not([type]),button[type=submit],input[type=submit]';

				if (match(globals.document.activeElement, buttonSelector)) {
					globals.capturedFormButtonElement =
						globals.document.activeElement;
				}
				else {
					globals.capturedFormButtonElement = formElement.querySelector(
						buttonSelector
					);
				}

				app.navigate(utils.getUrlPath(url));
			}
			else {
				formElement.submit();
			}
		});
	};

	Liferay.initComponentCache();

	Liferay.SPA.app = app;
	Liferay.SPA.version = version;

	Liferay.fire('SPAReady');

	return app;
};

export default {
	init(callback) {
		if (globals.document.readyState == 'loading') {
			globals.document.addEventListener('DOMContentLoaded', () => {
				callback.call(this, initSPA());
			});
		}
		else {
			callback.call(this, initSPA());
		}
	}
};
