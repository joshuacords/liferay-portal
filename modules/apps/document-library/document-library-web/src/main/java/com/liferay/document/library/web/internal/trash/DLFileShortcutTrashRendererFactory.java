/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.trash;

import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.trash.TrashRendererFactory;
import com.liferay.trash.TrashHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.document.library.kernel.model.DLFileShortcut",
	service = TrashRendererFactory.class
)
public class DLFileShortcutTrashRendererFactory
	implements TrashRendererFactory {

	@Override
	public TrashRenderer getTrashRenderer(long classPK) throws PortalException {
		DLFileShortcut dlFileShortcut =
			_dlFileShortcutLocalService.getFileShortcut(classPK);

		return new DLFileShortcutTrashRenderer(dlFileShortcut, _trashHelper);
	}

	@Reference(unbind = "-")
	protected void setDLFileShortcutLocalService(
		DLFileShortcutLocalService dlFileShortcutLocalService) {

		_dlFileShortcutLocalService = dlFileShortcutLocalService;
	}

	private DLFileShortcutLocalService _dlFileShortcutLocalService;

	@Reference
	private TrashHelper _trashHelper;

}