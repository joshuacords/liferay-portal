<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/control_menu/init.jsp" %>

<%
ProductNavigationControlMenuCategoryRegistry productNavigationControlMenuCategoryRegistry = ServletContextUtil.getProductNavigationControlMenuCategoryRegistry();

List<ProductNavigationControlMenuCategory> productNavigationControlMenuCategories = productNavigationControlMenuCategoryRegistry.getProductNavigationControlMenuCategories(ProductNavigationControlMenuCategoryKeys.ROOT);

ProductNavigationControlMenuEntryRegistry productNavigationControlMenuEntryRegistry = ServletContextUtil.getProductNavigationControlMenuEntryRegistry();

boolean hasControlMenuEntries = false;

Map<ProductNavigationControlMenuCategory, List<ProductNavigationControlMenuEntry>> productNavigationControlMenuEntriesMap = new LinkedHashMap<>();

for (ProductNavigationControlMenuCategory productNavigationControlMenuCategory : productNavigationControlMenuCategories) {
	List<ProductNavigationControlMenuEntry> productNavigationControlMenuEntries = productNavigationControlMenuEntryRegistry.getProductNavigationControlMenuEntries(productNavigationControlMenuCategory, request);

	productNavigationControlMenuEntriesMap.put(productNavigationControlMenuCategory, productNavigationControlMenuEntries);

	if (!productNavigationControlMenuEntries.isEmpty()) {
		hasControlMenuEntries = true;
	}
}
%>

<c:if test="<%= hasControlMenuEntries %>">
	<div class="control-menu control-menu-level-1 d-print-none" data-qa-id="controlMenu" id="<portlet:namespace />ControlMenu">
		<div class="container-fluid container-fluid-max-xl">
			<h1 class="sr-only"><liferay-ui:message key="admin-header" /></h1>

			<ul class="control-menu-level-1-nav control-menu-nav" data-namespace="<portlet:namespace />" data-qa-id="header" id="<portlet:namespace />controlMenu">

				<%
				for (Map.Entry entry : productNavigationControlMenuEntriesMap.entrySet()) {
					ProductNavigationControlMenuCategory productNavigationControlMenuCategory = (ProductNavigationControlMenuCategory)entry.getKey();
					List<ProductNavigationControlMenuEntry> productNavigationControlMenuEntries = (List<ProductNavigationControlMenuEntry>)entry.getValue();
				%>

					<li class="control-menu-nav-category <%= productNavigationControlMenuCategory.getKey() %>-control-group">
						<ul class="control-menu-nav" role="<%= (productNavigationControlMenuEntries.size() == 1) ? "presentation" : "menu" %>">

							<%
							for (ProductNavigationControlMenuEntry productNavigationControlMenuEntry : (List<ProductNavigationControlMenuEntry>)productNavigationControlMenuEntries) {
								if (productNavigationControlMenuEntry.includeIcon(request, PipingServletResponse.createPipingServletResponse(pageContext))) {
									continue;
								}
							%>

								<li class="control-menu-nav-item">
									<liferay-ui:icon
										data="<%= productNavigationControlMenuEntry.getData(request) %>"
										icon="<%= productNavigationControlMenuEntry.getIcon(request) %>"
										iconCssClass="<%= productNavigationControlMenuEntry.getIconCssClass(request) %>"
										label="<%= false %>"
										linkCssClass='<%= "control-menu-icon " + productNavigationControlMenuEntry.getLinkCssClass(request) %>'
										markupView="<%= productNavigationControlMenuEntry.getMarkupView(request) %>"
										message="<%= productNavigationControlMenuEntry.getLabel(locale) %>"
										method="get"
										url="<%= productNavigationControlMenuEntry.getURL(request) %>"
									/>
								</li>

							<%
							}
							%>

						</ul>
					</li>

				<%
				}
				%>

			</ul>
		</div>

		<div class="control-menu-body">

			<%
			for (ProductNavigationControlMenuCategory productNavigationControlMenuCategory : productNavigationControlMenuCategories) {
				List<ProductNavigationControlMenuEntry> productNavigationControlMenuEntries = productNavigationControlMenuEntriesMap.get(productNavigationControlMenuCategory);

				for (ProductNavigationControlMenuEntry productNavigationControlMenuEntry : productNavigationControlMenuEntries) {
					productNavigationControlMenuEntry.includeBody(request, PipingServletResponse.createPipingServletResponse(pageContext));
				}
			}
			%>

		</div>

		<div id="controlMenuAlertsContainer"></div>
	</div>

	<aui:script use="liferay-product-navigation-control-menu">
		Liferay.ControlMenu.init('#<portlet:namespace />controlMenu');

		var sidenavToggles = document.querySelectorAll(
			'#<portlet:namespace />ControlMenu [data-toggle="liferay-sidenav"]'
		);

		var sidenavInstances = Array.from(sidenavToggles).map(function(toggle) {
			return Liferay.SideNavigation.instance(toggle);
		});

		sidenavInstances.forEach(function(instance) {
			instance.on('openStart.lexicon.sidenav', function(event, source) {
				sidenavInstances.forEach(function(sidenav) {
					if (sidenav !== source) {
						sidenav.hide();
					}
				});
			});
		});
	</aui:script>
</c:if>