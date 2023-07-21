<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
LicenseKeySearchDisplayContext licenseKeySearchDisplayContext = ProvisioningWebComponentProvider.getLicenseKeySearchDisplayContext(renderRequest, renderResponse, request);

ViewLicenseKeysManagementToolbarDisplayContext viewLicenseKeysManagementToolbarDisplayContext = ProvisioningWebComponentProvider.getViewLicenseKeysManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, licenseKeySearchDisplayContext.getSearchContainer());
%>

<div class="title-bar">
	<h3><liferay-ui:message key="licenses" /></h3>

	<portlet:renderURL var="addLicenseKeyURL">
		<portlet:param name="mvcRenderCommandName" value="/licenses/add_license_key" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<c:if test="<%= licenseKeySearchDisplayContext.hasManageLicenseKeysPermission() %>">
		<a class="btn btn-primary" href="<%= addLicenseKeyURL %>">
			<span class="lfr-btn-label"><liferay-ui:message key="generate-license" /></span>
		</a>
	</c:if>
</div>

<div class="container-fluid home">
	<div class="licenses">
		<div class="custom-search license-search">
			<react:component
				data="<%= licenseKeySearchDisplayContext.getData() %>"
				module="js/apps/LicenseKeySearchApp"
			/>
		</div>

		<clay:management-toolbar
			clearResultsURL="<%= viewLicenseKeysManagementToolbarDisplayContext.getClearResultsURL() %>"
			displayContext="<%= viewLicenseKeysManagementToolbarDisplayContext %>"
			elementClasses="full-width"
			searchInputName="licenseKeySearchKeywords"
			showSearch="<%= false %>"
		/>

		<liferay-ui:search-container
			cssClass="license-details table-hover"
			searchContainer="<%= licenseKeySearchDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay"
				keyProperty="licenseKeyId"
				modelVar="licenseKeyDisplay"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/licenses/edit_license_key" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="licenseKeyId" value="<%= licenseKeyDisplay.getLicenseKeyId() %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name-description"
				>
					<strong>
						<%= HtmlUtil.escape(licenseKeyDisplay.getName()) %>
					</strong>

					<div class="secondary-information">
						<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="account"
					value="<%= licenseKeyDisplay.getAccountName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="expiration-date"
					value="<%= HtmlUtil.escape(licenseKeyDisplay.getExpirationDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product-type"
				>
					<%= HtmlUtil.escape(licenseKeyDisplay.getProductName()) %>

					<div class="secondary-information">
						<%= licenseKeyDisplay.getType() %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="details"
				>
					<dl>
						<c:if test="<%= licenseKeyDisplay.showHostName() %>">
							<div>
								<dt>
									<liferay-ui:message key="host-name" />:
								</dt>
								<dd>
									<%= licenseKeyDisplay.getHostName() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= licenseKeyDisplay.showIpAddresses() %>">
							<div>
								<dt>
									<liferay-ui:message key="ip-address" />:
								</dt>
								<dd>
									<%= licenseKeyDisplay.getIpAddresses() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= licenseKeyDisplay.showMacAddresses() %>">
							<div>
								<dt>
									<liferay-ui:message key="mac-address" />:
								</dt>
								<dd>
									<%= licenseKeyDisplay.getMacAddresses() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= licenseKeyDisplay.showMaxClusterNodes() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-cluster-nodes" />:
								</dt>
								<dd>
									<%= licenseKeyDisplay.getMaxClusterNodes() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= licenseKeyDisplay.showMaximumServers() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-servers" />:
								</dt>
								<dd>
									<%= licenseKeyDisplay.getMaximumServers() %>
								</dd>
							</div>
						</c:if>
					</dl>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="status"
				>
					<span class="label label-sm <%= licenseKeyDisplay.getStatusStyle() %>"><%= StringUtil.lowerCase(licenseKeyDisplay.getStatus()) %></span>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="complimentary"
					name="complimentary"
				>
					<aui:input checked="<%= licenseKeyDisplay.isComplimentary() %>" disabled="<%= true %>" label="" name="complimentaryCheckbox" type="checkbox" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>