/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.currency.service.persistence.impl;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.persistence.CommerceCurrencyPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Andrea Di Giorgi
 * @generated
 */
public class CommerceCurrencyFinderBaseImpl
	extends BasePersistenceImpl<CommerceCurrency> {

	public CommerceCurrencyFinderBaseImpl() {
		setModelClass(CommerceCurrency.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("code", "code_");
		dbColumnNames.put("primary", "primary_");
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
		return getCommerceCurrencyPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce currency persistence.
	 *
	 * @return the commerce currency persistence
	 */
	public CommerceCurrencyPersistence getCommerceCurrencyPersistence() {
		return commerceCurrencyPersistence;
	}

	/**
	 * Sets the commerce currency persistence.
	 *
	 * @param commerceCurrencyPersistence the commerce currency persistence
	 */
	public void setCommerceCurrencyPersistence(
		CommerceCurrencyPersistence commerceCurrencyPersistence) {

		this.commerceCurrencyPersistence = commerceCurrencyPersistence;
	}

	@BeanReference(type = CommerceCurrencyPersistence.class)
	protected CommerceCurrencyPersistence commerceCurrencyPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCurrencyFinderBaseImpl.class);

}