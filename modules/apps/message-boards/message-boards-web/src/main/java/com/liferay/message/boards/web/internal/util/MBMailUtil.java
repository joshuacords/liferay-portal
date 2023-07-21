/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Sergio González
 */
public class MBMailUtil {

	public static Map<String, String> getEmailDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<String, String> definitionTerms = LinkedHashMapBuilder.put(
			"[$CATEGORY_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-category-in-which-the-message-has-been-posted")
		).put(
			"[$COMPANY_ID$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-company-id-associated-with-the-message-board")
		).put(
			"[$COMPANY_MX$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-company-mx-associated-with-the-message-board")
		).put(
			"[$COMPANY_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-company-name-associated-with-the-message-board")
		).put(
			"[$FROM_ADDRESS$]", HtmlUtil.escape(emailFromAddress)
		).put(
			"[$FROM_NAME$]", HtmlUtil.escape(emailFromName)
		).build();

		if (PropsValues.POP_SERVER_NOTIFICATIONS_ENABLED) {
			definitionTerms.put(
				"[$MAILING_LIST_ADDRESS$]",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"the-email-address-of-the-mailing-list"));
		}

		definitionTerms.put(
			"[$MESSAGE_BODY$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-message-body"));
		definitionTerms.put(
			"[$MESSAGE_ID$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-message-id"));
		definitionTerms.put(
			"[$MESSAGE_SUBJECT$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-message-subject"));
		definitionTerms.put(
			"[$MESSAGE_URL$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-message-url"));
		definitionTerms.put(
			"[$MESSAGE_USER_ADDRESS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-email-address-of-the-user-who-added-the-message"));
		definitionTerms.put(
			"[$MESSAGE_USER_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-user-who-added-the-message"));

		Company company = themeDisplay.getCompany();

		definitionTerms.put("[$PORTAL_URL$]", company.getVirtualHostname());

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		definitionTerms.put(
			"[$PORTLET_NAME$]", HtmlUtil.escape(portletDisplay.getTitle()));

		definitionTerms.put(
			"[$SITE_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-site-name-associated-with-the-message-board"));

		if (!PropsValues.MESSAGE_BOARDS_EMAIL_BULK) {
			definitionTerms.put(
				"[$TO_ADDRESS$]",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"the-address-of-the-email-recipient"));
			definitionTerms.put(
				"[$TO_NAME$]",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"the-name-of-the-email-recipient"));
		}

		return definitionTerms;
	}

	public static Map<String, String> getEmailFromDefinitionTerms(
		PortletRequest portletRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<String, String> definitionTerms = LinkedHashMapBuilder.put(
			"[$COMPANY_ID$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-company-id-associated-with-the-message-board")
		).put(
			"[$COMPANY_MX$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-company-mx-associated-with-the-message-board")
		).put(
			"[$COMPANY_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-company-name-associated-with-the-message-board")
		).build();

		if (PropsValues.POP_SERVER_NOTIFICATIONS_ENABLED) {
			definitionTerms.put(
				"[$MAILING_LIST_ADDRESS$]",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"the-email-address-of-the-mailing-list"));
		}

		definitionTerms.put(
			"[$MESSAGE_USER_ADDRESS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-email-address-of-the-user-who-added-the-message"));
		definitionTerms.put(
			"[$MESSAGE_USER_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-user-who-added-the-message"));

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		definitionTerms.put(
			"[$PORTLET_NAME$]", HtmlUtil.escape(portletDisplay.getTitle()));

		definitionTerms.put(
			"[$SITE_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-site-name-associated-with-the-message-board"));

		return definitionTerms;
	}

}