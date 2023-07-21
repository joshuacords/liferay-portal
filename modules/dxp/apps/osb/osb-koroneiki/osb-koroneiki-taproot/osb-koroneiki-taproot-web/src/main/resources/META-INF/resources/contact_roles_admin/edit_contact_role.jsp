<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ContactRole contactRole = (ContactRole)request.getAttribute(TaprootWebKeys.CONTACT_ROLE);

String type = BeanParamUtil.getString(contactRole, request, "type");
%>

<liferay-util:include page="/contact_roles_admin/edit_contact_role_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/contact_roles_admin/edit_contact_role" var="editContactRoleURL" />

<aui:form action="<%= editContactRoleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="contactRoleId" type="hidden" value='<%= BeanParamUtil.getLong(contactRole, request, "contactRoleId") %>' />
	<aui:input name="type" type="hidden" value="<%= type %>" />

	<liferay-ui:error exception="<%= ContactRoleNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= ContactRoleTypeException.class %>" message="please-enter-a-valid-type" />

	<aui:model-context bean="<%= contactRole %>" model="<%= ContactRole.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= contactRole != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= contactRole.getContactRoleKey() %>" />
			</c:if>

			<h5><liferay-ui:message key="type" /></h5>

			<p>
				<%= HtmlUtil.escape(type) %>
			</p>

			<aui:input name="name" />

			<aui:input name="description" type="textarea" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>