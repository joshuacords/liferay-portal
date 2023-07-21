<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = String.valueOf(searchContainer.getIteratorURL());

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MDRRuleGroup ruleGroup = (MDRRuleGroup)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= MDRRuleGroupPermission.contains(permissionChecker, ruleGroup.getRuleGroupId(), ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroup.getRuleGroupId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= MDRRuleGroupPermission.contains(permissionChecker, ruleGroup.getRuleGroupId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= MDRRuleGroup.class.getName() %>"
			modelResourceDescription="<%= ruleGroup.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(ruleGroup.getRuleGroupId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= MDRRuleGroupPermission.contains(permissionChecker, ruleGroup.getRuleGroupId(), ActionKeys.VIEW) && MDRPermission.contains(permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP) %>">
		<portlet:actionURL name="/mobile_device_rules/edit_rule_group" var="copyURL">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.COPY %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroup.getRuleGroupId()) %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="copy"
			url="<%= copyURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= MDRRuleGroupPermission.contains(permissionChecker, ruleGroup.getRuleGroupId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="/mobile_device_rules/edit_rule_group" var="deleteURL">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroup.getRuleGroupId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>