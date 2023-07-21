<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String messageXml = null;

MoneyOrderGroupServiceConfiguration moneyOrderGroupServiceConfiguration = (MoneyOrderGroupServiceConfiguration)request.getAttribute(MoneyOrderGroupServiceConfiguration.class.getName());

LocalizedValuesMap messageLocalizedValuesMap = moneyOrderGroupServiceConfiguration.message();

if (messageLocalizedValuesMap != null) {
	messageXml = LocalizationUtil.getXml(messageLocalizedValuesMap, "message");
}
%>

<portlet:actionURL name="editMoneyOrderCommercePaymentMethodConfiguration" var="editCommercePaymentMethodActionURL" />

<aui:form action="<%= editCommercePaymentMethodActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="commerceChannelId" type="hidden" value='<%= ParamUtil.getLong(request, "commerceChannelId") %>' />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<commerce-ui:panel>
		<aui:input helpMessage="this-toggles-whether-the-money-order-message-page-is-shown-as-a-checkout-step-or-not" label="show-message-page" labelOff="no" labelOn="yes" name="settings--showMessagePage--" type="toggle-switch" value="<%= moneyOrderGroupServiceConfiguration.showMessagePage() %>" />

		<div id="<portlet:namespace />message">
			<aui:field-wrapper label="message">
				<liferay-ui:input-localized
					editorName="alloyeditor"
					fieldPrefix="settings"
					fieldPrefixSeparator="--"
					name="message"
					type="editor"
					xml="<%= messageXml %>"
				/>
			</aui:field-wrapper>
		</div>
	</commerce-ui:panel>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes(
		'<portlet:namespace />showMessagePage',
		'<portlet:namespace />message'
	);
</aui:script>