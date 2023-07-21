/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class OpenSocialBookmarkDefaultEventHandler extends DefaultEventHandler {
	handleClick(event) {
		var dataSet = event.currentTarget.dataset;

		return window.openSocialBookmarkOnClick(
			event,
			dataSet.classname,
			dataSet.classpk,
			dataSet.type,
			dataSet.posturl,
			dataSet.url
		);
	}
}

export default OpenSocialBookmarkDefaultEventHandler;
