<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNotNull(redirect)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);
}
%>

<liferay-site-navigation:breadcrumb
	breadcrumbEntries="<%= layoutsAdminDisplayContext.getPortletBreadcrumbEntries() %>"
/>

<c:choose>
	<c:when test="<%= layoutsAdminDisplayContext.isFirstColumn() %>">
		<liferay-ui:search-container
			searchContainer="<%= layoutsAdminDisplayContext.getFirstColumnLayoutsSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="java.lang.String"
				modelVar="name"
			>

				<%
				boolean privateLayout = false;

				if (Objects.equals(name, "private-pages")) {
					privateLayout = true;
				}

				PortletURL portletURL = liferayPortletResponse.createRenderURL();

				portletURL.setParameter("tabs1", layoutsAdminDisplayContext.getTabs1());
				portletURL.setParameter("privateLayout", String.valueOf(privateLayout));
				portletURL.setParameter("selPlid", String.valueOf(LayoutConstants.DEFAULT_PLID));
				portletURL.setParameter("displayStyle", layoutsAdminDisplayContext.getDisplayStyle());
				%>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200 table-title"
					href="<%= portletURL %>"
					name="title"
					value="<%= layoutsAdminDisplayContext.getTitle(privateLayout) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200"
					name="relative-path"
					value="<%= layoutsAdminDisplayContext.getTitle(privateLayout) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-ws-nowrap"
					name="type"
					value="<%= StringPool.TRIPLE_PERIOD %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-ws-nowrap"
					name="create-date"
					value="<%= StringPool.TRIPLE_PERIOD %>"
				/>

				<%
				row.setObject(privateLayout);
				%>

				<liferay-ui:search-container-column-jsp
					path="/layout_first_column_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="list"
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</c:when>
	<c:otherwise>
		<liferay-ui:search-container
			id="pages"
			searchContainer="<%= layoutsAdminDisplayContext.getLayoutsSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.Layout"
				keyProperty="plid"
				modelVar="layout"
			>

				<%
				PortletURL portletURL = null;

				if (layoutsAdminDisplayContext.isLayoutReachable(layout)) {
					portletURL = layoutsAdminDisplayContext.getPortletURL();

					portletURL.setParameter("selPlid", String.valueOf(layout.getPlid()));
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200 table-title"
					href="<%= portletURL %>"
					name="title"
					value="<%= layout.getName(locale) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-expand table-cell-minw-200"
					name="relative-path"
				>
					<liferay-site-navigation:breadcrumb
						breadcrumbEntries="<%= layoutsAdminDisplayContext.getRelativeBreadcrumbEntries(layout) %>"
					/>
				</liferay-ui:search-container-column-text>

				<%
				LayoutTypeController layoutTypeController = LayoutTypeControllerTracker.getLayoutTypeController(layout.getType());

				ResourceBundle layoutTypeResourceBundle = ResourceBundleUtil.getBundle("content.Language", locale, layoutTypeController.getClass());
				%>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-ws-nowrap"
					name="type"
					value='<%= LanguageUtil.get(request, layoutTypeResourceBundle, "layout.types." + layout.getType()) %>'
				/>

				<liferay-ui:search-container-column-date
					cssClass="table-cell-ws-nowrap"
					name="create-date"
					property="createDate"
				/>

				<liferay-ui:search-container-column-jsp
					path="/layout_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="list"
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</c:otherwise>
</c:choose>