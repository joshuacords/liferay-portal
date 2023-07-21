<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

EntitlementDefinition entitlementDefinition = (EntitlementDefinition)request.getAttribute(PhytohormoneWebKeys.ENTITLEMENT_DEFINITION);

long entitlementDefinitionId = BeanParamUtil.getLong(entitlementDefinition, request, "entitlementDefinitionId");
long classNameId = BeanParamUtil.getLong(entitlementDefinition, request, "classNameId");

renderResponse.setTitle((entitlementDefinition == null) ? LanguageUtil.get(request, "new-entitlement-definition") : entitlementDefinition.getName());
%>

<liferay-util:include page="/entitlement_definitions_admin/edit_entitlement_definition_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/entitlement_definitions_admin/edit_entitlement_definition" var="editEntitlementDefinitionURL" />

<aui:form action="<%= editEntitlementDefinitionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="entitlementDefinitionId" type="hidden" value="<%= entitlementDefinitionId %>" />

	<liferay-ui:error exception="<%= EntitlementDefinitionDefinitionException.class %>" message="please-enter-a-valid-definition" />
	<liferay-ui:error exception="<%= EntitlementDefinitionNameException.class %>" message="please-enter-a-valid-name" />

	<liferay-ui:error exception="<%= EntitlementDefinitionNameException.MustNotBeDuplicate.class %>">

		<%
		EntitlementDefinitionNameException.MustNotBeDuplicate mustNotBeDuplicateException = (EntitlementDefinitionNameException.MustNotBeDuplicate)errorException;
		%>

		<%= mustNotBeDuplicateException.getMessage() %>
	</liferay-ui:error>

	<aui:model-context bean="<%= entitlementDefinition %>" model="<%= EntitlementDefinition.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= entitlementDefinition != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= entitlementDefinition.getEntitlementDefinitionKey() %>" />
			</c:if>

			<h5><liferay-ui:message key="model" /></h5>

			<p>
				<c:choose>
					<c:when test="<%= classNameId == PortalUtil.getClassNameId(Account.class) %>">
						<liferay-ui:message key="account" />
					</c:when>
					<c:when test="<%= classNameId == PortalUtil.getClassNameId(Contact.class) %>">
						<liferay-ui:message key="contact" />
					</c:when>
				</c:choose>

				<aui:input name="classNameId" type="hidden" value="<%= classNameId %>" />
			</p>

			<c:choose>
				<c:when test="<%= entitlementDefinition != null %>">
					<h5><liferay-ui:message key="name" /></h5>

					<p>
						<%= HtmlUtil.escape(entitlementDefinition.getName()) %>
					</p>
				</c:when>
				<c:otherwise>
					<aui:input name="name" />
				</c:otherwise>
			</c:choose>

			<aui:input name="description" type="textarea" />

			<aui:input name="definition" type="textarea" />

			<aui:select name="status">
				<aui:option value="" />
				<aui:option label="<%= WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_APPROVED) %>" value="<%= WorkflowConstants.STATUS_APPROVED %>" />
				<aui:option label="<%= WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_INACTIVE) %>" value="<%= WorkflowConstants.STATUS_INACTIVE %>" />
			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<c:if test="<%= entitlementDefinition != null %>">
			<portlet:actionURL name="/entitlement_definitions_admin/synchronize_entitlement_definition" var="synchronizeEntitlementDefinitionURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="entitlementDefinitionId" value="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>" />
			</portlet:actionURL>

			<aui:button href="<%= synchronizeEntitlementDefinitionURL %>" type="submit" value="synchronize" />
		</c:if>

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>