/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.model.impl;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.service.CommerceBOMFolderLocalServiceUtil;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalServiceUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMDefinitionImpl extends CommerceBOMDefinitionBaseImpl {

	public CommerceBOMDefinitionImpl() {
	}

	@Override
	public CommerceBOMFolder fetchCommerceBOMFolder() {
		return CommerceBOMFolderLocalServiceUtil.fetchCommerceBOMFolder(
			getCommerceBOMFolderId());
	}

	@Override
	public CPAttachmentFileEntry fetchCPAttachmentFileEntry() {
		return CPAttachmentFileEntryLocalServiceUtil.fetchCPAttachmentFileEntry(
			getCPAttachmentFileEntryId());
	}

}