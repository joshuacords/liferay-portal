<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceTierCommercePriceEntryDisplayContext commerceTierPriceEntryDisplayContext = (CommerceTierCommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceTierPriceEntry commerceTierPriceEntry = commerceTierPriceEntryDisplayContext.getCommerceTierPriceEntry();
CommerceCurrency commerceCurrency = commerceTierPriceEntryDisplayContext.getCommercePriceListCurrency();
long commercePriceEntryId = commerceTierPriceEntryDisplayContext.getCommercePriceEntryId();
long commercePriceListId = commerceTierPriceEntryDisplayContext.getCommercePriceListId();
long commerceTierPriceEntryId = commerceTierPriceEntryDisplayContext.getCommerceTierPriceEntryId();

BigDecimal price = BigDecimal.ZERO;
BigDecimal promoPrice = BigDecimal.ZERO;

if (commerceTierPriceEntry != null) {
	price = commerceTierPriceEntry.getPrice();
	promoPrice = commerceTierPriceEntry.getPromoPrice();
}

boolean neverExpire = ParamUtil.getBoolean(request, "neverExpire", true);

if ((commerceTierPriceEntry != null) && (commerceTierPriceEntry.getExpirationDate() != null)) {
	neverExpire = false;
}
%>

<portlet:actionURL name="editCommerceTierPriceEntry" var="editCommerceTierPriceEntryActionURL" />

<commerce-ui:side-panel-content
	title='<%= LanguageUtil.get(request, "edit-price-tier") %>'
>
	<aui:form action="<%= editCommerceTierPriceEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commercePriceEntryId" type="hidden" value="<%= commercePriceEntryId %>" />
		<aui:input name="commercePriceListId" type="hidden" value="<%= commercePriceListId %>" />
		<aui:input name="commerceTierPriceEntryId" type="hidden" value="<%= commerceTierPriceEntryId %>" />

		<liferay-ui:error exception="<%= DuplicateCommerceTierPriceEntryException.class %>" message="there-is-already-a-tier-price-entry-with-the-same-minimum-quantity" />

		<div class="row">
			<div class="col-12">
				<commerce-ui:panel
					title='<%= LanguageUtil.get(request, "details") %>'
				>
					<%@ include file="/price_lists/tier_price_entry/details.jspf" %>
				</commerce-ui:panel>
			</div>

			<c:if test="<%= commerceTierPriceEntryDisplayContext.hasCustomAttributes() %>">
				<div class="col-12">
					<commerce-ui:panel
						title='<%= LanguageUtil.get(request, "custom-attribute") %>'
					>
						<liferay-expando:custom-attribute-list
							className="<%= CommerceTierPriceEntry.class.getName() %>"
							classPK="<%= (commerceTierPriceEntry != null) ? commerceTierPriceEntry.getCommerceTierPriceEntryId() : 0 %>"
							editable="<%= true %>"
							label="<%= true %>"
						/>
					</commerce-ui:panel>
				</div>
			</c:if>
		</div>

		<aui:button-row cssClass="tier-price-entry-button-row">
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</commerce-ui:side-panel-content>