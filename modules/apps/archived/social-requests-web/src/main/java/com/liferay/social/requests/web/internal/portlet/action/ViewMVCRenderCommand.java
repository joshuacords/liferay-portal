/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.requests.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.model.SocialRequestConstants;
import com.liferay.social.kernel.service.SocialRequestLocalService;
import com.liferay.social.requests.web.internal.constants.SocialRequestsPortletKeys;
import com.liferay.social.requests.web.internal.constants.SocialRequestsWebKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SocialRequestsPortletKeys.REQUESTS,
		"mvc.command.name=/", "mvc.command.name=/requests/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			return doRender(renderRequest);
		}
		catch (PortalException portalException) {
			throw new PortletException(portalException);
		}
	}

	protected String doRender(RenderRequest renderRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = _groupLocalService.getGroup(
			themeDisplay.getScopeGroupId());

		User user = themeDisplay.getUser();

		if (group.isUser()) {
			user = _userLocalService.getUserById(group.getClassPK());
		}

		if (UserPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), user.getUserId(),
				ActionKeys.UPDATE)) {

			List<SocialRequest> requests =
				_socialRequestLocalService.getReceiverUserRequests(
					user.getUserId(), SocialRequestConstants.STATUS_PENDING, 0,
					100);

			renderRequest.setAttribute(
				SocialRequestsWebKeys.SOCIAL_REQUESTS, requests);
		}

		return "/view.jsp";
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setSocialRequestLocalService(
		SocialRequestLocalService socialRequestLocalService) {

		_socialRequestLocalService = socialRequestLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private GroupLocalService _groupLocalService;
	private SocialRequestLocalService _socialRequestLocalService;
	private UserLocalService _userLocalService;

}