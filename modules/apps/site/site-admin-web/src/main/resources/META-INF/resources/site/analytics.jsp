<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");

UnicodeProperties groupTypeSettings = null;

if (liveGroup != null) {
	groupTypeSettings = liveGroup.getTypeSettingsProperties();
}
else {
	groupTypeSettings = new UnicodeProperties();
}
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="analytics"
/>

<%
String[] analyticsTypes = PrefsPropsUtil.getStringArray(company.getCompanyId(), PropsKeys.ADMIN_ANALYTICS_TYPES, StringPool.NEW_LINE);

for (String analyticsType : analyticsTypes) {
%>

	<c:choose>
		<c:when test='<%= StringUtil.equalsIgnoreCase(analyticsType, "google") %>'>
			<aui:input helpMessage="set-the-google-analytics-id-that-is-used-for-this-set-of-pages" label="google-analytics-id" name="googleAnalyticsId" type="text" value='<%= PropertiesParamUtil.getString(groupTypeSettings, request, "googleAnalyticsId") %>' />

			<aui:input helpMessage="set-the-google-analytics-create-custom-options-that-are-used-for-this-set-of-pages" label="google-analytics-create-custom-configuration" name="googleAnalyticsCreateCustomConfiguration" type="textarea" value='<%= PropertiesParamUtil.getString(groupTypeSettings, request, "googleAnalyticsCreateCustomConfiguration") %>' />

			<aui:input helpMessage="set-the-google-analytics-custom-options-that-are-used-for-this-set-of-pages" label="google-analytics-custom-configuration" name="googleAnalyticsCustomConfiguration" type="textarea" value='<%= PropertiesParamUtil.getString(groupTypeSettings, request, "googleAnalyticsCustomConfiguration") %>' />
		</c:when>
		<c:otherwise>

			<%
			String analyticsName = TextFormatter.format(analyticsType, TextFormatter.J);
			%>

			<aui:input helpMessage='<%= LanguageUtil.format(request, "set-the-script-for-x-that-is-used-for-this-set-of-pages", analyticsName, false) %>' label="<%= analyticsName %>" name="<%= Sites.ANALYTICS_PREFIX + analyticsType %>" type="textarea" value="<%= PropertiesParamUtil.getString(groupTypeSettings, request, Sites.ANALYTICS_PREFIX + analyticsType) %>" wrap="soft" />
		</c:otherwise>
	</c:choose>

<%
}
%>