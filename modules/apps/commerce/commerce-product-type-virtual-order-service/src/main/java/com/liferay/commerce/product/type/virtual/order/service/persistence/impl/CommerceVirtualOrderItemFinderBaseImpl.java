/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.order.service.persistence.impl;

import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceVirtualOrderItemFinderBaseImpl
	extends BasePersistenceImpl<CommerceVirtualOrderItem> {

	public CommerceVirtualOrderItemFinderBaseImpl() {
		setModelClass(CommerceVirtualOrderItem.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("active", "active_");

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
		return getCommerceVirtualOrderItemPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce virtual order item persistence.
	 *
	 * @return the commerce virtual order item persistence
	 */
	public CommerceVirtualOrderItemPersistence
		getCommerceVirtualOrderItemPersistence() {

		return commerceVirtualOrderItemPersistence;
	}

	/**
	 * Sets the commerce virtual order item persistence.
	 *
	 * @param commerceVirtualOrderItemPersistence the commerce virtual order item persistence
	 */
	public void setCommerceVirtualOrderItemPersistence(
		CommerceVirtualOrderItemPersistence
			commerceVirtualOrderItemPersistence) {

		this.commerceVirtualOrderItemPersistence =
			commerceVirtualOrderItemPersistence;
	}

	@BeanReference(type = CommerceVirtualOrderItemPersistence.class)
	protected CommerceVirtualOrderItemPersistence
		commerceVirtualOrderItemPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceVirtualOrderItemFinderBaseImpl.class);

}