/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.categories.internal.validator;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.asset.kernel.validator.AssetEntryValidator;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Juan Fernández
 */
@Component(
	immediate = true, property = "model.class.name=*",
	service = AssetEntryValidator.class
)
public class CardinalityAssetEntryValidator implements AssetEntryValidator {

	@Override
	public void validate(
			long groupId, String className, long classPK, long classTypePK,
			long[] categoryIds, String[] entryNames)
		throws PortalException {

		long classNameId = _classNameLocalService.getClassNameId(className);

		if (!isCategorizable(groupId, classNameId, classPK)) {
			return;
		}

		for (AssetVocabulary assetVocabulary :
				_assetVocabularyLocalService.getGroupsVocabularies(
					_portal.getCurrentAndAncestorSiteGroupIds(groupId))) {

			validate(classNameId, classTypePK, categoryIds, assetVocabulary);
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void validate(
			long groupId, String className, long classTypePK,
			long[] categoryIds, String[] entryNames)
		throws PortalException {

		validate(groupId, className, 0L, classTypePK, categoryIds, entryNames);
	}

	protected boolean isCategorizable(
		long groupId, long classNameId, long classPK) {

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				_portal.getClassName(classNameId));

		if ((assetRendererFactory == null) ||
			!assetRendererFactory.isCategorizable()) {

			return false;
		}

		if (classPK != 0L) {
			try {
				AssetRenderer<?> assetRenderer =
					assetRendererFactory.getAssetRenderer(classPK);

				if (!assetRenderer.isCategorizable(groupId)) {
					return false;
				}
			}
			catch (PortalException portalException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						StringBundler.concat(
							"Entity with ClassPK: ", classPK,
							" and ClassNameId: ", classNameId,
							" is not categorizable"),
						portalException);
				}

				return false;
			}
		}

		return true;
	}

	protected void validate(
			long classNameId, long classTypePK, final long[] categoryIds,
			AssetVocabulary assetVocabulary)
		throws PortalException {

		if (!assetVocabulary.isAssociatedToClassNameIdAndClassTypePK(
				classNameId, classTypePK)) {

			return;
		}

		if (assetVocabulary.isMissingRequiredCategory(
				classNameId, classTypePK, categoryIds)) {

			throw new AssetCategoryException(
				assetVocabulary, AssetCategoryException.AT_LEAST_ONE_CATEGORY);
		}

		if (!assetVocabulary.isMultiValued() &&
			assetVocabulary.hasMoreThanOneCategorySelected(categoryIds)) {

			throw new AssetCategoryException(
				assetVocabulary, AssetCategoryException.TOO_MANY_CATEGORIES);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CardinalityAssetEntryValidator.class.getName());

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private Portal _portal;

}