/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.document.library.internal.search.DLOpenSearchImpl}
 */
@Deprecated
public class DLOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String TITLE = "Liferay Documents and Media Search: ";

	@Override
	public String getClassName() {
		return DLFileEntry.class.getName();
	}

	@Override
	public Indexer<DLFileEntry> getIndexer() {
		return IndexerRegistryUtil.getIndexer(DLFileEntry.class);
	}

	@Override
	public String getSearchPath() {
		return StringPool.BLANK;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

}