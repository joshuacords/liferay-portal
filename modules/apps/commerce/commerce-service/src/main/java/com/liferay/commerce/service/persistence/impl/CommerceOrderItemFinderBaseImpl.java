/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.persistence.CommerceOrderItemPersistence;
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
public class CommerceOrderItemFinderBaseImpl
	extends BasePersistenceImpl<CommerceOrderItem> {

	public CommerceOrderItemFinderBaseImpl() {
		setModelClass(CommerceOrderItem.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"discountPercentageLevel1WithTaxAmount",
			"discountPctLevel1WithTaxAmount");
		dbColumnNames.put(
			"discountPercentageLevel2WithTaxAmount",
			"discountPctLevel2WithTaxAmount");
		dbColumnNames.put(
			"discountPercentageLevel3WithTaxAmount",
			"discountPctLevel3WithTaxAmount");
		dbColumnNames.put(
			"discountPercentageLevel4WithTaxAmount",
			"discountPctLevel4WithTaxAmount");

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
		return getCommerceOrderItemPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce order item persistence.
	 *
	 * @return the commerce order item persistence
	 */
	public CommerceOrderItemPersistence getCommerceOrderItemPersistence() {
		return commerceOrderItemPersistence;
	}

	/**
	 * Sets the commerce order item persistence.
	 *
	 * @param commerceOrderItemPersistence the commerce order item persistence
	 */
	public void setCommerceOrderItemPersistence(
		CommerceOrderItemPersistence commerceOrderItemPersistence) {

		this.commerceOrderItemPersistence = commerceOrderItemPersistence;
	}

	@BeanReference(type = CommerceOrderItemPersistence.class)
	protected CommerceOrderItemPersistence commerceOrderItemPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderItemFinderBaseImpl.class);

}