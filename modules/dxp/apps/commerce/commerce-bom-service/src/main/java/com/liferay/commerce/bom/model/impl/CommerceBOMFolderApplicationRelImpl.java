/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.model.impl;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.service.CommerceApplicationModelLocalServiceUtil;
import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.service.CommerceBOMFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMFolderApplicationRelImpl
	extends CommerceBOMFolderApplicationRelBaseImpl {

	public CommerceBOMFolderApplicationRelImpl() {
	}

	@Override
	public CommerceApplicationModel getCommerceApplicationModel()
		throws PortalException {

		return CommerceApplicationModelLocalServiceUtil.
			getCommerceApplicationModel(getCommerceApplicationModelId());
	}

	@Override
	public CommerceBOMFolder getCommerceBOMFolder() throws PortalException {
		return CommerceBOMFolderLocalServiceUtil.getCommerceBOMFolder(
			getCommerceBOMFolderId());
	}

}