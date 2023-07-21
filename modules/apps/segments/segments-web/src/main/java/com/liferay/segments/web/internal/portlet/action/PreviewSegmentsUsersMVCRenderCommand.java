/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.web.internal.portlet.action;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.odata.retriever.ODataRetriever;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.service.SegmentsEntryService;
import com.liferay.segments.web.internal.constants.SegmentsWebKeys;
import com.liferay.segments.web.internal.display.context.PreviewSegmentsEntryUsersDisplayContext;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SegmentsPortletKeys.SEGMENTS,
		"mvc.command.name=previewSegmentsEntryUsers"
	},
	service = MVCRenderCommand.class
)
public class PreviewSegmentsUsersMVCRenderCommand implements MVCRenderCommand {

	@Activate
	public void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, ODataRetriever.class, "model.class.name");
	}

	@Deactivate
	public void deactivate() {
		_serviceTrackerMap.close();
	}

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		ODataRetriever userODataRetriever = _serviceTrackerMap.getService(
			User.class.getName());

		PreviewSegmentsEntryUsersDisplayContext
			previewSegmentsEntryUsersDisplayContext =
				new PreviewSegmentsEntryUsersDisplayContext(
					httpServletRequest, renderRequest, renderResponse,
					_segmentsEntryProviderRegistry, _segmentsEntryService,
					userODataRetriever, _userLocalService);

		renderRequest.setAttribute(
			SegmentsWebKeys.PREVIEW_SEGMENTS_ENTRY_USERS_DISPLAY_CONTEXT,
			previewSegmentsEntryUsersDisplayContext);

		return "/preview_segments_entry_users.jsp";
	}

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry;

	@Reference
	private SegmentsEntryService _segmentsEntryService;

	private ServiceTrackerMap<String, ODataRetriever> _serviceTrackerMap;

	@Reference
	private UserLocalService _userLocalService;

}