<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long[] siteNavigationMenuItemIds = ParamUtil.getLongValues(request, "siteNavigationMenuItemId");

long siteNavigationMenuItemId = siteNavigationMenuItemIds[siteNavigationMenuItemIds.length - 1];

long selectedSiteNavigationMenuItemId = ParamUtil.getLong(request, "selectedSiteNavigationMenuItemId");

SiteNavigationMenuItem siteNavigationMenuItem = SiteNavigationMenuItemLocalServiceUtil.getSiteNavigationMenuItem(siteNavigationMenuItemId);

SiteNavigationMenuItemType siteNavigationMenuItemType = siteNavigationMenuItemTypeRegistry.getSiteNavigationMenuItemType(siteNavigationMenuItem.getType());

String title = siteNavigationMenuItemType.getTitle(siteNavigationMenuItem, locale);

Map<String, Object> data = new HashMap<String, Object>();

data.put("site-navigation-menu-item-id", siteNavigationMenuItemId);
data.put("title", HtmlUtil.escape(title));

request.setAttribute("edit_site_navigation_menu.jsp-siteNavigationMenuItemId", siteNavigationMenuItem.getSiteNavigationMenuItemId());
%>

<div class="site-navigation-menu-item <%= (siteNavigationMenuItem.getParentSiteNavigationMenuItemId() > 0) ? "site-navigation-menu-item--nested" : StringPool.BLANK %> <%= (selectedSiteNavigationMenuItemId == siteNavigationMenuItemId) ? "site-navigation-menu-item--selected" : StringPool.BLANK %>" <%= AUIUtil.buildData(data) %>" tabindex="0">
	<div class="site-navigation-menu-item__content">
		<div class="card card-horizontal taglib-horizontal-card">
			<div class="card-row card-row-padded site-navigation-menu-item__card">
				<div class="card-row">
					<div class="autofit-col site-navigation-menu-item__drag-icon">
						<liferay-ui:icon
							icon="drag"
							markupView="lexicon"
						/>
					</div>

					<div class="autofit-col autofit-col-expand autofit-col-gutters">
						<h4 class="list-group-title">
							<span class="text-truncate">
								<a href="javascript:;">
									<%= HtmlUtil.escape(title) %>
								</a>
							</span>
						</h4>

						<h6 class="list-group-subtitle text-truncate">
							<%= HtmlUtil.escape(siteNavigationMenuItemType.getSubtitle(siteNavigationMenuItem, locale)) %>
						</h6>
					</div>
				</div>

				<c:if test="<%= siteNavigationAdminDisplayContext.hasUpdatePermission() %>">
					<div class="card-col-field lfr-card-actions-column">
						<liferay-util:include page="/site_navigation_menu_item_action.jsp" servletContext="<%= application %>" />
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<%
	List<SiteNavigationMenuItem> children = SiteNavigationMenuItemLocalServiceUtil.getSiteNavigationMenuItems(siteNavigationMenuItem.getSiteNavigationMenuId(), siteNavigationMenuItemId);

	for (SiteNavigationMenuItem childSiteNavigationMenuItem : children) {
	%>

		<liferay-util:include page="/view_site_navigation_menu_item.jsp" servletContext="<%= application %>">
			<liferay-util:param name="siteNavigationMenuItemId" value="<%= String.valueOf(childSiteNavigationMenuItem.getSiteNavigationMenuItemId()) %>" />
		</liferay-util:include>

	<%
	}
	%>

</div>