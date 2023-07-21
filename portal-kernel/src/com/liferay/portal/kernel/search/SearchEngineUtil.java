/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search;

import java.util.Collection;

/**
 * @author     Bruno Farache
 * @author     Raymond Augé
 * @author     Michael C. Han
 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
 *             SearchEngineHelperUtil}
 */
@Deprecated
public class SearchEngineUtil extends SearchEngineHelperUtil {

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             IndexWriterHelperUtil#updateDocument(String, long, Document,
	 *             boolean)}
	 */
	@Deprecated
	public static void updateDocument(
			String searchEngineId, long companyId, Document document)
		throws SearchException {

		IndexWriterHelperUtil.updateDocument(
			searchEngineId, companyId, document, false);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
	 *             IndexWriterHelperUtil#updateDocuments(String, long,
	 *             Collection, boolean)}
	 */
	@Deprecated
	public static void updateDocuments(
			String searchEngineId, long companyId,
			Collection<Document> documents)
		throws SearchException {

		IndexWriterHelperUtil.updateDocuments(
			searchEngineId, companyId, documents, false);
	}

}