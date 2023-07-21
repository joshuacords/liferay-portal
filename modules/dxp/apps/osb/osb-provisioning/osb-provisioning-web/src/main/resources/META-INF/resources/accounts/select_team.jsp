<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String teamKey = ParamUtil.getString(request, "teamKey");

TeamSearchDisplayContext teamSearchDisplayContext = ProvisioningWebComponentProvider.getTeamSearchDisplayContext(renderRequest, renderResponse, request);

SearchContainer searchContainer = teamSearchDisplayContext.getSearchContainer();
%>

<div class="container-fluid container-fluid-max-xl">
	<clay:management-toolbar
		clearResultsURL="<%= teamSearchDisplayContext.getClearResultsURL() %>"
		itemsTotal="<%= searchContainer.getTotal() %>"
		searchActionURL="<%= currentURL %>"
		searchContainerId="teamContainer"
		searchFormName="searchFm"
		selectable="<%= false %>"
		showSearch="<%= true %>"
	/>

	<liferay-ui:search-container
		cssClass="details-search-container"
		id="teamContainer"
		searchContainer="<%= searchContainer %>"
		var="teamsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay"
			keyProperty="teamKey"
			modelVar="teamDisplay"
		>

			<%
			Map<String, Object> teamData = new HashMap<String, Object>();

			JSONObject jsonObject = JSONUtil.put(
				"key", teamDisplay.getKey()
			).put(
				"name", teamDisplay.getName()
			);

			teamData.put("key", jsonObject.toString());

			row.setData(teamData);

			if (teamKey.equals(teamDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="team-name"
				value="<%= HtmlUtil.escape(teamDisplay.getName()) %>"
			/>

			<%
			Account teamAccount = teamDisplay.getAccount();
			%>

			<liferay-ui:search-container-column-text
				name="account-name"
				value="<%= HtmlUtil.escape(teamAccount.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="account-code"
				value="<%= HtmlUtil.escape(teamAccount.getCode()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="partner-reseller-si-accounts"
				value="<%= teamDisplay.getPartnerAssignedAccountsCount() %>"
			/>

			<liferay-ui:search-container-column-text
				name="first-line-support-accounts"
				value="<%= teamDisplay.getFLSAssignedAccountsCount() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	function <portlet:namespace />resetActiveClass(nodes) {
		nodes.forEach(function(node) {
			node.classList.remove('active');
		});
	}

	function <portlet:namespace />resetFormData() {
		Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
			data: ''
		});
	}

	var searchContainer = document.getElementById(
		'<portlet:namespace />teamContainerSearchContainer'
	);

	if (searchContainer) {
		var entries = searchContainer.querySelectorAll('tbody tr');

		entries.forEach(function(entry) {
			entry.addEventListener('click', function() {
				<portlet:namespace />resetActiveClass(entries);

				entry.classList.add('active');

				var rowData = entry.dataset;

				if (rowData) {
					Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
						data: rowData.key
					});
				}
			});
		});
	}

	var paginationPages = document.querySelectorAll('.pagination-bar .page-link');

	paginationPages.forEach(function(page) {
		page.addEventListener('click', <portlet:namespace />resetFormData);
	});

	var searchForm = document.querySelector('form');

	if (searchForm) {
		searchForm.addEventListener('submit', <portlet:namespace />resetFormData);
	}
</aui:script>