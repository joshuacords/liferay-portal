<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.workflow.constants.WorkflowWebKeys" %><%@
page import="com.liferay.portal.workflow.portlet.tab.WorkflowPortletTab" %><%@
page import="com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys" %><%@
page import="com.liferay.portal.workflow.web.internal.display.context.WorkflowNavigationDisplayContext" %><%@
page import="com.liferay.taglib.servlet.PipingServletResponse" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<%
WorkflowPortletTab selectedWorkflowPortletTab = (WorkflowPortletTab)renderRequest.getAttribute(WorkflowWebKeys.SELECTED_WORKFLOW_PORTLET_TAB);
List<WorkflowPortletTab> workflowPortletTabs = (List<WorkflowPortletTab>)renderRequest.getAttribute(WorkflowWebKeys.WORKFLOW_PORTLET_TABS);
%>