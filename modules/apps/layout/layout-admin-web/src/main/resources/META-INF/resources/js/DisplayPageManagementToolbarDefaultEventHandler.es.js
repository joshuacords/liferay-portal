/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';
import {Config} from 'metal-state';

import {openDisplayPageModal} from './modal/openDisplayPageModal.es';

class DisplayPageManagementToolbarDefaultEventHandler extends DefaultEventHandler {
	addDisplayPage(itemData) {
		openDisplayPageModal({
			formSubmitURL: itemData.addDisplayPageURL,
			mappingTypes: itemData.mappingTypes,
			namespace: this.namespace,
			spritemap: this.spritemap,
			title: Liferay.Language.get('add-display-page-template')
		});
	}

	deleteSelectedDisplayPages() {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(this.one('#fm'));
		}
	}
}

DisplayPageManagementToolbarDefaultEventHandler.STATE = {
	spritemap: Config.string()
};

export default DisplayPageManagementToolbarDefaultEventHandler;
