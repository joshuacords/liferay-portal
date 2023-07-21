<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2");

ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();

PortletURL portletURL = viewAccountDisplayContext.getPortletURL();

SearchContainer productPurchasesSearchContainer = viewAccountDisplayContext.getProductPurchaseViewsSearchContainer();
%>

<div class="info-container">
	<div class="info">
		<clay:icon
			symbol="exclamation-circle"
		/>

		<liferay-ui:message key="date-and-time-displayed-in-utc-all-end-dates-are-exclusive" />
	</div>

	<c:if test="<%= viewAccountDisplayContext.hasManageAccountsPermission() && (viewAccountDisplayContext.getLatestActiveProductPurchaseEndDate() != null) %>">
		<div>
			<clay:button
				ariaLabel='<%= LanguageUtil.get(request, "extend-all-active-subscriptions") %>'
				elementClasses="btn-secondary"
				icon="time"
				id='<%= renderResponse.getNamespace() + "openExtendAllActiveSubscriptionsModal" %>'
				monospaced="<%= true %>"
				title='<%= LanguageUtil.get(request, "extend-all-active-subscriptions") %>'
				type="button"
			/>

			<span id="<portlet:namespace />extendAllSubscriptions">
				<react:component
					data="<%= viewAccountDisplayContext.getLatestActiveProductPurchaseDetails() %>"
					module="js/apps/ExtendAllSubscriptionsApp"
				/>
			</span>
		</div>
	</c:if>
</div>

