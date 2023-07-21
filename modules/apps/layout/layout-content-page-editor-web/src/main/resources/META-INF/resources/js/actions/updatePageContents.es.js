import {getPageContents} from '../utils/FragmentsEditorFetchUtils.es';
import {UPDATE_PAGE_CONTENTS} from './actions.es';

/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * @return {object}
 * @review
 */
function updatePageContentsAction() {
	return function(dispatch) {
		getPageContents().then(pageContents => {
			dispatch({
				type: UPDATE_PAGE_CONTENTS,
				value: pageContents || []
			});
		});
	};
}

export {updatePageContentsAction};
