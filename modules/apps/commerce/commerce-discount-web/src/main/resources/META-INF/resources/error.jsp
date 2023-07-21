<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error-header />

<liferay-ui:error exception="<%= NoSuchDiscountException.class %>" message="the-discount-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchDiscountRelException.class %>" message="the-discount-rel-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchDiscountRuleException.class %>" message="the-discount-rule-could-not-be-found" />

<liferay-ui:error-principal />