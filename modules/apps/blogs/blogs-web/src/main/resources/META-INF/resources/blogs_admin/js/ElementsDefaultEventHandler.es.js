/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';
import {Config} from 'metal-state';

class ElementsDefaultEventHandler extends DefaultEventHandler {
	delete(itemData) {
		const message = Liferay.Language.get(
			'are-you-sure-you-want-to-delete-this'
		);

		if (this.trashEnabled || confirm(message)) {
			this._send(itemData.deleteURL);
		}
	}

	permissions(itemData) {
		Liferay.Util.openWindow({
			dialog: {
				destroyOnHide: true,
				modal: true
			},
			dialogIframe: {
				bodyCssClass: 'dialog-with-footer'
			},
			title: Liferay.Language.get('permissions'),
			uri: itemData.permissionsURL
		});
	}

	publishToLive(itemData) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-publish-to-live')
			)
		) {
			this._send(itemData.publishEntryURL);
		}
	}

	_send(url) {
		submitForm(document.hrefFm, url);
	}
}

ElementsDefaultEventHandler.STATE = {
	trashEnabled: Config.bool()
};

export default ElementsDefaultEventHandler;
