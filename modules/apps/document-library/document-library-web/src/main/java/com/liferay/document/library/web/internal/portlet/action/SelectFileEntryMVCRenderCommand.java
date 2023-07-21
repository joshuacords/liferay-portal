/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.action;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.web.internal.util.DLTrashUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/select_file_entry"
	},
	service = MVCRenderCommand.class
)
public class SelectFileEntryMVCRenderCommand extends GetFolderMVCRenderCommand {

	@Override
	protected DLTrashUtil getDLTrashUtil() {
		return _dlTrashUtil;
	}

	@Override
	protected String getPath() {
		return "/document_library/select_file_entry.jsp";
	}

	@Reference
	private DLTrashUtil _dlTrashUtil;

}