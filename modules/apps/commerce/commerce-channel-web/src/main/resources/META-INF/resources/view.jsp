<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceChannelDisplayContext commerceChannelDisplayContext = (CommerceChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="row">
	<div class="col-12">
		<commerce-ui:dataset-display
			clayCreationMenu="<%= commerceChannelDisplayContext.getClayCreationMenu() %>"
			contextParams="<%= new HashMap<>() %>"
			dataProviderKey="<%= CommerceChannelClayTable.NAME %>"
			id="<%= CommerceChannelClayTable.NAME %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= commerceChannelDisplayContext.getPortletURL() %>"
			style="fluid"
		/>
	</div>
</div>