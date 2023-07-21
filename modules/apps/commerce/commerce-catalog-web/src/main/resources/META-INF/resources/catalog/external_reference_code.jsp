<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceCatalogDisplayContext commerceCatalogDisplayContext = (CommerceCatalogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCatalog commerceCatalog = commerceCatalogDisplayContext.getCommerceCatalog();
%>

<portlet:actionURL name="editCommerceCatalogExternalReferenceCode" var="editCommerceCatalogExternalReferenceCodeURL" />

<commerce-ui:modal-content>
	<aui:form action="<%= editCommerceCatalogExternalReferenceCodeURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceCatalogId" type="hidden" value="<%= commerceCatalog.getCommerceCatalogId() %>" />

		<aui:model-context bean="<%= commerceCatalog %>" model="<%= CommerceCatalog.class %>" />

		<aui:input name="externalReferenceCode" type="text" value="<%= commerceCatalog.getExternalReferenceCode() %>" wrapperCssClass="form-group-item" />
	</aui:form>
</commerce-ui:modal-content>