<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectLayoutPageTemplateEntryDisplayContext selectLayoutPageTemplateEntryDisplayContext = new SelectLayoutPageTemplateEntryDisplayContext(request);
%>

<div class="lfr-search-container-wrapper" id="<portlet:namespace />layoutTypes">
	<c:if test="<%= selectLayoutPageTemplateEntryDisplayContext.getPrimaryTypesCount() > 0 %>">
		<h6 class="sheet-subtitle">
			<liferay-ui:message key="main-types" />
		</h6>

		<ul class="card-page card-page-equal-height">

			<%
			for (String primaryType : selectLayoutPageTemplateEntryDisplayContext.getPrimaryTypes()) {
				SelectBasicPagesVerticalCard selectBasicPagesVerticalCard = new SelectBasicPagesVerticalCard(primaryType, renderRequest, renderResponse);
			%>

				<li class="card-page-item col-md-4 col-sm-6">
					<div class="add-layout-action-option card card-interactive card-interactive-primary card-type-template template-card " <%= AUIUtil.buildData(selectBasicPagesVerticalCard.getDataLink()) %> tabindex="0">
						<div class="aspect-ratio">
							<div class="aspect-ratio-item-center-middle aspect-ratio-item-flush layout-type-img">
								<img src="<%= selectBasicPagesVerticalCard.getImageSrc() %>" />
							</div>
						</div>

						<div class="card-body">
							<div class="card-title">
								<%= selectBasicPagesVerticalCard.getTitle() %>
							</div>

							<div class="card-text">
								<%= selectBasicPagesVerticalCard.getSubtitle() %>
							</div>
						</div>
					</div>
				</li>

			<%
			}
			%>

		</ul>
	</c:if>

	<c:if test="<%= selectLayoutPageTemplateEntryDisplayContext.getTypesCount() > 0 %>">
		<h6 class="sheet-subtitle">
			<liferay-ui:message key="other" />
		</h6>

		<ul class="card-page card-page-equal-height">

			<%
			for (String type : selectLayoutPageTemplateEntryDisplayContext.getTypes()) {
			%>

				<li class="card-page-item col-md-4 col-sm-6">
					<clay:horizontal-card
						horizontalCard="<%= new SelectBasicPagesHorizontalCard(type, renderRequest, renderResponse) %>"
					/>
				</li>

			<%
			}
			%>

		</ul>
	</c:if>
</div>