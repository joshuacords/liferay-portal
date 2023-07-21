/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.target;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.service.CommerceDiscountRelLocalService;
import com.liferay.commerce.discount.target.CommerceDiscountProductTarget;
import com.liferay.commerce.discount.target.CommerceDiscountTarget;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.ExistsFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.discount.target.key=" + CommerceDiscountConstants.TARGET_CATEGORIES,
		"commerce.discount.target.order:Integer=10"
	},
	service = {
		CommerceDiscountProductTarget.class, CommerceDiscountTarget.class
	}
)
public class ApplyToCategoriesCommerceDiscountTargetImpl
	implements CommerceDiscountProductTarget, CommerceDiscountTarget {

	@Override
	public void contributeDocument(
		Document document, CommerceDiscount commerceDiscount) {

		List<CommerceDiscountRel> commerceDiscountRels =
			_commerceDiscountRelLocalService.getCommerceDiscountRels(
				commerceDiscount.getCommerceDiscountId(),
				AssetCategory.class.getName());

		Stream<CommerceDiscountRel> stream = commerceDiscountRels.stream();

		LongStream longStream = stream.mapToLong(
			CommerceDiscountRel::getClassPK);

		long[] assetCategoryIds = longStream.toArray();

		document.addKeyword("target_asset_category_ids", assetCategoryIds);
	}

	@Override
	public String getKey() {
		return CommerceDiscountConstants.TARGET_CATEGORIES;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, CommerceDiscountConstants.TARGET_CATEGORIES);
	}

	@Override
	public Type getType() {
		return Type.APPLY_TO_PRODUCT;
	}

	@Override
	public void postProcessContextBooleanFilter(
		BooleanFilter contextBooleanFilter, CPDefinition cpDefinition) {

		long[] assetCategoryIds = _getAssetCategoryIds(cpDefinition);

		TermsFilter termsFilter = new TermsFilter("target_asset_category_ids");

		termsFilter.addValues(ArrayUtil.toStringArray(assetCategoryIds));

		Filter existFilter = new ExistsFilter("target_asset_category_ids");

		BooleanFilter existBooleanFilter = new BooleanFilter();

		existBooleanFilter.add(existFilter, BooleanClauseOccur.MUST_NOT);

		BooleanFilter fieldBooleanFilter = new BooleanFilter();

		fieldBooleanFilter.add(existBooleanFilter, BooleanClauseOccur.SHOULD);
		fieldBooleanFilter.add(termsFilter, BooleanClauseOccur.SHOULD);

		contextBooleanFilter.add(fieldBooleanFilter, BooleanClauseOccur.MUST);
	}

	private long[] _getAssetCategoryIds(CPDefinition cpDefinition) {
		try {
			AssetEntry assetEntry = _assetEntryLocalService.getEntry(
				CPDefinition.class.getName(), cpDefinition.getCPDefinitionId());

			Set<AssetCategory> assetCategories = new HashSet<>();

			for (AssetCategory assetCategory : assetEntry.getCategories()) {
				assetCategories.add(assetCategory);
				assetCategories.addAll(assetCategory.getAncestors());
			}

			Stream<AssetCategory> stream = assetCategories.stream();

			LongStream longStream = stream.mapToLong(
				AssetCategory::getCategoryId);

			return longStream.toArray();
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}

		return new long[0];
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ApplyToCategoriesCommerceDiscountTargetImpl.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private CommerceDiscountRelLocalService _commerceDiscountRelLocalService;

}