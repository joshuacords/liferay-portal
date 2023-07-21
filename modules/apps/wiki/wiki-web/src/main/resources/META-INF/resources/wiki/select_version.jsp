<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute(WikiWebKeys.WIKI_PAGE);

double sourceVersion = ParamUtil.getDouble(request, "sourceVersion");
String eventName = ParamUtil.getString(request, "eventName", renderResponse.getNamespace() + "selectVersionFm");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/wiki/select_version");
portletURL.setParameter("redirect", currentURL);
portletURL.setParameter("nodeId", String.valueOf(wikiPage.getNodeId()));
portletURL.setParameter("title", HtmlUtil.unescape(wikiPage.getTitle()));
portletURL.setParameter("sourceVersion", String.valueOf(sourceVersion));
%>

<div class="container-fluid-1280">
	<aui:form action="<%= portletURL %>" method="post" name="selectVersionFm">
		<liferay-ui:search-container
			id="wikiPageVersionSearchContainer"
			iteratorURL="<%= portletURL %>"
			total="<%= WikiPageLocalServiceUtil.getPagesCount(wikiPage.getNodeId(), wikiPage.getTitle()) %>"
		>
			<liferay-ui:search-container-results
				results="<%= WikiPageLocalServiceUtil.getPages(wikiPage.getNodeId(), wikiPage.getTitle(), searchContainer.getStart(), searchContainer.getEnd(), new PageVersionComparator()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.wiki.model.WikiPage"
				modelVar="curWikiPage"
			>
				<liferay-ui:search-container-column-text
					name="version"
				>
					<c:choose>
						<c:when test="<%= sourceVersion != curWikiPage.getVersion() %>">

							<%
							double curSourceVersion = sourceVersion;
							double curTargetVersion = curWikiPage.getVersion();

							if (curTargetVersion < curSourceVersion) {
								double tempVersion = curTargetVersion;

								curTargetVersion = curSourceVersion;
								curSourceVersion = tempVersion;
							}

							Map<String, Object> data = new HashMap<String, Object>();

							data.put("sourceversion", curSourceVersion);
							data.put("targetversion", curTargetVersion);
							%>

							<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
								<%= String.valueOf(curWikiPage.getVersion()) %>
							</aui:a>
						</c:when>
						<c:otherwise>
							<%= curWikiPage.getVersion() %>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-date
					name="date"
					value="<%= curWikiPage.getModifiedDate() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectVersionFm',
		'<%= HtmlUtil.escapeJS(eventName) %>'
	);
</aui:script>