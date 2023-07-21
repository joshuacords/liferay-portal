<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PanelCategory panelCategory = (PanelCategory)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY);

ContentPanelCategoryDisplayContext contentPanelCategoryDisplayContext = new ContentPanelCategoryDisplayContext(renderRequest);
%>

<liferay-application-list:panel-category
	panelCategory="<%= panelCategory %>"
	showBody="<%= false %>"
>

	<%
	Group curSite = themeDisplay.getSiteGroup();

	List<Layout> scopeLayouts = LayoutLocalServiceUtil.getScopeGroupLayouts(curSite.getGroupId());
	%>

	<c:choose>
		<c:when test="<%= scopeLayouts.isEmpty() %>">
			<liferay-application-list:panel-category-body
				panelCategory="<%= panelCategory %>"
			/>
		</c:when>
		<c:otherwise>

			<%
			PanelAppRegistry panelAppRegistry = (PanelAppRegistry)request.getAttribute(ApplicationListWebKeys.PANEL_APP_REGISTRY);

			List<PanelApp> panelApps = panelAppRegistry.getPanelApps(panelCategory, themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroup());
			%>

			<c:if test="<%= !panelApps.isEmpty() %>">
				<div class="scope-selector">

					<%
					Group curScopeGroup = themeDisplay.getScopeGroup();
					%>

					<div class="autofit-row autofit-row-center">
						<div class="autofit-col autofit-col-expand">
							<span class="scope-name">
								<c:choose>
									<c:when test="<%= curScopeGroup.isLayout() %>">
										<%= curScopeGroup.getDescriptiveName(locale) %> (<liferay-ui:message key="scope" />)
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="default-scope" />
									</c:otherwise>
								</c:choose>
							</span>
						</div>

						<div class="autofit-col autofit-col-end">
							<clay:dropdown-menu
								dropdownItems="<%= contentPanelCategoryDisplayContext.getScopesDropdownItemList() %>"
								icon="cog"
								triggerCssClasses="dropdown-toggle icon-monospaced text-light"
							/>
						</div>
					</div>

					<liferay-application-list:panel-category-body
						panelCategory="<%= panelCategory %>"
					/>
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
</liferay-application-list:panel-category>