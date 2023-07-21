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
	value="seo"
/>

<aui:model-context bean="<%= selLayout %>" model="<%= Layout.class %>" />

<c:if test="<%= !StringUtil.equals(selLayout.getType(), LayoutConstants.TYPE_ASSET_DISPLAY) %>">
	<aui:input label="html-title" name="title" placeholder="title" />

	<h4><liferay-ui:message key="meta-tags" /></h4>

	<aui:input id="descriptionSEO" name="description" placeholder="description" />

	<aui:input name="keywords" placeholder="keywords" />
</c:if>

<aui:input name="robots" placeholder="robots" />

<c:if test="<%= PortalUtil.isLayoutSitemapable(selLayout) %>">
	<h4><liferay-ui:message key="sitemap" /></h4>

	<div class="alert alert-warning layout-prototype-info-message <%= selLayout.isLayoutPrototypeLinkActive() ? StringPool.BLANK : "hide" %>">
		<liferay-ui:message arguments='<%= new String[] {"inherit-changes", "general"} %>' key="some-page-settings-are-unavailable-because-x-is-enabled" />
	</div>

	<liferay-ui:error exception="<%= SitemapChangeFrequencyException.class %>" message="please-select-a-valid-change-frequency" />
	<liferay-ui:error exception="<%= SitemapIncludeException.class %>" message="please-select-a-valid-include-value" />
	<liferay-ui:error exception="<%= SitemapPagePriorityException.class %>" message="please-enter-a-valid-page-priority" />

	<%
	boolean sitemapInclude = GetterUtil.getBoolean(layoutTypeSettings.getProperty(LayoutTypePortletConstants.SITEMAP_INCLUDE), true);
	%>

	<aui:select cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" label="include" name="TypeSettingsProperties--sitemap-include--">
		<aui:option label="yes" selected="<%= sitemapInclude %>" value="1" />
		<aui:option label="no" selected="<%= !sitemapInclude %>" value="0" />
	</aui:select>

	<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" helpMessage="(0.0 - 1.0)" label="page-priority" name="TypeSettingsProperties--sitemap-priority--" placeholder="0.0" size="3" type="text" value='<%= layoutTypeSettings.getProperty("sitemap-priority", PropsValues.SITES_SITEMAP_DEFAULT_PRIORITY) %>'>
		<aui:validator name="number" />
		<aui:validator errorMessage="please-enter-a-valid-page-priority" name="range">[0,1]</aui:validator>
	</aui:input>

	<aui:select cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" label="change-frequency" name="TypeSettingsProperties--sitemap-changefreq--" value='<%= layoutTypeSettings.getProperty("sitemap-changefreq", PropsValues.SITES_SITEMAP_DEFAULT_CHANGE_FREQUENCY) %>'>
		<aui:option label="always" />
		<aui:option label="hourly" />
		<aui:option label="daily" />
		<aui:option label="weekly" />
		<aui:option label="monthly" />
		<aui:option label="yearly" />
		<aui:option label="never" />
	</aui:select>
</c:if>

<c:if test="<%= !StringUtil.equals(selLayout.getType(), LayoutConstants.TYPE_ASSET_DISPLAY) %>">
	<h4><liferay-ui:message key="canonical-url" /></h4>

	<%
	LayoutSEOEntry selLayoutSEOEntry = layoutsAdminDisplayContext.getSelLayoutSEOEntry();
	%>

	<c:choose>
		<c:when test="<%= selLayoutSEOEntry != null %>">
			<aui:model-context bean="<%= selLayoutSEOEntry %>" model="<%= LayoutSEOEntry.class %>" />

			<aui:input checked="<%= selLayoutSEOEntry.isEnabled() %>" helpMessage="use-custom-canonical-url-help" id="useCustomCanonicalURL" label="use-custom-canonical-url" name="useCustomCanonicalURL" type="toggle-switch" />

			<div id="<portlet:namespace />customCanonicalURLSettings">
				<aui:input name="canonicalURL" placeholder="canonical-url">
					<aui:validator name="url" />
				</aui:input>
			</div>
		</c:when>
		<c:otherwise>
			<aui:input checked="<%= false %>" helpMessage="use-custom-canonical-url-help" id="useCustomCanonicalURL" label="use-custom-canonical-url" name="useCustomCanonicalURL" type="toggle-switch" />

			<div id="<portlet:namespace />customCanonicalURLSettings">
				<aui:input localized="<%= true %>" name="canonicalURL" placeholder="canonical-url" type="text">
					<aui:validator name="url" />
				</aui:input>
			</div>
		</c:otherwise>
	</c:choose>
	</></c:if>

<aui:script>
	Liferay.Util.toggleBoxes(
		'<portlet:namespace />useCustomCanonicalURL',
		'<portlet:namespace />customCanonicalURLSettings'
	);
</aui:script>