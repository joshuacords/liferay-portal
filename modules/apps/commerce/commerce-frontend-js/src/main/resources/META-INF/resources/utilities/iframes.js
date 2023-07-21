/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {OPEN_MODAL} from './eventsDefinitions';

export const iframeHandlerModalNamespace = 'iframe-handler-modal_';
export let counter = 0;
export const iframeInitialHandlerModalId = `${iframeHandlerModalNamespace}${counter}`;

Liferay.on('endNavigate', () => {
	counter = 0;
});

export function getIframeHandlerModalId() {
	return `${iframeHandlerModalNamespace}${counter++}`;
}

export function isPageInIframe() {
	return window.location !== window.parent.location;
}

export function initializeIframeListeners() {
	Liferay.on(OPEN_MODAL, payload => {
		window.top.Liferay.fire(OPEN_MODAL, {
			...payload,
			id: iframeInitialHandlerModalId
		});
	});
}
