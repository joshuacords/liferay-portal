<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ProductPurchase productPurchase = (ProductPurchase)request.getAttribute(TrunkWebKeys.PRODUCT_PURCHASE);

long productPurchaseId = BeanParamUtil.getLong(productPurchase, request, "productPurchaseId");
long accountId = BeanParamUtil.getLong(productPurchase, request, "accountId");
int quantity = BeanParamUtil.getInteger(productPurchase, request, "quantity", 1);

boolean defaultPerpetual = true;

if (productPurchase != null) {
	defaultPerpetual = productPurchase.isPerpetual();
}

boolean perpetual = ParamUtil.getBoolean(request, "perpetual", defaultPerpetual);

renderResponse.setTitle((productPurchase == null) ? LanguageUtil.get(request, "new-purchase") : LanguageUtil.get(request, "edit-purchase"));
%>

<liferay-util:include page="/products_admin/edit_product_purchase_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/products_admin/edit_product_purchase" var="editProductPurchaseURL" />

<aui:form action="<%= editProductPurchaseURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="productPurchaseId" type="hidden" value="<%= productPurchaseId %>" />

	<liferay-ui:error exception="<%= NoSuchAccountException.class %>" message="please-select-a-valid-account" />
	<liferay-ui:error exception="<%= NoSuchProductEntryException.class %>" message="please-select-a-valid-product" />
	<liferay-ui:error exception="<%= ProductPurchaseEndDateException.class %>" message="please-enter-a-valid-end-date" />
	<liferay-ui:error exception="<%= ProductPurchaseQuantityException.class %>" message="please-enter-a-valid-quantity" />

	<aui:model-context bean="<%= productPurchase %>" model="<%= ProductPurchase.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:choose>
				<c:when test="<%= productPurchase != null %>">
					<aui:input label="key" name="key" type="resource" value="<%= productPurchase.getProductPurchaseKey() %>" />

					<%
					Account koroneikiAccount = productPurchase.getAccount();
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
					ProductEntry productEntry = productPurchase.getProductEntry();
					%>

					<h5><liferay-ui:message key="product" /></h5>

					<p>
						<liferay-portlet:renderURL var="productEntryURL">
							<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_entry" />
							<portlet:param name="productEntryId" value="<%= String.valueOf(productEntry.getProductEntryId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= productEntryURL %>"><%= HtmlUtil.escape(productEntry.getName()) %></a>
					</p>
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
				</c:otherwise>
			</c:choose>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "toggleDate(this.checked, 'startDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'endDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'originalEndDate');";
			%>

			<aui:input checked="<%= perpetual %>" name="perpetual" onClick="<%= taglibOnClick %>" type="checkbox" />

			<aui:input disabled="<%= perpetual %>" name="startDate" />

			<aui:input disabled="<%= perpetual %>" name="endDate" />

			<aui:input disabled="<%= perpetual %>" name="originalEndDate" />

			<aui:input name="quantity" value="<%= String.valueOf(quantity) %>" />

			<aui:select name="status">
				<aui:option value="" />

				<%
				for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase.Status status : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase.Status.values()) {
				%>

					<aui:option label="<%= status %>" value="<%= WorkflowConstants.getLabelStatus(status.toString()) %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<div class="form-group">
			<h3 class="sheet-subtitle"><liferay-ui:message key="product-fields" /></h3>

			<aui:fieldset id='<%= renderResponse.getNamespace() + "productFields" %>'>

				<%
				List<ProductField> productFields = new ArrayList<>();

				if (productPurchase != null) {
					productFields.addAll(productPurchase.getProductFields());
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
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

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
</aui:script>