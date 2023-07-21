<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %><%@
page import="com.liferay.asset.kernel.model.AssetVocabulary" %><%@
page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.liferay.asset.kernel.service.AssetCategoryServiceUtil" %><%@
page import="com.liferay.asset.kernel.service.AssetVocabularyServiceUtil" %><%@
page import="com.liferay.portlet.asset.util.AssetUtil" %><%@
page import="com.liferay.portlet.asset.util.comparator.AssetVocabularyGroupLocalizedTitleComparator" %>

<%!
public long[] _filterCategoryIds(long vocabularyId, long[] categoryIds) {
	List<Long> filteredCategoryIds = new ArrayList<>();

	for (long categoryId : categoryIds) {
		AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

		if (category == null) {
			continue;
		}

		if (category.getVocabularyId() == vocabularyId) {
			filteredCategoryIds.add(category.getCategoryId());
		}
	}

	return ArrayUtil.toArray(filteredCategoryIds.toArray(new Long[0]));
}

private String[] _getCategoryIdsTitles(String categoryIds, String categoryNames, long vocabularyId, ThemeDisplay themeDisplay) {
	if (Validator.isNotNull(categoryIds)) {
		long[] categoryIdsArray = GetterUtil.getLongValues(StringUtil.split(categoryIds));

		if (vocabularyId > 0) {
			categoryIdsArray = _filterCategoryIds(vocabularyId, categoryIdsArray);
		}

		categoryIds = StringPool.BLANK;
		categoryNames = StringPool.BLANK;

		if (categoryIdsArray.length > 0) {
			StringBundler categoryIdsSB = new StringBundler(categoryIdsArray.length * 2);
			StringBundler categoryNamesSB = new StringBundler(categoryIdsArray.length * 2);

			for (long categoryId : categoryIdsArray) {
				AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

				if (category == null) {
					continue;
				}

				categoryIdsSB.append(categoryId);
				categoryIdsSB.append(StringPool.COMMA);

				categoryNamesSB.append(category.getTitle(themeDisplay.getLocale()));
				categoryNamesSB.append("_CATEGORY_");
			}

			if (categoryIdsSB.index() > 0) {
				categoryIdsSB.setIndex(categoryIdsSB.index() - 1);
				categoryNamesSB.setIndex(categoryNamesSB.index() - 1);

				categoryIds = categoryIdsSB.toString();
				categoryNames = categoryNamesSB.toString();
			}
		}
	}

	return new String[] {categoryIds, categoryNames};
}
%>