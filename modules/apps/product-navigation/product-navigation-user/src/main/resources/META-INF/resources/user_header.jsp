<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PanelCategoryHelper panelCategoryHelper = (PanelCategoryHelper)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY_HELPER);

UserPanelCategory userPanelCategory = (UserPanelCategory)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY);

int notificationsCount = panelCategoryHelper.getNotificationsCount(userPanelCategory.getKey(), permissionChecker, themeDisplay.getScopeGroup(), user);

ProductMenuDisplayContext productMenuDisplayContext = new ProductMenuDisplayContext(liferayPortletRequest, liferayPortletResponse);
%>

<a aria-controls="<portlet:namespace /><%= AUIUtil.normalizeId(userPanelCategory.getKey()) %>Collapse" aria-expanded="<%= Objects.equals(userPanelCategory.getKey(), productMenuDisplayContext.getRootPanelCategoryKey()) %>" class="collapse-icon collapse-icon-middle <%= Objects.equals(userPanelCategory.getKey(), productMenuDisplayContext.getRootPanelCategoryKey()) ? StringPool.BLANK : "collapsed" %> panel-toggler" data-parent="#<portlet:namespace />Accordion" data-qa-id="productMenuUserPanelCategory" data-toggle="collapse" href="#<portlet:namespace /><%= AUIUtil.normalizeId(userPanelCategory.getKey()) %>Collapse" role="button">
	<liferay-ui:user-portrait
		user="<%= user %>"
	/>

	<span class="truncate-text user-name">
		<%= HtmlUtil.escape(user.getFirstName()) %>

		<c:if test="<%= themeDisplay.isImpersonated() %>">

			<%
			String impersonatingUserLabel = "you-are-impersonating-the-guest-user";

			if (themeDisplay.isSignedIn()) {
				impersonatingUserLabel = LanguageUtil.format(request, "you-are-impersonating-x", HtmlUtil.escape(user.getFullName()), false);
			}
			%>

			<small class="impersonation-message">(<%= impersonatingUserLabel %>)</small>
		</c:if>
	</span>

	<c:if test="<%= notificationsCount > 0 %>">
		<span class="badge badge-danger panel-notifications-count sticker-right">
			<span class="badge-item badge-item-expand"><%= notificationsCount %></span>
		</span>
	</c:if>

	<aui:icon cssClass="collapse-icon-closed" image="angle-right" markupView="lexicon" />

	<aui:icon cssClass="collapse-icon-open" image="angle-down" markupView="lexicon" />
</a>