<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDataIntegrationProcess commerceDataIntegrationProcess = (CommerceDataIntegrationProcess)request.getAttribute(CommerceDataIntegrationWebKeys.COMMERCE_DATA_INTEGRATION_PROCESS);

boolean neverEnd = ParamUtil.getBoolean(request, "neverEnd", true);

if ((commerceDataIntegrationProcess != null) && (commerceDataIntegrationProcess.getEndDate() != null)) {
	neverEnd = false;
}
%>

<portlet:actionURL name="editCommerceDataIntegrationProcessTrigger" var="editCommerceDataIntegrationProcessTriggerActionURL" />

<aui:form action="<%= editCommerceDataIntegrationProcessTriggerActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceDataIntegrationProcessId" type="hidden" value="<%= String.valueOf(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId()) %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:model-context bean="<%= commerceDataIntegrationProcess %>" model="<%= CommerceDataIntegrationProcess.class %>" />

			<div class="lfr-form-content">
				<aui:fieldset>
					<aui:input name="active" />

					<aui:input name="cronExpression" />

					<aui:input formName="fm" name="startDate" />

					<aui:input dateTogglerCheckboxLabel="never-end" disabled="<%= neverEnd %>" formName="fm" name="endDate" />
				</aui:fieldset>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</div>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>