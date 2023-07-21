/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.category.property.internal.service;

import com.liferay.asset.category.property.service.AssetCategoryPropertyService;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.service.AssetCategoryPropertyServiceWrapper;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularAssetCategoryPropertyServiceWrapper
	extends AssetCategoryPropertyServiceWrapper {

	public ModularAssetCategoryPropertyServiceWrapper() {
		super(null);
	}

	public ModularAssetCategoryPropertyServiceWrapper(
		com.liferay.asset.kernel.service.AssetCategoryPropertyService
			assetCategoryPropertyService) {

		super(assetCategoryPropertyService);
	}

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long entryId, String key, String value)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryProperty.class,
			_assetCategoryPropertyService.addCategoryProperty(
				entryId, key, value));
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException {

		_assetCategoryPropertyService.deleteCategoryProperty(
			categoryPropertyId);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId) {
		return ModelAdapterUtil.adapt(
			AssetCategoryProperty.class,
			_assetCategoryPropertyService.getCategoryProperties(entryId));
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
		long companyId, String key) {

		return ModelAdapterUtil.adapt(
			AssetCategoryProperty.class,
			_assetCategoryPropertyService.getCategoryPropertyValues(
				companyId, key));
	}

	@Override
	public String getOSGiServiceIdentifier() {
		return _assetCategoryPropertyService.getOSGiServiceIdentifier();
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long userId, long categoryPropertyId, String key, String value)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryProperty.class,
			_assetCategoryPropertyService.updateCategoryProperty(
				userId, categoryPropertyId, key, value));
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryProperty.class,
			_assetCategoryPropertyService.updateCategoryProperty(
				categoryPropertyId, key, value));
	}

	@Reference
	private AssetCategoryPropertyService _assetCategoryPropertyService;

}