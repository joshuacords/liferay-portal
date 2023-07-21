<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/common/top_tabs.jsp" servletContext="<%= application %>" />

<%
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);
%>

<div class="float-container kb-entity-header">
	<div class="kb-title">
		<%= HtmlUtil.escape(kbTemplate.getTitle()) %>
	</div>
</div>

<div class="kb-entity-body">
	<%= kbTemplate.getContent() %>

	<liferay-util:include page="/admin/template_comments.jsp" servletContext="<%= application %>" />
</div>