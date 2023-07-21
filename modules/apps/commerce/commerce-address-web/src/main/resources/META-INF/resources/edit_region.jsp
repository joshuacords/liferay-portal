<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ServletContext commerceAdminServletContext = (ServletContext)request.getAttribute(CommerceAdminWebKeys.COMMERCE_ADMIN_SERVLET_CONTEXT);

CommerceRegionsDisplayContext commerceRegionsDisplayContext = (CommerceRegionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCountry commerceCountry = commerceRegionsDisplayContext.getCommerceCountry();

long commerceCountryId = commerceRegionsDisplayContext.getCommerceCountryId();

CommerceRegion commerceRegion = commerceRegionsDisplayContext.getCommerceRegion();

long commerceRegionId = commerceRegionsDisplayContext.getCommerceRegionId();

PortletURL editCountryURL = renderResponse.createRenderURL();

editCountryURL.setParameter("commerceAdminModuleKey", CountriesCommerceAdminModule.KEY);
editCountryURL.setParameter("commerceCountryId", String.valueOf(commerceCountryId));

String contextTitle = commerceCountry.getName(locale);

String title;

if (commerceRegion == null) {
	title = LanguageUtil.format(request, "add-region-to-x", contextTitle);
}
else {
	title = contextTitle + " - " + commerceRegion.getName();
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

String screenNavigationCategoryKey = commerceRegionsDisplayContext.getScreenNavigationCategoryKey();

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, commerceAdminModuleKey), countriesURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, editCountryURL.toString(), data);

editCountryURL.setParameter("screenNavigationCategoryKey", screenNavigationCategoryKey);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, screenNavigationCategoryKey), editCountryURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= commerceAdminServletContext %>">
	<liferay-util:param name="commerceAdminModuleKey" value="<%= commerceAdminModuleKey %>" />
</liferay-util:include>

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editCommerceRegion" var="editCommerceRegionActionURL" />

<aui:form action="<%= editCommerceRegionActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceRegion();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceRegion == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceCountryId" type="hidden" value="<%= String.valueOf(commerceCountryId) %>" />
	<aui:input name="commerceRegionId" type="hidden" value="<%= String.valueOf(commerceRegionId) %>" />

	<div class="lfr-form-content">
		<liferay-ui:error exception="<%= CommerceRegionNameException.class %>" message="please-enter-a-valid-name" />

		<aui:model-context bean="<%= commerceRegion %>" model="<%= CommerceRegion.class %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= true %>" name="name" />

				<aui:input name="code" />

				<aui:input name="priority" />

				<aui:input checked="<%= (commerceRegion == null) ? false : commerceRegion.isActive() %>" name="active" type="toggle-switch" />
			</aui:fieldset>
		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceRegion() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>