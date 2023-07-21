<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewSubscriptionDisplayContext viewSubscriptionDisplayContext = ProvisioningWebComponentProvider.getViewSubscriptionDisplayContext(renderRequest, renderResponse, request);

ProductPurchaseViewDisplay productPurchaseViewDisplay = viewSubscriptionDisplayContext.getProductPurchaseViewDisplay();
%>

<div class="autofit-row provisioning-accounts subscription-header">
	<svg class="autofit-col header-icon">
		<use xlink:href="#subscription-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="subscription-name">
			<%= HtmlUtil.escape(productPurchaseViewDisplay.getName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="state" />
				</div>

				<span class="label <%= productPurchaseViewDisplay.getStateStyle() %>">
					<%= productPurchaseViewDisplay.getState() %>
				</span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="instance-size" />
				</div>

				<%= productPurchaseViewDisplay.getSizing() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="support-life" />
				</div>

				<%= productPurchaseViewDisplay.getSupportLife() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="grace-period" />
				</div>

				<%= productPurchaseViewDisplay.getGracePeriod() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="current-provisioned" />
				</div>

				<a href="<%= productPurchaseViewDisplay.getProvisionedCountURL() %>"><%= productPurchaseViewDisplay.getCurrentProvisionedCount() %></a>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="current-purchased" />
				</div>

				<%= productPurchaseViewDisplay.getCurrentPurchasedCount() %>
			</li>
		</ul>
	</div>

	<c:if test="<%= viewSubscriptionDisplayContext.hasManageLicenseKeysPermission() %>">
		<div class="header-buttons">
			<div>
				<a class="btn btn-primary" href="<%= viewSubscriptionDisplayContext.getGenerateLicenseURL() %>">
					<span class="lfr-btn-label"><liferay-ui:message key="generate-license" /></span>
				</a>
			</div>
		</div>
	</c:if>
</div>