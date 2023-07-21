<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/layout/view/init.jsp" %>

<%
String mode = ParamUtil.getString(request, "liferay-layout:render-fragment-layout:mode", FragmentEntryLinkConstants.VIEW);
String ppid = ParamUtil.getString(request, "p_p_id");

RenderContentLayoutDisplayContext renderContentLayoutDisplayContext = new RenderContentLayoutDisplayContext(request, response);
%>

<liferay-util:html-top>
	<%= renderContentLayoutDisplayContext.getPortletHeaderPaths() %>
</liferay-util:html-top>

<liferay-ui:success key="layoutPublished" message="the-page-was-published-succesfully" />

<c:choose>
	<c:when test="<%= (themeDisplay.isStatePopUp() || themeDisplay.isWidget() || layoutTypePortlet.hasStateMax()) && Validator.isNotNull(ppid) %>">

		<%
		String templateId = null;
		String templateContent = null;
		String langType = null;

		if (themeDisplay.isStatePopUp() || themeDisplay.isWidget()) {
			templateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "pop_up";
			templateContent = LayoutTemplateLocalServiceUtil.getContent("pop_up", true, theme.getThemeId());
			langType = LayoutTemplateLocalServiceUtil.getLangType("pop_up", true, theme.getThemeId());
		}
		else {
			ppid = StringUtil.split(layoutTypePortlet.getStateMax())[0];

			templateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "max";
			templateContent = LayoutTemplateLocalServiceUtil.getContent("max", true, theme.getThemeId());
			langType = LayoutTemplateLocalServiceUtil.getLangType("max", true, theme.getThemeId());
		}

		if (Validator.isNotNull(templateContent)) {
			RuntimePageUtil.processTemplate(request, response, ppid, new StringTemplateResource(templateId, templateContent), langType);
		}
		%>

	</c:when>
	<c:otherwise>
		<liferay-layout:render-fragment-layout
			groupId="<%= layout.getGroupId() %>"
			mode="<%= mode %>"
			plid="<%= layout.getPlid() %>"
			showPreview="<%= true %>"
		/>
	</c:otherwise>
</c:choose>

<liferay-ui:layout-common />

<liferay-util:html-bottom>
	<%= renderContentLayoutDisplayContext.getPortletFooterPaths() %>
</liferay-util:html-bottom>