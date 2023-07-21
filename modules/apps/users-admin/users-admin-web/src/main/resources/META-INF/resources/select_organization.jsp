<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String p_u_i_d = ParamUtil.getString(request, "p_u_i_d");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectOrganization");

long selOrganizationId = ParamUtil.getLong(request, "organizationId");
User selUser = PortalUtil.getSelectedUser(request);

SelectOrganizationManagementToolbarDisplayContext selectOrganizationManagementToolbarDisplayContext = new SelectOrganizationManagementToolbarDisplayContext(request, renderRequest, renderResponse);

PortletURL portletURL = selectOrganizationManagementToolbarDisplayContext.getPortletURL();

LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

if (filterManageableOrganizations) {
	organizationParams.put("organizationsTree", user.getOrganizations());
}

SearchContainer searchContainer = selectOrganizationManagementToolbarDisplayContext.getSearchContainer(organizationParams);

renderResponse.setTitle(LanguageUtil.get(request, "organizations"));
%>

<clay:management-toolbar
	clearResultsURL="<%= selectOrganizationManagementToolbarDisplayContext.getClearResultsURL() %>"
	filterDropdownItems="<%= selectOrganizationManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= selectOrganizationManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchFormName="searchFm"
	selectable="<%= false %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= selectOrganizationManagementToolbarDisplayContext.getSortingURL() %>"
/>

<aui:form action="<%= portletURL %>" cssClass="container-fluid-1280" method="post" name="selectOrganizationFm">
	<liferay-ui:search-container
		searchContainer="<%= searchContainer %>"
		var="organizationSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Organization"
			escapedModel="<%= true %>"
			keyProperty="organizationId"
			modelVar="organization"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				orderable="<%= true %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="parent-organization"
				value="<%= HtmlUtil.escape(organization.getParentOrganizationName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				orderable="<%= true %>"
				value="<%= LanguageUtil.get(request, organization.getType()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="city"
				property="address.city"
			/>

			<liferay-ui:search-container-column-text
				name="region"
				property="address.region.name"
			/>

			<liferay-ui:search-container-column-text
				name="country"
				value="<%= UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization) %>"
			/>

			<liferay-ui:search-container-column-text>
				<c:if test="<%= Validator.isNull(p_u_i_d) || OrganizationMembershipPolicyUtil.isMembershipAllowed((selUser != null) ? selUser.getUserId() : 0, organization.getOrganizationId()) %>">

					<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("entityid", organization.getOrganizationId());
					data.put("entityname", organization.getName());
					data.put("type", LanguageUtil.get(request, organization.getType()));

					boolean disabled = false;

					if (selUser != null) {
						for (long curOrganizationId : selUser.getOrganizationIds()) {
							if (curOrganizationId == organization.getOrganizationId()) {
								disabled = true;

								break;
							}
						}
					}

					if (selOrganizationId == organization.getOrganizationId()) {
						disabled = true;
					}
					%>

					<aui:button cssClass="selector-button" data="<%= data %>" disabled="<%= disabled %>" value="choose" />
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectOrganizationFm',
		'<%= HtmlUtil.escapeJS(eventName) %>',
		<%= selUser != null %>
	);
</aui:script>