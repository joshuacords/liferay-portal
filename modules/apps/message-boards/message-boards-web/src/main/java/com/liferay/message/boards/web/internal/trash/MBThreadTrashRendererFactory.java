/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.trash;

import com.liferay.asset.util.AssetHelper;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.trash.TrashRendererFactory;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.message.boards.model.MBThread",
	service = TrashRendererFactory.class
)
public class MBThreadTrashRendererFactory implements TrashRendererFactory {

	@Override
	public TrashRenderer getTrashRenderer(long classPK) throws PortalException {
		MBThreadTrashRenderer mbThreadTrashRenderer = new MBThreadTrashRenderer(
			_mbThreadLocalService.getThread(classPK), _assetHelper);

		mbThreadTrashRenderer.setServletContext(_servletContext);

		return mbThreadTrashRenderer;
	}

	@Reference
	private AssetHelper _assetHelper;

	@Reference
	private MBThreadLocalService _mbThreadLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.message.boards.web)"
	)
	private ServletContext _servletContext;

}