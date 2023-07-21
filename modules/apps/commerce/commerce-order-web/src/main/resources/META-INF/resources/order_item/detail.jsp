<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>
<!-- test order_details.jsp -->

<%
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();
CommerceOrderItem commerceOrderItem = commerceOrderEditDisplayContext.getCommerceOrderItem();

CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

Date requestedDeliveryDate = commerceOrderItem.getRequestedDeliveryDate();
%>

<portlet:actionURL name="editCommerceOrderItem" var="editCommerceOrderItemActionURL" />

<commerce-ui:panel
	title='<%= LanguageUtil.get(request, "detail") %>'
>
	<aui:form action="<%= editCommerceOrderItemActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderId() %>" />
		<aui:input name="commerceOrderItemId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderItemId() %>" />

		<liferay-ui:error exception="<%= CommerceOrderItemRequestedDeliveryDateException.class %>" message="please-enter-a-valid-requested-delivery-date" />

		<liferay-ui:error exception="<%= CommerceOrderValidatorException.class %>">

			<%
			CommerceOrderValidatorException cove = (CommerceOrderValidatorException)errorException;
			%>

			<c:if test="<%= cove != null %>">

				<%
				for (CommerceOrderValidatorResult commerceOrderValidatorResult : cove.getCommerceOrderValidatorResults()) {
				%>

					<liferay-ui:message key="<%= HtmlUtil.escape(commerceOrderValidatorResult.getLocalizedMessage()) %>" />

				<%
				}
				%>

			</c:if>
		</liferay-ui:error>

		<aui:input bean="<%= commerceOrderItem %>" model="<%= CommerceOrderItem.class %>" name="quantity">
			<aui:validator name="min">1</aui:validator>
			<aui:validator name="number" />
		</aui:input>

		<c:if test="<%= !commerceOrder.isOpen() %>">
			<aui:input name="price" suffix="<%= HtmlUtil.escape(commerceCurrency.getCode()) %>" type="text" value="<%= commerceCurrency.round(commerceOrderItem.getUnitPrice()) %>">
				<aui:validator name="min">0</aui:validator>
				<aui:validator name="number" />
			</aui:input>
		</c:if>

		<%
		int requestedDeliveryDay = 0;
		int requestedDeliveryMonth = -1;
		int requestedDeliveryYear = 0;

		if (requestedDeliveryDate != null) {
			Calendar calendar = CalendarFactoryUtil.getCalendar(requestedDeliveryDate.getTime());

			requestedDeliveryDay = calendar.get(Calendar.DAY_OF_MONTH);
			requestedDeliveryMonth = calendar.get(Calendar.MONTH);
			requestedDeliveryYear = calendar.get(Calendar.YEAR);
		}
		%>

		<div class="form-group input-date-wrapper">
			<label for="requestedDeliveryDate"><liferay-ui:message key="requested-delivery-date" /></label>

			<liferay-ui:input-date
				dayParam="requestedDeliveryDateDay"
				dayValue="<%= requestedDeliveryDay %>"
				disabled="<%= false %>"
				monthParam="requestedDeliveryDateMonth"
				monthValue="<%= requestedDeliveryMonth %>"
				name="requestedDeliveryDate"
				nullable="<%= true %>"
				showDisableCheckbox="<%= false %>"
				yearParam="requestedDeliveryDateYear"
				yearValue="<%= requestedDeliveryYear %>"
			/>
		</div>

		<aui:input bean="<%= commerceOrderItem %>" model="<%= CommerceOrderItem.class %>" name="deliveryGroup" />

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</commerce-ui:panel>