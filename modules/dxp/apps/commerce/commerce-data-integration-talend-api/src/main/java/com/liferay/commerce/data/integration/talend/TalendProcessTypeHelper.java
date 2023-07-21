/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.talend;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.repository.model.FileEntry;

import java.io.InputStream;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface TalendProcessTypeHelper {

	public FileEntry addFileEntry(
			long companyId, long userId, long commerceDataIntegrationProcessId,
			String fileName, long size, String contentType,
			InputStream inputStream)
		throws Exception;

	public FileEntry getFileEntry(long commerceDataIntegrationProcessId)
		throws Exception;

}