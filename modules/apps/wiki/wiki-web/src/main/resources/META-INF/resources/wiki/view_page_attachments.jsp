<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(WikiWebKeys.WIKI_PAGE);

PortletURL portletURL = renderResponse.createActionURL();

portletURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
portletURL.setParameter("title", wikiPage.getTitle());

portletURL.setParameter(ActionRequest.ACTION_NAME, "/wiki/view");

PortalUtil.addPortletBreadcrumbEntry(request, wikiPage.getTitle(), portletURL.toString());

portletURL.setParameter(ActionRequest.ACTION_NAME, "/wiki/view_page_attachments");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "attachments"), portletURL.toString());
%>

<liferay-util:include page="/wiki/top_links.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/wiki/page_tabs.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tabs1" value="attachments" />
</liferay-util:include>

<%
List<FileEntry> attachmentsFileEntries = wikiPage.getAttachmentsFileEntries();
int attachmentsFileEntriesCount = wikiPage.getAttachmentsFileEntriesCount();
String emptyResultsMessage = "this-page-does-not-have-file-attachments";

PortletURL iteratorURL = renderResponse.createRenderURL();

iteratorURL.setParameter("mvcRenderCommandName", "/wiki/view_page_attachments");
iteratorURL.setParameter("redirect", currentURL);
iteratorURL.setParameter("nodeId", String.valueOf(node.getNodeId()));
iteratorURL.setParameter("title", wikiPage.getTitle());

boolean paginate = false;
boolean showPageAttachmentAction = false;
int status = WorkflowConstants.STATUS_APPROVED;
%>

<%@ include file="/wiki/attachments_list.jspf" %>