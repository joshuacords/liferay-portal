<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Map<String, Object> contextObjects = new HashMap<>();

CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

contextObjects.put("commerceOrderContentDisplayContext", commerceOrderContentDisplayContext);

CommerceAccount commerceAccount = commerceOrderContentDisplayContext.getCommerceAccount();
%>

<liferay-ddm:template-renderer
	className="<%= CommerceOpenOrderContentPortlet.class.getName() %>"
	contextObjects="<%= contextObjects %>"
	displayStyle="<%= commerceOrderContentDisplayContext.getDisplayStyle(CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT) %>"
	displayStyleGroupId="<%= commerceOrderContentDisplayContext.getDisplayStyleGroupId(CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT) %>"
	entries="<%= commerceOrderContentDisplayContext.getCommerceOrders() %>"
>
	<commerce-ui:dataset-display
		dataProviderKey="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PENDING_ORDERS %>"
		id="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PENDING_ORDERS %>"
		itemsPerPage="<%= 10 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
		style="stacked"
	/>

	<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

	<div class="commerce-cta is-visible">
		<c:if test="<%= commerceOrderContentDisplayContext.hasPermission(CommerceOrderActionKeys.ADD_COMMERCE_ORDER) %>">
			<aui:form action="<%= editCommerceOrderURL %>" method="post" name="fm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="deleteCommerceOrderIds" type="hidden" />

				<clay:button
					disabled="<%= commerceAccount == null %>"
					elementClasses="btn-fixed btn-primary"
					label='<%= LanguageUtil.get(request, "add-order") %>'
					size="lg"
					style="primary"
					type="submit"
				/>
			</aui:form>
		</c:if>
	</div>
</liferay-ddm:template-renderer>