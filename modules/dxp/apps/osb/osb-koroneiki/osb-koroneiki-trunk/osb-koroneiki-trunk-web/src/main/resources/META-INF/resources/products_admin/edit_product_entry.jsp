<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ProductEntry productEntry = (ProductEntry)request.getAttribute(TrunkWebKeys.PRODUCT_ENTRY);

long productEntryId = BeanParamUtil.getLong(productEntry, request, "productEntryId");

renderResponse.setTitle((productEntry == null) ? LanguageUtil.get(request, "new-product") : productEntry.getName());
%>

<liferay-util:include page="/products_admin/edit_product_entry_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/products_admin/edit_product_entry" var="editProductEntryURL" />

<aui:form action="<%= editProductEntryURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="productEntryId" type="hidden" value="<%= productEntryId %>" />

	<liferay-ui:error exception="<%= ProductEntryNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= ProductEntryNameException.MustNotBeDuplicate.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= productEntry %>" model="<%= ProductEntry.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= productEntry != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= productEntry.getProductEntryKey() %>" />
			</c:if>

			<aui:input name="name" />
		</aui:fieldset>

		<div class="form-group">
			<h3 class="sheet-subtitle"><liferay-ui:message key="product-fields" /></h3>

			<aui:fieldset id='<%= renderResponse.getNamespace() + "productFields" %>'>

				<%
				List<ProductField> productFields = new ArrayList<>();

				if (productEntry != null) {
					productFields.addAll(productEntry.getProductFields());
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

<aui:script use="aui-base,liferay-auto-fields">
	var autoFields = new Liferay.AutoFields(
		{
			contentBox: 'fieldset#<portlet:namespace />productFields',
			fieldIndexes: '<portlet:namespace />productFieldIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();
</aui:script>