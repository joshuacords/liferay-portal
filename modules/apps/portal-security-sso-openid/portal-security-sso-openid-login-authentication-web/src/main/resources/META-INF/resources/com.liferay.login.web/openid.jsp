<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/com.liferay.login.web/init.jsp" %>

<portlet:actionURL var="openIdURL">
	<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="/login/openid" />
</portlet:actionURL>

<aui:form action="<%= openIdURL %>" method="post" name="fm">
	<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />

	<liferay-ui:error exception="<%= DuplicateOpenIdException.class %>" message="a-user-with-that-openid-already-exists" />
	<liferay-ui:error exception="<%= OpenIdServiceException.AssociationException.class %>" message="an-error-occurred-while-establishing-an-association-with-the-openid-provider" />
	<liferay-ui:error exception="<%= OpenIdServiceException.ConsumerException.class %>" message="an-error-occurred-while-initializing-the-openid-consumer" />
	<liferay-ui:error exception="<%= OpenIdServiceException.DiscoveryException.class %>" message="an-error-occurred-while-discovering-the-openid-provider" />
	<liferay-ui:error exception="<%= OpenIdServiceException.MessageException.class %>" message="an-error-occurred-while-communicating-with-the-openid-provider" />
	<liferay-ui:error exception="<%= StrangersNotAllowedException.class %>" message="only-known-users-are-allowed-to-sign-in-using-an-openid" />
	<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeDuplicate.class %>" message="the-email-address-associated-with-your-openid-account-is-already-being-used" />
	<liferay-ui:error exception="<%= UserEmailAddressException.MustNotUseCompanyMx.class %>" message="the-email-address-associated-with-your-openid-cannot-be-used-to-register-a-new-user-because-its-email-domain-is-reserved" />

	<aui:fieldset>
		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" cssClass="openid-login" label="openid" name="openId" title="openid" type="text" value='<%= ParamUtil.getString(request, "openId") %>' />

		<aui:button-row>
			<aui:button type="submit" value="sign-in" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>