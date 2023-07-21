/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.liferayrepository.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.repository.model.RepositoryEntry;
import com.liferay.portlet.documentlibrary.util.RepositoryModelUtil;

import java.util.List;

/**
 * @author     Alexander Chow
 * @deprecated As of Wilberforce (7.0.x), replaced by {@link
 *             RepositoryModelUtil}
 */
@Deprecated
public abstract class LiferayBase {

	public List<FileEntry> toFileEntries(List<DLFileEntry> dlFileEntries) {
		return RepositoryModelUtil.toFileEntries(dlFileEntries);
	}

	public List<RepositoryEntry> toFileEntriesAndFolders(
		List<Object> dlFileEntriesAndDLFolders) {

		return RepositoryModelUtil.toRepositoryEntries(
			dlFileEntriesAndDLFolders);
	}

	public List<FileVersion> toFileVersions(
		List<DLFileVersion> dlFileVersions) {

		return RepositoryModelUtil.toFileVersions(dlFileVersions);
	}

	public List<Folder> toFolders(List<DLFolder> dlFolders) {
		return RepositoryModelUtil.toFolders(dlFolders);
	}

}