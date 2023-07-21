<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PayPalGroupServiceConfiguration payPalGroupServiceConfiguration = (PayPalGroupServiceConfiguration)request.getAttribute(PayPalGroupServiceConfiguration.class.getName());
%>

<portlet:actionURL name="editPayPalCommercePaymentMethodConfiguration" var="editCommercePaymentMethodActionURL" />

<aui:form action="<%= editCommercePaymentMethodActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="commerceChannelId" type="hidden" value='<%= ParamUtil.getLong(request, "commerceChannelId") %>' />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<commerce-ui:panel>
		<commerce-ui:info-box
			title="authentication"
		>
			<div class="alert alert-info">
				<%= LanguageUtil.format(resourceBundle, "paypal-configuration-help", new Object[] {"<a href=\"https://developer.paypal.com/developer/applications/create\" target=\"_blank\">", "</a>"}, false) %>
			</div>

			<aui:input id="paypal-client-id" label="client-id" name="settings--clientId--" value="<%= payPalGroupServiceConfiguration.clientId() %>" />

			<aui:input id="paypal-client-secret" label="client-secret" name="settings--clientSecret--" value="<%= payPalGroupServiceConfiguration.clientSecret() %>" />

			<aui:select id="paypal-settings--mode" name="settings--mode--">

				<%
				for (String mode : PayPalCommercePaymentMethodConstants.MODES) {
				%>

					<aui:option label="<%= mode %>" selected="<%= mode.equals(payPalGroupServiceConfiguration.mode()) %>" value="<%= mode %>" />

				<%
				}
				%>

			</aui:select>
		</commerce-ui:info-box>

		<commerce-ui:info-box
			title="options"
		>
			<aui:input id="paypal-payment-attempts-max-count" label="payment-attempts-max-count" name="settings--paymentAttempts--" value="<%= payPalGroupServiceConfiguration.paymentAttemptsMaxCount() %>" />
		</commerce-ui:info-box>
	</commerce-ui:panel>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>