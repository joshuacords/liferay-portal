<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%@ include file="/html/portal/layout/view/embedded_js.jspf" %>

<div id="iframe">

	<%
	UnicodeProperties typeSettingsProperties = layout.getTypeSettingsProperties();
	%>

	<iframe frameborder="0" id="embeddedIframe" src="<%= HtmlUtil.escapeHREF(typeSettingsProperties.getProperty("embeddedLayoutURL")) %>" width="100%"></iframe>
</div>

<liferay-ui:layout-common />