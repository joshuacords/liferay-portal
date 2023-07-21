/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.persistence.CommerceSubscriptionEntryPersistence;
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
public class CommerceSubscriptionEntryFinderBaseImpl
	extends BasePersistenceImpl<CommerceSubscriptionEntry> {

	public CommerceSubscriptionEntryFinderBaseImpl() {
		setModelClass(CommerceSubscriptionEntry.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put(
			"deliverySubscriptionTypeSettings", "deliverySubTypeSettings");

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
		return getCommerceSubscriptionEntryPersistence().getBadColumnNames();
	}

	/**
	 * Returns the commerce subscription entry persistence.
	 *
	 * @return the commerce subscription entry persistence
	 */
	public CommerceSubscriptionEntryPersistence
		getCommerceSubscriptionEntryPersistence() {

		return commerceSubscriptionEntryPersistence;
	}

	/**
	 * Sets the commerce subscription entry persistence.
	 *
	 * @param commerceSubscriptionEntryPersistence the commerce subscription entry persistence
	 */
	public void setCommerceSubscriptionEntryPersistence(
		CommerceSubscriptionEntryPersistence
			commerceSubscriptionEntryPersistence) {

		this.commerceSubscriptionEntryPersistence =
			commerceSubscriptionEntryPersistence;
	}

	@BeanReference(type = CommerceSubscriptionEntryPersistence.class)
	protected CommerceSubscriptionEntryPersistence
		commerceSubscriptionEntryPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSubscriptionEntryFinderBaseImpl.class);

}