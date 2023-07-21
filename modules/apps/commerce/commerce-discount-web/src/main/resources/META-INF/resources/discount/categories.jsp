<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDiscountRelDisplayContext commerceDiscountRelDisplayContext = (CommerceDiscountRelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="editCommerceDiscountRel" var="editCommerceDiscountRelActionURL" />

<aui:form action="<%= editCommerceDiscountRelActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="className" type="hidden" value="<%= AssetCategory.class.getName() %>" />
	<aui:input name="commerceDiscountId" type="hidden" value="<%= commerceDiscountRelDisplayContext.getCommerceDiscountId() %>" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<liferay-asset:asset-categories-error />

				<h4><liferay-ui:message key="select-categories" /></h4>

				<liferay-asset:asset-categories-selector
					categoryIds="<%= commerceDiscountRelDisplayContext.getAssetCategoryIds() %>"
					hiddenInput="classPKs"
				/>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>