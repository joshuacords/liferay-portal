/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler, openSimpleInputModal} from 'frontend-js-web';
import {Config} from 'metal-state';

class EmptyResultMessageDefaultEventHandler extends DefaultEventHandler {
	addAssetListEntry(itemData) {
		openSimpleInputModal({
			dialogTitle: itemData.title,
			formSubmitURL: itemData.addAssetListEntryURL,
			mainFieldLabel: Liferay.Language.get('title'),
			mainFieldName: 'title',
			mainFieldPlaceholder: Liferay.Language.get('title'),
			namespace: this.namespace,
			spritemap: this.spritemap
		});
	}
}

EmptyResultMessageDefaultEventHandler.STATE = {
	spritemap: Config.string()
};

export default EmptyResultMessageDefaultEventHandler;