<div class="details-table table-striped">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="<%= viewAccountDisplayContext.getTabsNames() %>" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="active,future,complimentary,expired,cancelled,all" />
	</liferay-util:include>

	<portlet:actionURL name="/accounts/edit_product_purchases_select_terms" var="editProductPurchasesURL">
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="tabs2" value="<%= tabs2 %>" />
	</portlet:actionURL>

	<aui:form action="<%= editProductPurchasesURL %>" method="post" name="editProductPurchasesFm">
		<aui:input name="productBundleIds" type="hidden" />
		<aui:input name="productKeys" type="hidden" />
		<aui:input name="productPurchaseViewKeys" type="hidden" />

		<clay:management-toolbar
			componentId="productPurchasesManagementToolbar"
			displayContext="<%= new ViewProductPurchasesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productPurchasesSearchContainer, accountDisplay.getKey()) %>"
			elementClasses="full-width"
			searchContainerId="productPurchases"
		/>

		<liferay-ui:search-container
			id="productPurchases"
			searchContainer="<%= productPurchasesSearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay"
				keyProperty="productKey"
				modelVar="productPurchaseViewDisplay"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/accounts/view_subscription" />
					<portlet:param name="accountKey" value="<%= productPurchaseViewDisplay.getAccountKey() %>" />
					<portlet:param name="productKey" value="<%= productPurchaseViewDisplay.getProductKey() %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product"
				>
					<%= HtmlUtil.escape(productPurchaseViewDisplay.getName()) %>

					<div class="secondary-information">
						<%= productPurchaseViewDisplay.getSizingWithLabel() %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="support-life"
				>
					<%= productPurchaseViewDisplay.getSupportLife() %>

					<c:choose>
						<c:when test="<%= productPurchaseViewDisplay.isInSupportGap() && Validator.isNotNull(productPurchaseViewDisplay.getNextTermStartDate()) %>">
							<div class="secondary-information">
								<liferay-ui:message key="next-term-starts" />: <%= productPurchaseViewDisplay.getNextTermStartDate() %>
							</div>
						</c:when>
						<c:when test="<%= !productPurchaseViewDisplay.isPerpetual() %>">
							<div class="secondary-information">
								<liferay-ui:message key="grace-period-end-date" />: <%= productPurchaseViewDisplay.getEndDate() %>
							</div>
						</c:when>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="semi-bold"
					href="<%= productPurchaseViewDisplay.getProvisionedCountURL() %>"
					name='<%= tabs2.equals("all") ? "provisioned" : "current-provisioned" %>'
					value='<%= tabs2.equals("all") ? productPurchaseViewDisplay.getProvisionedCount() : productPurchaseViewDisplay.getCurrentProvisionedCount() %>'
				/>

				<%
				String columnName = "current-purchased";
				String columnCount = productPurchaseViewDisplay.getCurrentPurchasedCount();

				if (tabs2.equals("all")) {
					columnName = "approved-purchased";
					columnCount = productPurchaseViewDisplay.getApprovedPurchasedCount();
				}
				else if (tabs2.equals("cancelled") || tabs2.equals("expired") || tabs2.equals("future")) {
					columnName = "latest-purchased";
					columnCount = productPurchaseViewDisplay.getLatestPurchasedCount();
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="semi-bold"
					href="<%= rowURL %>"
					name="<%= columnName %>"
					value="<%= columnCount %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="state"
				>
					<span class="label <%= productPurchaseViewDisplay.getStateStyle() %>"><%= productPurchaseViewDisplay.getState() %></span>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				resultRowSplitter="<%= new ProductPurchaseViewResultRowSplitter() %>"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script use="liferay-item-selector-dialog, liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />productPurchases'
	);

	if (searchContainer) {
		searchContainer.on('rowToggled', function(event) {
			var productKeys = '';

			var selectedItems = event.elements.allSelectedElements;

			if (selectedItems && selectedItems.size()) {
				productKeys = selectedItems.attr('value').join(',');
			}

			var productPurchaseViewKeys = A.one(
				'#<portlet:namespace />productPurchaseViewKeys'
			);

			if (productPurchaseViewKeys) {
				productPurchaseViewKeys.val(productKeys);
			}
		});
	}

	var selectedItemChange = function() {
		var itemSelectorDialog = new A.LiferayItemSelectorDialog({
			eventName: 'selectedItemChange',
			on: {
				selectedItemChange: function(event) {
					var selectedItems = event.newVal;

					if (selectedItems) {
						var selectedKeys = selectedItems.map(function(item) {
							return item.split('_')[0];
						});

						var productBundleIds = selectedKeys
							.filter(function(key) {
								return !key.startsWith('KOR');
							})
							.join(',');

						var productBundleIdsInput = A.one(
							'#<portlet:namespace />productBundleIds'
						);

						if (productBundleIdsInput) {
							productBundleIdsInput.val(productBundleIds);
						}

						var productKeys = selectedKeys
							.filter(function(key) {
								return key.startsWith('KOR');
							})
							.join(',');

						var productKeysInput = A.one(
							'#<portlet:namespace />productKeys'
						);

						if (productKeysInput) {
							productKeysInput.val(productKeys);
						}

						<portlet:namespace />editProductPurchases();
					}
				}
			},
			strings: {
				add: '<liferay-ui:message key="done" />',
				cancel: '<liferay-ui:message key="cancel" />'
			},
			title: '<liferay-ui:message key="select-subscriptions" />',
			url: '<%= viewAccountDisplayContext.getAssignProductsURL() %>'
		});

		itemSelectorDialog.open();
	};

	Liferay.componentReady('productPurchasesManagementToolbar')
		.then(function(managementToolbar) {
			managementToolbar.on('creationButtonClicked', selectedItemChange);
		})
		.catch(function(err) {
			console.error(err);
		});
</aui:script>

<aui:script>
	function <portlet:namespace />editProductPurchases() {
		var editProductPurchasesFm = document.getElementById(
			'<portlet:namespace />editProductPurchasesFm'
		);

		if (editProductPurchasesFm) {
			editProductPurchasesFm.submit();
		}
	}

	var extendAllActiveSubscriptionsBtn = document.getElementById(
		'<portlet:namespace />openExtendAllActiveSubscriptionsModal'
	);

	var extendAllActiveSubscriptionsEvent = new CustomEvent(
		'extendAllActiveSubscriptions',
		{
			detail: {modalVisible: true}
		}
	);

	if (extendAllActiveSubscriptionsBtn) {
		extendAllActiveSubscriptionsBtn.addEventListener('click', function() {
			window.dispatchEvent(extendAllActiveSubscriptionsEvent);
		});
	}
</aui:script>