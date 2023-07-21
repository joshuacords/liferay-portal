<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Contact selContact = (Contact)request.getAttribute("user.selContact");

String jabberSn = selContact.getJabberSn();
String skypeSn = selContact.getSkypeSn();
%>

<c:if test="<%= Validator.isNotNull(jabberSn) || Validator.isNotNull(skypeSn) %>">
	<h3 class="icon-comments"><liferay-ui:message key="instant-messenger" /></h3>

	<dl class="property-list">
		<c:if test="<%= Validator.isNotNull(jabberSn) %>">
			<dt>
				<liferay-ui:message key="jabber" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(jabberSn) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(skypeSn) %>">
			<dt>
				<liferay-ui:message key="skype" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(skypeSn) %>
				<a href="callto://<%= HtmlUtil.escapeAttribute(skypeSn) %>"><img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="call-this-user" />" class="instant-messenger-logo" src="http://mystatus.skype.com/smallicon/<%= HtmlUtil.escapeAttribute(skypeSn) %>" /></a>
			</dd>
		</c:if>
	</dl>
</c:if>