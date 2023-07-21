<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CPCatalogEntry> cpCatalogEntrySearchContainer = cpSearchResultsDisplayContext.getSearchContainer();
%>

<div class="m-0 mb-3 row">
	<div class="d-flex ml-auto pull-right">
		<p class="mb-auto mr-3 mt-auto">
			<liferay-ui:message arguments="<%= cpCatalogEntrySearchContainer.getTotal() %>" key="x-products-available" />
		</p>

		<button aria-expanded="false" aria-haspopup="true" class="btn btn-default commerce-order-by dropdown-toggle" data-toggle="dropdown" type="button">
			<c:set var="orderByColArgument">
				<span class="ml-1">
					<liferay-ui:message key="<%= cpSearchResultsDisplayContext.getOrderByCol() %>" />
				</span>
			</c:set>

			<liferay-ui:message arguments="${orderByColArgument}" key="sort-by-colon-x" />

			<aui:icon image="caret-double-l" markupView="lexicon" />
		</button>

		<div class="dropdown-menu dropdown-menu-right">

			<%
			String[] sortOptions = CPSearchResultsConstants.SORT_OPTIONS;

			for (String sortOption : sortOptions) {
			%>

				<clay:link
					elementClasses="dropdown-item transition-link"
					href="#"
					id="<%= renderResponse.getNamespace() + sortOption %>"
					label="<%= LanguageUtil.get(request, sortOption) %>"
					style="secondary"
				/>

				<aui:script>
					document
						.querySelector('#<%= renderResponse.getNamespace() + sortOption %>')
						.addEventListener('click', function(e) {
							e.preventDefault();
							<%= renderResponse.getNamespace() %>changeOrderBy('<%= sortOption %>');
						});
				</aui:script>

			<%
			}
			%>

		</div>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />changeOrderBy',
		function(orderBy) {
			var portletURL = new Liferay.PortletURL.createURL(
				'<%= themeDisplay.getURLCurrent() %>'
			);

			portletURL.setParameter('orderByCol', orderBy);
			portletURL.setPortletId('<%= portletDisplay.getId() %>');

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>