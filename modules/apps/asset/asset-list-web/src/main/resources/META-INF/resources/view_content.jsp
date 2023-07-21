<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid-1280 pt-3">
	<liferay-ui:search-container
		id="assetEntries"
		searchContainer="<%= assetListDisplayContext.getAssetListContentSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.asset.kernel.model.AssetEntry"
			keyProperty="entryId"
			modelVar="assetEntry"
		>

			<%
			AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

			AssetRendererFactory assetRendererFactory = assetRenderer.getAssetRendererFactory();
			%>

			<liferay-ui:search-container-column-text
				name="title"
				value="<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				value="<%= assetRendererFactory.getTypeName(locale) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:button-row>
	<aui:button type="cancel" value="close" />
</aui:button-row>