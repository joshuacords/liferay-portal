/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.DDMStructureIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author     Marcellus Tavares
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.document.library.internal.dynamic.data.mapping.util.DLFileEntryMetadataDDMStructureIndexer}
 */
@Deprecated
public class DLFileEntryMetadataIndexer
	extends BaseIndexer<DLFileEntryMetadata> implements DDMStructureIndexer {

	public static final String CLASS_NAME = DLFileEntryMetadata.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void reindexDDMStructures(List<Long> ddmStructureIds)
		throws SearchException {

		if (IndexWriterHelperUtil.isIndexReadOnly() || !isIndexerEnabled()) {
			return;
		}

		try {
			Indexer<DLFileEntry> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(DLFileEntry.class);

			if (IndexWriterHelperUtil.isIndexReadOnly(indexer.getClassName())) {
				return;
			}

			List<DLFileEntry> dlFileEntries =
				DLFileEntryLocalServiceUtil.getDDMStructureFileEntries(
					ArrayUtil.toLongArray(ddmStructureIds));

			for (DLFileEntry dlFileEntry : dlFileEntries) {
				indexer.reindex(dlFileEntry);
			}
		}
		catch (Exception exception) {
			throw new SearchException(exception);
		}
	}

	@Override
	protected void doDelete(DLFileEntryMetadata object) throws Exception {
	}

	@Override
	protected Document doGetDocument(DLFileEntryMetadata object)
		throws Exception {

		return null;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		return null;
	}

	@Override
	protected void doReindex(DLFileEntryMetadata object) throws Exception {
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
	}

}