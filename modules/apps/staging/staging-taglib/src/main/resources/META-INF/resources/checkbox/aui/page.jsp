<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/checkbox/init.jsp" %>

<liferay-util:buffer
	var="extendedLabel"
>
	<%@ include file="/checkbox/extended_label.jspf" %>
</liferay-util:buffer>

<aui:input checked="<%= checked %>" data-qa-id="<%= name %>" disabled="<%= disabled %>" id="<%= id %>" ignoreRequestValue="<%= true %>" label="<%= extendedLabel %>" name="<%= name %>" type="checkbox" />