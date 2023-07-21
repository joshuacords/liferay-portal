/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.model.impl;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.model.CommerceBOMFolderConstants;
import com.liferay.commerce.bom.service.CommerceBOMFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMFolderImpl extends CommerceBOMFolderBaseImpl {

	public CommerceBOMFolderImpl() {
	}

	@Override
	public List<Long> getAncestorCommerceBOMFolderIds() throws PortalException {
		List<Long> ancestorFolderIds = new ArrayList<>();

		CommerceBOMFolder commerceBOMFolder = this;

		while (!commerceBOMFolder.isRoot()) {
			commerceBOMFolder = commerceBOMFolder.getParentCommerceBOMFolder();

			ancestorFolderIds.add(commerceBOMFolder.getCommerceBOMFolderId());
		}

		return ancestorFolderIds;
	}

	@Override
	public List<CommerceBOMFolder> getAncestors() throws PortalException {
		List<CommerceBOMFolder> ancestors = new ArrayList<>();

		CommerceBOMFolder commerceBOMFolder = this;

		while (!commerceBOMFolder.isRoot()) {
			commerceBOMFolder = commerceBOMFolder.getParentCommerceBOMFolder();

			ancestors.add(commerceBOMFolder);
		}

		return ancestors;
	}

	@Override
	public CommerceBOMFolder getParentCommerceBOMFolder()
		throws PortalException {

		if (getParentCommerceBOMFolderId() ==
				CommerceBOMFolderConstants.DEFAULT_COMMERCE_BOM_FOLDER_ID) {

			return null;
		}

		return CommerceBOMFolderLocalServiceUtil.getCommerceBOMFolder(
			getParentCommerceBOMFolderId());
	}

	@Override
	public boolean isRoot() {
		if (getParentCommerceBOMFolderId() ==
				CommerceBOMFolderConstants.DEFAULT_COMMERCE_BOM_FOLDER_ID) {

			return true;
		}

		return false;
	}

}