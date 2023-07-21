/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service.persistence.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.persistence.CommerceAccountGroupPersistence;
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
public class CommerceAccountGroupFinderBaseImpl
	extends BasePersistenceImpl<CommerceAccountGroup> {

	public CommerceAccountGroupFinderBaseImpl() {
		setModelClass(CommerceAccountGroup.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");
		dbColumnNames.put("system", "system_");

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
		return getCommerceAccountGroupPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce account group persistence.
	 *
	 * @return the commerce account group persistence
	 */
	public CommerceAccountGroupPersistence
		getCommerceAccountGroupPersistence() {

		return commerceAccountGroupPersistence;
	}

	/**
	 * Sets the commerce account group persistence.
	 *
	 * @param commerceAccountGroupPersistence the commerce account group persistence
	 */
	public void setCommerceAccountGroupPersistence(
		CommerceAccountGroupPersistence commerceAccountGroupPersistence) {

		this.commerceAccountGroupPersistence = commerceAccountGroupPersistence;
	}

	@BeanReference(type = CommerceAccountGroupPersistence.class)
	protected CommerceAccountGroupPersistence commerceAccountGroupPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountGroupFinderBaseImpl.class);

}