<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String className = (String)request.getAttribute("phones.className");
long classPK = (Long)request.getAttribute("phones.classPK");

List<Phone> phones = Collections.emptyList();

if (classPK > 0) {
	phones = PhoneServiceUtil.getPhones(className, classPK);
}
%>

<c:if test="<%= !phones.isEmpty() %>">
	<h3 class="icon-phone-sign"><liferay-ui:message key="phones" /></h3>

	<ul class="property-list">

		<%
		for (Phone phone : phones) {
		%>

			<li class="<%= (phone.isPrimary() && !phones.isEmpty()) ? "icon-star" : StringPool.BLANK %>">
				<%= HtmlUtil.escape(phone.getNumber()) %> <%= phone.getExtension() %> <%= LanguageUtil.get(request, phone.getType().getName()) %>
			</li>

		<%
		}
		%>

	</ul>
</c:if>