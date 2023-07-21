<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String className = (String)request.getAttribute("emailAddresses.className");
long classPK = (Long)request.getAttribute("emailAddresses.classPK");

List<EmailAddress> emailAddresses = Collections.emptyList();

if (classPK > 0) {
	emailAddresses = EmailAddressServiceUtil.getEmailAddresses(className, classPK);
}
%>

<c:if test="<%= !emailAddresses.isEmpty() %>">
	<h3 class="icon-envelope"><liferay-ui:message key="additional-email-addresses" /></h3>

	<ul class="property-list">

		<%
		for (EmailAddress emailAddress : emailAddresses) {
		%>

			<li class="<%= (emailAddress.isPrimary() && !emailAddresses.isEmpty()) ? "icon-star" : StringPool.BLANK %>">
				<a href="mailto:<%= emailAddress.getAddress() %>"><%= emailAddress.getAddress() %></a>

				<%= LanguageUtil.get(request, emailAddress.getType().getName()) %>
			</li>

		<%
		}
		%>

	</ul>
</c:if>