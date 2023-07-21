<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="editCommerceShipment" var="editCommerceShipmentURL" />

<commerce-ui:modal-content
	title='<%= LanguageUtil.format(request, "edit-x", "carrier-details") %>'
>
	<aui:form action="<%= editCommerceShipmentURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="carrierDetails" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<aui:model-context bean="<%= commerceShipmentDisplayContext.getCommerceShipment() %>" model="<%= CommerceShipment.class %>" />

		<aui:input name="commerceShipmentId" type="hidden" />

		<aui:input name="carrier" wrapperCssClass="form-group-item" />

		<aui:input name="trackingNumber" wrapperCssClass="form-group-item" />
	</aui:form>
</commerce-ui:modal-content>