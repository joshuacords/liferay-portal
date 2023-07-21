<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<ConfigurationCategorySectionDisplay> configurationCategorySectionDisplays = (List<ConfigurationCategorySectionDisplay>)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_CATEGORY_SECTION_DISPLAYS);
ConfigurationEntryRetriever configurationEntryRetriever = (ConfigurationEntryRetriever)request.getAttribute(ConfigurationAdminWebKeys.CONFIGURATION_ENTRY_RETRIEVER);
%>

<portlet:renderURL var="redirectURL" />

<portlet:renderURL var="searchURL">
	<portlet:param name="mvcRenderCommandName" value="/search" />
	<portlet:param name="redirect" value="<%= redirectURL %>" />
</portlet:renderURL>

<clay:management-toolbar
	searchActionURL="<%= searchURL %>"
	selectable="<%= false %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl container-view">
	<ul class="list-group">

		<%
		for (ConfigurationCategorySectionDisplay configurationCategorySectionDisplay : configurationCategorySectionDisplays) {
		%>

			<li class="list-group-header">
				<h3 class="list-group-header-title text-uppercase">
					<%= HtmlUtil.escape(configurationCategorySectionDisplay.getConfigurationCategorySectionLabel(locale)) %>
				</h3>
			</li>
			<li class="list-group-card">
				<ul class="list-group">

					<%
					ConfigurationScopeDisplayContext configurationScopeDisplayContext = new ConfigurationScopeDisplayContext(renderRequest);

					for (ConfigurationCategoryDisplay configurationCategoryDisplay : configurationCategorySectionDisplay.getConfigurationCategoryDisplays()) {
						ConfigurationCategoryMenuDisplay configurationCategoryMenuDisplay = configurationEntryRetriever.getConfigurationCategoryMenuDisplay(configurationCategoryDisplay.getCategoryKey(), themeDisplay.getLanguageId(), configurationScopeDisplayContext.getScope(), configurationScopeDisplayContext.getScopePK());

						if (configurationCategoryMenuDisplay.isEmpty()) {
							continue;
						}
					%>

						<li class="list-group-card-item">
							<a href="<%= ConfigurationCategoryUtil.getHREF(configurationCategoryMenuDisplay, liferayPortletResponse, renderRequest, renderResponse) %>">
								<clay:icon
									elementClasses="user-icon-sm"
									symbol="<%= configurationCategoryDisplay.getCategoryIcon() %>"
								/>

								<span class="list-group-card-item-text">
									<%= HtmlUtil.escape(configurationCategoryDisplay.getCategoryLabel(locale)) %>
								</span>
							</a>
						</li>

					<%
					}
					%>

				</ul>
			</li>

		<%
		}
		%>

	</ul>
</div>