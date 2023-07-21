<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDataIntegrationProcessLogDisplayContext commerceDataIntegrationProcessLogDisplayContext = (CommerceDataIntegrationProcessLogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog = commerceDataIntegrationProcessLogDisplayContext.getCommerceDataIntegrationProcessLog();

Date logEndDate = commerceDataIntegrationProcessLog.getEndDate();
Date logStartDate = commerceDataIntegrationProcessLog.getStartDate();

long timeMillis = logEndDate.getTime() - logStartDate.getTime();
%>

<portlet:actionURL name="editCommerceDataIntegrationProcessLog" var="editCommerceDataIntegrationProcessLogActionURL" />

<div class="container-fluid-1280 sheet">
	<aui:form action="<%= editCommerceDataIntegrationProcessLogActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="cDataIntegrationProcessLogId" type="hidden" value="<%= String.valueOf(commerceDataIntegrationProcessLog.getCommerceDataIntegrationProcessLogId()) %>" />

		<div class="lfr-form-content">
			<aui:fieldset>
				<aui:input disabled="<%= true %>" label="start-date" name="startDate" value="<%= commerceDataIntegrationProcessLogDisplayContext.getFormattedDate(logStartDate) %>" />

				<aui:input disabled="<%= true %>" name="status" value="<%= LanguageUtil.get(request, BackgroundTaskConstants.getStatusLabel(commerceDataIntegrationProcessLog.getStatus())) %>" />

				<aui:input disabled="<%= true %>" label="runtime" name="runTime" value='<%= timeMillis + " ms" %>' />

				<aui:input disabled="<%= true %>" label="error" name="error" type="textarea" value="<%= commerceDataIntegrationProcessLog.getError() %>" />

				<aui:input disabled="<%= true %>" label="output" name="output" type="textarea" value="<%= commerceDataIntegrationProcessLog.getOutput() %>" />
			</aui:fieldset>

			<aui:button-row>
				<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>