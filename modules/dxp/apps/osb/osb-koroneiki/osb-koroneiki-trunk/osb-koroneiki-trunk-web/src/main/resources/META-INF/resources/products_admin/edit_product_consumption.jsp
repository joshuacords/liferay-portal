<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ProductConsumption productConsumption = (ProductConsumption)request.getAttribute(TrunkWebKeys.PRODUCT_CONSUMPTION);

long accountId = ParamUtil.getLong(request, "accountId");
long productPurchaseId = ParamUtil.getLong(request, "productPurchaseId");

boolean defaultPerpetual = true;

if (productConsumption != null) {
	defaultPerpetual = productConsumption.isPerpetual();
}

boolean perpetual = ParamUtil.getBoolean(request, "perpetual", defaultPerpetual);

renderResponse.setTitle((productConsumption == null) ? LanguageUtil.get(request, "new-consumption") : LanguageUtil.get(request, "consumption"));
%>

<liferay-util:include page="/products_admin/edit_product_consumption_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/products_admin/edit_product_consumption" var="editProductConsumptionURL" />

<aui:form action="<%= editProductConsumptionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= NoSuchAccountException.class %>" message="please-select-a-valid-account" />
	<liferay-ui:error exception="<%= NoSuchProductEntryException.class %>" message="please-select-a-valid-product" />

	<aui:model-context bean="<%= productConsumption %>" model="<%= ProductConsumption.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:choose>
				<c:when test="<%= productConsumption != null %>">
					<aui:input label="key" name="key" type="resource" value="<%= productConsumption.getProductConsumptionKey() %>" />

					<%
					Account koroneikiAccount = productConsumption.getAccount();
					%>

					<h5><liferay-ui:message key="account" /></h5>

					<p>
						<liferay-portlet:renderURL portletName="<%= TaprootPortletKeys.ACCOUNTS_ADMIN %>" var="accountURL">
							<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account" />
							<portlet:param name="accountId" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= accountURL %>"><%= HtmlUtil.escape(koroneikiAccount.getName()) %></a>
					</p>

					<%
					ProductEntry productEntry = productConsumption.getProductEntry();
					%>

					<h5><liferay-ui:message key="product" /></h5>

					<p>
						<liferay-portlet:renderURL var="productEntryURL">
							<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_entry" />
							<portlet:param name="productEntryId" value="<%= String.valueOf(productEntry.getProductEntryId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= productEntryURL %>"><%= HtmlUtil.escape(productEntry.getName()) %></a>
					</p>

					<%
					ProductPurchase productPurchase = productConsumption.getProductPurchase();
					%>

					<c:if test="<%= productPurchase != null %>">
						<h5><liferay-ui:message key="product-purchase" /></h5>

						<p>
							<liferay-portlet:renderURL var="productPurchaseURL">
								<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_purchase" />
								<portlet:param name="productPurchaseId" value="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>" />
							</liferay-portlet:renderURL>

							<a href="<%= productPurchaseURL %>"><%= HtmlUtil.escape(productPurchase.getProductPurchaseKey()) %></a>
						</p>
					</c:if>
				</c:when>
				<c:otherwise>
					<h5><liferay-ui:message key="account" /></h5>

					<p>
						<aui:input name="accountId" type="hidden" value="<%= accountId %>" />

						<span id="<portlet:namespace />accountName">
							<c:if test="<%= accountId > 0 %>">

								<%
								Account koroneikiAccount = AccountLocalServiceUtil.getAccount(accountId);
								%>

								<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
							</c:if>
						</span>

						<aui:button onClick='<%= renderResponse.getNamespace() + "openAccountSelector();" %>' value="select" />
					</p>

					<aui:select label="product" name="productEntryId">
						<aui:option value="" />

						<%
						for (ProductEntry productEntry : ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
						%>

							<aui:option label="<%= productEntry.getName() %>" value="<%= productEntry.getProductEntryId() %>" />

						<%
						}
						%>

					</aui:select>

					<h5><liferay-ui:message key="product-purchase" /></h5>

					<p>
						<aui:input name="productPurchaseId" type="hidden" value="<%= productPurchaseId %>" />

						<span id="<portlet:namespace />productPurchaseKey">
							<c:if test="<%= productPurchaseId > 0 %>">

								<%
								ProductPurchase productPurchase = ProductPurchaseLocalServiceUtil.getProductPurchase(productPurchaseId);
								%>

								<%= HtmlUtil.escape(productPurchase.getProductPurchaseKey()) %>
							</c:if>
						</span>

						<aui:button onClick='<%= renderResponse.getNamespace() + "openProductPurchaseSelector();" %>' value="select" />
					</p>
				</c:otherwise>
			</c:choose>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "toggleDate(this.checked, 'startDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'endDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'originalEndDate');";
			%>

			<aui:input checked="<%= perpetual %>" disabled="<%= productConsumption != null %>" name="perpetual" onClick="<%= taglibOnClick %>" type="checkbox" />

			<aui:input disabled="<%= perpetual || (productConsumption != null) %>" name="startDate" />

			<aui:input disabled="<%= perpetual || (productConsumption != null) %>" name="endDate" />

			<div class="form-group">
				<h3 class="sheet-subtitle"><liferay-ui:message key="product-fields" /></h3>

				<aui:fieldset id='<%= renderResponse.getNamespace() + "productFields" %>'>

					<%
					List<ProductField> productFields = new ArrayList<>();

					if (productConsumption != null) {
						productFields.addAll(productConsumption.getProductFields());
					}

					if (productFields.isEmpty()) {
						productFields.add(ProductFieldLocalServiceUtil.createProductField(0));
					}

					int[] productFieldIndexes = new int[productFields.size()];

					for (int i = 0; i < productFields.size(); i++) {
						ProductField productField = productFields.get(i);

						productFieldIndexes[i] = i;
					%>

						<div class="lfr-form-row lfr-form-row-inline">
							<div class="row-fields">
								<aui:row>
									<aui:col md="5">
										<aui:input label="name" name='<%= "productFieldName_" + i %>' type="text" value="<%= productField.getName() %>" />
									</aui:col>

									<aui:col md="5">
										<aui:input label="value" name='<%= "productFieldValue_" + i %>' type="text" value="<%= productField.getValue() %>" />
									</aui:col>
								</aui:row>
							</div>
						</div>

					<%
					}
					%>

					<aui:input name="productFieldIndexes" type="hidden" value="<%= StringUtil.merge(productFieldIndexes) %>" />
				</aui:fieldset>
			</div>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<c:if test="<%= productConsumption == null %>">
			<aui:button type="submit" />
		</c:if>

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />toggleDate',
		function(checked, dateParam) {
			var A = AUI();

			var dayField = A.one('#<portlet:namespace />' + dateParam + 'Day');

			if (dayField) {
				dayField.attr('disabled', checked);
			}

			var inputDateField = A.one('#<portlet:namespace />' + dateParam);

			if (inputDateField) {
				inputDateField.attr('disabled', checked);

				if (checked) {
					inputDateField.addClass('disabled');
				}
				else {
					inputDateField.removeClass('disabled');
				}
			}

			var monthField = A.one('#<portlet:namespace />' + dateParam + 'Month');

			if (monthField) {
				monthField.attr('disabled', checked);
			}

			var yearField = A.one('#<portlet:namespace />' + dateParam + 'Year');

			if (yearField) {
				yearField.attr('disabled', checked);
			}
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base,liferay-auto-fields">
	var autoFields = new Liferay.AutoFields(
		{
			contentBox: 'fieldset#<portlet:namespace />productFields',
			fieldIndexes: '<portlet:namespace />productFieldIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();

	<portlet:namespace />openAccountSelector = function() {
		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					modal: true
				},
				eventName: 'selectAccount',
				title: '<%= UnicodeLanguageUtil.get(request, "accounts") %>',
				uri: '<liferay-portlet:renderURL portletName="<%= TaprootPortletKeys.ACCOUNTS_ADMIN %>" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/accounts_admin/select_account.jsp" /></liferay-portlet:renderURL>'
			},
			function(event) {
				A.one('#<portlet:namespace />accountName').html(event.accountname);
				A.one('#<portlet:namespace />accountId').val(event.accountid);
			}
		);
	}

	<portlet:renderURL var="selectProductPurchasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/products_admin/select_product_purchase.jsp" />
	</portlet:renderURL>

	<portlet:namespace />openProductPurchaseSelector = function() {
		var uri = '<%= selectProductPurchasesURL %>';

		uri = Liferay.Util.addParams('<portlet:namespace />accountId=' + A.one('#<portlet:namespace />accountId').val(), uri);
		uri = Liferay.Util.addParams('<portlet:namespace />productEntryId=' + A.one('#<portlet:namespace />productEntryId').val(), uri);

		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					modal: true
				},
				eventName: 'selectProductPurchase',
				title: '<%= UnicodeLanguageUtil.get(request, "product-purchases") %>',
				uri: uri
			},
			function(event) {
				A.one('#<portlet:namespace />productPurchaseId').val(event.productpurchaseid);
				A.one('#<portlet:namespace />productPurchaseKey').html(event.productpurchasekey);
			}
		);
	}
</aui:script>