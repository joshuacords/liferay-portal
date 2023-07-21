/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.util.comparator;

import com.liferay.document.library.kernel.model.DLFileVersion;

import java.util.Comparator;

/**
 * @author Bruno Farache
 */
public class DLFileVersionVersionComparator
	implements Comparator<DLFileVersion> {

	public DLFileVersionVersionComparator() {
		this(false);
	}

	public DLFileVersionVersionComparator(boolean ascending) {
		_versionNumberComparator = new VersionNumberComparator(ascending);
	}

	@Override
	public int compare(
		DLFileVersion dlFileVersion1, DLFileVersion dlFileVersion2) {

		return _versionNumberComparator.compare(
			dlFileVersion1.getVersion(), dlFileVersion2.getVersion());
	}

	public boolean isAscending() {
		return _versionNumberComparator.isAscending();
	}

	private final VersionNumberComparator _versionNumberComparator;

}