<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error-header />

<liferay-ui:error exception="<%= NoSuchCPInstanceException.class %>" message="the-sku-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchPriceEntryException.class %>" message="the-entry-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchPriceListException.class %>" message="the-price-list-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchTierPriceEntryException.class %>" message="the-tier-price-entry-could-not-be-found" />

<liferay-ui:error-principal />