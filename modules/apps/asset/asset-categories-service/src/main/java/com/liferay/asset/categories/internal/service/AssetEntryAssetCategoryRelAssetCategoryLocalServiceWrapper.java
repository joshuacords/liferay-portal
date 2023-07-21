/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.internal.service;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceWrapper;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryAssetCategoryRelAssetCategoryLocalServiceWrapper
	extends AssetCategoryLocalServiceWrapper {

	public AssetEntryAssetCategoryRelAssetCategoryLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryAssetCategoryRelAssetCategoryLocalServiceWrapper(
		AssetCategoryLocalService assetCategoryLocalService) {

		super(assetCategoryLocalService);
	}

	@Override
	public void addAssetEntryAssetCategories(
		long entryId, List<AssetCategory> assetCategories) {

		addAssetEntryAssetCategories(
			entryId,
			ListUtil.toLongArray(
				assetCategories, AssetCategoryModel::getCategoryId));
	}

	@Override
	public void addAssetEntryAssetCategories(long entryId, long[] categoryIds) {
		for (long categoryId : categoryIds) {
			addAssetEntryAssetCategory(entryId, categoryId);
		}
	}

	@Override
	public void addAssetEntryAssetCategory(
		long entryId, AssetCategory assetCategory) {

		addAssetEntryAssetCategory(entryId, assetCategory.getCategoryId());
	}

	@Override
	public void addAssetEntryAssetCategory(long entryId, long categoryId) {
		_assetEntryAssetCategoryRelLocalService.addAssetEntryAssetCategoryRel(
			entryId, categoryId);
	}

	@Override
	public void clearAssetEntryAssetCategories(long entryId) {
		_assetEntryAssetCategoryRelLocalService.
			deleteAssetEntryAssetCategoryRelByAssetEntryId(entryId);
	}

	@Override
	public void deleteAssetEntryAssetCategories(
		long entryId, List<AssetCategory> assetCategories) {

		deleteAssetEntryAssetCategories(
			entryId,
			ListUtil.toLongArray(
				assetCategories, AssetCategoryModel::getCategoryId));
	}

	@Override
	public void deleteAssetEntryAssetCategories(
		long entryId, long[] categoryIds) {

		for (long categoryId : categoryIds) {
			deleteAssetEntryAssetCategory(entryId, categoryId);
		}
	}

	@Override
	public void deleteAssetEntryAssetCategory(
		long entryId, AssetCategory assetCategory) {

		deleteAssetEntryAssetCategory(entryId, assetCategory.getCategoryId());
	}

	@Override
	public void deleteAssetEntryAssetCategory(long entryId, long categoryId) {
		_assetEntryAssetCategoryRelLocalService.
			deleteAssetEntryAssetCategoryRel(entryId, categoryId);
	}

	@Override
	public AssetCategory deleteCategory(
			AssetCategory category, boolean skipRebuildTree)
		throws PortalException {

		_assetEntryAssetCategoryRelLocalService.
			deleteAssetEntryAssetCategoryRelByAssetCategoryId(
				category.getCategoryId());

		List<AssetEntry> entries = _getAssetEntriesByAssetCategoryId(
			category.getCategoryId());

		_assetEntryLocalService.reindex(entries);

		return super.deleteCategory(category, skipRebuildTree);
	}

	@Override
	public List<AssetCategory> getAssetEntryAssetCategories(long entryId) {
		return _getAssetCategoriesByEntryId(entryId);
	}

	@Override
	public int getAssetEntryAssetCategoriesCount(long entryId) {
		return _assetEntryAssetCategoryRelLocalService.
			getAssetEntryAssetCategoryRelsCount(entryId);
	}

	@Override
	public List<AssetCategory> getCategories(long classNameId, long classPK) {
		AssetEntry entry = _assetEntryLocalService.fetchEntry(
			classNameId, classPK);

		if (entry == null) {
			return Collections.emptyList();
		}

		return _getAssetCategoriesByEntryId(entry.getEntryId());
	}

	@Override
	public List<AssetCategory> getEntryCategories(long entryId) {
		return _getAssetCategoriesByEntryId(entryId);
	}

	@Override
	public boolean hasAssetEntryAssetCategories(long entryId) {
		int assetEntryAssetCategoryRelsCount =
			_assetEntryAssetCategoryRelLocalService.
				getAssetEntryAssetCategoryRelsCount(entryId);

		if (assetEntryAssetCategoryRelsCount > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasAssetEntryAssetCategory(long entryId, long categoryId) {
		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel =
			_assetEntryAssetCategoryRelLocalService.
				fetchAssetEntryAssetCategoryRel(entryId, categoryId);

		if (assetEntryAssetCategoryRel != null) {
			return true;
		}

		return false;
	}

	@Override
	public AssetCategory mergeCategories(long fromCategoryId, long toCategoryId)
		throws PortalException {

		List<AssetEntry> entries = _getAssetEntriesByAssetCategoryId(
			fromCategoryId);

		for (AssetEntry entry : entries) {
			_assetEntryAssetCategoryRelLocalService.
				addAssetEntryAssetCategoryRel(entry.getEntryId(), toCategoryId);
		}

		return super.mergeCategories(fromCategoryId, toCategoryId);
	}

	@Override
	public AssetCategory updateCategory(
			long userId, long categoryId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		String name = titleMap.get(LocaleUtil.getSiteDefault());

		name = ModelHintsUtil.trimString(
			AssetCategory.class.getName(), "name", name);

		AssetCategory category = _assetCategoryLocalService.getCategory(
			categoryId);

		if (!Objects.equals(category.getName(), name)) {
			List<AssetEntry> entries = _getAssetEntriesByAssetCategoryId(
				category.getCategoryId());

			_assetEntryLocalService.reindex(entries);
		}

		return super.updateCategory(
			userId, categoryId, parentCategoryId, titleMap, descriptionMap,
			vocabularyId, categoryProperties, serviceContext);
	}

	private List<AssetCategory> _getAssetCategoriesByEntryId(
		long assetEntryId) {

		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			_assetEntryAssetCategoryRelLocalService.
				getAssetEntryAssetCategoryRelsByAssetEntryId(assetEntryId);

		List<AssetCategory> categories = new ArrayList<>();

		for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel :
				assetEntryAssetCategoryRels) {

			AssetCategory category = fetchAssetCategory(
				assetEntryAssetCategoryRel.getAssetCategoryId());

			if (category != null) {
				categories.add(category);
			}
		}

		return categories;
	}

	private List<AssetEntry> _getAssetEntriesByAssetCategoryId(
		long assetCategoryId) {

		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			_assetEntryAssetCategoryRelLocalService.
				getAssetEntryAssetCategoryRelsByAssetCategoryId(
					assetCategoryId);

		List<AssetEntry> entries = new ArrayList<>();

		for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel :
				assetEntryAssetCategoryRels) {

			AssetEntry entry = _assetEntryLocalService.fetchEntry(
				assetEntryAssetCategoryRel.getAssetEntryId());

			if (entry != null) {
				entries.add(entry);
			}
		}

		return entries;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryAssetCategoryRelLocalService
		_assetEntryAssetCategoryRelLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}