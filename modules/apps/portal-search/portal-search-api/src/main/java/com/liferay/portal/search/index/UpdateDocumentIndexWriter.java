/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.index;

import com.liferay.portal.kernel.search.Document;

import java.util.Collection;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author André de Oliveira
 */
@ProviderType
public interface UpdateDocumentIndexWriter {

	public void updateDocument(
		String searchEngineId, long companyId, Document document,
		boolean commitImmediately);

	public void updateDocumentPartially(
		String searchEngineId, long companyId, Document document,
		boolean commitImmediately);

	public void updateDocuments(
		String searchEngineId, long companyId, Collection<Document> documents,
		boolean commitImmediately);

	public void updateDocumentsPartially(
		String searchEngineId, long companyId, Collection<Document> documents,
		boolean commitImmediately);

}