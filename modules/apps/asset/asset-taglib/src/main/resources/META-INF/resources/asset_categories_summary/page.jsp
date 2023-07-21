<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset_categories_summary/init.jsp" %>

<%
String className = (String)request.getAttribute("liferay-asset:asset-categories-summary:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-asset:asset-categories-summary:classPK"));
String displayStyle = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-categories-summary:displayStyle"), "default");
String paramName = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-categories-summary:paramName"), "categoryId");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-asset:asset-categories-summary:portletURL");

List<AssetCategory> categories = (List<AssetCategory>)request.getAttribute("liferay-asset:asset-categories-summary:assetCategories");

if (ListUtil.isEmpty(categories)) {
	categories = AssetCategoryServiceUtil.getCategories(className, classPK);
}

AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(className, classPK);

List<AssetVocabulary> vocabularies = AssetVocabularyServiceUtil.getGroupVocabularies(PortalUtil.getCurrentAndAncestorSiteGroupIds((assetEntry != null) ? assetEntry.getGroupId() : scopeGroupId));

for (AssetVocabulary vocabulary : vocabularies) {
	List<AssetCategory> curCategories = _filterCategories(categories, vocabulary);
%>

	<c:if test="<%= !curCategories.isEmpty() %>">
		<c:choose>
			<c:when test='<%= displayStyle.equals("simple-category") %>'>
				<span class="taglib-asset-categories-summary">
					<c:choose>
						<c:when test="<%= portletURL != null %>">

							<%
							for (AssetCategory category : curCategories) {
								portletURL.setParameter(paramName, String.valueOf(category.getCategoryId()));
							%>

								<a class="label label-dark label-lg text-uppercase" href="<%= HtmlUtil.escape(portletURL.toString()) %>"><%= HtmlUtil.escape(category.getTitle(themeDisplay.getLocale())) %></a>

							<%
							}
							%>

						</c:when>
						<c:otherwise>

							<%
							for (AssetCategory category : curCategories) {
							%>

								<span class="label label-dark label-lg text-uppercase">
									<%= HtmlUtil.escape(category.getTitle(themeDisplay.getLocale())) %>
								</span>

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</span>
			</c:when>
			<c:otherwise>
				<span class="taglib-asset-categories-summary">
					<%= HtmlUtil.escape(vocabulary.getUnambiguousTitle(vocabularies, themeDisplay.getSiteGroupId(), themeDisplay.getLocale())) %>:

					<c:choose>
						<c:when test="<%= portletURL != null %>">

							<%
							for (AssetCategory category : curCategories) {
								portletURL.setParameter(paramName, String.valueOf(category.getCategoryId()));
							%>

								<a class="asset-category text-uppercase" href="<%= HtmlUtil.escape(portletURL.toString()) %>"><%= _buildCategoryPath(category, themeDisplay) %></a>

							<%
							}
							%>

						</c:when>
						<c:otherwise>

							<%
							for (AssetCategory category : curCategories) {
							%>

								<span class="asset-category text-uppercase">
									<%= _buildCategoryPath(category, themeDisplay) %>
								</span>

							<%
							}
							%>

						</c:otherwise>
					</c:choose>
				</span>
			</c:otherwise>
		</c:choose>
	</c:if>

<%
}
%>

<%!
private String _buildCategoryPath(AssetCategory category, ThemeDisplay themeDisplay) throws PortalException, SystemException {
	List<AssetCategory> ancestorCategories = category.getAncestors();

	if (ancestorCategories.isEmpty()) {
		return HtmlUtil.escape(category.getTitle(themeDisplay.getLocale()));
	}

	Collections.reverse(ancestorCategories);

	StringBundler sb = new StringBundler(ancestorCategories.size() * 2 + 1);

	for (AssetCategory ancestorCategory : ancestorCategories) {
		sb.append(HtmlUtil.escape(ancestorCategory.getTitle(themeDisplay.getLocale())));
		sb.append(" &raquo; ");
	}

	sb.append(HtmlUtil.escape(category.getTitle(themeDisplay.getLocale())));

	return sb.toString();
}

private List<AssetCategory> _filterCategories(List<AssetCategory> categories, AssetVocabulary vocabulary) {
	List<AssetCategory> filteredCategories = new ArrayList<AssetCategory>();

	for (AssetCategory category : categories) {
		if (category.getVocabularyId() == vocabulary.getVocabularyId()) {
			filteredCategories.add(category);
		}
	}

	return filteredCategories;
}
%>