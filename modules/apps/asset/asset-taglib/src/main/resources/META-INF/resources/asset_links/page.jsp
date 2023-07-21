<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset_links/init.jsp" %>

<%
List<Tuple> assetLinkEntries = (List<Tuple>)request.getAttribute("liferay-asset:asset-links:assetLinkEntries");
%>

<h2 class="mb-3 sheet-tertiary-title">
	<liferay-ui:message key="related-assets" />
</h2>

<ul class="list-group sidebar-list-group">

	<%
	for (Tuple tuple : assetLinkEntries) {
		AssetEntry assetLinkEntry = (AssetEntry)tuple.getObject(0);

		AssetRenderer assetRenderer = assetLinkEntry.getAssetRenderer();
	%>

		<li class="list-group-item list-group-item-flex">
			<div class="autofit-col">
				<div class="sticker sticker-secondary">
					<span class="inline-item">
						<aui:icon image="<%= assetRenderer.getIconCssClass() %>" markupView="lexicon" />
					</span>
				</div>
			</div>

			<div class="autofit-col autofit-col-expand">
				<section class="autofit-section">
					<div class="list-group-title text-truncate-inline">
						<c:choose>
							<c:when test="<%= assetRenderer.getStatus() == WorkflowConstants.STATUS_SCHEDULED %>">
								<%= HtmlUtil.escape(assetLinkEntry.getTitle(locale)) %>
								<span class="label label-<%= LabelItem.getStyleFromWorkflowStatus(assetRenderer.getStatus()) %> ml-2 text-uppercase">
									<liferay-ui:message key="<%= WorkflowConstants.getStatusLabel(assetRenderer.getStatus()) %>" />
								</span>
							</c:when>
							<c:otherwise>
								<aui:a cssClass="text-truncate" href="<%= (String)tuple.getObject(1) %>" target='<%= themeDisplay.isStatePopUp() ? "_blank" : "_self" %>'>
									<%= HtmlUtil.escape(assetLinkEntry.getTitle(locale)) %>
								</aui:a>
							</c:otherwise>
						</c:choose>
					</div>
				</section>
			</div>
		</li>

	<%
	}
	%>

</ul>