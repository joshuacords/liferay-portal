<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String parentAccountKey = ParamUtil.getString(request, "parentAccountKey");

AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);

SearchContainer accountSearchContainer = accountSearchDisplayContext.getSearchContainer();
%>

<div class="container-fluid container-fluid-max-xl">
	<clay:management-toolbar
		clearResultsURL="<%= accountSearchDisplayContext.getClearResultsURL() %>"
		itemsTotal="<%= accountSearchContainer.getTotal() %>"
		searchActionURL="<%= currentURL %>"
		searchContainerId="accountContainer"
		selectable="<%= false %>"
		showSearch="<%= true %>"
	/>

	<liferay-ui:search-container
		cssClass="details-search-container"
		id="accountContainer"
		searchContainer="<%= accountSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			keyProperty="accountKey"
			modelVar="accountDisplay"
		>

			<%
			Map<String, Object> accountData = new HashMap<String, Object>();

			JSONObject jsonObject = JSONUtil.put(
				"key", accountDisplay.getKey()
			).put(
				"name", accountDisplay.getName()
			);

			accountData.put("key", jsonObject.toString());

			row.setData(accountData);

			if (Validator.isNotNull(parentAccountKey) && parentAccountKey.equals(accountDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-end-date"
				value="<%= accountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				name="partner"
				value="<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="region"
				value="<%= accountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				name="sla-tier"
			>
				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= accountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>">
					<%= accountDisplay.getSubscriptionState() %>
				</span>
			</liferay-ui:search-container-column-text>
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
		'<portlet:namespace />accountContainerSearchContainer'
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