<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceApplicationAdminDisplayContext commerceApplicationAdminDisplayContext = (CommerceApplicationAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceApplicationModel commerceApplicationModel = commerceApplicationAdminDisplayContext.getCommerceApplicationModel();
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<portlet:actionURL name="editCommerceApplicationModel" var="editCommerceApplicationModelActionURL" />

<div class="container-fluid-1280 entry-body">
	<aui:form action="<%= editCommerceApplicationModelActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceApplicationModel == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= backURL %>" />
		<aui:input name="commerceApplicationBrandId" type="hidden" value="<%= commerceApplicationAdminDisplayContext.getCommerceApplicationBrandId() %>" />
		<aui:input name="commerceApplicationModelId" type="hidden" value="<%= commerceApplicationAdminDisplayContext.getCommerceApplicationModelId() %>" />

		<aui:model-context bean="<%= commerceApplicationModel %>" model="<%= CommerceApplicationModel.class %>" />

		<div class="lfr-form-content">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input autoFocus="<%= true %>" name="name" />

					<aui:input name="year" />
				</aui:fieldset>

				<aui:fieldset>
					<aui:button-row>
						<aui:button cssClass="btn-lg" type="submit" value="save" />

						<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
					</aui:button-row>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</aui:form>
</div>