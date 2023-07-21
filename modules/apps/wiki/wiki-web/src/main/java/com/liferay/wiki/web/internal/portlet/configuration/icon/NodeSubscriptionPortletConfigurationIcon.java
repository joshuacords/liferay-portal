/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.portlet.configuration.icon;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.subscription.service.SubscriptionLocalService;
import com.liferay.wiki.configuration.WikiGroupServiceOverriddenConfiguration;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.web.internal.display.context.util.WikiRequestHelper;
import com.liferay.wiki.web.internal.portlet.action.ActionUtil;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI_ADMIN,
		"path=/wiki/view_pages"
	},
	service = PortletConfigurationIcon.class
)
public class NodeSubscriptionPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		String key = "subscribe";

		try {
			if (isSubscribed(
					portletRequest, ActionUtil.getNode(portletRequest))) {

				key = "unsubscribe";
			}
		}
		catch (Exception exception) {
		}

		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), key);
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			WikiNode node = ActionUtil.getNode(portletRequest);

			PortletURL portletURL = _portal.getControlPanelPortletURL(
				portletRequest, WikiPortletKeys.WIKI_ADMIN,
				PortletRequest.ACTION_PHASE);

			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "/wiki/edit_node");

			if (isSubscribed(portletRequest, node)) {
				portletURL.setParameter(Constants.CMD, Constants.UNSUBSCRIBE);
			}
			else {
				portletURL.setParameter(Constants.CMD, Constants.SUBSCRIBE);
			}

			portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
			portletURL.setParameter("nodeId", String.valueOf(node.getNodeId()));

			return portletURL.toString();
		}
		catch (Exception exception) {
		}

		return StringPool.BLANK;
	}

	@Override
	public double getWeight() {
		return 102;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		WikiRequestHelper wikiRequestHelper = new WikiRequestHelper(
			_portal.getHttpServletRequest(portletRequest));

		WikiGroupServiceOverriddenConfiguration
			wikiGroupServiceOverriddenConfiguration =
				wikiRequestHelper.getWikiGroupServiceOverriddenConfiguration();

		try {
			if (_wikiNodeModelResourcePermission.contains(
					themeDisplay.getPermissionChecker(),
					ActionUtil.getNode(portletRequest), ActionKeys.SUBSCRIBE) &&
				(wikiGroupServiceOverriddenConfiguration.
					emailPageAddedEnabled() ||
				 wikiGroupServiceOverriddenConfiguration.
					 emailPageUpdatedEnabled())) {

				return true;
			}
		}
		catch (Exception exception) {
		}

		return false;
	}

	protected boolean isSubscribed(
		PortletRequest portletRequest, WikiNode node) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _subscriptionLocalService.isSubscribed(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			WikiNode.class.getName(), node.getNodeId());
	}

	@Reference(unbind = "-")
	protected void setSubscriptionLocalService(
		SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

	@Reference
	private Portal _portal;

	private SubscriptionLocalService _subscriptionLocalService;

	@Reference(target = "(model.class.name=com.liferay.wiki.model.WikiNode)")
	private ModelResourcePermission<WikiNode> _wikiNodeModelResourcePermission;

}