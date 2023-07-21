<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:field-wrapper helpMessage='<%= LanguageUtil.get(resourceBundle, "terms-of-use-web-content-help") %>' label='<%= LanguageUtil.get(resourceBundle, "terms-of-use-web-content") %>'>
	<aui:fieldset>
		<aui:input label='<%= LanguageUtil.get(resourceBundle, "group-id") %>' name='<%= "settings--" + JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID + "--" %>' type="text" value="<%= PrefsPropsUtil.getLong(themeDisplay.getCompanyId(), JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID, JournalServiceConfigurationValues.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID) %>" />

		<aui:input label='<%= LanguageUtil.get(resourceBundle, "article-id") %>' name='<%= "settings--" + JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_ID + "--" %>' type="text" value="<%= PrefsPropsUtil.getString(themeDisplay.getCompanyId(), JournalServiceConfigurationKeys.TERMS_OF_USE_JOURNAL_ARTICLE_ID, JournalServiceConfigurationValues.TERMS_OF_USE_JOURNAL_ARTICLE_ID) %>" />
	</aui:fieldset>
</aui:field-wrapper>