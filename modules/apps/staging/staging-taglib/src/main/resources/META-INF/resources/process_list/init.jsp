<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean deleteMenu = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list:deleteMenu"));
String localTaskExecutorClassName = GetterUtil.getString(request.getAttribute("liferay-staging:process-list:localTaskExecutorClassName"));
String mvcRenderCommandName = GetterUtil.getString(request.getAttribute("liferay-staging:process-list:mvcRenderCommandName"));
boolean relaunchMenu = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list:relaunchMenu"));
String remoteTaskExecutorClassName = GetterUtil.getString(request.getAttribute("liferay-staging:process-list:remoteTaskExecutorClassName"));
ResultRowSplitter resultRowSplitter = (ResultRowSplitter)request.getAttribute("liferay-staging:process-list:resultRowSplitter");
boolean summaryMenu = GetterUtil.getBoolean(request.getAttribute("liferay-staging:process-list:summaryMenu"));

if (Validator.isNull(remoteTaskExecutorClassName)) {
	remoteTaskExecutorClassName = localTaskExecutorClassName;
}

String displayStyle = ParamUtil.getString(request, "displayStyle", "descriptive");
String navigation = ParamUtil.getString(request, "navigation", "all");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
String searchContainerId = ParamUtil.getString(request, "searchContainerId");

OrderByComparator<BackgroundTask> orderByComparator = BackgroundTaskComparatorFactoryUtil.getBackgroundTaskOrderByComparator(orderByCol, orderByType);

String processListListViewCss = "process-list";

if (Objects.equals(displayStyle, "list")) {
	processListListViewCss += " process-list-list-view";
}

PortletURL renderURL = liferayPortletResponse.createRenderURL();

renderURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
renderURL.setParameter("tabs1", "processes");

StagingGroupHelper stagingGroupHelper = StagingGroupHelperUtil.getStagingGroupHelper();

boolean localPublishing = (liveGroup == null) || stagingGroupHelper.isLocalStagingOrLocalLiveGroup(liveGroup);

renderURL.setParameter("localPublishing", String.valueOf(localPublishing));

renderURL.setParameter("displayStyle", displayStyle);
renderURL.setParameter("navigation", navigation);
renderURL.setParameter("orderByCol", orderByCol);
renderURL.setParameter("orderByType", orderByType);
renderURL.setParameter("searchContainerId", searchContainerId);

String taskExecutorClassName = localPublishing ? localTaskExecutorClassName : remoteTaskExecutorClassName;
%>