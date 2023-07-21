<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="custom-fields"
/>

<aui:model-context bean="<%= commerceAccountDisplayContext.getCurrentCommerceAccount() %>" model="<%= CommerceAccount.class %>" />

<liferay-expando:custom-attribute-list
	className="<%= CommerceAccount.class.getName() %>"
	classPK="<%= commerceAccountDisplayContext.getCurrentCommerceAccountId() %>"
	editable="<%= true %>"
	label="<%= true %>"
/>