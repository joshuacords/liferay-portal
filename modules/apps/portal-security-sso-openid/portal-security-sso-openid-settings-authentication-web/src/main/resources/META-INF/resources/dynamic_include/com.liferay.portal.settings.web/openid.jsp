<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
OpenIdConfiguration openIdConfiguration = ConfigurationProviderUtil.getConfiguration(OpenIdConfiguration.class, new ParameterMapSettingsLocator(request.getParameterMap(), PortalSettingsOpenIdConstants.FORM_PARAMETER_NAMESPACE, new CompanyServiceSettingsLocator(company.getCompanyId(), OpenIdConstants.SERVICE_NAME)));

boolean enabled = openIdConfiguration.enabled();
%>

<aui:fieldset>
	<aui:input label="enabled" name='<%= PortalSettingsOpenIdConstants.FORM_PARAMETER_NAMESPACE + "enabled" %>' type="checkbox" value="<%= enabled %>" />
</aui:fieldset>