<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
assetTagsSelectorDisplayContext = new AssetTagsSelectorDisplayContext(renderRequest, renderResponse, request, false);
%>

<clay:management-toolbar
	displayContext="<%= new AssetTagsSelectorManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, assetTagsSelectorDisplayContext) %>"
/>

<aui:form action="<%= assetTagsSelectorDisplayContext.getPortletURL() %>" cssClass="container-fluid-1280" method="post" name="selectAssetTagFm">
	<liferay-ui:search-container
		searchContainer="<%= assetTagsSelectorDisplayContext.getTagsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.asset.kernel.model.AssetTag"
			escapedModel="<%= true %>"
			keyProperty="name"
			modelVar="tag"
			rowIdProperty="friendlyURL"
			rowVar="row"
		>

			<%
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("entityid", tag.getTagId());
			data.put("entityname", tag.getName());
			%>

			<liferay-ui:search-container-column-text
				name="name"
				truncate="<%= true %>"
			>
				<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
					<%= HtmlUtil.escape(tag.getName()) %>
				</aui:a>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectAssetTagFm',
		'<%= HtmlUtil.escapeJS(assetTagsSelectorDisplayContext.getEventName()) %>',
		true
	);
</aui:script>