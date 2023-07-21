/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class ManagementToolbarDefaultEventHandler extends DefaultEventHandler {
	deleteTags() {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(this.one('#fm'));
		}
	}

	mergeTags(itemData) {
		const mergeURL = itemData.mergeTagsURL;

		location.href = mergeURL.replace(
			escape('[$MERGE_TAGS_IDS$]'),
			Liferay.Util.listCheckedExcept(
				this.one('#fm'),
				this.ns('allRowIds')
			)
		);
	}
}

export default ManagementToolbarDefaultEventHandler;
