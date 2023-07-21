<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view_configuration_screen");
portletURL.setParameter("configurationScreenKey", "2-synced-contact-data");

String redirect = ParamUtil.getString(request, "redirect", portletURL.toString());

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(ParamUtil.getString(request, "backURL", redirect));

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(resourceBundle, "select-contact-data"), portletURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(resourceBundle, "select-contacts"), redirect);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(resourceBundle, "sync-by-organizations"), currentURL);
%>

<portlet:renderURL var="editSyncedContactsFieldsURL">
	<portlet:param name="mvcRenderCommandName" value="/analytics_settings/edit_synced_contacts_fields" />
</portlet:renderURL>

<div class="container-fluid container-fluid-max-xl">
	<div class="col-12">
		<div id="breadcrumb">
			<liferay-ui:breadcrumb
				showCurrentGroup="<%= false %>"
				showGuestGroup="<%= false %>"
				showLayout="<%= false %>"
				showPortletBreadcrumb="<%= true %>"
			/>
		</div>
	</div>
</div>

<div class="sheet sheet-lg">
	<h2 class="autofit-row">
		<span class="autofit-col autofit-col-expand">
			<liferay-ui:message key="select-contacts-by-organizations" />
		</span>
	</h2>

	<hr />

	<div class="autofit-row form-text">
		<span class="autofit-col autofit-col-expand pb-3">
			<liferay-ui:message key="select-contacts-by-organizations-help" />
		</span>
	</div>

	<%
	OrganizationDisplayContext organizationDisplayContext = new OrganizationDisplayContext(renderRequest, renderResponse);
	%>

	<clay:management-toolbar
		displayContext="<%= new OrganizationManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, organizationDisplayContext) %>"
	/>

	<aui:form action="<%= editSyncedContactsFieldsURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="update_synced_organizations" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<liferay-ui:search-container
			id="selectOrganizations"
			searchContainer="<%= organizationDisplayContext.getOrganizationSearch() %>"
			var="organizationSearchContainer"
		>
			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.Organization"
				escapedModel="<%= true %>"
				keyProperty="organizationId"
				modelVar="organization"
			>
				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand"
					name="organization-name"
					value="<%= HtmlUtil.escape(organization.getName()) %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				searchResultCssClass="show-quick-actions-on-hover table table-autofit"
			/>
		</liferay-ui:search-container>

		<div class="text-right">
			<aui:button-row>
				<aui:button href="<%= redirect %>" type="cancel" value="cancel" />
				<aui:button type="submit" value="save-and-next" />
			</aui:button-row>
		</div>
	</aui:form>
</div>