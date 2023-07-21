<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= themeDisplay.isImpersonated() %>">
	<aui:a cssClass="list-group-heading" href="<%= PortalUtil.getLayoutURL(layout, themeDisplay, false) %>">
		<aui:icon image="change" markupView="lexicon" />

		<liferay-ui:message key="be-yourself-again" /> <span class="impersonation-real-user">(<%= HtmlUtil.escape(realUser.getFullName()) %>)</span>
	</aui:a>

	<%
	Locale realUserLocale = realUser.getLocale();
	Locale userLocale = user.getLocale();
	%>

	<c:if test="<%= !realUserLocale.equals(userLocale) %>">

		<%
		String changeLanguageMessage = null;
		String doAsUserLanguageId = null;

		String country = locale.getCountry();
		String language = locale.getLanguage();

		if (language.equals(realUserLocale.getLanguage()) && country.equals(realUserLocale.getCountry())) {
			changeLanguageMessage = LanguageUtil.format(realUserLocale, "use-x's-preferred-language-(x)", new String[] {HtmlUtil.escape(user.getFullName()), userLocale.getDisplayLanguage(realUserLocale)}, false);
			doAsUserLanguageId = userLocale.getLanguage() + "_" + userLocale.getCountry();
		}
		else {
			changeLanguageMessage = LanguageUtil.format(realUserLocale, "use-your-preferred-language-(x)", realUserLocale.getDisplayLanguage(realUserLocale), false);
			doAsUserLanguageId = realUserLocale.getLanguage() + "_" + realUserLocale.getCountry();
		}
		%>

		<aui:icon cssClass="list-group-heading" image="flag-full" label="<%= changeLanguageMessage %>" markupView="lexicon" url='<%= HttpUtil.setParameter(PortalUtil.getCurrentURL(request), "doAsUserLanguageId", doAsUserLanguageId) %>' />
	</c:if>
</c:if>

<liferay-application-list:panel-category-body
	panelCategory="<%= (UserPanelCategory)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY) %>"
/>