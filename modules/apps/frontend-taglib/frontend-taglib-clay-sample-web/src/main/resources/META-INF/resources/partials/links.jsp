<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<blockquote>
	<p>A link (also known as hyperlink) is a clickable (text or image) element. The link control is used for navigation.</p>
</blockquote>

<h3>SINGLE LINK</h3>

<blockquote>
	<p>Used for stand-alone hyperlinks. Can be a text or an image.</p>
</blockquote>

<%
Map<String, String> data = new HashMap<>();

data.put("customProperty", "customValue");
%>

<clay:link
	data="<%= data %>"
	href="#"
	label="link text"
/>