<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/table_iterator/init.jsp" %>

<table
	border="0"
	cellpadding="0"
	cellspacing="0"
	<c:if test="<%= Validator.isNotNull(width) %>">
		width="<%= width %>"
	</c:if>
>
<tr>
	<td class="lrf-<%= rowValign %>">