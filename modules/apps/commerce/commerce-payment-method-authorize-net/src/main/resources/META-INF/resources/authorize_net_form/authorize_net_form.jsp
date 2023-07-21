<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirectUrlAttribute = (String)request.getAttribute("redirectUrl");

String redirectUrl = URLCodec.decodeURL(redirectUrlAttribute);

String tokenAttribute = (String)request.getAttribute("token");
%>

<form action="<%= HtmlUtil.escapeHREF(redirectUrl) %>" class="hide" id="formAuthorizeNet" method="post" name="formAuthorizeNet">
	<input name="token" type="hidden" value="<%= HtmlUtil.escapeAttribute(URLDecoder.decode(tokenAttribute, "UTF-8")) %>" />
	<button id="btnContinue">Continue</button>
</form>

<script>
	window.onload = function() {
		document.querySelector('form').submit();
	};
</script>