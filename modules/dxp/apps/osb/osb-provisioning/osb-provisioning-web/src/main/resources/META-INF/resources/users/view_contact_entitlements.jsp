<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Contact koroneikiContact = (Contact)renderRequest.getAttribute(ProvisioningWebKeys.CONTACT);

List<Entitlement> entitlements = Arrays.asList(koroneikiContact.getEntitlements());
%>

<div class="main-content-body">
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
				className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement"
				keyProperty="entitlementId"
				modelVar="entitlement"
			>
				<liferay-ui:search-container-column-text
					name="name"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>
	</div>
</div>