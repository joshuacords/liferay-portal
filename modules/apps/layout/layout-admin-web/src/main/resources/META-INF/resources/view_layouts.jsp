<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:success key='<%= portletDisplay.getPortletName() + "layoutUpdated" %>' message='<%= LanguageUtil.get(resourceBundle, "the-page-was-updated-succesfully") %>' />

<liferay-ui:success key="layoutPublished" message="the-page-was-published-succesfully" />

<liferay-ui:error embed="<%= false %>" exception="<%= GroupInheritContentException.class %>" message="this-page-cannot-be-deleted-and-cannot-have-child-pages-because-it-is-associated-with-a-site-template" />

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= layoutsAdminDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= new LayoutsAdminManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, layoutsAdminDisplayContext) %>"
/>

<liferay-ui:error exception="<%= LayoutTypeException.class %>">

	<%
	LayoutTypeException lte = (LayoutTypeException)errorException;
	%>

	<c:if test="<%= lte.getType() == LayoutTypeException.FIRST_LAYOUT %>">
		<liferay-ui:message arguments='<%= "layout.types." + lte.getLayoutType() %>' key="the-first-page-cannot-be-of-type-x" />
	</c:if>
</liferay-ui:error>

<liferay-ui:error exception="<%= RequiredSegmentsExperienceException.MustNotDeleteSegmentsExperienceReferencedBySegmentsExperiments.class %>" message="this-page-cannot-be-deleted-because-it-has-ab-tests-in-progress" />

<portlet:actionURL name="/layout/delete_layout" var="deleteLayoutURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteLayoutURL %>" cssClass="container-fluid-1280" name="fm">
	<c:choose>
		<c:when test="<%= layoutsAdminDisplayContext.hasLayouts() %>">
			<c:choose>
				<c:when test="<%= layoutsAdminDisplayContext.isSearch() %>">
					<liferay-util:include page="/flattened_view.jsp" servletContext="<%= application %>" />
				</c:when>
				<c:otherwise>

					<%
					Map<String, Object> context = new HashMap<>();

					context.put("breadcrumbEntries", layoutsAdminDisplayContext.getBreadcrumbEntriesJSONArray());
					context.put("getItemChildrenURL", layoutsAdminDisplayContext.getLayoutChildrenURL());
					context.put("layoutColumns", layoutsAdminDisplayContext.getLayoutColumnsJSONArray());
					context.put("moveLayoutColumnItemURL", layoutsAdminDisplayContext.getMoveLayoutColumnItemURL());
					context.put("pathThemeImages", themeDisplay.getPathThemeImages());
					context.put("portletNamespace", renderResponse.getNamespace());
					context.put("searchContainerId", "pages");
					context.put("siteNavigationMenuNames", layoutsAdminDisplayContext.getAutoSiteNavigationMenuNames());
					%>

					<soy:component-renderer
						context="<%= context %>"
						module="js/miller_columns/Layout.es"
						templateNamespace="com.liferay.layout.admin.web.Layout.render"
					/>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<liferay-frontend:empty-result-message
				actionDropdownItems="<%= layoutsAdminDisplayContext.isShowAddRootLayoutButton() ? layoutsAdminDisplayContext.getAddLayoutDropdownItems() : null %>"
				description='<%= LanguageUtil.get(request, "fortunately-it-is-very-easy-to-add-new-ones") %>'
				elementType='<%= LanguageUtil.get(request, "pages") %>'
			/>
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script sandbox="<%= true %>">
	var deleteSelectedPages = function() {
		if (
			confirm(
				'<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-pages-if-the-selected-pages-have-child-pages-they-will-also-be-removed" />'
			)
		) {
			submitForm(document.<portlet:namespace />fm);
		}
	};

	var ACTIONS = {
		deleteSelectedPages: deleteSelectedPages
	};

	Liferay.componentReady('pagesManagementToolbar').then(function(
		managementToolbar
	) {
		managementToolbar.on('actionItemClicked', function(event) {
			var itemData = event.data.item.data;

			if (itemData && itemData.action && ACTIONS[itemData.action]) {
				ACTIONS[itemData.action]();
			}
		});
	});
</aui:script>