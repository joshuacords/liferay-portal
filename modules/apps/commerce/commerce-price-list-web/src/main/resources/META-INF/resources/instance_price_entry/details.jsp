<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPInstanceCommercePriceEntryDisplayContext cpInstanceCommercePriceEntryDisplayContext = (CPInstanceCommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommercePriceEntry commercePriceEntry = cpInstanceCommercePriceEntryDisplayContext.getCommercePriceEntry();

long commercePriceEntryId = commercePriceEntry.getCommercePriceEntryId();
CommercePriceList commercePriceList = commercePriceEntry.getCommercePriceList();
%>

<portlet:actionURL name="editCPInstanceCommercePriceEntry" var="editCommercePriceEntryActionURL" />

<aui:form action="<%= editCommercePriceEntryActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commercePriceEntryId" type="hidden" value="<%= commercePriceEntryId %>" />
	<aui:input name="cpInstanceId" type="hidden" value="<%= cpInstanceCommercePriceEntryDisplayContext.getCPInstanceId() %>" />

	<aui:model-context bean="<%= commercePriceEntry %>" model="<%= CommercePriceEntry.class %>" />

	<div class="row">
		<div class="col-12">
			<%@ include file="/price_entry/details.jspf" %>

			<%@ include file="/price_entry/custom_fields.jspf" %>
		</div>
	</div>

	<aui:button-row cssClass="price-entry-button-row">
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>