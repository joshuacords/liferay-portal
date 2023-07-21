<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/blogs_aggregator/init.jsp" %>

<%
boolean blogsPortletFound = ParamUtil.getBoolean(request, "blogsPortletFound", true);
%>

<c:if test="<%= !blogsPortletFound %>">
	<clay:stripe
		message='<%= LanguageUtil.get(resourceBundle, "no-suitable-application-found-to-display-the-blogs-entry") %>'
		style="danger"
		title='<%= LanguageUtil.get(resourceBundle, "error") + ":" %>'
	/>
</c:if>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/blogs_aggregator/view");

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

List entries = null;

if (selectionMethod.equals("users")) {
	if (organizationId > 0) {
		entries = BlogsEntryServiceUtil.getOrganizationEntries(organizationId, new Date(), WorkflowConstants.STATUS_APPROVED, max);
	}
	else {
		entries = BlogsEntryServiceUtil.getGroupsEntries(company.getCompanyId(), scopeGroupId, new Date(), WorkflowConstants.STATUS_APPROVED, max);
	}
}
else {
	entries = BlogsEntryServiceUtil.getGroupEntries(scopeGroupId, new Date(), WorkflowConstants.STATUS_APPROVED, max);
}

searchContainer.setTotal(entries.size());

List results = ListUtil.subList(entries, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);
%>

<%@ include file="/blogs_aggregator/view_entries.jspf" %>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(
			document.<portlet:namespace />fm1.<portlet:namespace />keywords
		);
	</aui:script>
</c:if>