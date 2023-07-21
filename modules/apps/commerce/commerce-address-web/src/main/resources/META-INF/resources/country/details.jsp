<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceCountriesDisplayContext commerceCountriesDisplayContext = (CommerceCountriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCountry commerceCountry = commerceCountriesDisplayContext.getCommerceCountry();
long commerceCountryId = commerceCountriesDisplayContext.getCommerceCountryId();
CommerceRegionsStarter commerceRegionsStarter = commerceCountriesDisplayContext.getCommerceRegionsStarter();
%>

<portlet:actionURL name="editCommerceCountry" var="editCommerceCountryActionURL" />

<aui:form action="<%= editCommerceCountryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceCountry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceCountry == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="backURL" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceCountryId" type="hidden" value="<%= String.valueOf(commerceCountryId) %>" />

	<liferay-ui:error exception="<%= CommerceCountryAlreadyExistsException.class %>" message="the-two-letter-iso-code-is-already-used" />
	<liferay-ui:error exception="<%= CommerceCountryNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= CommerceCountryThreeLettersISOCodeException.class %>" message="please-enter-a-valid-three-letter-iso-code" />
	<liferay-ui:error exception="<%= CommerceCountryTwoLettersISOCodeException.class %>" message="please-enter-a-valid-two-letter-iso-code" />

	<aui:model-context bean="<%= commerceCountry %>" model="<%= CommerceCountry.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:fieldset>
				<aui:input autoFocus="<%= true %>" name="name" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.getBillingAllowed() %>" name="billingAllowed" type="toggle-switch" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.getShippingAllowed() %>" name="shippingAllowed" type="toggle-switch" />

				<aui:input label="two-letter-iso-code" name="twoLettersISOCode" />

				<aui:input label="three-letter-iso-code" name="threeLettersISOCode" />

				<aui:input name="numericISOCode" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.getSubjectToVAT() %>" name="subjectToVAT" type="toggle-switch" />

				<aui:input name="priority" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.isActive() %>" name="active" type="toggle-switch" />

				<c:if test="<%= commerceRegionsStarter != null %>">
					<aui:input name="key" type="hidden" value="<%= commerceRegionsStarter.getKey() %>" />

					<aui:button cssClass="btn-lg" disabled="<%= commerceCountriesDisplayContext.hasCommerceRegions(commerceCountry) %>" name="importCommerceRegionsButton" value='<%= LanguageUtil.get(request, "import-regions") %>' />
				</c:if>
			</aui:fieldset>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceCountry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<c:if test="<%= commerceRegionsStarter != null %>">
	<aui:script use="aui-io-request,aui-parse-content,liferay-notification">
		A.one('#<portlet:namespace />importCommerceRegionsButton').on('click', function(
			event
		) {
			var data = {
				<portlet:namespace />key: '<%= commerceRegionsStarter.getKey() %>'
			};

			this.attr('disabled', true);

			A.io.request(
				'<liferay-portlet:actionURL name="importCommerceRegions" portletName="<%= portletDisplay.getPortletName() %>" />',
				{
					data: data,
					on: {
						success: function(event, id, obj) {
							var response = JSON.parse(obj.response);

							if (!response.success) {
								A.one(
									'#<portlet:namespace />importCommerceRegionsButton'
								).attr('disabled', false);

								new Liferay.Notification({
									closeable: true,
									delay: {
										hide: 5000,
										show: 0
									},
									duration: 500,
									message:
										'<liferay-ui:message key="an-unexpected-error-occurred" />',
									render: true,
									title: '<liferay-ui:message key="danger" />',
									type: 'danger'
								});
							}
						}
					}
				}
			);
		});
	</aui:script>
</c:if>