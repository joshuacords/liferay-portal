/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mentions.web.internal.portlet;

import com.liferay.mentions.constants.MentionsPortletKeys;
import com.liferay.mentions.util.MentionsUserFinder;
import com.liferay.mentions.util.MentionsUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.service.permission.PortletPermission;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GroupThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.social.kernel.util.SocialInteractionsConfiguration;
import com.liferay.social.kernel.util.SocialInteractionsConfigurationUtil;
import com.liferay.taglib.ui.UserPortraitTag;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.icon=/icons/mentions.png",
		"javax.portlet.display-name=Mentions",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.name=" + MentionsPortletKeys.MENTIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class MentionsPortlet extends MVCPortlet {

	@Override
	public void serveResource(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (!MentionsUtil.isMentionsEnabled(
					themeDisplay.getSiteGroupId())) {

				return;
			}

			JSONArray jsonArray = _getJSONArray(
				themeDisplay, ParamUtil.getString(resourceRequest, "query"),
				ParamUtil.getString(resourceRequest, "discussionPortletId"));

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(resourceResponse);

			httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(
				httpServletResponse, jsonArray.toString());
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private JSONArray _getJSONArray(
			ThemeDisplay themeDisplay, String query, String discussionPortletId)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		SocialInteractionsConfiguration socialInteractionsConfiguration =
			SocialInteractionsConfigurationUtil.
				getSocialInteractionsConfiguration(
					themeDisplay.getCompanyId(), MentionsPortletKeys.MENTIONS);

		GroupThreadLocal.setGroupId(themeDisplay.getSiteGroupId());

		List<User> users = _mentionsUserFinder.getUsers(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(), query,
			socialInteractionsConfiguration);

		for (User user : users) {
			if (user.isDefaultUser() ||
				(themeDisplay.getUserId() == user.getUserId())) {

				continue;
			}

			Layout layout = themeDisplay.getLayout();

			if (layout != null) {
				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(user);

				if (!_layoutPermission.contains(
						permissionChecker, layout, true, ActionKeys.VIEW) ||
					!_portletPermission.contains(
						permissionChecker, layout, discussionPortletId,
						ActionKeys.VIEW)) {

					continue;
				}
			}

			JSONObject jsonObject = JSONUtil.put(
				"fullName", user.getFullName());

			String mention = "@" + user.getScreenName();

			String profileURL = user.getDisplayURL(themeDisplay);

			if (Validator.isNotNull(profileURL)) {
				mention = StringBundler.concat(
					"<a href=\"", profileURL, "\">@", user.getScreenName(),
					"</a>");
			}

			jsonObject.put(
				"mention", mention
			).put(
				"portraitHTML",
				UserPortraitTag.getUserPortraitHTML(
					StringPool.BLANK, user, themeDisplay)
			).put(
				"screenName", user.getScreenName()
			);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MentionsPortlet.class);

	@Reference
	private LayoutPermission _layoutPermission;

	@Reference
	private MentionsUserFinder _mentionsUserFinder;

	@Reference
	private Portal _portal;

	@Reference
	private PortletPermission _portletPermission;

}