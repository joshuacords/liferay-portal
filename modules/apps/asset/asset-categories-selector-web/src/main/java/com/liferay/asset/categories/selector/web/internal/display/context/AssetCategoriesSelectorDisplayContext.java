/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.selector.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.asset.util.comparator.AssetVocabularyGroupLocalizedTitleComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AssetCategoriesSelectorDisplayContext {

	public AssetCategoriesSelectorDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
	}

	public JSONArray getCategoriesJSONArray() throws Exception {
		JSONArray vocabulariesJSONArray = _getVocabulariesJSONArray();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (vocabulariesJSONArray.length() == 1) {
			jsonObject = vocabulariesJSONArray.getJSONObject(0);
		}
		else {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			jsonObject.put(
				"children", vocabulariesJSONArray
			).put(
				"icon", "folder"
			).put(
				"id", "0"
			).put(
				"name",
				LanguageUtil.get(themeDisplay.getLocale(), "vocabularies")
			);
		}

		jsonObject.put(
			"disabled", true
		).put(
			"expanded", true
		);

		return JSONUtil.put(jsonObject);
	}

	public String getEventName() {
		if (Validator.isNotNull(_eventName)) {
			return _eventName;
		}

		_eventName = ParamUtil.getString(
			_httpServletRequest, "eventName",
			_renderResponse.getNamespace() + "selectCategory");

		return _eventName;
	}

	public List<String> getSelectedCategoryIds() {
		if (_selectedCategoryIds != null) {
			return _selectedCategoryIds;
		}

		_selectedCategoryIds = Arrays.asList(
			StringUtil.split(
				ParamUtil.getString(
					_httpServletRequest, "selectedCategories")));

		return _selectedCategoryIds;
	}

	public long[] getVocabularyIds() {
		if (_vocabularyIds != null) {
			return _vocabularyIds;
		}

		long[] vocabularyIds = StringUtil.split(
			ParamUtil.getString(_httpServletRequest, "vocabularyIds"), 0L);

		List<AssetVocabulary> assetVocabularies = new ArrayList<>();

		for (long vocabularyId : vocabularyIds) {
			AssetVocabulary assetVocabulary =
				AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(
					vocabularyId);

			if (assetVocabulary != null) {
				assetVocabularies.add(assetVocabulary);
			}
		}

		if (assetVocabularies.isEmpty()) {
			_vocabularyIds = new long[0];

			return _vocabularyIds;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		assetVocabularies.sort(
			new AssetVocabularyGroupLocalizedTitleComparator(
				themeDisplay.getScopeGroupId(), themeDisplay.getLocale(),
				true));

		Stream<AssetVocabulary> assetVocabulariesStream =
			assetVocabularies.stream();

		_vocabularyIds = assetVocabulariesStream.mapToLong(
			assetVocabulary -> assetVocabulary.getVocabularyId()
		).toArray();

		return _vocabularyIds;
	}

	public String getVocabularyTitle(long vocabularyId) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetVocabulary assetVocabulary =
			AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(vocabularyId);

		StringBundler sb = new StringBundler(5);

		sb.append(
			HtmlUtil.escape(
				assetVocabulary.getTitle(themeDisplay.getLocale())));

		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);

		if (assetVocabulary.getGroupId() == themeDisplay.getCompanyGroupId()) {
			sb.append(LanguageUtil.get(_httpServletRequest, "global"));
		}
		else {
			Group group = GroupLocalServiceUtil.fetchGroup(
				assetVocabulary.getGroupId());

			sb.append(group.getDescriptiveName(themeDisplay.getLocale()));
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	public boolean isAllowedSelectVocabularies() {
		if (_allowedSelectVocabularies != null) {
			return _allowedSelectVocabularies;
		}

		_allowedSelectVocabularies = ParamUtil.getBoolean(
			_httpServletRequest, "allowedSelectVocabularies");

		return _allowedSelectVocabularies;
	}

	public boolean isSingleSelect() {
		if (_singleSelect != null) {
			return _singleSelect;
		}

		_singleSelect = ParamUtil.getBoolean(
			_httpServletRequest, "singleSelect");

		return _singleSelect;
	}

	private JSONArray _getCategoriesJSONArray(
			long vocabularyId, long categoryId, JSONObject parentJSONObject)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		List<AssetCategory> categories =
			AssetCategoryServiceUtil.getVocabularyCategories(
				categoryId, vocabularyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null);

		for (AssetCategory category : categories) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONArray categoriesJSONArray = _getCategoriesJSONArray(
				vocabularyId, category.getCategoryId(), jsonObject);

			if (categoriesJSONArray.length() > 0) {
				jsonObject.put("children", categoriesJSONArray);
			}

			jsonObject.put(
				"icon", "categories"
			).put(
				"id", category.getCategoryId()
			).put(
				"name", category.getTitle(themeDisplay.getLocale())
			).put(
				"nodePath", category.getPath(themeDisplay.getLocale(), true)
			);

			if (getSelectedCategoryIds().contains(
					String.valueOf(category.getCategoryId()))) {

				jsonObject.put("selected", true);
			}

			if (jsonObject.getBoolean("expanded") ||
				jsonObject.getBoolean("selected")) {

				parentJSONObject.put("expanded", true);
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private JSONArray _getVocabulariesJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		boolean allowedSelectVocabularies = isAllowedSelectVocabularies();

		for (long vocabularyId : getVocabularyIds()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONArray categoriesJSONArray = _getCategoriesJSONArray(
				vocabularyId, 0, jsonObject);

			jsonObject.put(
				"children", categoriesJSONArray
			).put(
				"disabled", !allowedSelectVocabularies
			).put(
				"icon", "vocabulary"
			).put(
				"id", vocabularyId
			).put(
				"name", getVocabularyTitle(vocabularyId)
			).put(
				"vocabulary", true
			);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private Boolean _allowedSelectVocabularies;
	private String _eventName;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private List<String> _selectedCategoryIds;
	private Boolean _singleSelect;
	private long[] _vocabularyIds;

}