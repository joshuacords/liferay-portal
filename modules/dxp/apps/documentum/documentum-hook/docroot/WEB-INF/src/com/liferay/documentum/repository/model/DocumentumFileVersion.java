/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfTime;

import com.liferay.document.library.repository.external.ExtRepositoryFileVersion;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

/**
 * @author Iván Zaera
 */
public class DocumentumFileVersion implements ExtRepositoryFileVersion {

	public DocumentumFileVersion(
		IDfDocument idfDocument, IDfDocument idfDocumentFirstVersion) {

		_idfDocument = idfDocument;
		_idfDocumentFirstVersion = idfDocumentFirstVersion;
	}

	@Override
	public String getChangeLog() {
		try {
			return _idfDocument.getLogEntry();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public Date getCreateDate() {
		try {
			IDfTime creationDate = _idfDocument.getCreationDate();

			return creationDate.getDate();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public String getExtRepositoryModelKey() {
		try {
			IDfId idfId = _idfDocumentFirstVersion.getObjectId();

			return idfId.getId() + StringPool.AT + getVersion();
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
	public String getOwner() {
		try {
			return _idfDocument.getOwnerName();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public long getSize() {
		try {
			return _idfDocument.getContentSize();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public String getVersion() {
		try {
			return _idfDocument.getVersionLabel(0);
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	private final IDfDocument _idfDocument;
	private final IDfDocument _idfDocumentFirstVersion;

}