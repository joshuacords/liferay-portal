<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectFragmentCollectionDisplayContext selectFragmentCollectionDisplayContext = new SelectFragmentCollectionDisplayContext(renderRequest, renderResponse, request);
%>

<clay:management-toolbar
	displayContext="<%= new SelectFragmentCollectionManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, selectFragmentCollectionDisplayContext.getFragmentCollectionsSearchContainer()) %>"
/>

<aui:form cssClass="container-fluid-1280" name="selectFragmentCollectionFm">
	<liferay-ui:search-container
		searchContainer="<%= selectFragmentCollectionDisplayContext.getFragmentCollectionsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.fragment.model.FragmentCollection"
			keyProperty="fragmentCollectionId"
			modelVar="fragmentCollection"
		>

			<%
			row.setCssClass("card-page-item-asset " + row.getCssClass());
			%>

			<liferay-ui:search-container-column-text>
				<clay:horizontal-card
					horizontalCard="<%= new FragmentCollectionHorizontalCard(fragmentCollection) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
			searchResultCssClass="card-page"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectFragmentCollectionFm',
		'<%= HtmlUtil.escapeJS(selectFragmentCollectionDisplayContext.getEventName()) %>'
	);
</aui:script>