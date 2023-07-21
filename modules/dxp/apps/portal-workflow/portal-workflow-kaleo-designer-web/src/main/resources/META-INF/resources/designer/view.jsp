<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/designer/init.jsp" %>

<liferay-portlet:renderURL copyCurrentRenderParameters="<%= false %>" varImpl="portletURL">
	<portlet:param name="mvcPath" value="/designer/view.jsp" />
</liferay-portlet:renderURL>

<%
String definitionsNavigation = ParamUtil.getString(request, "definitionsNavigation");

int displayedStatus = KaleoDefinitionVersionConstants.STATUS_ALL;

if (StringUtil.equals(definitionsNavigation, "published")) {
	displayedStatus = KaleoDefinitionVersionConstants.STATUS_PUBLISHED;
}
else if (StringUtil.equals(definitionsNavigation, "not-published")) {
	displayedStatus = KaleoDefinitionVersionConstants.STATUS_NOT_PUBLISHED;
}

String orderByCol = ParamUtil.getString(request, "orderByCol", "title");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL navigationPortletURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM);

if (delta > 0) {
	navigationPortletURL.setParameter("delta", String.valueOf(delta));
}

navigationPortletURL.setParameter("orderByCol", orderByCol);
navigationPortletURL.setParameter("orderByType", orderByType);

PortletURL displayStyleURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

if (cur > 0) {
	displayStyleURL.setParameter("cur", String.valueOf(cur));
}
%>

<c:choose>
	<c:when test="<%= WorkflowEngineManagerUtil.isDeployed() %>">
		<liferay-util:include page="/designer/navigation_bar.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/designer/view_workflow_definitions.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="no-workflow-engine-is-deployed" />
		</div>
	</c:otherwise>
</c:choose>