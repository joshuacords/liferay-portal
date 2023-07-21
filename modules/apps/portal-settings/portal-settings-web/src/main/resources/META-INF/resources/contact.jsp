<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
request.setAttribute("addresses.className", Account.class.getName());
request.setAttribute("emailAddresses.className", Account.class.getName());
request.setAttribute("phones.className", Account.class.getName());
request.setAttribute("websites.className", Account.class.getName());

request.setAttribute("addresses.classPK", company.getAccountId());
request.setAttribute("emailAddresses.classPK", company.getAccountId());
request.setAttribute("phones.classPK", company.getAccountId());
request.setAttribute("websites.classPK", company.getAccountId());
%>

<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="addresses" /></h3>

	<%@ include file="/addresses.jsp" %>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="phone-numbers" /></h3>

	<%@ include file="/phone_numbers.jsp" %>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="additional-email-addresses" /></h3>

	<%@ include file="/additional_email_addresses.jsp" %>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="websites" /></h3>

	<%@ include file="/websites.jsp" %>
</div>