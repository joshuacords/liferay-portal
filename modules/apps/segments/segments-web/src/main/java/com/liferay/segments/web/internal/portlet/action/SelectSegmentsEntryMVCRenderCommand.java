/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.service.SegmentsEntryLocalService;
import com.liferay.segments.web.internal.constants.SegmentsWebKeys;
import com.liferay.segments.web.internal.display.context.SelectSegmentsEntryDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SegmentsPortletKeys.SEGMENTS,
		"mvc.command.name=selectSegmentsEntry"
	},
	service = MVCRenderCommand.class
)
public class SelectSegmentsEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		SelectSegmentsEntryDisplayContext selectSegmentsEntryDisplayContext =
			new SelectSegmentsEntryDisplayContext(
				_portal.getHttpServletRequest(renderRequest), renderRequest,
				renderResponse, _segmentsEntryLocalService);

		renderRequest.setAttribute(
			SegmentsWebKeys.SELECT_SEGMENTS_ENTRY_DISPLAY_CONTEXT,
			selectSegmentsEntryDisplayContext);

		return "/select_segments_entry.jsp";
	}

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

}