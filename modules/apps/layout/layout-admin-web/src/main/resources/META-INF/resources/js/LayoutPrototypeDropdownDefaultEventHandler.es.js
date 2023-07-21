/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class LayoutPrototypeDropdownDefaultEventHandler extends DefaultEventHandler {
	deleteLayoutPrototype(itemData) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			this._send(itemData.deleteLayoutPrototypeURL);
		}
	}

	exportLayoutPrototype(itemData) {
		this._openWindow('export', itemData.exportLayoutPrototypeURL);
	}

	importLayoutPrototype(itemData) {
		this._openWindow('import', itemData.importLayoutPrototypeURL);
	}

	permissionsLayoutPrototype(itemData) {
		this._openWindow('permissions', itemData.permissionsLayoutPrototypeURL);
	}

	_openWindow(label, url) {
		Liferay.Util.openWindow({
			dialog: {
				destroyOnHide: true,
				modal: true
			},
			dialogIframe: {
				bodyCssClass: 'dialog-with-footer'
			},
			title: Liferay.Language.get(label),
			uri: url
		});
	}

	_send(url) {
		submitForm(document.hrefFm, url);
	}
}

export default LayoutPrototypeDropdownDefaultEventHandler;
