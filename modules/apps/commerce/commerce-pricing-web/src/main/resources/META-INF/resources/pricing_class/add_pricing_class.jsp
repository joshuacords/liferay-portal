<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePricingClassDisplayContext commercePricingClassDisplayContext = (CommercePricingClassDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL editPricingClassPortletURL = commercePricingClassDisplayContext.getEditCommercePricingClassRenderURL();

Locale defaultLocale = LocaleUtil.getSiteDefault();

String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);
%>

<portlet:actionURL name="editCommercePricingClass" var="editCommercePricingClassActionURL" />

<commerce-ui:modal-content
	title='<%= LanguageUtil.get(request, "add-product-group") %>'
>
	<div class="col-12 lfr-form-content">
		<aui:form cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "apiSubmit(this.form);" %>' useNamespace="<%= false %>">
			<aui:input name="title" required="<%= true %>" />

			<aui:input name="description" type="textarea" />
		</aui:form>

		<aui:script require="commerce-frontend-js/utilities/eventsDefinitions as events, commerce-frontend-js/utilities/forms/index as FormUtils, commerce-frontend-js/ServiceProvider/index as ServiceProvider">
			var CommerceProductGroupsResource = ServiceProvider.default.AdminCatalogAPI(
				'v1'
			);

			Liferay.provide(
				window,
				'<portlet:namespace />apiSubmit',
				function(form) {
					var description = form.querySelector('#description').value;
					var title = form.querySelector('#title').value;

					var productGroupData = {
						description: {<%= defaultLanguageId %>: description},
						title: {<%= defaultLanguageId %>: title}
					};

					return CommerceProductGroupsResource.addProductGroup(productGroupData)
						.then(function(payload) {
							var redirectURL = new Liferay.PortletURL.createURL(
								'<%= editPricingClassPortletURL.toString() %>'
							);

							redirectURL.setParameter('commercePricingClassId', payload.id);
							redirectURL.setParameter('p_auth', Liferay.authToken);

							window.parent.Liferay.fire(events.CLOSE_MODAL, {
								redirectURL: redirectURL.toString(),
								successNotification: {
									message:
										'<liferay-ui:message key="your-request-completed-successfully" />',
									showSuccessNotification: true
								}
							});
						})
						.catch(function(error) {
							return Promise.reject(error);
						});
				},
				['liferay-portlet-url']
			);
		</aui:script>
	</div>
</commerce-ui:modal-content>