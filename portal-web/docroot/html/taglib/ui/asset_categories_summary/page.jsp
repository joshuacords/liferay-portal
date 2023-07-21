<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/asset_categories_summary/init.jsp" %>

<%
String className = (String)request.getAttribute("liferay-ui:asset-categories-summary:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:asset-categories-summary:classPK"));
String paramName = GetterUtil.getString((String)request.getAttribute("liferay-ui:asset-tags-summary:paramName"), "categoryId");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:asset-categories-summary:portletURL");

List<AssetCategory> categories = (List<AssetCategory>)request.getAttribute("liferay-ui:asset-categories-summary:assetCategories");

if (ListUtil.isEmpty(categories)) {
	categories = AssetCategoryServiceUtil.getCategories(className, classPK);
}

AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(className, classPK);

List<AssetVocabulary> vocabularies = new ArrayList<>();

vocabularies.addAll(AssetVocabularyServiceUtil.getGroupVocabularies(PortalUtil.getCurrentAndAncestorSiteGroupIds((assetEntry != null) ? assetEntry.getGroupId() : scopeGroupId)));

vocabularies.sort(new AssetVocabularyGroupLocalizedTitleComparator((assetEntry != null) ? assetEntry.getGroupId() : scopeGroupId, themeDisplay.getLocale(), true));

for (AssetVocabulary vocabulary : vocabularies) {
	vocabulary = vocabulary.toEscapedModel();

	List<AssetCategory> curCategories = _filterCategories(categories, vocabulary);
%>

	<c:if test="<%= !curCategories.isEmpty() %>">
		<span class="taglib-asset-categories-summary">
			<%= HtmlUtil.escape(vocabulary.getUnambiguousTitle(vocabularies, themeDisplay.getSiteGroupId(), themeDisplay.getLocale())) %>:

			<c:choose>
				<c:when test="<%= portletURL != null %>">

					<%
					for (AssetCategory category : curCategories) {
						category = category.toEscapedModel();

						portletURL.setParameter(paramName, String.valueOf(category.getCategoryId()));
					%>

						<a class="asset-category" href="<%= HtmlUtil.escape(portletURL.toString()) %>"><%= _buildCategoryPath(category, themeDisplay) %></a>

					<%
					}
					%>

				</c:when>
				<c:otherwise>

					<%
					for (AssetCategory category : curCategories) {
						category = category.toEscapedModel();
					%>

						<span class="asset-category">
							<%= _buildCategoryPath(category, themeDisplay) %>
						</span>

					<%
					}
					%>

				</c:otherwise>
			</c:choose>
		</span>
	</c:if>

<%
}
%>

<%!
private String _buildCategoryPath(AssetCategory category, ThemeDisplay themeDisplay) throws PortalException, SystemException {
	List<AssetCategory> ancestorCategories = category.getAncestors();

	if (ancestorCategories.isEmpty()) {
		return category.getTitle(themeDisplay.getLocale());
	}

	Collections.reverse(ancestorCategories);

	StringBundler sb = new StringBundler(ancestorCategories.size() * 2 + 1);

	for (AssetCategory ancestorCategory : ancestorCategories) {
		ancestorCategory = ancestorCategory.toEscapedModel();

		sb.append(ancestorCategory.getTitle(themeDisplay.getLocale()));
		sb.append(" &raquo; ");
	}

	sb.append(category.getTitle(themeDisplay.getLocale()));

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