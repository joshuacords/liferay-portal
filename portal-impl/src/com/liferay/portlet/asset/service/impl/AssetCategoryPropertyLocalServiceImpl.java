/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.service.impl;

import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portlet.asset.service.base.AssetCategoryPropertyLocalServiceBaseImpl;

import java.util.List;

/**
 * @author     Brian Wing Shun Chan
 * @author     Jorge Ferrer
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.asset.category.property.service.impl.AssetCategoryPropertyLocalServiceImpl}
 */
@Deprecated
public class AssetCategoryPropertyLocalServiceImpl
	extends AssetCategoryPropertyLocalServiceBaseImpl {

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long userId, long categoryId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public void deleteCategoryProperties(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public void deleteCategoryProperty(AssetCategoryProperty categoryProperty) {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties() {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(long categoryPropertyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(
			long categoryId, String key)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
		long groupId, String key) {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long userId, long categoryPropertyId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	protected boolean hasCategoryProperty(long categoryId, String key) {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

	protected void validate(String key, String value) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecated and replaced by " +
				"com.liferay.asset.category.property.service.impl." +
					"AssetCategoryPropertyLocalServiceImpl");
	}

}