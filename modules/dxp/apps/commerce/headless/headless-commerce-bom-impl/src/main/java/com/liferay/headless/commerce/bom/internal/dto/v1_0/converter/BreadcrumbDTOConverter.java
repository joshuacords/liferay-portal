/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.bom.internal.dto.v1_0.converter;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.service.CommerceBOMFolderService;
import com.liferay.headless.commerce.bom.dto.v1_0.Breadcrumb;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=breadcrumb",
	service = {BreadcrumbDTOConverter.class, DTOConverter.class}
)
public class BreadcrumbDTOConverter
	implements DTOConverter<CommerceBOMFolder, Breadcrumb> {

	@Override
	public String getContentType() {
		return Breadcrumb.class.getSimpleName();
	}

	public Breadcrumb toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceBOMFolder commerceBOMFolder =
			_commerceBOMFolderService.getCommerceBOMFolder(
				(Long)dtoConverterContext.getId());

		return new Breadcrumb() {
			{
				label = commerceBOMFolder.getName();
				url = "/folders/" + commerceBOMFolder.getCommerceBOMFolderId();
			}
		};
	}

	@Reference
	private CommerceBOMFolderService _commerceBOMFolderService;

}