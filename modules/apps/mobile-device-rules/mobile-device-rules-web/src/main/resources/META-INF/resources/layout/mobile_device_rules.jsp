<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long selPlid = ParamUtil.getLong(request, "selPlid", LayoutConstants.DEFAULT_PLID);

Layout selLayout = LayoutLocalServiceUtil.fetchLayout(selPlid);

String className = Layout.class.getName();
long classPK = selLayout.getPlid();
%>

<%@ include file="/layout/mobile_device_rules_header.jspf" %>

<liferay-util:buffer
	var="rootNodeNameLink"
>

	<%
	PortletURL editLayoutSetURL = liferayPortletResponse.createRenderURL();

	editLayoutSetURL.setParameter("selPlid", String.valueOf(LayoutConstants.DEFAULT_PLID));
	editLayoutSetURL.setParameter("groupId", String.valueOf(selLayout.getGroupId()));

	Group group = GroupLocalServiceUtil.getGroup(selLayout.getGroupId());

	String rootNodeName = group.getLayoutRootNodeName(selLayout.isPrivateLayout(), locale);
	%>

	<aui:a href='<%= editLayoutSetURL.toString() + "#tab=mobileDeviceRules" %>'><%= HtmlUtil.escape(rootNodeName) %></aui:a>
</liferay-util:buffer>

<%
int mdrRuleGroupInstancesCount = MDRRuleGroupInstanceServiceUtil.getRuleGroupInstancesCount(className, classPK);
%>

<aui:input label='<%= LanguageUtil.format(resourceBundle, "use-the-same-mobile-device-rules-of-the-x", rootNodeNameLink, false) %>' name="inheritRuleGroupInstances" type="toggle-switch" value="<%= mdrRuleGroupInstancesCount == 0 %>" />

<div class="<%= (mdrRuleGroupInstancesCount == 0) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />inheritRuleGroupInstancesContainer">
	<p class="text-muted">
		<liferay-ui:message arguments="<%= rootNodeNameLink %>" key="mobile-device-rules-are-inhertited-from-x" translateArguments="<%= false %>" />
	</p>
</div>

<div class="<%= (mdrRuleGroupInstancesCount > 0) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />uniqueRuleGroupInstancesContainer">
	<p class="text-muted">
		<liferay-ui:message key="device-family" />
	</p>

	<liferay-util:include page="/layout/mobile_device_rules_rule_group_instances.jsp" servletContext="<%= application %>">
		<liferay-util:param name="groupId" value="<%= String.valueOf(selLayout.getGroupId()) %>" />
		<liferay-util:param name="className" value="<%= className %>" />
		<liferay-util:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	</liferay-util:include>
</div>

<aui:script>
	Liferay.Util.toggleBoxes(
		'<portlet:namespace />inheritRuleGroupInstances',
		'<portlet:namespace />inheritRuleGroupInstancesContainer'
	);
	Liferay.Util.toggleBoxes(
		'<portlet:namespace />inheritRuleGroupInstances',
		'<portlet:namespace />uniqueRuleGroupInstancesContainer',
		true
	);
</aui:script>