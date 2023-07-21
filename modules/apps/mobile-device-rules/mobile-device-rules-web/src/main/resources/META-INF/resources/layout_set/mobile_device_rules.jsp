<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long selPlid = LayoutConstants.DEFAULT_PLID;

boolean privateLayout = ParamUtil.getBoolean(liferayPortletRequest, "privateLayout");

Group group = GroupLocalServiceUtil.getGroup(groupId);

if (group.isLayoutSetPrototype() || group.isLayoutSetPrototype()) {
	privateLayout = true;
}

LayoutSet selLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(groupId, privateLayout);

String className = LayoutSet.class.getName();
long classPK = selLayoutSet.getLayoutSetId();
%>

<%@ include file="/layout/mobile_device_rules_header.jspf" %>

<div id="<portlet:namespace />uniqueRuleGroupInstancesContainer">
	<liferay-util:include page="/layout/mobile_device_rules_rule_group_instances.jsp" servletContext="<%= application %>">
		<liferay-util:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		<liferay-util:param name="className" value="<%= className %>" />
		<liferay-util:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	</liferay-util:include>
</div>