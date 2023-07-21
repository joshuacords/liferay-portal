<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/permissions/init.jsp" %>

<aui:fieldset cssClass="options-group" markupView="lexicon">
	<div class="sheet-section">
		<h3 class="sheet-subtitle"><liferay-ui:message key="permissions" /></h3>

		<%
		ExportImportServiceConfiguration exportImportServiceConfiguration = ConfigurationProviderUtil.getSystemConfiguration(ExportImportServiceConfiguration.class);
		%>

		<liferay-staging:checkbox
			checked="<%= MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.PERMISSIONS, exportImportServiceConfiguration.publishPermissionsByDefault()) %>"
			description="<%= inputDescription %>"
			disabled="<%= disableInputs %>"
			label="<%= inputTitle %>"
			name="<%= PortletDataHandlerKeys.PERMISSIONS %>"
		/>
	</div>
</aui:fieldset>