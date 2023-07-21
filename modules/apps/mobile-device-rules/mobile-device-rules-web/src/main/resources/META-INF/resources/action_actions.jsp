<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MDRAction action = (MDRAction)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= MDRRuleGroupInstancePermission.contains(permissionChecker, action.getRuleGroupInstanceId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editActionURL">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_action" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="actionId" value="<%= String.valueOf(action.getActionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editActionURL %>"
		/>

		<portlet:actionURL name="/mobile_device_rules/edit_action" var="deleteURL">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_action" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="actionId" value="<%= String.valueOf(action.getActionId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL.toString() %>"
		/>
	</c:if>
</liferay-ui:icon-menu>