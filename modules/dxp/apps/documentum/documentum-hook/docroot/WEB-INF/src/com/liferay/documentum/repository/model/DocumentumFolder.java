/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.common.DfException;

import com.liferay.document.library.repository.external.ExtRepositoryFolder;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Iván Zaera
 */
public class DocumentumFolder
	extends DocumentumObject implements ExtRepositoryFolder {

	public DocumentumFolder(IDfFolder idfFolder, boolean root) {
		super(idfFolder);

		_idfFolder = idfFolder;
		_root = root;
	}

	public IDfFolder getIDfFolder() {
		return _idfFolder;
	}

	@Override
	public String getName() {
		try {
			return _idfFolder.getObjectName();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public boolean isRoot() {
		return _root;
	}

	private final IDfFolder _idfFolder;
	private final boolean _root;

}