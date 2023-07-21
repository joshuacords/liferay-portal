/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.portlet.configuration.icon;

import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.subscription.service.SubscriptionLocalService;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ambrín Chaudhary
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + KBPortletKeys.KNOWLEDGE_BASE_ADMIN,
		"path=/admin/view_article.jsp", "path=/admin/view_articles.jsp"
	},
	service = PortletConfigurationIcon.class
)
public class KBArticleSubscriptionPortletConfigurationIcon
	extends BaseGetKBArticlePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		String key = "subscribe";

		if (isSubscribed(portletRequest)) {
			key = "unsubscribe";
		}

		return LanguageUtil.get(
			getResourceBundle(getLocale(portletRequest)), key);
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			portletRequest, KBPortletKeys.KNOWLEDGE_BASE_ADMIN,
			PortletRequest.ACTION_PHASE);

		if (isSubscribed(portletRequest)) {
			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "unsubscribeKBArticle");
		}
		else {
			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "subscribeKBArticle");
		}

		portletURL.setParameter(
			"redirect", _portal.getCurrentURL(portletRequest));

		KBArticle kbArticle = getKBArticle(portletRequest);

		portletURL.setParameter(
			"resourceClassNameId", String.valueOf(kbArticle.getClassNameId()));
		portletURL.setParameter(
			"resourcePrimKey", String.valueOf(kbArticle.getResourcePrimKey()));

		return portletURL.toString();
	}

	@Override
	public double getWeight() {
		return 110;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		KBArticle kbArticle = getKBArticle(portletRequest);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		try {
			if ((kbArticle.isApproved() || !kbArticle.isFirstVersion()) &&
				_kbArticleModelResourcePermission.contains(
					permissionChecker, kbArticle, KBActionKeys.SUBSCRIBE)) {

				return true;
			}
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}
		}

		return false;
	}

	protected boolean isSubscribed(PortletRequest portletRequest) {
		KBArticle kbArticle = getKBArticle(portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _subscriptionLocalService.isSubscribed(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());
	}

	@Reference(unbind = "-")
	protected void setSubscriptionLocalService(
		SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KBArticleSubscriptionPortletConfigurationIcon.class);

	@Reference(
		target = "(model.class.name=com.liferay.knowledge.base.model.KBArticle)"
	)
	private ModelResourcePermission<KBArticle>
		_kbArticleModelResourcePermission;

	@Reference
	private Portal _portal;

	private SubscriptionLocalService _subscriptionLocalService;

}