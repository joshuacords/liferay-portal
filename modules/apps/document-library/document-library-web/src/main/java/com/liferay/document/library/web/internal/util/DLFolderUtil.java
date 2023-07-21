/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.util;

import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.util.RepositoryUtil;

/**
 * @author Adolfo Pérez
 */
public class DLFolderUtil {

	public static boolean isRepositoryRoot(Folder folder) {
		if (folder.isMountPoint() ||
			(folder.isRoot() &&
			 RepositoryUtil.isExternalRepository(folder.getRepositoryId()))) {

			return true;
		}

		return false;
	}

}