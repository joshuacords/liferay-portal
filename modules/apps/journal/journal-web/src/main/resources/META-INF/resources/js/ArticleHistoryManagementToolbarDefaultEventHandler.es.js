/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {DefaultEventHandler} from 'frontend-js-web';

class ArticleHistoryManagementToolbarDefaultEventHandler extends DefaultEventHandler {
	deleteArticles(itemData) {
		if (
			confirm(
				Liferay.Language.get(
					'are-you-sure-you-want-to-delete-the-selected-version'
				)
			)
		) {
			submitForm(this.one('#fm'), itemData.deleteArticlesURL);
		}
	}

	expireArticles(itemData) {
		if (
			confirm(
				Liferay.Language.get(
					'are-you-sure-you-want-to-expire-the-selected-version'
				)
			)
		) {
			submitForm(this.one('#fm'), itemData.expireArticlesURL);
		}
	}
}

export default ArticleHistoryManagementToolbarDefaultEventHandler;
