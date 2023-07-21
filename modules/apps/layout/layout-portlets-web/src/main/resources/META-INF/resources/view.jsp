<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutPortletsDisplayContext layoutPortletsDisplayContext = new LayoutPortletsDisplayContext(renderRequest, renderResponse, request);
%>

<clay:management-toolbar
	displayContext="<%= new LayoutPortletsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, layoutPortletsDisplayContext) %>"
/>

<aui:form action="" cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		searchContainer="<%= layoutPortletsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Portlet"
			cssClass="selectable"
			escapedModel="<%= true %>"
			keyProperty="portletId"
			modelVar="portlet"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand-small table-cell-minw-200 table-title"
				name="name"
				property="displayName"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-cell-minw-300"
				name="categories"
				value="<%= layoutPortletsDisplayContext.getPortletCategoryLabels(portlet.getRootPortletId()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= layoutPortletsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>