<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LocalizedItemSelectorRendering localizedItemSelectorRendering = LocalizedItemSelectorRendering.get(liferayPortletRequest);

List<NavigationItem> navigationItems = localizedItemSelectorRendering.getNavigationItems();
%>

<c:choose>
	<c:when test="<%= navigationItems.isEmpty() %>">

		<%
		if (_log.isWarnEnabled()) {
			String[] criteria = ParamUtil.getParameterValues(renderRequest, "criteria");

			_log.warn("No item selector views found for " + StringUtil.merge(criteria, StringPool.COMMA_AND_SPACE));
		}
		%>

		<div class="alert alert-info">
			<%= LanguageUtil.get(resourceBundle, "selection-is-not-available") %>
		</div>
	</c:when>
	<c:otherwise>
		<clay:navigation-bar
			inverted="<%= false %>"
			navigationItems="<%= navigationItems %>"
		/>

		<c:choose>
			<c:when test='<%= ParamUtil.getBoolean(request, "showGroupSelector") %>'>
				<liferay-item-selector:group-selector />
			</c:when>
			<c:otherwise>

				<%
				ItemSelectorViewRenderer itemSelectorViewRenderer = localizedItemSelectorRendering.getSelectedItemSelectorViewRenderer();

				itemSelectorViewRenderer.renderHTML(pageContext);
				%>

			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<%!
private static Log _log = LogFactoryUtil.getLog("com_liferay_item_selector_web.view_jsp");
%>