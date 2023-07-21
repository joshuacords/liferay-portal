<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset_display/init.jsp" %>

<%
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute(WebKeys.ASSET_RENDERER);

AssetRendererFactory assetRendererFactory = (AssetRendererFactory)request.getAttribute(WebKeys.ASSET_RENDERER_FACTORY);
%>

<div class="card">
	<c:choose>
		<c:when test="<%= Validator.isNotNull(assetRenderer.getThumbnailPath(renderRequest)) %>">
			<div class="aspect-ratio aspect-ratio-bg-center aspect-ratio-bg-cover" style="background-image: url('<%= assetRenderer.getThumbnailPath(renderRequest) %>');">
				<img alt="" class="sr-only" src="<%= assetRenderer.getThumbnailPath(renderRequest) %>" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="aspect-ratio aspect-ratio-bg-center aspect-ratio-bg-cover vertical-card-container">
				<aui:icon cssClass="icon-vertical-card-image" image="<%= assetRendererFactory.getIconCssClass() %>" markupView="lexicon" />
			</div>
		</c:otherwise>
	</c:choose>

	<div class="card-row card-row-layout-fixed card-row-padded card-row-valign-top">
		<div class="card-col-content lfr-card-details-column">
			<span class="lfr-card-title-text truncate-text">
				<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>
			</span>
			<span class="lfr-card-subtitle-text truncate-text">
				<%= HtmlUtil.escape(assetRendererFactory.getTypeName(locale)) %>
			</span>
		</div>
	</div>
</div>