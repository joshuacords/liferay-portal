<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/portlet/init.jsp" %>

<%
PanelAppRegistry panelAppRegistry = (PanelAppRegistry)request.getAttribute(ApplicationListWebKeys.PANEL_APP_REGISTRY);
PanelCategoryRegistry panelCategoryRegistry = (PanelCategoryRegistry)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY_REGISTRY);

PanelCategory panelCategory = panelCategoryRegistry.getPanelCategory(ProductNavigationSimulationConstants.SIMULATION_PANEL_CATEGORY_KEY);
PanelCategoryHelper panelCategoryHelper = new PanelCategoryHelper(panelAppRegistry, panelCategoryRegistry);
%>

<div class="simulation-menu" data-qa-id="simulationMenuBody" id="<portlet:namespace />simulationPanelContainer">
	<div aria-multiselectable="true" class="panel-group" role="tablist">

		<%
		for (PanelApp panelApp : panelCategoryHelper.getAllPanelApps(panelCategory.getKey())) {
		%>

			<div class="panel panel-secondary">
				<div class="panel-header panel-heading" id="<portlet:namespace /><%= AUIUtil.normalizeId(panelApp.getKey()) %>Header" role="tab">
					<div class="panel-title">
						<div aria-controls="<portlet:namespace /><%= AUIUtil.normalizeId(panelApp.getKey()) %>Collapse" aria-expanded="<%= true %>" class="collapse-icon collapse-icon-middle panel-toggler" data-toggle="collapse" href="#<portlet:namespace /><%= AUIUtil.normalizeId(panelApp.getKey()) %>Collapse" role="button">
							<span class="category-name truncate-text"><%= panelApp.getLabel(locale) %></span>

							<aui:icon cssClass="collapse-icon-closed" image="angle-right" markupView="lexicon" />

							<aui:icon cssClass="collapse-icon-open" image="angle-down" markupView="lexicon" />
						</div>
					</div>
				</div>

				<div aria-expanded="<%= true %>" aria-labelledby="<portlet:namespace /><%= AUIUtil.normalizeId(panelApp.getKey()) %>Header" class="collapse in panel-collapse" id="<portlet:namespace /><%= AUIUtil.normalizeId(panelApp.getKey()) %>Collapse" role="tabpanel">
					<div class="simulation-app-panel-body">
						<liferay-application-list:panel-app
							panelApp="<%= panelApp %>"
						/>
					</div>
				</div>
			</div>

		<%
		}
		%>

	</div>
</div>