<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
VirtualHost virtualHost = null;

try {
	virtualHost = VirtualHostLocalServiceUtil.getVirtualHost(company.getCompanyId(), 0);
}
catch (Exception e) {
}
%>

<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<h4><liferay-ui:message key="main-configuration" /></h4>

<aui:model-context bean="<%= account %>" model="<%= Account.class %>" />

<aui:row>
	<aui:col width="<%= 50 %>">
		<liferay-ui:error exception="<%= AccountNameException.class %>" message="please-enter-a-valid-name" />

		<aui:input name="name" />

		<liferay-ui:error exception="<%= CompanyMxException.class %>" message="please-enter-a-valid-mail-domain" />

		<aui:input bean="<%= company %>" disabled="<%= !PropsValues.MAIL_MX_UPDATE %>" label="mail-domain" model="<%= Company.class %>" name="mx" />

		<liferay-ui:error exception="<%= CompanyVirtualHostException.class %>" message="please-enter-a-valid-virtual-host" />

		<aui:input bean="<%= virtualHost %>" fieldParam="virtualHostname" label="virtual-host" model="<%= VirtualHost.class %>" name="hostname" />
	</aui:col>

	<aui:col width="<%= 50 %>">
		<aui:input label="cdn-host-http" name='<%= "settings--" + PropsKeys.CDN_HOST_HTTP + "--" %>' type="text" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.CDN_HOST_HTTP, PropsValues.CDN_HOST_HTTP) %>" />

		<aui:input label="cdn-host-https" name='<%= "settings--" + PropsKeys.CDN_HOST_HTTPS + "--" %>' type="text" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.CDN_HOST_HTTPS, PropsValues.CDN_HOST_HTTPS) %>" />

		<aui:input label="enable-cdn-dynamic-resources" name='<%= "settings--" + PropsKeys.CDN_DYNAMIC_RESOURCES_ENABLED + "--" %>' type="checkbox" value="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.CDN_DYNAMIC_RESOURCES_ENABLED, PropsValues.CDN_DYNAMIC_RESOURCES_ENABLED) %>" />
	</aui:col>
</aui:row>

<h4><liferay-ui:message key="navigation" /></h4>

<aui:row>
	<aui:col width="<%= 50 %>">
		<aui:input bean="<%= company %>" helpMessage="home-url-help" label="home-url" model="<%= Company.class %>" name="homeURL" />

		<aui:input helpMessage="default-landing-page-help" label="default-landing-page" name='<%= "settings--" + PropsKeys.DEFAULT_LANDING_PAGE_PATH + "--" %>' type="text" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.DEFAULT_LANDING_PAGE_PATH, PropsValues.DEFAULT_LANDING_PAGE_PATH) %>" />
	</aui:col>

	<aui:col width="<%= 50 %>">
		<aui:input helpMessage="default-logout-page-help" label="default-logout-page" name='<%= "settings--" + PropsKeys.DEFAULT_LOGOUT_PAGE_PATH + "--" %>' type="text" value="<%= PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.DEFAULT_LOGOUT_PAGE_PATH, PropsValues.DEFAULT_LOGOUT_PAGE_PATH) %>" />
	</aui:col>
</aui:row>

<h4><liferay-ui:message key="additional-information" /></h4>

<aui:row>
	<aui:col width="<%= 50 %>">
		<aui:input name="legalName" />

		<aui:input name="legalId" />

		<aui:input name="legalType" />

		<aui:input name="sicCode" />
	</aui:col>

	<aui:col width="<%= 50 %>">
		<aui:input name="tickerSymbol" />

		<aui:input name="industry" />

		<aui:input name="type" />
	</aui:col>
</aui:row>