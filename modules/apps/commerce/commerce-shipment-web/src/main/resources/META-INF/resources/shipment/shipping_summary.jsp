<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceShipment commerceShipment = commerceShipmentDisplayContext.getCommerceShipment();

String trackingNumber = commerceShipment.getTrackingNumber();
String carrier = commerceShipment.getCarrier();
Date expectedDate = commerceShipment.getExpectedDate();
Date shippingDate = commerceShipment.getShippingDate();

Format dateFormat = FastDateFormatFactoryUtil.getDate(DateFormat.MEDIUM, locale, user.getTimeZone());
%>

<div class="mb-4">
	<commerce-ui:step-tracker
		steps="<%= commerceShipmentDisplayContext.getShipmentSteps() %>"
	/>
</div>

<commerce-ui:panel
	elementClasses="flex-fill"
	title='<%= LanguageUtil.get(request, "details") %>'
>
	<div class="row vertically-divided">
		<div class="col-xl-4">
			<liferay-portlet:renderURL var="editCommerceShipmentCourierDetailURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="editCommerceShipmentCourierDetail" />
				<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
			</liferay-portlet:renderURL>

			<commerce-ui:modal
				id="edit-courier-modal"
				refreshPageOnClose="<%= true %>"
				url="<%= editCommerceShipmentCourierDetailURL %>"
			/>

			<commerce-ui:info-box
				actionLabel='<%= LanguageUtil.get(request, "edit") %>'
				actionTargetId="edit-courier-modal"
				actionUrl="<%= editCommerceShipmentCourierDetailURL %>"
				title='<%= LanguageUtil.get(request, "carrier-details") %>'
			>
				<div class="item">
					<span class="title">
						<liferay-ui:message key="carrier" />
					</span>

					<c:choose>
						<c:when test="<%= Validator.isBlank(carrier) %>">
							<span class="text-muted"><liferay-ui:message key="click-edit-to-insert" /></span>
						</c:when>
						<c:otherwise>
							<b><%= HtmlUtil.escape(carrier) %></b>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="item">
					<span class="title">
						<liferay-ui:message key="tracking-number" />
					</span>

					<c:choose>
						<c:when test="<%= Validator.isBlank(trackingNumber) %>">
							<span class="text-muted"><liferay-ui:message key="click-edit-to-insert" /></span>
						</c:when>
						<c:otherwise>
							<b><%= HtmlUtil.escape(trackingNumber) %></b>
						</c:otherwise>
					</c:choose>
				</div>
			</commerce-ui:info-box>
		</div>

		<div class="col-xl-4">
			<liferay-portlet:renderURL var="editCommerceShipmentAddressURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="editCommerceShipmentAddress" />
				<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
			</liferay-portlet:renderURL>

			<commerce-ui:modal
				id="edit-address-modal"
				refreshPageOnClose="<%= true %>"
				size="lg"
				url="<%= editCommerceShipmentAddressURL %>"
			/>

			<commerce-ui:info-box
				title='<%= LanguageUtil.get(request, "shipping-address") %>'
			>
				<div class="item">
					<%= HtmlUtil.replaceNewLine(HtmlUtil.escape(commerceShipmentDisplayContext.getDescriptiveShippingAddress())) %>
				</div>
			</commerce-ui:info-box>

			<commerce-ui:info-box
				title='<%= LanguageUtil.get(request, "channel") %>'
			>
				<div class="item">
					<%= HtmlUtil.escape(commerceShipmentDisplayContext.getCommerceChannelName()) %>
				</div>
			</commerce-ui:info-box>
		</div>

		<div class="col-xl-4">
			<liferay-portlet:renderURL var="editCommerceShipmentShippingDateURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="editCommerceShipmentShippingDate" />
				<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
			</liferay-portlet:renderURL>

			<commerce-ui:modal
				id="edit-shipping-date-modal"
				refreshPageOnClose="<%= true %>"
				size="lg"
				url="<%= editCommerceShipmentShippingDateURL %>"
			/>

			<commerce-ui:info-box
				actionLabel='<%= LanguageUtil.get(request, "edit") %>'
				actionTargetId="edit-shipping-date-modal"
				actionUrl=""
				title='<%= LanguageUtil.get(request, "estimated-shipping-date") %>'
			>
				<div class="item">
					<c:choose>
						<c:when test="<%= Validator.isNull(shippingDate) %>">
							<span class="text-muted"><liferay-ui:message key="click-edit-to-insert" /></span>
						</c:when>
						<c:otherwise>
							<b><%= dateFormat.format(shippingDate) %></b>
						</c:otherwise>
					</c:choose>
				</div>
			</commerce-ui:info-box>

			<liferay-portlet:renderURL var="editCommerceShipmentExpectedDateURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="editCommerceShipmentExpectedDate" />
				<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
			</liferay-portlet:renderURL>

			<commerce-ui:modal
				id="edit-expected-date-modal"
				refreshPageOnClose="<%= true %>"
				size="lg"
				url="<%= editCommerceShipmentExpectedDateURL %>"
			/>

			<commerce-ui:info-box
				actionLabel='<%= LanguageUtil.get(request, "edit") %>'
				actionTargetId="edit-expected-date-modal"
				actionUrl=""
				title='<%= LanguageUtil.get(request, "estimated-delivery-date") %>'
			>
				<div class="item">
					<c:choose>
						<c:when test="<%= Validator.isNull(expectedDate) %>">
							<span class="text-muted"><liferay-ui:message key="click-edit-to-insert" /></span>
						</c:when>
						<c:otherwise>
							<b><%= dateFormat.format(expectedDate) %></b>
						</c:otherwise>
					</c:choose>
				</div>
			</commerce-ui:info-box>
		</div>
	</div>
</commerce-ui:panel>

<commerce-ui:panel
	bodyClasses="p-0"
	title='<%= LanguageUtil.get(request, "products") %>'
>

	<%
	Map<String, String> contextParams = new HashMap<>();

	contextParams.put("commerceShipmentId", String.valueOf(commerceShipment.getCommerceShipmentId()));
	%>

	<commerce-ui:dataset-display
		clayCreationMenu="<%= commerceShipmentDisplayContext.getShipmentItemClayCreationMenu() %>"
		contextParams="<%= contextParams %>"
		dataProviderKey="<%= CommerceShipmentDataSetConstants.COMMERCE_DATA_SET_KEY_SHIPMENT_ITEMS %>"
		id="<%= commerceShipmentDisplayContext.getDatasetView() %>"
		itemsPerPage="<%= 10 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= currentURLObj %>"
		showSearch="<%= false %>"
	/>
</commerce-ui:panel>