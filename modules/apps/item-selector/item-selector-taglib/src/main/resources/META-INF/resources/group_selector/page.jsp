<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/group_selector/init.jsp" %>

<%
List<Group> groups = (List<Group>)request.getAttribute("liferay-item-selector:group-selector:groups");
int groupsCount = GetterUtil.getInteger(request.getAttribute("liferay-item-selector:group-selector:groupsCount"));
ItemSelector itemSelector = (ItemSelector)request.getAttribute("liferay-item-selector:group-selector:itemSelector");

RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(request);

String itemSelectedEventName = ParamUtil.getString(request, "itemSelectedEventName");

List<ItemSelectorCriterion> itemSelectorCriteria = itemSelector.getItemSelectorCriteria(liferayPortletRequest.getParameterMap());

PortletURL iteratorURL = itemSelector.getItemSelectorURL(requestBackedPortletURLFactory, itemSelectedEventName, itemSelectorCriteria.toArray(new ItemSelectorCriterion[0]));

iteratorURL.setParameter("selectedTab", ParamUtil.getString(request, "selectedTab"));
iteratorURL.setParameter("showGroupSelector", Boolean.TRUE.toString());

SearchContainer searchContainer = new GroupSearch(liferayPortletRequest, iteratorURL);
%>

<div class="container-fluid-1280 lfr-item-viewer">
	<liferay-ui:search-container
		searchContainer="<%= searchContainer %>"
		total="<%= groupsCount %>"
		var="listSearchContainer"
	>
		<liferay-ui:search-container-results
			results="<%= groups %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Group"
			modelVar="curGroup"
		>

			<%
			long refererGroupId = (themeDisplay.getRefererGroupId() != 0) ? themeDisplay.getRefererGroupId() : themeDisplay.getScopeGroupId();

			PortletURL viewGroupURL = itemSelector.getItemSelectorURL(requestBackedPortletURLFactory, curGroup, refererGroupId, itemSelectedEventName, itemSelectorCriteria.toArray(new ItemSelectorCriterion[0]));

			viewGroupURL.setParameter("selectedTab", ParamUtil.getString(request, "selectedTab"));

			row.setCssClass("entry-card lfr-asset-item");
			%>

			<liferay-ui:search-container-column-text
				colspan="<%= 3 %>"
			>
				<liferay-frontend:horizontal-card
					cardCssClass="card-interactive card-interactive-primary"
					resultRow="<%= row %>"
					text="<%= curGroup.getDescriptiveName(locale) %>"
					url="<%= viewGroupURL.toString() %>"
				>
					<liferay-frontend:horizontal-card-col>
						<liferay-frontend:horizontal-card-icon
							icon="folder"
						/>
					</liferay-frontend:horizontal-card-col>
				</liferay-frontend:horizontal-card>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
			searchContainer="<%= searchContainer %>"
		/>
	</liferay-ui:search-container>
</div>