<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/definition_link/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

String randomNamespace = (String)row.getParameter("randomNamespace");

WorkflowDefinitionLinkSearchEntry workflowDefinitionLinkSearchEntry = (WorkflowDefinitionLinkSearchEntry)row.getObject();

Map<String, String> resourceTooltips = (Map<String, String>)row.getParameter("resourceTooltips");

String classname = workflowDefinitionLinkSearchEntry.getClassName();

String resource = workflowDefinitionLinkSearchEntry.getResource();
%>

<c:choose>
	<c:when test="<%= resourceTooltips.containsKey(classname) %>">
		<div class="workflow-definition-link-resource">
			<span id="<%= randomNamespace %>resourceTooltip">
				<%= resource %>
				<div class="clay-tooltip-right tooltip" id="<%= randomNamespace %>tooltip" role="tooltip">
					<div class="arrow"></div>
					<div class="tooltip-inner">
						<div><%= resourceTooltips.get(classname) %></div>
					</div>
				</div>
			</span>
		</div>
	</c:when>
	<c:otherwise><span><%= resource %></span></c:otherwise>
</c:choose>

<aui:script require="metal-dom/src/dom">
	var dom = metalDomSrcDom.default;

	var tooltip = document.getElementById('<%= randomNamespace %>tooltip');

	var resourceTooltip = document.getElementById(
		'<%= randomNamespace %>resourceTooltip'
	);

	if (resourceTooltip) {
		resourceTooltip.addEventListener('mouseover', function() {
			dom.toggleClasses(tooltip, 'show');
		});

		resourceTooltip.addEventListener('mouseout', function() {
			dom.toggleClasses(tooltip, 'show');
		});
	}
</aui:script>