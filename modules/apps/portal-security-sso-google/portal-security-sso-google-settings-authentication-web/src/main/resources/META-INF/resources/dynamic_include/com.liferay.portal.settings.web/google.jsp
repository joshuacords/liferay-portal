<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
GoogleAuthorizationConfiguration googleAuthorizationConfiguration = ConfigurationProviderUtil.getConfiguration(GoogleAuthorizationConfiguration.class, new ParameterMapSettingsLocator(request.getParameterMap(), PortalSettingsGoogleConstants.FORM_PARAMETER_NAMESPACE, new CompanyServiceSettingsLocator(company.getCompanyId(), GoogleConstants.SERVICE_NAME)));
%>

<liferay-ui:error key="googleClientIdInvalid" message="the-google-client-id-is-invalid" />
<liferay-ui:error key="googleClientSecretInvalid" message="the-google-client-secret-is-invalid" />

<aui:fieldset>
	<aui:input label="enabled" name='<%= PortalSettingsGoogleConstants.FORM_PARAMETER_NAMESPACE + "enabled" %>' type="checkbox" value="<%= googleAuthorizationConfiguration.enabled() %>" />

	<aui:input label="google-client-id" name='<%= PortalSettingsGoogleConstants.FORM_PARAMETER_NAMESPACE + "clientId" %>' type="text" value="<%= googleAuthorizationConfiguration.clientId() %>" wrapperCssClass="lfr-input-text-container" />

	<aui:input label="google-client-secret" name='<%= PortalSettingsGoogleConstants.FORM_PARAMETER_NAMESPACE + "clientSecret" %>' type="text" value="<%= googleAuthorizationConfiguration.clientSecret() %>" wrapperCssClass="lfr-input-text-container" />
</aui:fieldset>