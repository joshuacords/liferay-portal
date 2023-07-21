<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group liveGroup = layoutsAdminDisplayContext.getLiveGroup();
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="logo"
/>

<liferay-ui:error exception="<%= FileSizeException.class %>">
	<liferay-ui:message arguments="<%= LanguageUtil.formatStorageSize(DLValidatorUtil.getMaxAllowableSize(), locale) %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
</liferay-ui:error>

<p class="text-muted">
	<liferay-ui:message key='<%= "upload-a-logo-for-the-" + (layoutsAdminDisplayContext.isPrivateLayout() ? "private" : "public") + "-pages-that-is-used-instead-of-the-default-enterprise-logo" %>' />
</p>

<c:if test="<%= liveGroup.isLayoutSetPrototype() && !PropsValues.LAYOUT_SET_PROTOTYPE_PROPAGATE_LOGO %>">
	<div class="alert alert-warning">
		<liferay-ui:message key="modifying-the-site-template-logo-only-affects-sites-that-are-not-yet-created" />
	</div>
</c:if>

<%
String companyLogoURL = themeDisplay.getPathImage() + "/company_logo?img_id=" + company.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(company.getLogoId());

boolean defaultLogo = false;

if (selLayoutSet.getLogoId() == 0) {
	defaultLogo = true;
}
else {
	LayoutSet guestGroupLayoutSet = layoutsAdminDisplayContext.getGuestGroupLayoutSet(company.getCompanyId());

	if (selLayoutSet.getLogoId() == guestGroupLayoutSet.getLogoId()) {
		defaultLogo = true;
	}
}
%>

<liferay-ui:logo-selector
	currentLogoURL='<%= (selLayoutSet.getLogoId() == 0) ? companyLogoURL : themeDisplay.getPathImage() + "/layout_set_logo?img_id=" + selLayoutSet.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(selLayoutSet.getLogoId()) %>'
	defaultLogo="<%= defaultLogo %>"
	defaultLogoURL="<%= companyLogoURL %>"
	logoDisplaySelector=".layoutset-logo"
	showButtons="<%= GroupPermissionUtil.contains(permissionChecker, layoutsAdminDisplayContext.getSelGroup(), ActionKeys.MANAGE_LAYOUTS) && SitesUtil.isLayoutSetPrototypeUpdateable(selLayoutSet) %>"
	tempImageFileName="<%= String.valueOf(selLayoutSet.getLayoutSetId()) %>"
/>

<%
Theme selTheme = selLayoutSet.getTheme();

boolean showSiteNameSupported = GetterUtil.getBoolean(selTheme.getSetting("show-site-name-supported"), true);

boolean showSiteNameDefault = GetterUtil.getBoolean(selTheme.getSetting("show-site-name-default"), showSiteNameSupported);
%>

<aui:input disabled="<%= !showSiteNameSupported %>" helpMessage='<%= showSiteNameSupported ? StringPool.BLANK : "the-theme-selected-for-the-site-does-not-support-displaying-the-title" %>' label="show-site-name" name="TypeSettingsProperties--showSiteName--" type="toggle-switch" value='<%= GetterUtil.getBoolean(selLayoutSet.getSettingsProperty("showSiteName"), showSiteNameDefault) %>' />