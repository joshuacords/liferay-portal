<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.result.display.context.SearchResultFieldDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.result.display.context.SearchResultSummaryDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.search.results.portlet.SearchResultsPortletDisplayContext" %>

<portlet:defineObjects />

<%
SearchResultsPortletDisplayContext searchResultsPortletDisplayContext = (SearchResultsPortletDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));

if (searchResultsPortletDisplayContext.isRenderNothing()) {
	return;
}

com.liferay.portal.kernel.dao.search.SearchContainer<com.liferay.portal.kernel.search.Document> searchContainer1 = searchResultsPortletDisplayContext.getSearchContainer();
%>

<p class="search-total-label text-default">
	<liferay-ui:message arguments='<%= new String[] {String.valueOf(searchContainer1.getTotal()), "<strong>" + HtmlUtil.escape(searchResultsPortletDisplayContext.getKeywords()) + "</strong>"} %>' key="x-results-for-x" />
</p>

<liferay-ui:search-container
	emptyResultsMessage='<%= LanguageUtil.format(request, "no-results-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(searchResultsPortletDisplayContext.getKeywords()) + "</strong>", false) %>'
	id='<%= renderResponse.getNamespace() + "searchContainerTag" %>'
	searchContainer="<%= searchContainer1 %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.search.Document"
		escapedModel="<%= false %>"
		keyProperty="UID"
		modelVar="document"
		stringKey="<%= true %>"
	>

		<%
		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext = java.util.Objects.requireNonNull(searchResultsPortletDisplayContext.getSearchResultSummaryDisplayContext(document));
		%>

		<c:choose>
			<c:when test="<%= !searchResultSummaryDisplayContext.isTemporarilyUnavailable() %>">
				<liferay-ui:search-container-column-text>
					<c:choose>
						<c:when test="<%= searchResultSummaryDisplayContext.isThumbnailVisible() %>">
							<span class="sticker">
								<span class="sticker-overlay">
									<img alt="<liferay-ui:message key="thumbnail" />" class="sticker-img" src="<%= searchResultSummaryDisplayContext.getThumbnailURLString() %>" />
								</span>
							</span>
						</c:when>
						<c:when test="<%= searchResultSummaryDisplayContext.isUserPortraitVisible() && java.util.Objects.equals(searchResultSummaryDisplayContext.getClassName(), User.class.getName()) %>">
							<liferay-ui:user-portrait
								userId="<%= searchResultSummaryDisplayContext.getAssetEntryUserId() %>"
							/>
						</c:when>
						<c:when test="<%= searchResultSummaryDisplayContext.isIconVisible() %>">
							<span class="sticker sticker-rounded sticker-secondary sticker-static">
								<svg class="lexicon-icon">
									<use xlink:href="<%= searchResultSummaryDisplayContext.getPathThemeImages() %>/lexicon/icons.svg#<%= searchResultSummaryDisplayContext.getIconId() %>" />

									<title><%= searchResultSummaryDisplayContext.getIconId() %></title>
								</svg>
							</span>
						</c:when>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					colspan="<%= 2 %>"
				>
					<div>
						<a href="<%= searchResultSummaryDisplayContext.getViewURL() %>">
							<strong><%= searchResultSummaryDisplayContext.getHighlightedTitle() %></strong>
						</a>
					</div>

					<div class="text-default">
						<c:if test="<%= searchResultSummaryDisplayContext.isModelResourceVisible() %>">
							<strong><%= searchResultSummaryDisplayContext.getModelResource() %></strong>
						</c:if>

						<c:if test="<%= searchResultSummaryDisplayContext.isLocaleReminderVisible() %>">
							<liferay-ui:icon
								image='<%= "../language/" + searchResultSummaryDisplayContext.getLocaleLanguageId() %>'
								message="<%= searchResultSummaryDisplayContext.getLocaleReminder() %>"
							/>
						</c:if>

						<c:if test="<%= searchResultSummaryDisplayContext.isCreatorVisible() %>">
							&#183;
							<liferay-ui:message key="written-by" /> <strong><%= HtmlUtil.escape(searchResultSummaryDisplayContext.getCreatorUserName()) %></strong>
						</c:if>

						<c:if test="<%= searchResultSummaryDisplayContext.isCreationDateVisible() %>">
							<liferay-ui:message key="on-date" /> <%= searchResultSummaryDisplayContext.getCreationDateString() %>
						</c:if>
					</div>

					<c:if test="<%= searchResultSummaryDisplayContext.isContentVisible() %>">
						<span class="search-document-content text-default">
							<%= searchResultSummaryDisplayContext.getContent() %>
						</span>
					</c:if>

					<c:if test="<%= searchResultSummaryDisplayContext.isFieldsVisible() %>">
						<div class="search-document-content text-default">

							<%
							boolean separate = false;

							for (SearchResultFieldDisplayContext searchResultFieldDisplayContext : searchResultSummaryDisplayContext.getFieldDisplayContexts()) {
							%>

								<c:if test="<%= separate %>">
									&#183;
								</c:if>

								<span class="badge"><%= searchResultFieldDisplayContext.getName() %></span>
								<span><%= searchResultFieldDisplayContext.getValuesToString() %></span>

							<%
								separate = true;
							}
							%>

						</div>
					</c:if>

					<c:if test="<%= searchResultSummaryDisplayContext.isAssetCategoriesOrTagsVisible() %>">
						<div class="search-document-tags text-default">
							<liferay-asset:asset-tags-summary
								className="<%= searchResultSummaryDisplayContext.getClassName() %>"
								classPK="<%= searchResultSummaryDisplayContext.getClassPK() %>"
								paramName="<%= searchResultSummaryDisplayContext.getFieldAssetTagNames() %>"
								portletURL="<%= searchResultSummaryDisplayContext.getPortletURL() %>"
							/>

							<liferay-asset:asset-categories-summary
								className="<%= searchResultSummaryDisplayContext.getClassName() %>"
								classPK="<%= searchResultSummaryDisplayContext.getClassPK() %>"
								paramName="<%= searchResultSummaryDisplayContext.getFieldAssetCategoryIds() %>"
								portletURL="<%= searchResultSummaryDisplayContext.getPortletURL() %>"
							/>
						</div>
					</c:if>

					<c:if test="<%= searchResultSummaryDisplayContext.isDocumentFormVisible() %>">
						<div class="expand-details text-default"><span style="font-size:xx-small;"><a href="javascript:;"><liferay-ui:message key="details" />...</a></span></div>

						<div class="hide table-details table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th style="text-align: right;">
											<liferay-ui:message key="key" />
										</th>
										<th>
											<liferay-ui:message key="value" />
										</th>
									</tr>
								</thead>

								<tbody>

									<%
									for (SearchResultFieldDisplayContext searchResultFieldDisplayContext : searchResultSummaryDisplayContext.getDocumentFormFieldDisplayContexts()) {
									%>

										<tr>
											<td style="padding-bottom: 0; padding-top: 0; text-align: right; word-break: break-all;" width="15%">
												<strong><%= HtmlUtil.escape(searchResultFieldDisplayContext.getName()) %></strong>
											</td>
											<td style="padding-bottom: 0; padding-top: 0;">
												<code>
													<%= searchResultFieldDisplayContext.getValuesToString() %>
												</code>
											</td>
										</tr>

									<%
									}
									%>

								</tbody>
							</table>
						</div>
					</c:if>
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:otherwise>
				<liferay-ui:search-container-column-text
					colspan="<%= 3 %>"
				>
					<div class="alert alert-danger">
						<liferay-ui:message arguments="result" key="is-temporarily-unavailable" translateArguments="<%= true %>" />
					</div>
				</liferay-ui:search-container-column-text>
			</c:otherwise>
		</c:choose>
	</liferay-ui:search-container-row>

	<aui:form action="#" useNamespace="<%= false %>">
		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
			type="more"
		/>
	</aui:form>
</liferay-ui:search-container>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />searchContainerTag').delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			currentTarget.siblings('.table-details').toggleClass('hide');
		},
		'.expand-details'
	);
</aui:script>