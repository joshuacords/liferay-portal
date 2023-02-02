<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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