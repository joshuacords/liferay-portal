<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
NtlmConfiguration ntlmConfiguration = ConfigurationProviderUtil.getConfiguration(NtlmConfiguration.class, new ParameterMapSettingsLocator(request.getParameterMap(), PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE, new CompanyServiceSettingsLocator(company.getCompanyId(), NtlmConstants.SERVICE_NAME)));

boolean enabled = ntlmConfiguration.enabled();
String domainController = ntlmConfiguration.domainController();
String domainControllerName = ntlmConfiguration.domainControllerName();
String domain = ntlmConfiguration.domain();
String negotiateFlags = ntlmConfiguration.negotiateFlags();
String serviceAccount = ntlmConfiguration.serviceAccount();

String servicePassword = ntlmConfiguration.servicePassword();

if (Validator.isNotNull(servicePassword)) {
	servicePassword = Portal.TEMP_OBFUSCATION_VALUE;
}
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "enabled" %>' type="checkbox" value="<%= enabled %>" />

	<aui:input cssClass="lfr-input-text-container" label="domain-controller" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "domainController" %>' type="text" value="<%= domainController %>" />

	<aui:input cssClass="lfr-input-text-container" helpMessage="domain-controller-name-help" label="domain-controller-name" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "domainControllerName" %>' type="text" value="<%= domainControllerName %>" />

	<aui:input cssClass="lfr-input-text-container" label="domain" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "domain" %>' type="text" value="<%= domain %>" />

	<aui:input cssClass="lfr-input-text-container" label="service-account" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "serviceAccount" %>' type="text" value="<%= serviceAccount %>" />

	<aui:input cssClass="lfr-input-text-container" label="service-password" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "servicePassword" %>' type="password" value="<%= servicePassword %>" />

	<aui:input cssClass="lfr-input-text-container" label="negotiate-flags" name='<%= PortalSettingsNtlmConstants.FORM_PARAMETER_NAMESPACE + "negotiateFlags" %>' type="text" value="<%= negotiateFlags %>" />
</aui:fieldset>