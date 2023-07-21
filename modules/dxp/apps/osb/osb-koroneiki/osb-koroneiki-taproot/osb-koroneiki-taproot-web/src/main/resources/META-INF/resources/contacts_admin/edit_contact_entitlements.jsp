<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Contact koroneikiContact = (Contact)request.getAttribute(TaprootWebKeys.CONTACT);

renderResponse.setTitle(koroneikiContact.getFullName());
%>

<liferay-util:include page="/contacts_admin/edit_contact_tabs.jsp" servletContext="<%= application %>" />

<div class="main-content-body">

	<%
	List<Entitlement> entitlements = koroneikiContact.getEntitlements();
	%>

	<div class="container-fluid-1280">
		<liferay-ui:search-container
			emptyResultsMessage="no-entitlements-were-found"
			headerNames="name"
			total="<%= entitlements.size() %>"
		>
			<liferay-ui:search-container-results
				results="<%= entitlements %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.phytohormone.model.Entitlement"
				escapedModel="<%= true %>"
				keyProperty="entitlementId"
				modelVar="entitlement"
			>

				<%
				EntitlementDefinition entitlementDefinition = entitlement.getEntitlementDefinition();
				%>

				<liferay-ui:search-container-column-text
					name="name"
				>
					<span class="lfr-portal-tooltip" data-title="<%= HtmlUtil.escapeAttribute(entitlementDefinition.getDescription()) %>">
						<%= HtmlUtil.escape(entitlement.getName()) %>
					</span>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>
	</div>
</div>