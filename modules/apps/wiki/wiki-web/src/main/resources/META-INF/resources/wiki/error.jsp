<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);

String title = ParamUtil.getString(request, "title");

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);

	String backURL = request.getHeader(HttpHeaders.REFERER);

	if (Validator.isNull(backURL)) {
		WikiURLHelper wikiURLHelper = new WikiURLHelper(wikiRequestHelper, renderResponse, wikiGroupServiceConfiguration);

		PortletURL backToViewPagesURL = wikiURLHelper.getBackToViewPagesURL(node);

		backURL = backToViewPagesURL.toString();
	}

	portletDisplay.setURLBack(backURL);

	renderResponse.setTitle(title);
}
%>

<c:if test="<%= !portletTitleBasedNavigation %>">
	<liferay-ui:error-header />
</c:if>

<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="please-enter-a-valid-page-title" />
<liferay-ui:error exception="<%= PageVersionException.class %>" message="another-user-made-changes-that-are-pending-publication" />

<c:if test="<%= SessionErrors.contains(renderRequest, NoSuchPageException.class.getName()) %>">

	<%
	long nodeId = ParamUtil.getLong(request, "nodeId");

	if ((nodeId == 0) && (node != null)) {
		nodeId = node.getNodeId();
	}

	PortletURL editPageURL = renderResponse.createRenderURL();

	editPageURL.setParameter("mvcRenderCommandName", "/wiki/edit_page");
	editPageURL.setParameter("redirect", currentURL);
	editPageURL.setParameter("nodeId", String.valueOf(nodeId));
	editPageURL.setParameter("title", title);

	PortletURL searchURL = renderResponse.createRenderURL();

	searchURL.setParameter("mvcRenderCommandName", "/wiki/search");
	searchURL.setParameter("redirect", currentURL);
	searchURL.setParameter("nodeId", String.valueOf(nodeId));
	searchURL.setParameter("keywords", title);
	%>

	<div <%= portletTitleBasedNavigation ? "class=\"container-fluid-1280\"" : StringPool.BLANK %>>
		<div class="main-content-card panel">
			<div class="panel-body">
				<div class="alert alert-info">
					<liferay-ui:message key="this-page-is-empty.-use-the-buttons-below-to-create-it-or-to-search-for-the-words-in-the-title" />
				</div>

				<div class="btn-toolbar">

					<%
					String taglibEditPage = "location.href = '" + editPageURL.toString() + "';";
					%>

					<aui:button onClick="<%= taglibEditPage %>" primary="<%= true %>" value='<%= LanguageUtil.format(request, "create-page-x", HtmlUtil.escapeAttribute(title), false) %>' />

					<%
					String taglibSearch = "location.href = '" + searchURL.toString() + "';";
					%>

					<aui:button cssClass="btn-secondary" onClick="<%= taglibSearch %>" value='<%= LanguageUtil.format(request, "search-for-x", HtmlUtil.escapeAttribute(title), false) %>' />
				</div>
			</div>
		</div>
	</div>
</c:if>

<liferay-ui:error exception="<%= PageTitleException.class %>" message="please-enter-a-valid-page-title" />

<liferay-ui:error-principal />