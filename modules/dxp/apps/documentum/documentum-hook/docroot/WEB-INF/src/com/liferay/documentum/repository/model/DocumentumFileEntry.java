/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.common.DfException;

import com.liferay.document.library.repository.external.ExtRepositoryFileEntry;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Iván Zaera
 */
public class DocumentumFileEntry
	extends DocumentumObject implements ExtRepositoryFileEntry {

	public DocumentumFileEntry(
		IDfDocument idfDocument, IDfDocument idfDocumentLastVersion) {

		super(idfDocument);

		_idfDocument = idfDocument;
		_idfDocumentLastVersion = idfDocumentLastVersion;
	}

	@Override
	public String getCheckedOutBy() {
		try {
			return _idfDocumentLastVersion.getLockOwner();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	public IDfDocument getIDfDocument() {
		return _idfDocument;
	}

	@Override
	public String getMimeType() {
		return null;
	}

	@Override
	public String getTitle() {
		try {
			return _idfDocumentLastVersion.getObjectName();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	private final IDfDocument _idfDocument;
	private final IDfDocument _idfDocumentLastVersion;

}