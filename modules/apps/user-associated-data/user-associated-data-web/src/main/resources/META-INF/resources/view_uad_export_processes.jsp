<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
UADExportProcessDisplayContext uadExportProcessDisplayContext = new UADExportProcessDisplayContext(request, renderResponse);

UADExportProcessManagementToolbarDisplayContext uadExportProcessManagementToolbarDisplayContext = new UADExportProcessManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, uadExportProcessDisplayContext.getSearchContainer());

portletDisplay.setShowBackIcon(true);

LiferayPortletURL usersAdminURL = liferayPortletResponse.createLiferayPortletURL(UsersAdminPortletKeys.USERS_ADMIN, PortletRequest.RENDER_PHASE);

portletDisplay.setURLBack(usersAdminURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "export-personal-data")));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(true);
						navigationItem.setHref(StringPool.BLANK);
						navigationItem.setLabel(LanguageUtil.get(request, "export-processes"));
					});
			}
		}
	%>'
/>

<clay:management-toolbar
	displayContext="<%= uadExportProcessManagementToolbarDisplayContext %>"
/>

<aui:form cssClass="container-fluid-1280">
	<div id="<portlet:namespace />exportProcesses">

		<%
		request.setAttribute("UADExportProcessDisplayContext", uadExportProcessDisplayContext);
		%>

		<liferay-util:include page="/export_processes.jsp" servletContext="<%= application %>" />
	</div>
</aui:form>

<aui:script use="liferay-uad-export">
	<portlet:resourceURL id="/get_export_processes" var="exportProcessesURL">
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
		<portlet:param name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) %>" />
		<portlet:param name="<%= SearchContainer.DEFAULT_DELTA_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) %>" />
	</portlet:resourceURL>

	new Liferay.UADExport({
		exportProcessesNode: '#exportProcesses',
		exportProcessesResourceURL: '<%= exportProcessesURL.toString() %>',
		namespace: '<portlet:namespace />'
	});
</aui:script>