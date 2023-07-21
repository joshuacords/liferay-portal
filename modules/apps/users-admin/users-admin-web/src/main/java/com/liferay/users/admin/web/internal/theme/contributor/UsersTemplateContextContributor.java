/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.internal.theme.contributor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(
	immediate = true,
	property = "type=" + TemplateContextContributor.TYPE_THEME,
	service = TemplateContextContributor.class
)
public class UsersTemplateContextContributor
	implements TemplateContextContributor {

	@Override
	public void prepare(
		Map<String, Object> contextObjects,
		HttpServletRequest httpServletRequest) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		contextObjects.put("is_default_user", user.isDefaultUser());

		try {
			Contact contact = user.getContact();

			contextObjects.put("is_female", !contact.isMale());
			contextObjects.put("is_male", contact.isMale());
			contextObjects.put("user_birthday", contact.getBirthday());
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);
		}

		contextObjects.put("is_setup_complete", user.isSetupComplete());
		contextObjects.put("language", themeDisplay.getLanguageId());
		contextObjects.put("language_id", user.getLanguageId());
		contextObjects.put("user_comments", user.getComments());
		contextObjects.put("user_email_address", user.getEmailAddress());
		contextObjects.put("user_first_name", user.getFirstName());
		contextObjects.put(
			"user_greeting", HtmlUtil.escape(user.getGreeting()));
		contextObjects.put("user_id", user.getUserId());
		contextObjects.put("user_initialized", true);
		contextObjects.put("user_last_login_ip", user.getLastLoginIP());
		contextObjects.put("user_last_name", user.getLastName());
		contextObjects.put("user_login_ip", user.getLoginIP());
		contextObjects.put("user_middle_name", user.getMiddleName());
		contextObjects.put("user_name", user.getFullName());

		Group group = themeDisplay.getSiteGroup();

		if (group.isUser()) {
			try {
				User user2 = _userLocalService.getUserById(group.getClassPK());

				contextObjects.put("user2", user2);
			}
			catch (PortalException portalException) {
				_log.error(portalException, portalException);
			}
		}

		contextObjects.put(
			"w3c_language_id",
			LocaleUtil.toW3cLanguageId(themeDisplay.getLanguageId()));
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UsersTemplateContextContributor.class);

	private UserLocalService _userLocalService;

}