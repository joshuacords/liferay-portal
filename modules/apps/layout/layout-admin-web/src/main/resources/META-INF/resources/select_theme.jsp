<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectThemeDisplayContext selectThemeDisplayContext = new SelectThemeDisplayContext(request, liferayPortletRequest, liferayPortletResponse);
%>

<clay:management-toolbar
	displayContext="<%= new SelectThemeManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, selectThemeDisplayContext) %>"
/>

<c:if test="<%= permissionChecker.isOmniadmin() && PortletLocalServiceUtil.hasPortlet(themeDisplay.getCompanyId(), PortletKeys.MARKETPLACE_STORE) && PropsValues.AUTO_DEPLOY_ENABLED %>">

	<%
	PortletURL marketplaceURL = PortalUtil.getControlPanelPortletURL(request, PortletKeys.MARKETPLACE_STORE, PortletRequest.RENDER_PHASE);
	%>

	<div class="button-holder">
		<aui:button cssClass="manage-layout-set-branches-link" href="<%= marketplaceURL.toString() %>" id="installMore" value="install-more" />
	</div>
</c:if>

<aui:form cssClass="container-fluid-1280" name="selectThemeFm">
	<liferay-ui:search-container
		id="themes"
		searchContainer="<%= selectThemeDisplayContext.getThemesSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Theme"
			escapedModel="<%= true %>"
			keyProperty="themeId"
			modelVar="theme"
		>

			<%
			PluginPackage selPluginPackage = theme.getPluginPackage();

			Map<String, Object> data = new HashMap<String, Object>();

			data.put("themeid", theme.getThemeId());
			%>

			<c:choose>
				<c:when test='<%= Objects.equals(selectThemeDisplayContext.getDisplayStyle(), "descriptive") %>'>
					<liferay-ui:search-container-column-image
						src='<%= theme.getStaticResourcePath() + theme.getImagesPath() + "/thumbnail.png" %>'
					/>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h5>
							<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
								<%= theme.getName() %>
							</aui:a>
						</h5>

						<c:if test="<%= (selPluginPackage != null) && Validator.isNotNull(selPluginPackage.getAuthor()) %>">
							<h6 class="text-default">
								<span><%= LanguageUtil.format(request, "by-x", selPluginPackage.getAuthor()) %></span>
							</h6>
						</c:if>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= Objects.equals(selectThemeDisplayContext.getDisplayStyle(), "icon") %>'>

					<%
					row.setCssClass("entry-card lfr-asset-item");
					%>

					<liferay-ui:search-container-column-text>
						<clay:vertical-card
							verticalCard="<%= new SelectThemeVerticalCard(theme, renderRequest) %>"
						/>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= Objects.equals(selectThemeDisplayContext.getDisplayStyle(), "list") %>'>
					<liferay-ui:search-container-column-text
						name="name"
						truncate="<%= true %>"
					>
						<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
							<%= theme.getName() %>
						</aui:a>
					</liferay-ui:search-container-column-text>

					<%
					String author = StringPool.BLANK;

					if ((selPluginPackage != null) && Validator.isNotNull(selPluginPackage.getAuthor())) {
						author = selPluginPackage.getAuthor();
					}
					%>

					<liferay-ui:search-container-column-text
						name="author"
						value="<%= author %>"
					/>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectThemeDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectThemeFm',
		'<%= HtmlUtil.escapeJS(selectThemeDisplayContext.getEventName()) %>'
	);
</aui:script>