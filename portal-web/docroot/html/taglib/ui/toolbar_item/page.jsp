<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/toolbar_item/init.jsp" %>

<%
ToolbarItem toolbarItem = (ToolbarItem)request.getAttribute("liferay-ui:toolbar-item:toolbarItem");
%>

<c:choose>
	<c:when test="<%= toolbarItem instanceof JavaScriptToolbarItem %>">

		<%
		JavaScriptToolbarItem javaScriptToolbarItem = (JavaScriptToolbarItem)toolbarItem;
		%>

		<aui:a cssClass="btn btn-default" href="javascript:;" onClick="<%= javaScriptToolbarItem.getOnClick() %>">
			<c:if test="<%= Validator.isNotNull(javaScriptToolbarItem.getIcon()) %>">
				<aui:icon image="<%= javaScriptToolbarItem.getIcon() %>" markupView="lexicon" />
			</c:if>

			<%= javaScriptToolbarItem.getLabel() %>
		</aui:a>

		<c:if test="<%= Validator.isNotNull(javaScriptToolbarItem.getJavaScript()) %>">
			<script>
				<%= javaScriptToolbarItem.getJavaScript() %>
			</script>
		</c:if>
	</c:when>
	<c:when test="<%= toolbarItem instanceof URLToolbarItem %>">

		<%
		URLToolbarItem urlToolbarItem = (URLToolbarItem)toolbarItem;
		%>

		<aui:a cssClass="btn btn-default" href="<%= urlToolbarItem.getURL() %>" target="<%= urlToolbarItem.getTarget() %>">
			<c:if test="<%= Validator.isNotNull(urlToolbarItem.getIcon()) %>">
				<aui:icon image="<%= urlToolbarItem.getIcon() %>" markupView="lexicon" />
			</c:if>

			<%= urlToolbarItem.getLabel() %>
		</aui:a>
	</c:when>
</c:choose>