<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/deletions/init.jsp" %>

<c:if test="<%= cmd.equals(Constants.EXPORT) || cmd.equals(Constants.IMPORT) || cmd.equals(Constants.PUBLISH) %>">
	<aui:fieldset cssClass="options-group" markupView="lexicon">
		<div class="sheet-section">
			<h3 class="sheet-subtitle"><liferay-ui:message key="deletions" /></h3>

			<c:if test="<%= !cmd.equals(Constants.EXPORT) %>">
				<liferay-staging:checkbox
					checked="<%= MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.DELETE_PORTLET_DATA, false) %>"
					disabled="<%= disableInputs %>"
					label="delete-application-data-before-importing"
					name="<%= PortletDataHandlerKeys.DELETE_PORTLET_DATA %>"
					suggestion="delete-content-before-importing-suggestion"
					warning="delete-content-before-importing-warning"
				/>
			</c:if>

			<%
			ExportImportServiceConfiguration exportImportServiceConfiguration = ConfigurationProviderUtil.getSystemConfiguration(ExportImportServiceConfiguration.class);
			%>

			<liferay-staging:checkbox
				checked="<%= MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.DELETIONS, exportImportServiceConfiguration.replicateIndividualDeletionsByDefault()) %>"
				description="<%= individualDeletionsDescription %>"
				disabled="<%= disableInputs %>"
				label="<%= individualDeletionsTitle %>"
				name="<%= PortletDataHandlerKeys.DELETIONS %>"
			/>
		</div>
	</aui:fieldset>
</c:if>