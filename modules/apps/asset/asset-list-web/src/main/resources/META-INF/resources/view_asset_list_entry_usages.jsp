<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

AssetListEntryUsagesDisplayContext assetListEntryUsagesDisplayContext = new AssetListEntryUsagesDisplayContext(renderRequest, renderResponse);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(assetListDisplayContext.getAssetListEntryTitle());
%>

<div class="container-fluid container-fluid-max-xl container-form-lg">
	<div class="row">
		<div class="col-lg-3">
			<nav class="menubar menubar-transparent menubar-vertical-expand-lg">
				<ul class="nav nav-nested">
					<li class="nav-item">
						<strong class="text-uppercase">
							<liferay-ui:message key="usages" />
						</strong>

						<ul class="nav nav-stacked">
							<li class="nav-item">

								<%
								PortletURL allNavigationURL = assetListEntryUsagesDisplayContext.getPortletURL();

								allNavigationURL.setParameter("navigation", "all");
								%>

								<a class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "all") ? "active" : StringPool.BLANK %>" href="<%= allNavigationURL.toString() %>">
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getAllUsageCount() %>" key="all-x" />
								</a>
							</li>
							<li class="nav-item">

								<%
								PortletURL pagesNavigationURL = assetListEntryUsagesDisplayContext.getPortletURL();

								pagesNavigationURL.setParameter("navigation", "pages");
								%>

								<a class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "pages") ? "active" : StringPool.BLANK %>" href="<%= pagesNavigationURL.toString() %>">
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPagesUsageCount() %>" key="pages-x" />
								</a>
							</li>
							<li class="nav-item">

								<%
								PortletURL pageTemplatesNavigationURL = assetListEntryUsagesDisplayContext.getPortletURL();

								pageTemplatesNavigationURL.setParameter("navigation", "page-templates");
								%>

								<a class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "page-templates") ? "active" : StringPool.BLANK %>" href="<%= pageTemplatesNavigationURL.toString() %>">
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
								</a>
							</li>
							<li class="nav-item">

								<%
								PortletURL displayPagesNavigationURL = assetListEntryUsagesDisplayContext.getPortletURL();

								displayPagesNavigationURL.setParameter("navigation", "display-page-templates");
								%>

								<a class="nav-link <%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "display-page-templates") ? "active" : StringPool.BLANK %>" href="<%= displayPagesNavigationURL.toString() %>">
									<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>

		<div class="col-lg-9">
			<div class="sheet">
				<h3 class="sheet-title">
					<c:choose>
						<c:when test='<%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "pages") %>'>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPagesUsageCount() %>" key="pages-x" />
						</c:when>
						<c:when test='<%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "page-templates") %>'>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getPageTemplatesUsageCount() %>" key="page-templates-x" />
						</c:when>
						<c:when test='<%= Objects.equals(assetListEntryUsagesDisplayContext.getNavigation(), "display-page-templates") %>'>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getDisplayPagesUsageCount() %>" key="display-page-templates-x" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message arguments="<%= assetListEntryUsagesDisplayContext.getAllUsageCount() %>" key="all-x" />
						</c:otherwise>
					</c:choose>
				</h3>

				<clay:management-toolbar
					displayContext="<%= new AssetListEntryUsagesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, assetListEntryUsagesDisplayContext.getSearchContainer()) %>"
				/>

				<liferay-ui:search-container
					id="assetListEntryUsages"
					searchContainer="<%= assetListEntryUsagesDisplayContext.getSearchContainer() %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.asset.list.model.AssetListEntryUsage"
						keyProperty="assetListEntryUsageId"
						modelVar="assetListEntryUsage"
					>
						<liferay-ui:search-container-column-text
							name="name"
							value="<%= HtmlUtil.escape(assetListEntryUsagesDisplayContext.getAssetListEntryUsageName(assetListEntryUsage)) %>"
						/>

						<liferay-ui:search-container-column-text
							name="type"
							translate="<%= true %>"
							value="<%= assetListEntryUsagesDisplayContext.getAssetListEntryUsageTypeLabel(assetListEntryUsage) %>"
						/>

						<liferay-ui:search-container-column-date
							name="modified-date"
							value="<%= assetListEntryUsage.getModifiedDate() %>"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
						searchResultCssClass="show-quick-actions-on-hover table table-autofit"
					/>
				</liferay-ui:search-container>
			</div>
		</div>
	</div>
</div>