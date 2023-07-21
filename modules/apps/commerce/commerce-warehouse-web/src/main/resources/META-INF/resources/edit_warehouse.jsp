<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ServletContext commerceAdminServletContext = (ServletContext)request.getAttribute(CommerceAdminWebKeys.COMMERCE_ADMIN_SERVLET_CONTEXT);

CommerceInventoryWarehousesDisplayContext commerceInventoryWarehousesDisplayContext = (CommerceInventoryWarehousesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceInventoryWarehouse commerceInventoryWarehouse = commerceInventoryWarehousesDisplayContext.getCommerceInventoryWarehouse();

String title = LanguageUtil.get(request, "add-warehouse");

if (commerceInventoryWarehouse != null) {
	title = LanguageUtil.format(request, "edit-x", commerceInventoryWarehouse.getName(), false);
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, commerceAdminModuleKey), PortalUtil.escapeRedirect(redirect), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= commerceAdminServletContext %>">
	<liferay-util:param name="commerceAdminModuleKey" value="<%= commerceAdminModuleKey %>" />
</liferay-util:include>

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editCommerceInventoryWarehouse" var="editCommerceInventoryWarehouseActionURL" />

<aui:form action="<%= editCommerceInventoryWarehouseActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceInventoryWarehouse();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceInventoryWarehouse == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceInventoryWarehouseId" type="hidden" value="<%= (commerceInventoryWarehouse == null) ? 0 : commerceInventoryWarehouse.getCommerceInventoryWarehouseId() %>" />
	<aui:input name="commerceChannelIds" type="hidden" />
	<aui:input name="mvccVersion" type="hidden" value="<%= (commerceInventoryWarehouse == null) ? 0 : commerceInventoryWarehouse.getMvccVersion() %>" />

	<liferay-ui:form-navigator
		formModelBean="<%= commerceInventoryWarehouse %>"
		id="<%= CommerceInventoryWarehouseFormNavigatorConstants.FORM_NAVIGATOR_ID_COMMERCE_WAREHOUSE %>"
		markupView="lexicon"
	/>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceInventoryWarehouse() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>