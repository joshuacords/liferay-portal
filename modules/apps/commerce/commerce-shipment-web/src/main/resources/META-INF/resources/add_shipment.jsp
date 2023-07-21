<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="editCommerceShipment" var="editCommerceShipmentActionURL" />

<commerce-ui:modal-content
	title='<%= LanguageUtil.get(request, "create-new-shipment") %>'
>
	<aui:form action="<%= editCommerceShipmentActionURL %>" cssClass="container-fluid-1280" method="post" name="form">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />

		<aui:select id="commerceChannelGroupId" label="channel" name="commerceChannelGroupId" required="<%= true %>" showEmptyOption="<%= true %>">

			<%
			for (CommerceChannel commerceChannel : commerceShipmentDisplayContext.getCommerceChannels()) {
			%>

				<aui:option label="<%= commerceChannel.getName() %>" value="<%= commerceChannel.getGroupId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select id="commerceAccountId" label="account" name="commerceAccountId" required="<%= true %>" showEmptyOption="<%= true %>">

			<%
			for (CommerceAccount commerceAccount : commerceShipmentDisplayContext.getCommerceAccountsWithShippableOrders()) {
			%>

				<aui:option label="<%= commerceAccount.getName() %>" value="<%= commerceAccount.getCommerceAccountId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select label="address" name="commerceAddressId" required="<%= true %>" showEmptyOption="<%= true %>" />
	</aui:form>
</commerce-ui:modal-content>

<aui:script use="aui-base">
	var commerceAccount = <portlet:namespace />form.querySelector(
		'select[name=<portlet:namespace />commerceAccountId]'
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateAddressField',
		function <portlet:namespace />updateAddressField(commerceAccountId) {
			return fetch(
				'/o/headless-commerce-admin-account/v1.0/accounts/' +
					commerceAccountId +
					'/accountAddresses/',
				{
					credentials: 'include',
					headers: new Headers({
						Accept: 'application/json',
						'Content-Type': 'application/json',
						'x-csrf-token': Liferay.authToken
					}),
					method: 'GET'
				}
			)
				.then(function(response) {
					return response.json();
				})
				.then(function(response) {
					var select = A.one('#<portlet:namespace />commerceAddressId');

					response.items.forEach(function(item) {
						var option = A.Node.create(
							'<option id="<portlet:namespace />commerceAddressId-' +
								item.id +
								'" value="' +
								item.id +
								'">' +
								item.street1 +
								' - ' +
								item.city +
								' - ' +
								item.regionISOCode +
								' - ' +
								item.countryISOCode +
								'</option>'
						);

						select.append(option);
					});

					select.show();
				});
		}
	);

	if (commerceAccount) {
		commerceAccount.addEventListener('change', function() {
			if (commerceAccount.value) {
				<portlet:namespace />updateAddressField(commerceAccount.value);
			}
		});
	}
</aui:script>