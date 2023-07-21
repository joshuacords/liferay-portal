/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class ArchivedSetuptsDropdownDefaultEventHandler extends DefaultEventHandler {
	deleteArchivedSetups(itemData) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			this._send(itemData.deleteArchivedSetupsURL);
		}
	}

	restoreArchivedSetup(itemData) {
		this._send(itemData.restoreArchivedSetupURL);
	}

	_send(url) {
		submitForm(document.hrefFm, url);
	}
}

export default ArchivedSetuptsDropdownDefaultEventHandler;
