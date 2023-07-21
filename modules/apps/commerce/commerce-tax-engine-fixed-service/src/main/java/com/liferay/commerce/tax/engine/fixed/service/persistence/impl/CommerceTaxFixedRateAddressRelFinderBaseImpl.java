/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.tax.engine.fixed.service.persistence.impl;

import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateAddressRel;
import com.liferay.commerce.tax.engine.fixed.service.persistence.CommerceTaxFixedRateAddressRelPersistence;
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
public class CommerceTaxFixedRateAddressRelFinderBaseImpl
	extends BasePersistenceImpl<CommerceTaxFixedRateAddressRel> {

	public CommerceTaxFixedRateAddressRelFinderBaseImpl() {
		setModelClass(CommerceTaxFixedRateAddressRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put(
			"commerceTaxFixedRateAddressRelId", "CTaxFixedRateAddressRelId");

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
		return getCommerceTaxFixedRateAddressRelPersistence().
			getBadColumnNames();
	}

	/**
	 * Returns the commerce tax fixed rate address rel persistence.
	 *
	 * @return the commerce tax fixed rate address rel persistence
	 */
	public CommerceTaxFixedRateAddressRelPersistence
		getCommerceTaxFixedRateAddressRelPersistence() {

		return commerceTaxFixedRateAddressRelPersistence;
	}

	/**
	 * Sets the commerce tax fixed rate address rel persistence.
	 *
	 * @param commerceTaxFixedRateAddressRelPersistence the commerce tax fixed rate address rel persistence
	 */
	public void setCommerceTaxFixedRateAddressRelPersistence(
		CommerceTaxFixedRateAddressRelPersistence
			commerceTaxFixedRateAddressRelPersistence) {

		this.commerceTaxFixedRateAddressRelPersistence =
			commerceTaxFixedRateAddressRelPersistence;
	}

	@BeanReference(type = CommerceTaxFixedRateAddressRelPersistence.class)
	protected CommerceTaxFixedRateAddressRelPersistence
		commerceTaxFixedRateAddressRelPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTaxFixedRateAddressRelFinderBaseImpl.class);

}