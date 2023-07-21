/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.action;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.web.internal.constants.DLWebKeys;
import com.liferay.document.library.web.internal.util.DLTrashUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"mvc.command.name=/document_library/info_panel"
	},
	service = MVCResourceCommand.class
)
public class InfoPanelMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		resourceRequest.setAttribute(
			DLWebKeys.DOCUMENT_LIBRARY_TRASH_UTIL, _dlTrashUtil);
		resourceRequest.setAttribute(
			WebKeys.DOCUMENT_LIBRARY_FILE_ENTRIES,
			ActionUtil.getFileEntries(resourceRequest));
		resourceRequest.setAttribute(
			WebKeys.DOCUMENT_LIBRARY_FILE_SHORTCUTS,
			ActionUtil.getFileShortcuts(resourceRequest));
		resourceRequest.setAttribute(
			WebKeys.DOCUMENT_LIBRARY_FOLDERS,
			ActionUtil.getFolders(resourceRequest));

		include(
			resourceRequest, resourceResponse,
			"/document_library/info_panel.jsp");
	}

	@Reference
	private DLTrashUtil _dlTrashUtil;

}