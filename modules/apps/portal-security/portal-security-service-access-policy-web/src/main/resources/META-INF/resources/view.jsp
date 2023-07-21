<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

boolean orderByAsc = false;

String orderByType = ParamUtil.getString(request, "orderByType", "asc");

if (orderByType.equals("asc")) {
	orderByAsc = true;
}

OrderByComparator<SAPEntry> orderByComparator = new SAPEntryNameComparator(orderByAsc);

int sapEntriesCount = SAPEntryServiceUtil.getCompanySAPEntriesCount(company.getCompanyId());

PortletURL portletURL = renderResponse.createRenderURL();

PortletURL sortingURL = renderResponse.createRenderURL();

sortingURL.setParameter("displayStyle", displayStyle);
sortingURL.setParameter("orderByType", orderByAsc ? "desc" : "asc");
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="policies" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<clay:management-toolbar
	creationMenu='<%=
		new JSPCreationMenu(pageContext) {
			{
				addPrimaryDropdownItem(dropdownItem -> dropdownItem.setHref(renderResponse.createRenderURL(), "mvcPath", "/edit_entry.jsp", "redirect", PortalUtil.getCurrentURL(request)));
			}
		}
	%>'
	disabled="<%= sapEntriesCount == 0 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	selectable="<%= false %>"
	showCreationMenu="<%= SAPPermission.contains(permissionChecker, SAPActionKeys.ACTION_ADD_SAP_ENTRY) %>"
	showSearch="<%= false %>"
	sortingOrder="<%= orderByType %>"
	sortingURL="<%= sortingURL.toString() %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-service-access-policies"
		iteratorURL="<%= portletURL %>"
		total="<%= sapEntriesCount %>"
	>
		<liferay-ui:search-container-results
			results="<%= SAPEntryServiceUtil.getCompanySAPEntries(company.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.security.service.access.policy.model.SAPEntry"
			escapedModel="<%= true %>"
			keyProperty="sapEntryId"
			modelVar="sapEntry"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/edit_entry.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="sapEntryId" value="<%= String.valueOf(sapEntry.getSapEntryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				href="<%= rowURL %>"
				name="name"
			>
				<strong><%= sapEntry.getName() %></strong>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="description"
				value="<%= sapEntry.getTitle(locale) %>"
			/>

			<liferay-ui:search-container-column-text
				name="enabled"
				value='<%= LanguageUtil.get(request, sapEntry.isEnabled() ? "yes" : "no") %>'
			/>

			<liferay-ui:search-container-column-text
				name="default"
				value='<%= LanguageUtil.get(request, sapEntry.isDefaultSAPEntry() ? "yes" : "no") %>'
			/>

			<liferay-ui:search-container-column-jsp
				path="/entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>