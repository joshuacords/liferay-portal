/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository;

import com.liferay.document.library.repository.external.ExtRepositoryFileVersion;
import com.liferay.documentum.repository.model.DocumentumVersionNumber;

import java.util.Comparator;

/**
 * @author Iván Zaera
 */
public class ExtRepositoryFileVersionsComparator
	implements Comparator<ExtRepositoryFileVersion> {

	@Override
	public int compare(
		ExtRepositoryFileVersion extRepositoryFileVersion1,
		ExtRepositoryFileVersion extRepositoryFileVersion2) {

		DocumentumVersionNumber documentumVersionNumber1 =
			new DocumentumVersionNumber(extRepositoryFileVersion1.getVersion());
		DocumentumVersionNumber documentumVersionNumber2 =
			new DocumentumVersionNumber(extRepositoryFileVersion2.getVersion());

		return -documentumVersionNumber1.compareTo(documentumVersionNumber2);
	}

}