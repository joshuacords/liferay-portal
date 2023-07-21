<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/portlet/init.jsp" %>

<c:if test="<%= productMenuDisplayContext.isShowProductMenu() %>">
	<div aria-multiselectable="true" class="panel-group" data-qa-id="productMenuBody" id="<portlet:namespace />Accordion" role="tablist">

		<%
		List<PanelCategory> childPanelCategories = productMenuDisplayContext.getChildPanelCategories();

		for (PanelCategory childPanelCategory : childPanelCategories) {
		%>

			<div class="panel panel-secondary">
				<div class="panel-header panel-heading" id="<portlet:namespace /><%= AUIUtil.normalizeId(childPanelCategory.getKey()) %>Heading" role="tab">
					<div class="panel-title">
						<c:if test="<%= !childPanelCategory.includeHeader(request, PipingServletResponse.createPipingServletResponse(pageContext)) %>">

							<%
							Class<?> childPanelCategoryClass = childPanelCategory.getClass();
							%>

							<a aria-controls="<portlet:namespace /><%= AUIUtil.normalizeId(childPanelCategory.getKey()) %>Collapse" aria-expanded="<%= Objects.equals(childPanelCategory.getKey(), productMenuDisplayContext.getRootPanelCategoryKey()) %>" class="collapse-icon collapse-icon-middle panel-toggler panel-header-link <%= Objects.equals(childPanelCategory.getKey(), productMenuDisplayContext.getRootPanelCategoryKey()) ? StringPool.BLANK : "collapsed" %>" data-parent="#<portlet:namespace />Accordion" data-qa-id="productMenu<%= childPanelCategoryClass.getSimpleName() %>" data-toggle="collapse" href="#<portlet:namespace /><%= AUIUtil.normalizeId(childPanelCategory.getKey()) %>Collapse" role="button">
								<span class="category-name truncate-text"><%= childPanelCategory.getLabel(locale) %></span>

								<%
								int notificationsCount = productMenuDisplayContext.getNotificationsCount(childPanelCategory);
								%>

								<c:if test="<%= notificationsCount > 0 %>">
									<span class="panel-notifications-count sticker sticker-right sticker-rounded sticker-sm sticker-warning"><%= notificationsCount %></span>
								</c:if>

								<aui:icon cssClass="collapse-icon-closed" image="angle-right" markupView="lexicon" />

								<aui:icon cssClass="collapse-icon-open" image="angle-down" markupView="lexicon" />
							</a>
						</c:if>
					</div>
				</div>

				<div aria-expanded="<%= Objects.equals(childPanelCategory.getKey(), productMenuDisplayContext.getRootPanelCategoryKey()) %>" aria-labelledby="<portlet:namespace /><%= AUIUtil.normalizeId(childPanelCategory.getKey()) %>Heading" class="collapse panel-collapse <%= Objects.equals(childPanelCategory.getKey(), productMenuDisplayContext.getRootPanelCategoryKey()) ? "in" : StringPool.BLANK %>" data-parent="#<portlet:namespace />Accordion" id="<portlet:namespace /><%= AUIUtil.normalizeId(childPanelCategory.getKey()) %>Collapse" role="tabpanel">
					<div class="panel-body">
						<liferay-application-list:panel-content
							panelCategory="<%= childPanelCategory %>"
						/>
					</div>
				</div>
			</div>

		<%
		}
		%>

	</div>
</c:if>