<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirectionDataAttribute = (String)request.getAttribute("redirectionData");

String redirectionData = URLDecoder.decode(redirectionDataAttribute, "UTF-8");

String redirectUrlAttribute = (String)request.getAttribute("redirectUrl");

String redirectUrl = URLCodec.decodeURL(redirectUrlAttribute);

String sealAttribute = (String)request.getAttribute("seal");
%>

<form action="<%= HtmlUtil.escapeHREF(redirectUrl) %>" class="hide" id="formMercanet" method="post" name="formMercanet">
	<input name="redirectionData" type="hidden" value="<%= HtmlUtil.escapeAttribute(redirectionData) %>" />
	<input name="seal" type="hidden" value="<%= HtmlUtil.escapeAttribute(URLDecoder.decode(sealAttribute, "UTF-8")) %>" />
	<input type="submit" value="Proceed to checkout" />
</form>

<script>
	window.onload = function() {
		document.querySelector('form').submit();
	};
</script>