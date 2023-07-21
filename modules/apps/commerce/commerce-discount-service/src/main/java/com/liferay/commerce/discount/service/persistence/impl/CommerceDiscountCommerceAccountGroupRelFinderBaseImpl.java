/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.service.persistence.impl;

import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountCommerceAccountGroupRelPersistence;
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
public class CommerceDiscountCommerceAccountGroupRelFinderBaseImpl
	extends BasePersistenceImpl<CommerceDiscountCommerceAccountGroupRel> {

	public CommerceDiscountCommerceAccountGroupRelFinderBaseImpl() {
		setModelClass(CommerceDiscountCommerceAccountGroupRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceDiscountCommerceAccountGroupRelId",
			"CDiscountCAccountGroupRelId");

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
		return getCommerceDiscountCommerceAccountGroupRelPersistence().
			getBadColumnNames();
	}

	/**
	 * Returns the commerce discount commerce account group rel persistence.
	 *
	 * @return the commerce discount commerce account group rel persistence
	 */
	public CommerceDiscountCommerceAccountGroupRelPersistence
		getCommerceDiscountCommerceAccountGroupRelPersistence() {

		return commerceDiscountCommerceAccountGroupRelPersistence;
	}

	/**
	 * Sets the commerce discount commerce account group rel persistence.
	 *
	 * @param commerceDiscountCommerceAccountGroupRelPersistence the commerce discount commerce account group rel persistence
	 */
	public void setCommerceDiscountCommerceAccountGroupRelPersistence(
		CommerceDiscountCommerceAccountGroupRelPersistence
			commerceDiscountCommerceAccountGroupRelPersistence) {

		this.commerceDiscountCommerceAccountGroupRelPersistence =
			commerceDiscountCommerceAccountGroupRelPersistence;
	}

	@BeanReference(
		type = CommerceDiscountCommerceAccountGroupRelPersistence.class
	)
	protected CommerceDiscountCommerceAccountGroupRelPersistence
		commerceDiscountCommerceAccountGroupRelPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDiscountCommerceAccountGroupRelFinderBaseImpl.class);

}