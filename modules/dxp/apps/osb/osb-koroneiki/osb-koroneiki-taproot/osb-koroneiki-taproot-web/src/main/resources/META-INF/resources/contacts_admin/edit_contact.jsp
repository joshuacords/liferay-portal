<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Contact koroneikiContact = (Contact)request.getAttribute(TaprootWebKeys.CONTACT);
%>

<liferay-util:include page="/contacts_admin/edit_contact_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/contacts_admin/edit_contact" var="editContactURL" />

<aui:form action="<%= editContactURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="contactId" type="hidden" value='<%= BeanParamUtil.getLong(koroneikiContact, request, "contactId") %>' />

	<liferay-ui:error exception="<%= ContactEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= ContactEmailAddressException.MustNotBeDuplicate.class %>" message="the-email-address-you-requested-is-already-taken" />

	<aui:model-context bean="<%= koroneikiContact %>" model="<%= Contact.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= koroneikiContact != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= koroneikiContact.getContactKey() %>" />
			</c:if>

			<aui:input name="uuid" />

			<aui:input name="firstName" />

			<aui:input name="middleName" />

			<aui:input name="lastName" />

			<aui:input name="emailAddress" />

			<aui:input checked="<%= (koroneikiContact != null) && koroneikiContact.isEmailAddressVerified() %>" name="emailAddressVerified" type="checkbox" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>