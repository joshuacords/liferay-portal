<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/remote_options/init.jsp" %>

<div id="<portlet:namespace />remote">
	<div class="alert alert-info">
		<liferay-ui:message key="export-the-selected-data-to-the-site-of-a-remote-portal-or-to-another-site-in-the-same-portal" />
	</div>

	<aui:fieldset>
		<aui:input disabled="<%= disableInputs %>" label="remote-host-ip" name="remoteAddress" size="20" type="text" value='<%= MapUtil.getString(settingsMap, "remoteAddress", liveGroupTypeSettings.getProperty("remoteAddress")) %>' />

		<aui:input disabled="<%= disableInputs %>" label="remote-port" name="remotePort" size="10" type="text" value='<%= MapUtil.getString(settingsMap, "remotePort", liveGroupTypeSettings.getProperty("remotePort")) %>' />

		<aui:input disabled="<%= disableInputs %>" label="remote-path-context" name="remotePathContext" size="10" type="text" value='<%= MapUtil.getString(settingsMap, "remotePathContext", liveGroupTypeSettings.getProperty("remotePathContext")) %>' />

		<aui:input disabled="<%= disableInputs %>" label="remote-site-id" name="remoteGroupId" size="10" type="text" value='<%= MapUtil.getString(settingsMap, "targetGroupId", liveGroupTypeSettings.getProperty("remoteGroupId")) %>' />

		<aui:input disabled="<%= disableInputs %>" name="remotePrivateLayout" type="hidden" value='<%= MapUtil.getBoolean(settingsMap, "remotePrivateLayout", privateLayout) %>' />
	</aui:fieldset>

	<aui:fieldset>
		<aui:input disabled="<%= disableInputs %>" label="use-a-secure-network-connection" name="secureConnection" type="checkbox" value='<%= MapUtil.getString(settingsMap, "secureConnection", liveGroupTypeSettings.getProperty("secureConnection")) %>' />
	</aui:fieldset>
</div>