/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.service.impl;

import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portlet.asset.service.base.AssetCategoryPropertyServiceBaseImpl;

import java.util.List;

/**
 * @author     Brian Wing Shun Chan
 * @author     Jorge Ferrer
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.asset.category.property.service.impl.AssetCategoryPropertyServiceImpl}
 */
@Deprecated
public class AssetCategoryPropertyServiceImpl
	extends AssetCategoryPropertyServiceBaseImpl {

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long entryId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
		long companyId, String key) {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long userId, long categoryPropertyId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

	protected List<AssetCategoryProperty> filterAssetCategoryProperties(
		List<AssetCategoryProperty> assetCategoryProperties) {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyServiceImpl");
	}

}