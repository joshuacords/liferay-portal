/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.trash;

import com.liferay.message.boards.service.MBCategoryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.trash.TrashRendererFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.message.boards.model.MBCategory",
	service = TrashRendererFactory.class
)
public class MBCategoryTrashRendererFactory implements TrashRendererFactory {

	@Override
	public TrashRenderer getTrashRenderer(long classPK) throws PortalException {
		return new MBCategoryTrashRenderer(
			_mbCategoryLocalService.getCategory(classPK));
	}

	@Reference
	private MBCategoryLocalService _mbCategoryLocalService;

}