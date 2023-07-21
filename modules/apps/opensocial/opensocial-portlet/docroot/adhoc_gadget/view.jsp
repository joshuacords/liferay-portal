<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Gadget gadget = (Gadget)renderRequest.getAttribute(WebKeys.GADGET);
%>

<c:choose>
	<c:when test="<%= gadget == null %>">
		<div class="alert alert-info portlet-configuration">
			<a href="<%= portletDisplay.getURLConfiguration() %>" onClick="<%= portletDisplay.getURLConfigurationJS() %>">
				<liferay-ui:message key="configure-a-gadget-to-be-displayed-in-this-widget" />
			</a>
		</div>

		<liferay-ui:icon
			cssClass="portlet-configuration"
			iconCssClass="icon-cog"
			message="configure-gadget"
			method="get"
			onClick="<%= portletDisplay.getURLConfigurationJS() %>"
			url="<%= portletDisplay.getURLConfiguration() %>"
		/>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/gadget/view.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>