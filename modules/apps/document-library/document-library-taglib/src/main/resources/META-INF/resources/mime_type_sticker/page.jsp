<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext = (DLViewFileVersionDisplayContext)request.getAttribute("liferay-document-library:mime-type-sticker:dlViewFileVersionDisplayContext");
%>

<div class="sticker sticker-document <%= (String)request.getAttribute("liferay-document-library:mime-type-sticker:cssClass") %> <%= dlViewFileVersionDisplayContext.getCssClassFileMimeType() %>">
	<clay:icon
		symbol="<%= dlViewFileVersionDisplayContext.getIconFileMimeType() %>"
	/>
</div>