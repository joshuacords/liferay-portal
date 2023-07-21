<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/edit_form/init.jsp" %>

<%
String fullName = namespace.concat(HtmlUtil.escapeAttribute(name));
%>

<form action="<%= HtmlUtil.escapeAttribute(action) %>" class="container container-no-gutters-sm-down container-view form <%= cssClass %> <%= inlineLabels ? "field-labels-inline" : StringPool.BLANK %>" data-fm-namespace="<%= namespace %>" id="<%= fullName %>" method="<%= method %>" name="<%= fullName %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<c:if test="<%= !themeDisplay.isStatePopUp() %>">
		<div class="sheet <%= fluid ? StringPool.BLANK : "sheet-lg" %>">
	</c:if>

		<div class="panel-group panel-group-flush">
			<c:if test="<%= Validator.isNotNull(onSubmit) %>">
				<fieldset class="input-container" disabled="disabled">
					<legend class="sr-only"><%= HtmlUtil.escape(portletDisplay.getTitle()) %></legend>
			</c:if>

			<aui:input name="formDate" type="hidden" value="<%= System.currentTimeMillis() %>" />