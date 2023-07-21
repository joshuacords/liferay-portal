<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDiscountRuleDisplayContext commerceDiscountRuleDisplayContext = (CommerceDiscountRuleDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceDiscountRule> commerceDiscountRuleSearchContainer = commerceDiscountRuleDisplayContext.getCommerceDiscountRuleSearchContainer();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceDiscountRules"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			disabled="<%= true %>"
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= commerceDiscountRuleDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="list"
		/>

		<portlet:renderURL var="addCommerceDiscountRuleURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceDiscountRule" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceDiscountId" value="<%= String.valueOf(commerceDiscountRuleDisplayContext.getCommerceDiscountId()) %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu
			inline="<%= true %>"
		>
			<liferay-frontend:add-menu-item
				title='<%= LanguageUtil.get(request, "add-discount-rule") %>'
				url="<%= addCommerceDiscountRuleURL.toString() %>"
			/>
		</liferay-frontend:add-menu>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceDiscountRuleSearchContainer.getOrderByCol() %>"
			orderByType="<%= commerceDiscountRuleSearchContainer.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= commerceDiscountRuleDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button
			href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceDiscountRules();" %>'
			icon="times"
			label="delete"
		/>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280">
	<portlet:actionURL name="editCommerceDiscountRule" var="editCommerceDiscountRuleActionURL" />

	<aui:form action="<%= editCommerceDiscountRuleActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="deleteCommerceDiscountRuleIds" type="hidden" />

		<liferay-ui:search-container
			id="commerceDiscountRules"
			searchContainer="<%= commerceDiscountRuleSearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.discount.model.CommerceDiscountRule"
				keyProperty="commerceDiscountRuleId"
				modelVar="commerceDiscountRule"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="editCommerceDiscountRule" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commerceDiscountId" value="<%= String.valueOf(commerceDiscountRule.getCommerceDiscountId()) %>" />
					<portlet:param name="commerceDiscountRuleId" value="<%= String.valueOf(commerceDiscountRule.getCommerceDiscountRuleId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= rowURL %>"
					name="type"
					value="<%= HtmlUtil.escape(LanguageUtil.get(request, commerceDiscountRule.getType())) %>"
				/>

				<liferay-ui:search-container-column-jsp
					cssClass="entry-action-column"
					path="/discount_rule_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />deleteCommerceDiscountRules() {
		if (
			confirm(
				'<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-discount-rules" />'
			)
		) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('deleteCommerceDiscountRuleIds').val(
				Liferay.Util.listCheckedExcept(
					form,
					'<portlet:namespace />allRowIds'
				)
			);

			submitForm(form);
		}
	}
</aui:script>