<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

UnicodeProperties layoutTypeSettings = selLayout.getTypeSettingsProperties();
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="advanced"
/>

<aui:model-context bean="<%= selLayout %>" model="<%= Layout.class %>" />

<liferay-ui:error exception="<%= ImageTypeException.class %>" message="please-enter-a-file-with-a-valid-file-type" />

<%
Group group = layoutsAdminDisplayContext.getGroup();
%>

<c:if test="<%= !group.isLayoutPrototype() %>">
	<div class="alert alert-warning layout-prototype-info-message <%= selLayout.isLayoutPrototypeLinkActive() ? StringPool.BLANK : "hide" %>">
		<liferay-ui:message arguments='<%= new String[] {LanguageUtil.get(request, "inherit-changes"), "General"} %>' key="some-page-settings-are-unavailable-because-x-is-enabled" translateArguments="<%= false %>" />
	</div>

	<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" helpMessage="query-string-help" label="query-string" name="TypeSettingsProperties--query-string--" size="30" type="text" value='<%= GetterUtil.getString(layoutTypeSettings.getProperty("query-string")) %>' />
</c:if>

<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" label="target" name="TypeSettingsProperties--target--" size="15" type="text" value='<%= GetterUtil.getString(layoutTypeSettings.getProperty("target")) %>' />

<aui:field-wrapper helpMessage="this-icon-will-be-shown-in-the-navigation-menu" label="icon" name="iconFileName">
	<liferay-ui:logo-selector
		currentLogoURL='<%= (selLayout.getIconImageId() == 0) ? themeDisplay.getPathThemeImages() + "/spacer.png" : themeDisplay.getPathImage() + "/logo?img_id=" + selLayout.getIconImageId() + "&t=" + WebServerServletTokenUtil.getToken(selLayout.getIconImageId()) %>'
		defaultLogo="<%= selLayout.getIconImageId() == 0 %>"
		defaultLogoURL='<%= themeDisplay.getPathThemeImages() + "/spacer.png" %>'
		tempImageFileName="<%= String.valueOf(selLayout.getPlid()) %>"
	/>
</aui:field-wrapper>