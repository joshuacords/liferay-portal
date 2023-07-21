/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.service.persistence.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseItemPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryWarehouseItemFinderBaseImpl
	extends BasePersistenceImpl<CommerceInventoryWarehouseItem> {

	public CommerceInventoryWarehouseItemFinderBaseImpl() {
		setModelClass(CommerceInventoryWarehouseItem.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceInventoryWarehouseItemId", "CIWarehouseItemId");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getCommerceInventoryWarehouseItemPersistence().
			getBadColumnNames();
	}

	/**
	 * Returns the commerce inventory warehouse item persistence.
	 *
	 * @return the commerce inventory warehouse item persistence
	 */
	public CommerceInventoryWarehouseItemPersistence
		getCommerceInventoryWarehouseItemPersistence() {

		return commerceInventoryWarehouseItemPersistence;
	}

	/**
	 * Sets the commerce inventory warehouse item persistence.
	 *
	 * @param commerceInventoryWarehouseItemPersistence the commerce inventory warehouse item persistence
	 */
	public void setCommerceInventoryWarehouseItemPersistence(
		CommerceInventoryWarehouseItemPersistence
			commerceInventoryWarehouseItemPersistence) {

		this.commerceInventoryWarehouseItemPersistence =
			commerceInventoryWarehouseItemPersistence;
	}

	@BeanReference(type = CommerceInventoryWarehouseItemPersistence.class)
	protected CommerceInventoryWarehouseItemPersistence
		commerceInventoryWarehouseItemPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceInventoryWarehouseItemFinderBaseImpl.class);

}