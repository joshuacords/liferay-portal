/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.JSONAction;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 */
public class GetCategoriesAction extends JSONAction {

	@Override
	public String getJSON(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<AssetCategory> categories = getCategories(httpServletRequest);

		for (AssetCategory category : categories) {
			List<AssetCategory> childCategories =
				AssetCategoryServiceUtil.getChildCategories(
					category.getCategoryId());

			jsonArray.put(
				JSONUtil.put(
					"categoryId", category.getCategoryId()
				).put(
					"childrenCount", childCategories.size()
				).put(
					"hasChildren", !childCategories.isEmpty()
				).put(
					"name", category.getName()
				).put(
					"parentCategoryId", category.getParentCategoryId()
				).put(
					"titleCurrentValue", category.getTitleCurrentValue()
				));
		}

		return jsonArray.toString();
	}

	protected List<AssetCategory> getCategories(
			HttpServletRequest httpServletRequest)
		throws Exception {

		long scopeGroupId = ParamUtil.getLong(
			httpServletRequest, "scopeGroupId");
		long categoryId = ParamUtil.getLong(httpServletRequest, "categoryId");
		long vocabularyId = ParamUtil.getLong(
			httpServletRequest, "vocabularyId");
		int start = ParamUtil.getInteger(
			httpServletRequest, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(
			httpServletRequest, "end", QueryUtil.ALL_POS);

		List<AssetCategory> categories = Collections.emptyList();

		if (categoryId > 0) {
			if (scopeGroupId > 0) {
				categories = AssetCategoryServiceUtil.getVocabularyCategories(
					scopeGroupId, categoryId, vocabularyId, start, end, null);
			}
			else {
				categories = AssetCategoryServiceUtil.getChildCategories(
					categoryId, start, end, null);
			}
		}
		else if (vocabularyId > 0) {
			long parentCategoryId = ParamUtil.getLong(
				httpServletRequest, "parentCategoryId",
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

			if (scopeGroupId > 0) {
				categories = AssetCategoryServiceUtil.getVocabularyCategories(
					scopeGroupId, parentCategoryId, vocabularyId, start, end,
					null);
			}
			else {
				categories = AssetCategoryServiceUtil.getVocabularyCategories(
					parentCategoryId, vocabularyId, start, end, null);
			}
		}

		return categories;
	}

}