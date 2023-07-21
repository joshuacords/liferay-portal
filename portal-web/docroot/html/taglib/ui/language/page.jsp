<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/language/init.jsp" %>

<%
Map<String, Object> contextObjects = new HashMap<String, Object>();

contextObjects.put("formAction", formAction);
contextObjects.put("formName", formName);
contextObjects.put("languageId", languageId);
contextObjects.put("name", name);
contextObjects.put("namespace", namespace);
%>

<c:if test="<%= !languageEntries.isEmpty() %>">

	<%
	String renderedDDMTemplate = StringPool.BLANK;

	DDMTemplate portletDisplayDDMTemplate = PortletDisplayTemplateManagerUtil.getDDMTemplate(displayStyleGroupId, PortalUtil.getClassNameId(LanguageEntry.class), displayStyle, true);

	if (portletDisplayDDMTemplate != null) {
		renderedDDMTemplate = PortletDisplayTemplateManagerUtil.renderDDMTemplate(request, response, portletDisplayDDMTemplate.getTemplateId(), languageEntries, contextObjects);
	}
	%>

	<%= renderedDDMTemplate %>
</c:if>