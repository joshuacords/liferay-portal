/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.model.CPInstanceOptionValueRel;
import com.liferay.commerce.product.service.persistence.CPInstanceOptionValueRelPersistence;
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
public class CPInstanceOptionValueRelFinderBaseImpl
	extends BasePersistenceImpl<CPInstanceOptionValueRel> {

	public CPInstanceOptionValueRelFinderBaseImpl() {
		setModelClass(CPInstanceOptionValueRel.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

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
		return getCPInstanceOptionValueRelPersistence().getBadColumnNames();
	}

	/**
	 * Returns the cp instance option value rel persistence.
	 *
	 * @return the cp instance option value rel persistence
	 */
	public CPInstanceOptionValueRelPersistence
		getCPInstanceOptionValueRelPersistence() {

		return cpInstanceOptionValueRelPersistence;
	}

	/**
	 * Sets the cp instance option value rel persistence.
	 *
	 * @param cpInstanceOptionValueRelPersistence the cp instance option value rel persistence
	 */
	public void setCPInstanceOptionValueRelPersistence(
		CPInstanceOptionValueRelPersistence
			cpInstanceOptionValueRelPersistence) {

		this.cpInstanceOptionValueRelPersistence =
			cpInstanceOptionValueRelPersistence;
	}

	@BeanReference(type = CPInstanceOptionValueRelPersistence.class)
	protected CPInstanceOptionValueRelPersistence
		cpInstanceOptionValueRelPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceOptionValueRelFinderBaseImpl.class);

}