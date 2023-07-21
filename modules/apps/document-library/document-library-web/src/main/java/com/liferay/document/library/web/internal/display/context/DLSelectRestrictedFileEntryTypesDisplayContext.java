/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.display.context;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alejandro Tardín
 */
public class DLSelectRestrictedFileEntryTypesDisplayContext
	extends DLViewFileEntryTypesDisplayContext {

	public DLSelectRestrictedFileEntryTypesDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		super(renderRequest, renderResponse, httpServletRequest);
	}

	public String getFormActionURL() {
		return getSearchActionURL();
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath",
			"/document_library/select_restricted_file_entry_type.jsp");
		portletURL.setParameter(
			"includeBasicFileEntryType", Boolean.TRUE.toString());

		return portletURL;
	}

}