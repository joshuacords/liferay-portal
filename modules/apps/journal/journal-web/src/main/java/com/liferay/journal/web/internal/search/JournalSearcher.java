/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.search;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;

/**
 * @author Julio Camarero
 * @author Eudaldo Alonso
 */
public class JournalSearcher extends BaseSearcher {

	public static final String[] CLASS_NAMES = {
		JournalArticle.class.getName(), JournalFolder.class.getName()
	};

	public static Indexer<?> getInstance() {
		return new JournalSearcher();
	}

	public JournalSearcher() {
		setDefaultSelectedFieldNames(
			Field.ARTICLE_ID, Field.COMPANY_ID, Field.DEFAULT_LANGUAGE_ID,
			Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
			Field.VERSION, Field.UID);
		setDefaultSelectedLocalizedFieldNames(
			Field.CONTENT, Field.DESCRIPTION, Field.TITLE);
		setFilterSearch(true);
		setPermissionAware(true);
		setSelectAllLocales(true);
	}

	@Override
	public String[] getSearchClassNames() {
		return CLASS_NAMES;
	}

}