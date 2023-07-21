/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class SynonymSetsDropdownDefaultEventHandler extends DefaultEventHandler {
	delete(itemData) {
		const message = Liferay.Language.get(
			'are-you-sure-you-want-to-delete-this'
		);

		if (confirm(message)) {
			this._send(itemData.deleteURL);
		}
	}

	_send(url) {
		submitForm(document.hrefFm, url);
	}
}

export default SynonymSetsDropdownDefaultEventHandler;
