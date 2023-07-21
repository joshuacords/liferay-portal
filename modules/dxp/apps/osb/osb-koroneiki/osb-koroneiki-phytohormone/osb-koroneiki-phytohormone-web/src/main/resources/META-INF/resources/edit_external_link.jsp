<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ExternalLink externalLink = (ExternalLink)request.getAttribute(RootWebKeys.EXTERNAL_LINK);

long externalLinkId = BeanParamUtil.getLong(externalLink, request, "externalLinkId");
long classNameId = BeanParamUtil.getLong(externalLink, request, "classNameId");
long classPK = BeanParamUtil.getLong(externalLink, request, "classPK");

renderResponse.setTitle(ParamUtil.getString(request, "title"));
%>

<portlet:actionURL name="/edit_external_link" var="editExternalLinkURL" />

<aui:form action="<%= editExternalLinkURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="externalLinkId" type="hidden" value="<%= externalLinkId %>" />
	<aui:input name="classNameId" type="hidden" value="<%= classNameId %>" />
	<aui:input name="classPK" type="hidden" value="<%= classPK %>" />

	<liferay-ui:error exception="<%= ExternalLinkDomainException.class %>" message="please-enter-a-valid-domain" />
	<liferay-ui:error exception="<%= ExternalLinkEntityIdException.class %>" message="please-enter-a-valid-entity-id" />
	<liferay-ui:error exception="<%= ExternalLinkEntityNameException.class %>" message="please-enter-a-valid-entity-name" />

	<aui:model-context bean="<%= externalLink %>" model="<%= ExternalLink.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= externalLink != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= externalLink.getExternalLinkKey() %>" />
			</c:if>

			<aui:input disabled="<%= externalLink != null %>" name="domain" />

			<aui:input disabled="<%= externalLink != null %>" name="entityName" />

			<aui:input name="entityId" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-debounce,autocomplete,autocomplete-highlighters">
	var autoCompleteConfig = {
		activateFirstItem: true,
		maxResults: 10,
		minQueryLength: 1,
		render: false,
		resultHighlighter: 'phraseMatch'
	};

	var domain = A.one('#<portlet:namespace />domain');

	if (domain) {
		domain.plug(A.Plugin.AutoComplete, autoCompleteConfig);

		domain.ac.render();

		domain.on(
			'input',
			function() {
				if (domain.val() != '') {
					A.debounce(
						AUI.$.ajax(
							'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="externalLinkDomains" />',
							{
								data: {
									<portlet:namespace />domain: domain.val()
								},
								success: function(responseData) {
									domain.ac.set('source', responseData);
								}
							}
						),
						50
					);
				}
			}
		);
	}

	var entityName = A.one('#<portlet:namespace />entityName');

	if (entityName) {
		entityName.plug(A.Plugin.AutoComplete, autoCompleteConfig);

		entityName.ac.render();

		entityName.on(
			'input',
			function() {
				if ((domain.val() != '') && (entityName.val() != '')) {
					A.debounce(
						AUI.$.ajax(
							'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="externalLinkEntityNames" />',
							{
								data: {
									<portlet:namespace />domain: domain.val(),
									<portlet:namespace />entityName: entityName.val()
								},
								success: function(responseData) {
									entityName.ac.set('source', responseData);
								}
							}
						),
						50
					);
				}
			}
		);
	}
</aui:script>