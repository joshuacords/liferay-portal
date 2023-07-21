<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);

ContactDisplay contactDisplay = viewContactDisplayContext.getContactDisplay();
%>

<div class="autofit-row contact-header">
	<svg class="autofit-col header-icon">
		<use xlink:href="#contact-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="contact-name">
			<%= HtmlUtil.escape(contactDisplay.getFullName()) %>

			<span class="email-address">
				<%= HtmlUtil.escape(contactDisplay.getEmailAddress()) %>
			</span>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="status" />
				</div>

				<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="no.-of-accounts" />
				</div>

				<%= contactDisplay.getAccountsCount() %>
			</li>
		</ul>
	</div>

	<div class="header-buttons">
		<portlet:actionURL name="/users/sync_with_okta" var="syncWithOktaURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="contactUuid" value="<%= contactDisplay.getUuid() %>" />
		</portlet:actionURL>

		<aui:form action="<%= syncWithOktaURL %>" method="post" name="fm1">
			<aui:button cssClass="btn-secondary btn-sm" href="<%= syncWithOktaURL %>" value="sync-with-okta" />
		</aui:form>
	</div>
</div>