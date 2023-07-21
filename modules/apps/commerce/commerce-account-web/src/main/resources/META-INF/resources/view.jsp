<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

List<CommerceAccount> commerceAccounts = commerceAccountDisplayContext.getCommerceAccounts();
%>

<c:choose>
	<c:when test="<%= !commerceAccountDisplayContext.hasCommerceChannel() %>">
		<div class="alert alert-info mx-auto">
			<liferay-ui:message key="this-site-does-not-have-a-channel" />
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= commerceAccountDisplayContext.getCommerceSiteType() == CommerceAccountConstants.SITE_TYPE_B2C %>">
				<liferay-util:include page="/edit_user.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test="<%= (commerceAccounts.size() > 1) || commerceAccountDisplayContext.hasManageCommerceAccountPermissions() %>">
				<liferay-util:include page="/view_account_list.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test="<%= !commerceAccounts.isEmpty() && commerceAccountDisplayContext.hasCommerceAccountModelPermissions(commerceAccounts.get(0), ActionKeys.VIEW) %>">
				<liferay-util:include page="/view_account.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/view_user.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>