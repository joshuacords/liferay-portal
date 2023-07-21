/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.portlet.configuration.icon;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.web.internal.portlet.action.ActionUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"path=/message_boards/view_message"
	},
	service = PortletConfigurationIcon.class
)
public class ThreadLockPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	@Override
	public String getMessage(PortletRequest portletRequest) {
		try {
			MBMessage message = ActionUtil.getMessage(portletRequest);

			MBThread thread = message.getThread();

			String key = "lock";

			if (thread.isLocked()) {
				key = "unlock";
			}

			return LanguageUtil.get(
				getResourceBundle(getLocale(portletRequest)), key);
		}
		catch (Exception exception) {
			return null;
		}
	}

	@Override
	public String getURL(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			portletRequest, MBPortletKeys.MESSAGE_BOARDS_ADMIN,
			PortletRequest.ACTION_PHASE);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/message_boards/edit_message");

		try {
			MBMessage message = ActionUtil.getMessage(portletRequest);

			MBThread thread = message.getThread();

			if (thread.isLocked()) {
				portletURL.setParameter(Constants.CMD, Constants.UNLOCK);
			}
			else {
				portletURL.setParameter(Constants.CMD, Constants.LOCK);
			}

			portletURL.setParameter(
				"redirect", _portal.getCurrentURL(portletRequest));
			portletURL.setParameter(
				"threadId", String.valueOf(thread.getThreadId()));
		}
		catch (Exception exception) {
			return null;
		}

		return portletURL.toString();
	}

	@Override
	public double getWeight() {
		return 103;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			MBMessage message = ActionUtil.getMessage(portletRequest);

			return ModelResourcePermissionHelper.contains(
				_categoryModelResourcePermission,
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), message.getCategoryId(),
				ActionKeys.LOCK_THREAD);
		}
		catch (Exception exception) {
		}

		return false;
	}

	@Reference(
		target = "(model.class.name=com.liferay.message.boards.model.MBCategory)"
	)
	private ModelResourcePermission<MBCategory>
		_categoryModelResourcePermission;

	@Reference
	private Portal _portal;

}