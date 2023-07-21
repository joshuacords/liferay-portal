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

<c:if test="<%= commerceShipmentDisplayContext.hasManageCommerceShipmentsPermission() %>">
	<div class="row" id="<portlet:namespace />editShipmentContainer">
		<div class="col-12">
			<commerce-ui:dataset-display
				dataProviderKey="<%= CommerceShipmentDataSetConstants.COMMERCE_DATA_SET_KEY_SHIPMENTS %>"
				id="<%= CommerceShipmentDataSetConstants.COMMERCE_DATA_SET_KEY_SHIPMENTS %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= commerceShipmentDisplayContext.getPortletURL() %>"
				style="fluid"
			/>
		</div>
	</div>
</c:if>