<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");
%>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	deltaConfigurable="<%= false %>"
	emptyResultsMessage="none"
	id="rules"
	iteratorURL='<%= (PortletURL)request.getAttribute("mobile_device_rules_header.jspf-portletURL") %>'
	total="<%= MDRRuleGroupInstanceServiceUtil.getRuleGroupInstancesCount(className, classPK) %>"
>
	<liferay-ui:search-container-results
		results="<%= MDRRuleGroupInstanceServiceUtil.getRuleGroupInstances(className, classPK, searchContainer.getStart(), searchContainer.getEnd(), RuleGroupInstancePriorityComparator.INSTANCE_ASCENDING) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.mobile.device.rules.model.MDRRuleGroupInstance"
		escapedModel="<%= true %>"
		keyProperty="ruleGroupInstanceId"
		modelVar="mdrRuleGroupInstance"
	>
		<liferay-portlet:renderURL portletName="<%= MDRPortletKeys.MOBILE_DEVICE_RULES %>" varImpl="rowURL" windowState="<%= themeDisplay.isStateExclusive() ? LiferayWindowState.POP_UP.toString() : windowState.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/view_actions" />
			<portlet:param name="redirect" value='<%= currentURL + "#tab=mobileDeviceRules" %>' />
			<portlet:param name="showBackURL" value="<%= themeDisplay.isStateExclusive() ? Boolean.FALSE.toString() : Boolean.TRUE.toString() %>" />
			<portlet:param name="ruleGroupInstanceId" value="<%= String.valueOf(mdrRuleGroupInstance.getRuleGroupInstanceId()) %>" />
		</liferay-portlet:renderURL>

		<%@ include file="/layout/mobile_device_rules_rule_group_instance_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		type="more"
	/>
</liferay-ui:search-container>

<%@ include file="/layout/mobile_device_rules_toolbar.jspf" %>