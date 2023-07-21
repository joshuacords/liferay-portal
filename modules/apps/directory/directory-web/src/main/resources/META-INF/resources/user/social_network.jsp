<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Contact selContact = (Contact)request.getAttribute("user.selContact");

String facebook = selContact.getFacebookSn();
String twitter = selContact.getTwitterSn();
%>

<c:if test="<%= Validator.isNotNull(facebook) || Validator.isNotNull(twitter) %>">
	<h3 class="icon-group"><liferay-ui:message key="social-network" /></h3>

	<dl class="property-list">
		<c:if test="<%= Validator.isNotNull(facebook) %>">
			<dt>
				<liferay-ui:message key="facebook" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(facebook) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(twitter) %>">
			<dt>
				<liferay-ui:message key="twitter" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(twitter) %>
			</dd>
		</c:if>
	</dl>
</c:if>