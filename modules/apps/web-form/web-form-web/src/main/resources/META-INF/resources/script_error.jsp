<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String name = portletDisplay.getRootPortletId();
String primKey = portletDisplay.getResourcePK();
%>

<div>
	<c:choose>
		<c:when test="<%= permissionChecker.hasPermission(themeDisplay.getScopeGroup(), name, primKey, ActionKeys.CONFIGURATION) %>">
			<span class="alert alert-danger"><liferay-ui:message key="an-error-occurred-while-executing-the-validation.-please-review-the-following-errors" /></span>

			<pre><%= SessionErrors.get(renderRequest, "validationScriptError") %></pre>
		</c:when>
		<c:otherwise>
			<span class="alert alert-danger"><liferay-ui:message key="an-error-occurred-while-executing-the-validation.-please-contact-an-administrator" /></span>
		</c:otherwise>
	</c:choose>
</div>