<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<c:choose>
	<c:when test="<%= !themeDisplay.isStatePopUp() %>">
		<aui:container class="lfr-panel-page" id="main-content">
			<aui:row>

				<%
				String panelBodyCssClass = "panel-page-body";

				if (!layoutTypePortlet.hasStateMax()) {
					panelBodyCssClass += " panel-page-frontpage";
				}
				else {
					panelBodyCssClass += " panel-page-application";
				}
				%>

				<aui:col cssClass="panel-page-menu" width="<%= 20 %>">

					<%
					PortletCategory portletCategory = (PortletCategory)WebAppPool.get(company.getCompanyId(), WebKeys.PORTLET_CATEGORY);

					portletCategory = PortletCategoryUtil.getRelevantPortletCategory(permissionChecker, user.getCompanyId(), layout, portletCategory, layoutTypePortlet);

					List<PortletCategory> portletCategories = ListUtil.fromCollection(portletCategory.getCategories());

					portletCategories = ListUtil.sort(portletCategories, new PortletCategoryComparator(locale));

					for (PortletCategory curPortletCategory : portletCategories) {
					%>

						<c:if test="<%= !curPortletCategory.isHidden() %>">

							<%
							request.setAttribute(WebKeys.PORTLET_CATEGORY, curPortletCategory);
							%>

							<liferay-util:include page="/html/portal/layout/view/view_category.jsp" />
						</c:if>

					<%
					}
					%>

				</aui:col>

				<aui:col cssClass="<%= panelBodyCssClass %>" width="<%= 80 %>">
					<%@ include file="/html/portal/layout/view/panel_description.jspf" %>
				</aui:col>
			</aui:row>
		</aui:container>
	</c:when>
	<c:otherwise>
		<%@ include file="/html/portal/layout/view/panel_description.jspf" %>
	</c:otherwise>
</c:choose>

<liferay-ui:layout-common />