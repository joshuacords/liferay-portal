<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DDMTemplate ddmTemplate = journalContentDisplayContext.getDDMTemplate();

Map<String, Object> data = new HashMap<String, Object>();

data.put("destroyOnHide", true);
data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");
data.put("title", HtmlUtil.escape(ddmTemplate.getName(locale)));
%>

<liferay-ui:icon
	data="<%= data %>"
	id="editTemplateIcon"
	message="edit-template"
	url="<%= journalContentDisplayContext.getURLEditTemplate() %>"
	useDialog="<%= true %>"
/>