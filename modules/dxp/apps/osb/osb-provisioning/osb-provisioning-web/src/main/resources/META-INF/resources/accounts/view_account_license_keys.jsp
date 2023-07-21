<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountLicenseKeysDisplayContext viewAccountLicenseKeysDisplayContext = ProvisioningWebComponentProvider.getViewAccountLicenseKeysDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountLicenseKeysDisplayContext.getAccountDisplay();

PortletURL portletURL = viewAccountLicenseKeysDisplayContext.getPortletURL();
%>

<div class="details-table table-striped">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="<%= viewAccountLicenseKeysDisplayContext.getTabsNames() %>" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="active,expired,deactivated,all" />
	</liferay-util:include>

	<aui:form action="<%= currentURL %>" name="licenseKeysFm">
		<aui:input name="accountKey" type="hidden" value="<%= accountDisplay.getKey() %>" />
		<aui:input name="licenseKeyIds" type="hidden" />
		<aui:input name="complimentary" type="hidden" />
		<aui:input name="active" type="hidden" />

		<liferay-ui:search-container
			cssClass="license-details"
			id="license-keys"
			searchContainer="<%= viewAccountLicenseKeysDisplayContext.getSearchContainer() %>"
		>
			<clay:management-toolbar
				actionDropdownItems="<%= viewAccountLicenseKeysDisplayContext.getActionDropdownItems() %>"
				clearResultsURL="<%= viewAccountLicenseKeysDisplayContext.getClearResultsURL() %>"
				creationMenu="<%= viewAccountLicenseKeysDisplayContext.getCreationMenu() %>"
				elementClasses="full-width"
				itemsTotal="<%= searchContainer.getTotal() %>"
				searchActionURL="<%= viewAccountLicenseKeysDisplayContext.getCurrentURL() %>"
				searchContainerId="license-keys"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay"
				keyProperty="licenseKeyId"
				modelVar="licenseKeyDisplay"
			>
				<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.LICENSES %>" var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/licenses/edit_license_key" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="licenseKeyId" value="<%= licenseKeyDisplay.getLicenseKeyId() %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name-description"
				>
					<%= HtmlUtil.escape(licenseKeyDisplay.getName()) %>

					<div class="secondary-information">
						<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="expiration-date"
					value="<%= licenseKeyDisplay.getExpirationDate() %>"
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
	</aui:form>
</div>

<span id="<portlet:namespace />bulkLicenseReplacement">
	<react:component
		data="<%= viewAccountLicenseKeysDisplayContext.getReplaceLicenseKeysData() %>"
		module="js/apps/BulkLicenseReplacementApp"
	/>
</span>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />license-keys'
	);

	if (searchContainer) {
		searchContainer.on('rowToggled', function(event) {
			var licenseKeyIds = '';

			var selectedItems = event.elements.allSelectedElements;

			if (selectedItems && selectedItems.size()) {
				licenseKeyIds = selectedItems.attr('value').join(',');
			}

			var licenseKeyIdsInput = A.one('#<portlet:namespace />licenseKeyIds');

			if (licenseKeyIdsInput) {
				licenseKeyIdsInput.val(licenseKeyIds);
			}
		});
	}
</aui:script>

<aui:script>
	function <portlet:namespace />downloadLicenseKeys() {
		var licenseKeysFm = document.getElementById(
			'<portlet:namespace />licenseKeysFm'
		);

		if (licenseKeysFm) {
			submitForm(
				licenseKeysFm,
				'<portlet:actionURL name="/accounts/download_license_keys"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>'
			);
		}
	}

	function <portlet:namespace />extendLicenseKeys() {
		var licenseKeysFm = document.getElementById(
			'<portlet:namespace />licenseKeysFm'
		);

		if (licenseKeysFm) {
			submitForm(
				licenseKeysFm,
				'<portlet:renderURL><portlet:param name="mvcRenderCommandName" value="/accounts/extend_license_keys" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>'
			);
		}
	}

	function <portlet:namespace />replaceLicenseKeys() {
		var licenseKeyIdsInput = document.getElementById(
			'<portlet:namespace />licenseKeyIds'
		);

		if (licenseKeyIdsInput) {
			var event = new CustomEvent('bulkReplaceLicenses', {
				detail: {
					licenseKeyIds: licenseKeyIdsInput.value,
					modalVisible: true
				}
			});

			window.dispatchEvent(event);
		}
	}

	function <portlet:namespace />updateLicenseKeysProperties(
		confirmMessage,
		fieldName,
		value
	) {
		if (!confirm(confirmMessage)) {
			return;
		}

		var field = document.getElementById('<portlet:namespace />' + fieldName);

		if (field) {
			field.value = value;
		}

		var licenseKeysFm = document.getElementById(
			'<portlet:namespace />licenseKeysFm'
		);

		if (licenseKeysFm) {
			submitForm(
				licenseKeysFm,
				'<portlet:actionURL name="/accounts/edit_license_keys"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>'
			);
		}
	}
</aui:script>