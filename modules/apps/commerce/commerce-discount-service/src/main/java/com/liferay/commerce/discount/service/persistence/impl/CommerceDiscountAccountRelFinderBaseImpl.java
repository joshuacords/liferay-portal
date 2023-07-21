/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service.persistence.impl;

import com.liferay.commerce.discount.model.CommerceDiscountAccountRel;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountAccountRelPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Marco Leo
 * @generated
 */
public class CommerceDiscountAccountRelFinderBaseImpl
	extends BasePersistenceImpl<CommerceDiscountAccountRel> {

	public CommerceDiscountAccountRelFinderBaseImpl() {
		setModelClass(CommerceDiscountAccountRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("order", "order_");

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
		return getCommerceDiscountAccountRelPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce discount account rel persistence.
	 *
	 * @return the commerce discount account rel persistence
	 */
	public CommerceDiscountAccountRelPersistence
		getCommerceDiscountAccountRelPersistence() {

		return commerceDiscountAccountRelPersistence;
	}

	/**
	 * Sets the commerce discount account rel persistence.
	 *
	 * @param commerceDiscountAccountRelPersistence the commerce discount account rel persistence
	 */
	public void setCommerceDiscountAccountRelPersistence(
		CommerceDiscountAccountRelPersistence
			commerceDiscountAccountRelPersistence) {

		this.commerceDiscountAccountRelPersistence =
			commerceDiscountAccountRelPersistence;
	}

	@BeanReference(type = CommerceDiscountAccountRelPersistence.class)
	protected CommerceDiscountAccountRelPersistence
		commerceDiscountAccountRelPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountAccountRelFinderBaseImpl.class);

}