<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SiteNavigationMenuItemSelectorViewDisplayContext siteNavigationMenuItemSelectorViewDisplayContext = (SiteNavigationMenuItemSelectorViewDisplayContext)request.getAttribute(SiteNavigationItemSelectorWebKeys.SITE_NAVIGATION_MENU_ITEM_SELECTOR_DISPLAY_CONTEXT);

String displayStyle = siteNavigationMenuItemSelectorViewDisplayContext.getDisplayStyle();
%>

<clay:management-toolbar
	displayContext="<%= new SiteNavigationMenuItemSelectorViewManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, siteNavigationMenuItemSelectorViewDisplayContext) %>"
/>

<aui:form action="<%= siteNavigationMenuItemSelectorViewDisplayContext.getPortletURL() %>" cssClass="container-fluid-1280" name="selectSiteNavigationMenuFm">
	<liferay-ui:search-container
		searchContainer="<%= siteNavigationMenuItemSelectorViewDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.site.navigation.model.SiteNavigationMenu"
			keyProperty="siteNavigationMenuId"
			modelVar="siteNavigationMenu"
		>

			<%
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("id", siteNavigationMenu.getSiteNavigationMenuId());
			data.put("name", siteNavigationMenu.getName());
			%>

			<c:choose>
				<c:when test='<%= displayStyle.equals("descriptive") %>'>
					<liferay-ui:search-container-column-user
						showDetails="<%= false %>"
						userId="<%= siteNavigationMenu.getUserId() %>"
					/>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h4>
							<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
								<%= HtmlUtil.escape(siteNavigationMenu.getName()) %>
							</aui:a>
						</h4>

						<%
						Date createDate = siteNavigationMenu.getCreateDate();

						String createDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
						%>

						<h5 class="text-default">
							<liferay-ui:message arguments="<%= new String[] {siteNavigationMenu.getUserName(), createDateDescription} %>" key="x-created-x-ago" />
						</h5>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="title"
					>
						<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
							<%= HtmlUtil.escape(siteNavigationMenu.getName()) %>
						</aui:a>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="author"
						property="userName"
					/>

					<liferay-ui:search-container-column-date
						name="create-date"
						property="createDate"
					/>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectSiteNavigationMenuFm',
		'<%= HtmlUtil.escapeJS(siteNavigationMenuItemSelectorViewDisplayContext.getItemSelectedEventName()) %>'
	);
</aui:script>