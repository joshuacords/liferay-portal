<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/search/init.jsp" %>

<%
long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");
%>

<div class="kb-search-header">
	<liferay-util:include page="/search/view.jsp" servletContext="<%= application %>" />
</div>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/search/search.jsp" />
	<portlet:param name="categoryId" value="<%= String.valueOf(assetCategoryId) %>" />
	<portlet:param name="tag" value="<%= assetTagName %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	iteratorURL="<%= iteratorURL %>"
	orderByCol='<%= ParamUtil.getString(request, "orderByCol") %>'
	orderByType='<%= ParamUtil.getString(request, "orderByType", "desc") %>'
>

	<%
	AssetEntryQuery assetEntryQuery = new AssetEntryQuery(KBArticle.class.getName(), searchContainer);

	searchContainer.setTotal(AssetEntryServiceUtil.getEntriesCount(assetEntryQuery));

	total = searchContainer.getTotal();

	assetEntryQuery.setEnd(searchContainer.getEnd());
	assetEntryQuery.setStart(searchContainer.getStart());

	searchContainer.setResults(AssetEntryServiceUtil.getEntries(assetEntryQuery));
	%>

	<liferay-ui:search-container-row
		className="com.liferay.asset.kernel.model.AssetEntry"
		keyProperty="entryId"
		modelVar="assetEntry"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="mvcPath" value="/search/view_article.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(assetEntry.getClassPK()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			orderable="<%= true %>"
			property="title"
		/>

		<c:if test="<%= kbSearchPortletInstanceConfiguration.showKBArticleAuthorColumn() %>">
			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="author"
				orderable="<%= true %>"
				orderableProperty="userName"
				property="userName"
			/>
		</c:if>

		<c:if test="<%= kbSearchPortletInstanceConfiguration.showKBArticleCreateDateColumn() %>">
			<liferay-ui:search-container-column-date
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="create-date"
				orderable="<%= true %>"
				orderableProperty="createDate"
				value="<%= assetEntry.getCreateDate() %>"
			/>
		</c:if>

		<c:if test="<%= kbSearchPortletInstanceConfiguration.showKBArticleModifiedDateColumn() %>">
			<liferay-ui:search-container-column-date
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="modified-date"
				orderable="<%= true %>"
				orderableProperty="modifiedDate"
				value="<%= assetEntry.getModifiedDate() %>"
			/>
		</c:if>

		<c:if test="<%= kbSearchPortletInstanceConfiguration.showKBArticleViewsColumn() %>">
			<liferay-ui:search-container-column-text
				buffer="buffer"
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
			>

				<%
				KBArticle kbArticle = KBArticleLocalServiceUtil.fetchLatestKBArticle(assetEntry.getClassPK(), WorkflowConstants.STATUS_APPROVED);

				int viewCount = (kbArticle != null) ? kbArticle.getViewCount() : 0;

				buffer.append(viewCount);

				buffer.append(StringPool.SPACE);
				buffer.append((viewCount == 1) ? LanguageUtil.get(request, "view") : LanguageUtil.get(request, "views"));
				%>

			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<c:if test="<%= (assetCategoryId > 0) || Validator.isNotNull(assetTagName) %>">
		<div class="alert alert-info">
			<c:choose>
				<c:when test="<%= assetCategoryId > 0 %>">

					<%
					AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(assetCategoryId);

					AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());
					%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(assetTagName) %>">
							<c:choose>
								<c:when test="<%= total > 0 %>">
									<%= LanguageUtil.format(request, "articles-with-x-x-and-tag-x", new String[] {HtmlUtil.escape(assetVocabulary.getTitle(locale)), HtmlUtil.escape(assetCategory.getTitle(locale)), HtmlUtil.escape(assetTagName)}, false) %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(request, "there-are-no-articles-with-x-x-and-tag-x", new String[] {HtmlUtil.escape(assetVocabulary.getTitle(locale)), HtmlUtil.escape(assetCategory.getTitle(locale)), HtmlUtil.escape(assetTagName)}, false) %>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="<%= total > 0 %>">
									<%= LanguageUtil.format(request, "articles-with-x-x", new String[] {HtmlUtil.escape(assetVocabulary.getTitle(locale)), HtmlUtil.escape(assetCategory.getTitle(locale))}, false) %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(request, "there-are-no-articles-with-x-x", new String[] {HtmlUtil.escape(assetVocabulary.getTitle(locale)), HtmlUtil.escape(assetCategory.getTitle(locale))}, false) %>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="<%= total > 0 %>">
							<%= LanguageUtil.format(request, "articles-with-tag-x", HtmlUtil.escape(assetTagName), false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(request, "there-are-no-articles-with-tag-x", HtmlUtil.escape(assetTagName), false) %>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>