/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.documentlibrary;

import com.liferay.portal.kernel.repository.model.FileEntry;

/**
 * @author     Iván Zaera
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface DLStoreConverter {

	public void migrateDLFileEntry(
		long companyId, long repositoryId, FileEntry fileEntry);

}